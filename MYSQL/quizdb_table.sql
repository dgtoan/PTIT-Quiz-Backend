CREATE SCHEMA `quiz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `quiz`.`class` (
	`id` INT AUTO_INCREMENT,
    `name` VARCHAR(20),
    PRIMARY KEY (`id`)
);

CREATE TABLE `quiz`.`user` (
 `id` INT AUTO_INCREMENT,
 `classId` INT,
 `fullName` VARCHAR(100),
 `email` VARCHAR(50),
 `passwordHash` VARCHAR(32),
 `role` VARCHAR(20),
 PRIMARY KEY (`id`),
 CONSTRAINT `fk_user_class`
  FOREIGN KEY (`classId`)
  REFERENCES `quiz`.`class` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
);

CREATE TABLE `quiz`.`quiz` (
 `id` INT AUTO_INCREMENT,
 `classId` INT,
 `title` VARCHAR(75),
 `metaTitle` VARCHAR(100),
 `startsAt` DATETIME,
 `endsAt` DATETIME,
 `content` TEXT,
 PRIMARY KEY (`id`),
 CONSTRAINT `fk_quiz_class`
  FOREIGN KEY (`classId`)
  REFERENCES `quiz`.`class` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
);

CREATE TABLE `quiz`.`quiz_question` (
 `id` INT AUTO_INCREMENT,
 `quizId` INT,
 `type` VARCHAR(50),
 `score` INT,
 `content` TEXT,
 PRIMARY KEY (`id`),
 INDEX `idx_question_quiz` (`quizId` ASC),
 CONSTRAINT `fk_question_quiz`
  FOREIGN KEY (`quizId`)
  REFERENCES `quiz`.`quiz` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
);

CREATE TABLE `quiz`.`quiz_answer` (
 `id` INT AUTO_INCREMENT,
 `quizId` INT ,
 `questionId` INT ,
 `correct` INT,
 `content` TEXT,
 PRIMARY KEY (`id`),
 INDEX `idx_answer_quiz` (`quizId` ASC),
 CONSTRAINT `fk_answer_quiz`
  FOREIGN KEY (`quizId`)
  REFERENCES `quiz`.`quiz` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

ALTER TABLE `quiz`.`quiz_answer` 
ADD INDEX `idx_answer_question` (`questionId` ASC);
ALTER TABLE `quiz`.`quiz_answer` 
ADD CONSTRAINT `fk_answer_question`
 FOREIGN KEY (`questionId`)
 REFERENCES `quiz`.`quiz_question` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION;
 
CREATE TABLE `quiz`.`take` (
 `id` INT AUTO_INCREMENT,
 `userId` INT ,
 `quizId` INT ,
 `score` INT,
 PRIMARY KEY (`id`),
 INDEX `idx_take_user` (`userId` ASC),
 CONSTRAINT `fk_take_user`
  FOREIGN KEY (`userId`)
  REFERENCES `quiz`.`user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

ALTER TABLE `quiz`.`take` 
ADD INDEX `idx_take_quiz` (`quizId` ASC);
ALTER TABLE `quiz`.`take` 
ADD CONSTRAINT `fk_take_quiz`
 FOREIGN KEY (`quizId`)
 REFERENCES `quiz`.`quiz` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION;
 
CREATE TABLE `quiz`.`take_answer` (
 `id` INT AUTO_INCREMENT,
 `takeId` INT ,
 `questionId` INT ,
 `answerId` INT ,
 `content` TEXT,
 PRIMARY KEY (`id`),
 INDEX `idx_answer_take` (`takeId` ASC),
 CONSTRAINT `fk_answer_take`
  FOREIGN KEY (`takeId`)
  REFERENCES `quiz`.`take` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

ALTER TABLE `quiz`.`take_answer` 
ADD INDEX `idx_tanswer_question` (`questionId` ASC);
ALTER TABLE `quiz`.`take_answer` 
ADD CONSTRAINT `fk_tanswer_question`
 FOREIGN KEY (`questionId`)
 REFERENCES `quiz`.`quiz_question` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION;

ALTER TABLE `quiz`.`take_answer` 
ADD INDEX `idx_tanswer_answer` (`answerId` ASC);
ALTER TABLE `quiz`.`take_answer` 
ADD CONSTRAINT `fk_tanswer_answer`
 FOREIGN KEY (`answerId`)
 REFERENCES `quiz`.`quiz_answer` (`id`)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION;
 
 



