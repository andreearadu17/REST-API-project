/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_quiz;
    private String quizName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_creator_id")
    @JsonIgnore
    private QuizCreator quizCreator;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "quiz_question", joinColumns = @JoinColumn(name = "quiz_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz")
    private List<Answer> answers;
    // @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Answer> answers;

    public Quiz() {

    }

    public Quiz(Long id_quiz, String quizName, QuizCreator quizCreator, List<Question> questionArray) {
        this.id_quiz = id_quiz;
        this.quizName = quizName;
        this.quizCreator = quizCreator;
        this.questions = questionArray;

    }

    public Long getId() {
        return id_quiz;
    }

    public void setId(Long id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public QuizCreator getQuizCreator() {
        return quizCreator;
    }

    public void setQuizCreator(QuizCreator quizCreator) {
        this.quizCreator = quizCreator;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
