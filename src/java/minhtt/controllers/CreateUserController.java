/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtt.controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import minhtt.dtos.UserErrorDTO;

/**
 *
 * @author minhv
 */
public class CreateUserController extends HttpServlet {
    private final static String ERROR = "createUser.jsp";
    private final static String SUCCESS = "createUser.jsp";
    public static final String APP_EMAIL = "hotspicy696969@gmail.com"; 
    public static final String APP_PASSWORD = "minhlk123";

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
            String fullName = request.getParameter("txtFullName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            UserErrorDTO errorObject = new UserErrorDTO("", "", "", "", "");
            UserDAO dao = new UserDAO();
            boolean check = true;
            if (email.trim().isEmpty()) {
                check = false;
                errorObject.setEmailError("Email is not empty !");
            }
            if (fullName.trim().isEmpty()) {
                check = false;
                errorObject.setFullNameError("FullName is not empty !");
            }
            if (password.trim().isEmpty()) {
                check = false;
                errorObject.setPasswordError("Password is not empty !");
            }
            if (!password.equals(confirm)) {
                check = false;
                errorObject.setConfirmError("Confirm must be the same as password");
            }
            if (dao.checkID(email)==true) {
                check = false;
                errorObject.setEmailError("Email is duplicate !");
            }
            if (check == true) {
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
                int number = rd.nextInt(8999)+1000; 
                try {
                    MimeMessage message = new MimeMessage(session1);
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject("Verify Email");
                    message.setText("Your verify code : "+number);

                    // send message
                    Transport.send(message);

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();
                String strCurrentDate = formatter.format(date);
                UserDTO user = new UserDTO(email, fullName, toHexString(getSHA(password)),strCurrentDate, "2", "new");
                dao.create(user);
                url = SUCCESS;
                HttpSession session=request.getSession();
                session.setAttribute("VERIFY_NUMBER", number+"_"+email);    
            } else {
                request.setAttribute("USER_ERROR", errorObject);
            }
        } catch (Exception e) {
            log("Error at CreateUserServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
