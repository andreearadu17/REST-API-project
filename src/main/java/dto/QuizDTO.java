package dto;

import java.util.List;
import java.util.stream.Collectors;

import model.Question;
import model.Quiz;

public class QuizDTO {
    private Long id;
    private String quizName;
    private String creatorName;
    private List<Long> questionIds;

    // Default constructor (needed for JSON deserialization)
    public QuizDTO() {
    }

    // Constructor to map from Quiz entity
    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.quizName = quiz.getQuizName();
        this.creatorName = quiz.getQuizCreator().getUsername();
        this.questionIds = quiz.getQuestions().stream()
                .map(Question::getId) // Convert Question objects to their IDs
                .collect(Collectors.toList());
    }

    // Constructor for manual object creation
    public QuizDTO(Long id, String quizName, String creatorName, List<Long> questionIds) {
        this.id = id;
        this.quizName = quizName;
        this.creatorName = creatorName;
        this.questionIds = questionIds;
    }

    public QuizDTO(Long id, String quizName, String creatorName) {
        this.id = id;
        this.quizName = quizName;
        this.creatorName = creatorName;

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

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }
}
