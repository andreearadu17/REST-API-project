/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_creator_id")
    @JsonIgnore
    private QuizCreator creator;

    @ManyToMany(mappedBy = "questions", cascade = { CascadeType.MERGE })
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answer;

    public Question() {

    }

    // Constructor
    public Question(String qText, QuizCreator creator, List<Quiz> quizzes) {
        this.qText = qText;
        this.creator = creator;
        this.quizzes = quizzes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter for qText
    public String getQText() {
        return qText;
    }

    // Setter for qText
    public void setQText(String qText) {
        this.qText = qText;
    }

    public QuizCreator getCreator() {
        return creator;
    }

    public void setCreator(QuizCreator creator) {
        this.creator = creator;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
