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
public class ArticleDTO {
    private String articleID;
    private String email;
    private String title;
    private String description;
    private String img;
    private String createDate;
    private int upvote;
    private int downvote;
    private boolean visible;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleID, String email, String title, String description, String img, String createDate, int upvote, int downvote, boolean visible) {
        this.articleID = articleID;
        this.email = email;
        this.title = title;
        this.description = description;
        this.img = img;
        this.createDate = createDate;
        this.upvote = upvote;
        this.downvote = downvote;
        this.visible = visible;
    }
    

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
}
