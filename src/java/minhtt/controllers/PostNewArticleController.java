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
import minhtt.dtos.ArticleDTO;
import minhtt.dtos.ArticleErrorDTO;
import minhtt.dtos.UserDTO;

/**
 *
 * @author minhv
 */
public class PostNewArticleController extends HttpServlet {
    private static final String SUCCESS = "ListArticleController";
    private static final String ERROR = "homePage.jsp";


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
        HttpSession session = request.getSession(true);
        try {
            ArticleDTO dto = null;
            ArticleDAO dao = new ArticleDAO();
            UserDTO userdto = (UserDTO) session.getAttribute("LOGIN_USER");
            int lastArticle = dao.getNoOfArticle();
            String articleID = "A"+(lastArticle+1);
            String title = request.getParameter("txtPostTitle");
            String description = request.getParameter("txtPostDescription");
            String fileName = request.getParameter("fileIMG");
            String email = userdto.getEmail();
            int upvote = 0;
            int downvote = 0;           
            boolean visible = true;
          
            ArticleErrorDTO errorObject = new ArticleErrorDTO("", "", "", "", "", "", "", "", "");
            boolean check = true;
            if (title.trim().isEmpty()) {
                check = false;
                errorObject.setTitleError("Title is not empty !");
            }
            if (description.trim().isEmpty()) {
                check = false;
                errorObject.setDescriptionError("Description is not empty !");
            }
            if (fileName==null) {
                check = false;
                errorObject.setImgError("Please choose image !");
            }
            if (check == true) {
                
                dao.readImg(fileName, fileName);
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                String strCurrentDate = formatter.format(date);     
                dto = new ArticleDTO(articleID, email, title, description, "images/"+fileName, strCurrentDate, upvote, downvote, visible);               
                dao.postArticle(dto);         
                url = SUCCESS;

            } else {
                request.setAttribute("ARTICLE_ERROR", errorObject);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
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
