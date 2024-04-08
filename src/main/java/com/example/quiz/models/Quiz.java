package com.example.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "QUIZ")
@NoArgsConstructor @Getter @Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String title;

    @Size(max = 120)
    private String description;

    private Timestamp startsAt;

    private Timestamp endsAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(  name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions = new HashSet<>();

    public void addQuestion(Question question) {
        questions.add(question);
        question.getQuizzes().add(this);
    }

    public void removeQuestion(Long questionId) {
        Question question = this.questions.stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .orElse(null);

        if (question != null) {
            questions.remove(question);
            question.getQuizzes().remove(this);
        }
    }
}
