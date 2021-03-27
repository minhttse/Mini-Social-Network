/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.daos;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhtt.dtos.ArticleDTO;
import minhtt.utils.DBUtils;

/**
 *
 * @author minhv
 */
public class ArticleDAO {
    public List<ArticleDTO> getListArticles(int page, String content) throws Exception {
        List<ArticleDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT articleID, email, title, descripton, img, createdate, upvote,downvote, isVisible\n" +
                        "FROM tblArticles \n" +
                        "WHERE isVisible = 'True' and descripton like ? ORDER BY createdate DESC\n" +
                        "OFFSET (? - 1)* 20 ROWS FETCH NEXT 20 \n" +
                        "ROWS ONLY";
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1,"%"+ content+"%");
                stm.setInt(2, page);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String articleID = rs.getString("articleID");
                    String title = rs.getNString("title");
                    String email = rs.getString("email");
                    String description = rs.getNString("descripton");
                    String img = rs.getString("img");
                    String createDate = rs.getString("createdate");
                    int upvote = rs.getInt("upvote");
                    int downvote = rs.getInt("downvote");
                    boolean visible = rs.getBoolean("isVisible");
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(new ArticleDTO(articleID, email, title, description, img, createDate, upvote, downvote, visible));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public int countArticle(String content) throws Exception{
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT articleID\n" +
                        "FROM tblArticles \n" +
                        "WHERE isVisible = 'True' and descripton like ?\n";
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setString(1,"%"+ content+"%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    count++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }
    
    public ArticleDTO performArticleDetails(String articleID) throws Exception {
        ArticleDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT  email,title, descripton, img, createdate, upvote,downvote, isVisible\n" +
                        "FROM tblArticles\n" +
                        "WHERE articleID = ?";
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, articleID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String email = rs.getString("email");
                String description = rs.getNString("descripton");
                String img = rs.getString("img");
                String createDate = rs.getString("createdate");
                int upvote = rs.getInt("upvote");
                int downvote = rs.getInt("downvote");
                boolean visible = rs.getBoolean("isVisible");
                result = new ArticleDTO(articleID, email, title, description, img, createDate, upvote, downvote, visible);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public int getNoOfArticle() throws SQLException{
        int result=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select articleID\n" +
                            "From tblArticles\n";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while(rs.next()){
                    result++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    
    public void postArticle(ArticleDTO article) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="INSERT INTO tblArticles\n" +
                            "values(?,?,?,?,?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1,article.getArticleID());
                stm.setString(2,article.getEmail());
                stm.setString(3,article.getTitle());
                stm.setString(4,article.getDescription());
                stm.setString(5,article.getImg());
                stm.setString(6,article.getCreateDate());
                stm.setInt(7,article.getUpvote());
                stm.setInt(8,article.getDownvote());
                stm.setBoolean(9,article.isVisible());
                
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public static void readImg(String fileName1,String fileName2){
        FileInputStream fi=null;
        FileOutputStream fo=null;

        try {
            fi=new FileInputStream("C:\\Users\\minhv\\Desktop\\Images\\"+fileName1);
            fo=new FileOutputStream("C:\\Users\\minhv\\Documents\\LabJavaWeb\\Assignment4_TranTuanMinh\\web\\images\\"+fileName2);
            int size=fi.available();
            byte[] rs = new byte[size];
            fi.read(rs);
            
            fo.write(rs);
            fo.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                
                if(fi!=null)fi.close();
                if(fo!=null)fo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteArticle(String articleID) throws Exception{
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblArticles"
                    + " SET isVisible = ?"
                    + " WHERE articleID = ?";
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2, articleID);
                stm.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
