/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Service;

import Contenu.Contenu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucien
 */
public class TelechargerFichier extends HttpServlet {

    public static int BUFFER_SIZE = 1024 * 100;
    public static final String UPLOAD_DIR = "resources";
    public static String fileName = null;

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
        Contenu contenuById = (Contenu) request.getAttribute("contenuById");
        fileName = contenuById.getNomfichier();
        if (fileName == null || fileName.equals("")) {
            Exception exception = new Exception();
            request.setAttribute("exception", new Exception("Le fichier n'est pas disponible"));
            request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
        } else {
            String applicationPath = getServletContext().getRealPath("");
            String downloadPath = applicationPath + File.separator + UPLOAD_DIR;
            String filePath = downloadPath + File.separator + fileName;

            File file = new File(filePath);
            OutputStream outStream = null;
            FileInputStream inputStream = null;

            if (file.exists()) {
                String mimeType = "application/octe-stream";
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachement; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);

                try {
                    if (outStream == null) {
                        outStream = response.getOutputStream();
                    }
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                    request.getRequestDispatcher("/Contenu/home.jsp").forward(request, response);
                } catch (IOException e) {
                    request.setAttribute("exception", e);
                    request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
                    System.out.println("Exception while performing the I/O operation?= " + e.getMessage());
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outStream != null) {
                        outStream.flush();
                        outStream.close();
                    }
                }

            } else {
                Exception exception = new Exception();
                request.setAttribute("exception", new Exception("Le fichier n'est pas disponible"));
                request.getRequestDispatcher("/Exception/Exception.jsp").forward(request, response);
            }
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
