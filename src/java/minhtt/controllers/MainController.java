/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author minhv
 */
public class MainController extends HttpServlet {
    private final static String DEFAULT="login.jsp";
    private final static String LOGIN="LoginController";
    private final static String LOGOUT="LogoutController";  
    private final static String CREATE_PAGE="createUser.jsp";
    private final static String CREATE_USER="CreateUserController";
    private final static String SEARCH="ListArticleController";
    private final static String ARTICLE_DETAIL="ArticleDetailController";
    private final static String POST_COMMENT="PostCommentController";
    private final static String POST_NEW_ARTICLE="PostNewArticleController";
    private final static String DELETE_COMMENT="DeleteCommentController";
    private final static String DELETE_ARTICLE="DeleteArticleController";
    private final static String VERIFY_USER="VerifyUserController";
    private final static String VERIFY_USER_AGAIN="VerifyUserAgainController";
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
        String url=DEFAULT;
        try {
            String action =request.getParameter("btnAction");
            if("Login".equals(action)){
                url=LOGIN;
            }else if("Logout".equals(action)){
                url=LOGOUT;
            }else if("Create Page".equals(action)){
                url=CREATE_PAGE;
            }else if("Create User".equals(action)){
                url=CREATE_USER;
            }else if("Search".equals(action)){
                url=SEARCH;
            }else if("ArticleDetail".equals(action)){
                url=ARTICLE_DETAIL;
            }else if("Comment".equals(action)){
                url=POST_COMMENT;
            }else if("Post".equals(action)){
                url=POST_NEW_ARTICLE;
            }else if("Delete Comment".equals(action)){
                url=DELETE_COMMENT;
            }else if("Delete Article".equals(action)){
                url=DELETE_ARTICLE;
            }else if("Verify User".equals(action)){
                url=VERIFY_USER;
            }else if("Verify User Again".equals(action)){
                url=VERIFY_USER_AGAIN;
            }
            
        }catch(Exception e){
            log("Error at Maincontroller "+e.toString());
        }
        finally{
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
