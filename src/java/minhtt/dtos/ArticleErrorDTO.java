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
public class ArticleErrorDTO {
    private String articleIDError;
    private String emailError;
    private String titleError;
    private String descriptionError;
    private String imgError;
    private String createDateError;
    private String upvoteError;
    private String downvoteError;
    private String visibleError;

    public ArticleErrorDTO(String articleIDError, String emailError, String titleError, String descriptionError, String imgError, String createDateError, String upvoteError, String downvoteError, String visibleError) {
        this.articleIDError = articleIDError;
        this.emailError = emailError;
        this.titleError = titleError;
        this.descriptionError = descriptionError;
        this.imgError = imgError;
        this.createDateError = createDateError;
        this.upvoteError = upvoteError;
        this.downvoteError = downvoteError;
        this.visibleError = visibleError;
    }

    public ArticleErrorDTO() {
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

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getImgError() {
        return imgError;
    }

    public void setImgError(String imgError) {
        this.imgError = imgError;
    }

    public String getCreateDateError() {
        return createDateError;
    }

    public void setCreateDateError(String createDateError) {
        this.createDateError = createDateError;
    }

    public String getUpvoteError() {
        return upvoteError;
    }

    public void setUpvoteError(String upvoteError) {
        this.upvoteError = upvoteError;
    }

    public String getDownvoteError() {
        return downvoteError;
    }

    public void setDownvoteError(String downvoteError) {
        this.downvoteError = downvoteError;
    }

    public String getVisibleError() {
        return visibleError;
    }

    public void setVisibleError(String visibleError) {
        this.visibleError = visibleError;
    }
    
    
}
