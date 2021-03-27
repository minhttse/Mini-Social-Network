/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtt.daos.ArticleDAO;
import minhtt.dtos.ArticleDTO;

/**
 *
 * @author minhv
 */
public class ListArticleController extends HttpServlet {
    private static final String HOME_PAGE = "homePage.jsp";   

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
        String url = HOME_PAGE;
        try {
            String content = request.getParameter("txtSearchArticle");
            String currentPage = request.getParameter("pagenum");
            HttpSession session=request.getSession();
            if (content==null) {
                content = "";
            }           
            if (currentPage==null) {
                currentPage = "1";
            }
            ArticleDAO dao = new ArticleDAO();
            int totalPage =0;
            int count=dao.countArticle(content);
            if(count % 20!=0){
                totalPage=count/20 +1;
            }else{
                totalPage=count/20;
            }
            List<ArticleDTO> list = dao.getListArticles(Integer.parseInt(currentPage),content);
            if (list != null) {               
                session.setAttribute("LIST_ARTICLE", list);
                request.setAttribute("PAGE", totalPage);
            }else{
                session.setAttribute("LIST_ARTICLE", null);
                request.setAttribute("MESSAGE", "No result for search");
            }
        } catch (Exception e) {
            log("Error at ListArticleServlet " + e.toString());
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
