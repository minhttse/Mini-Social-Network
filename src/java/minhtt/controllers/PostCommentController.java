/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtt.daos.ArticleDAO;
import minhtt.daos.CommentDAO;
import minhtt.dtos.CommentDTO;
import minhtt.dtos.CommentErrorDTO;
import minhtt.dtos.UserDTO;

/**
 *
 * @author minhv
 */
public class PostCommentController extends HttpServlet {
    private static final String ERROR = "article.jsp";
    private static final String SUCCESS = "ArticleDetailController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;       
        try {
            HttpSession session = request.getSession();
            CommentDAO dao = new CommentDAO();
            ArticleDAO articleDao = new ArticleDAO();
            int lastComment = dao.getNoOfComment();
            String commentID = "C"+(lastComment+1);
            String articleID = request.getParameter("txtArticleID");
            UserDTO userdto = (UserDTO) session.getAttribute("LOGIN_USER");
            String email = userdto.getEmail();
            String description = request.getParameter("txtComment");                       
            boolean visible = true;
            boolean seen = false;
            
            CommentErrorDTO errorObject = new CommentErrorDTO("", "", "", "", "", "", "");
            boolean check = true;
            if (description.trim().isEmpty()) {
                check = false;
                errorObject.setDescriptionError("Description is not empty !");
            }
            if (check==true) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                String strCurrentDate = formatter.format(date);                
                dao.postComment(new CommentDTO(commentID, articleID, email, description, strCurrentDate, visible, seen));               
                url = SUCCESS;
            }else{
                request.setAttribute("COMMENT_ERROR", errorObject);
            }                
        } catch (Exception e) {
            log("Error at PostCommentcontroller "+e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
