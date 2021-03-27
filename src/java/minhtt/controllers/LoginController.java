/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtt.daos.UserDAO;
import minhtt.dtos.UserDTO;

/**
 *
 * @author minhv
 */
public class LoginController extends HttpServlet {
    private static final String ADMIN_LOGIN = "admin.jsp";   
    private static final String USER_LOGIN = "ListArticleController";   
    public static final String APP_EMAIL = "hotspicy696969@gmail.com"; 
    public static final String APP_PASSWORD = "minhlk123";
    private static final String ERROR = "login.jsp";
    

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
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.checkLogin(email, toHexString(getSHA(password)));
            HttpSession session=request.getSession();
            if (user==null) {
                session.setAttribute("LOGIN_INVALID", "Invalid Email and Password !");
            }else {
                if(user.getRoleID().equals("1") && user.getStatus().equals("active")){
                    url = ADMIN_LOGIN;
                    session.setAttribute("LOGIN_USER", user);
                }else if(user.getRoleID().equals("2") && user.getStatus().equals("active")){     
                    url = USER_LOGIN;
                    session.setAttribute("LOGIN_USER", user);
                } else if (user.getRoleID().equals("2") && user.getStatus().equals("new")) {
                    Properties p = new Properties();
                    p.put("mail.smtp.auth", "true");
                    p.put("mail.smtp.starttls.enable", "true");
                    p.put("mail.smtp.host", "smtp.gmail.com");
                    p.put("mail.smtp.port", 587);

                    // get Session
                    Session session1 = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
                        }
                    });

                    // compose message
                    Random rd = new Random();
                    int number = rd.nextInt(8999) + 1000;
                    try {
                        MimeMessage message = new MimeMessage(session1);
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                        message.setSubject("Verify Email");
                        message.setText("Your verify code : " + number);

                        // send message
                        Transport.send(message);

                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                    url = ERROR;
                    session.setAttribute("VERIFY_NUMBER_AGAIN", number+"_"+email);    
                }

            }
        } catch (Exception e) {
            log("Error at LoginServlet: " + e.toString());
        } finally {
            response.sendRedirect(url);//request huy
        }
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
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
