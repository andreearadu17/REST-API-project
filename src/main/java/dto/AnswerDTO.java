package dto;

public class AnswerDTO {
    private Long id;
    private String questionText;
    private String creatorUsername;
    private String questionAnswer;

    public AnswerDTO() {

    }

    public AnswerDTO(Long id, String questionText, String creatorUsername, String questionAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.creatorUsername = creatorUsername;
        this.questionAnswer = questionAnswer;
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

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
