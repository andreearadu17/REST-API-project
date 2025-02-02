package dto;

import java.util.List;
import java.util.stream.Collectors;

import model.Question;
import model.Quiz;

public class QuizWithQuestionsDTO {
    private Long id;
    private String quizName;
    private String creatorName;
    private List<QuestionDto> questions;

    // Default constructor (needed for JSON deserialization)
    public QuizWithQuestionsDTO() {
    }

    // Constructor to map from Quiz entity
    public QuizWithQuestionsDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.quizName = quiz.getQuizName();
        this.creatorName = quiz.getQuizCreator().getUsername();
        this.questions = quiz.getQuestions().stream().map(question -> new QuestionDto(
                question.getId(),
                question.getQText(),
                question.getCreator() != null ? question.getCreator().getUsername() : "Unknown"))
                .collect(Collectors.toList());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
