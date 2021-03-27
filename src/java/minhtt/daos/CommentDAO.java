/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhtt.dtos.ArticleDTO;
import minhtt.dtos.CommentDTO;
import minhtt.utils.DBUtils;

/**
 *
 * @author minhv
 */
public class CommentDAO {
    public List<CommentDTO> loadAllCommentByArticleID(String articleID) throws Exception {
        List<CommentDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.id,c.articleID,c.email,u.fullName,c.descripton,c.commentdate,c.isVisible,c.isSeen\n" +
                        "FROM tblComment c, tblArticles a, tblUsers u\n" +
                        "WHERE c.articleID = a.articleID AND u.email = c.email AND c.articleID = ? AND c.isVisible = ? ORDER BY commentdate DESC";
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, articleID);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String email = rs.getString("email");
                String description = rs.getNString("descripton");
                String commentDate = rs.getString("commentdate");
                boolean visible = rs.getBoolean("isVisible");
                boolean seen = rs.getBoolean("isSeen");
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(new CommentDTO(id, articleID, email, description, commentDate, visible, seen));
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
    
    public int getNoOfComment() throws SQLException{
        int result=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select id\n" +
                            "From tblComment\n";
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
    
    public void postComment(CommentDTO comment) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="INSERT INTO tblComment\n" +
                           "values(?,?,?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1,comment.getId());
                stm.setString(2,comment.getArticleID());
                stm.setString(3,comment.getEmail());
                stm.setString(4,comment.getDescription());
                stm.setString(5,comment.getCommentDate());
                stm.setBoolean(6,comment.isVisible());
                stm.setBoolean(7,comment.isSeen());
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public void deleteComment(String id) throws Exception {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblComment"
                    + " SET isVisible = ?"
                    + " WHERE id = ?";
            if (conn != null) {
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2, id);
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
