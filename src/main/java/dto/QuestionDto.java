package dto;

import model.Question;

public class QuestionDto {

    private Long id;
    private String questionText;
    private String creatorUsername;
    // private String answer;

    public QuestionDto() {

    }

    public QuestionDto(Long id, String questionText, String creatorUsername) {
        this.id = id;
        this.questionText = questionText;
        this.creatorUsername = creatorUsername;
    }

    // Constructor that takes a Question object and maps its fields
    public QuestionDto(Question question) {
        this.id = question.getId();
        this.questionText = question.getQText();
        this.creatorUsername = question.getCreator().getUsername();
        // this.answer = question.getAnswer();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }
}