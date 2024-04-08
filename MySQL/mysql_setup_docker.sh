docker run --name mysql -d \
	-p 3306:3306 \
	-e MYSQL_ROOT_PASSWORD=1 \
	-e MYSQL_DATABASE=quizdb \
	mysql:8.3.0

# Add user to mysql
# sudo docker exec -it mysql bash
# mysql -uroot -p1
# CREATE USER 'toan'@'%' IDENTIFIED BY '1';
# GRANT ALL PRIVILEGES ON *.* TO 'toan'@'localhost' WITH GRANT OPTION;
# SELECT user FROM mysql. user;

# add data to mysql
# sudo docker exec -i mysql mysql -uroot -p1 quizdb < quizdb_setup.sql