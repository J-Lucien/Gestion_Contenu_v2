/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Service;

import Contenu.Contenu;
import Controller.ContenuController;
import Utilisateur.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Lucien
 */
public class ContenuService extends HttpServlet {

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
        PrintWriter out;
        out = response.getWriter();
        /*
        Enregistrer contenu
        ilaina:
        ->Nomfichier                                          ok
        ->chemin                                              ok
        ->daty(alaina fa rah vide dia dateActuel no atw       ok
        ->typContenu                                           ?
         */
        try {
            Contenu contenuToAdd = (Contenu) request.getAttribute("contenuToAdd");
            ContenuController contenuController = new ContenuController();
            Part filePart = request.getPart("fichier");
            if (filePart != null) {
                String folderName = "resources";
                String uploadPath = request.getServletContext().getRealPath("") + File.separator + folderName;
                File dir = new File(uploadPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String fileName = filePart.getSubmittedFileName();
                if (!fileName.isEmpty()) {
                    //String path = folderName + File.separator + fileName;
                    //Timestamp added_date = new Timestamp(System.currentTimeMillis());
                    out.println("FileName: " + fileName + "<br>");
                    out.println("Path: " + uploadPath + "<br>");
                    contenuToAdd.setNomfichier(fileName);
                    InputStream is = filePart.getInputStream();
                    Files.copy(is, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
                }

            } 
            
            contenuController.addContenu(contenuToAdd);
            
            request.setAttribute("message", "Enregistrement termine");
            RequestDispatcher dispat = null;
            dispat = request.getRequestDispatcher("/Contenu/home.jsp");
            dispat.forward(request, response);
            //dispach /Contenu/home.jsp;

        } catch (Exception e) {
            out.print(e.getMessage() + "<br>");
            out.print(Arrays.toString(e.getStackTrace()) + "<br>");

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
