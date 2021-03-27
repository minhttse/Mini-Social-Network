/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.dtos;

/**
 *
 * @author minhv
 */
public class CommentErrorDTO {
    private String idError;
    private String articleIDError;
    private String emailError;
    private String descriptionError;
    private String commentDateError;
    private String visibleError;
    private String seenError;

    public CommentErrorDTO() {
    }

    public CommentErrorDTO(String idError, String articleIDError, String emailError, String descriptionError, String commentDateError, String visibleError, String seenError) {
        this.idError = idError;
        this.articleIDError = articleIDError;
        this.emailError = emailError;
        this.descriptionError = descriptionError;
        this.commentDateError = commentDateError;
        this.visibleError = visibleError;
        this.seenError = seenError;
    }

    

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getArticleIDError() {
        return articleIDError;
    }

    public void setArticleIDError(String articleIDError) {
        this.articleIDError = articleIDError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getCommentDateError() {
        return commentDateError;
    }

    public void setCommentDateError(String commentDateError) {
        this.commentDateError = commentDateError;
    }

    public String getVisibleError() {
        return visibleError;
    }

    public void setVisibleError(String visibleError) {
        this.visibleError = visibleError;
    }

    public String getSeenError() {
        return seenError;
    }

    public void setSeenError(String seenError) {
        this.seenError = seenError;
    }

    
    
    
}

