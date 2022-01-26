/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author Lucien
 */
import Contenu.Contenu;
import Controller.ContenuController;
import ModelView.ModelView;
import static Service.TelechargerFichier.BUFFER_SIZE;
import TypeContenu.TypeContenu;
import Utilisateur.Utilisateur;
import database.MyConnexion;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jdk.nashorn.internal.objects.NativeMath;
import static sun.security.jgss.GSSUtil.login;

public class Test {

    public static void URLDnldFile(URL urlink, String fileName) throws IOException {
        try (InputStream inp = urlink.openStream();
                BufferedInputStream bis = new BufferedInputStream(inp);
                FileOutputStream fops = new FileOutputStream(fileName)) {

            byte[] d = new byte[1024];
            int i;
            while ((i = bis.read(d, 0, 1024)) != -1) {
                fops.write(d, 0, i);
            }
        }
    }

    public static void telechargerSecond(String nomFichier) {
        try {
            //http://localhost:8081/Gestion_Contenu/recherche.do
            //String fileLink = "C:/Users/Lucien/Documents/NetBeansProjects/Gestion_Contenu/build/web/resources/Rapport de mission.txt";
            String fileLink = "http://localhost:8081/Gestion_Contenu/karlos/" + nomFichier;
            String oppath = "D:\\" + nomFichier;
            URL link = new URL(fileLink);
            try ( //InputStream ins = link.openStream();
                    ReadableByteChannel chh = Channels.newChannel(link.openStream()); FileOutputStream fos = new FileOutputStream(new File(oppath))) {
                fos.getChannel().transferFrom(chh, 0, Long.MAX_VALUE);
                System.out.println("File downloaded");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void telecharger(String nomFichier) {
        OutputStream ops = null;
        InputStream ins = null;

        String fileLink = "http://localhost:8081/Gestion_Contenu/karlos/" + nomFichier;
        String oppath = "D:\\" + nomFichier;
        try {
            URL url = new URL(fileLink);
            URLConnection connection = url.openConnection();
            ins = connection.getInputStream();
            ops = new FileOutputStream(oppath);
            final byte[] bt = new byte[1024];
            int len;
            while ((len = ins.read(bt)) != -1) {
                ops.write(bt, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // close streams
            System.out.println("URL's File downloaded....");
        }
    }

    public static void download(String nomFichier) {
        FileOutputStream fos = null;
        FileInputStream inputStream = null;
        String filePath = "C:\\Users\\Lucien\\Documents\\NetBeansProjects\\Gestion_Contenu\\build\\web\\resources\\" + nomFichier;
        File file = new File(filePath);
        if (file.exists()) {

            try {
                //outStream = response.getOutputStream();
                inputStream = new FileInputStream(file);
                fos = new FileOutputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    //outStream.write(buffer, 0, bytesRead);
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.out.println("Exception while performing the I/O operation?= " + e.getMessage());
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                if (fos != null) {
                    try {
                        //                        outStream.flush();
//                        outStream.close();
                        fos.flush();
                        fos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }

        } else {
            System.out.println("File not found");
        }
    }

    public static void main(String[] args) {
        MyConnexion conn = new MyConnexion();
        Connection connection = null;
        try {
//            UtilisateurController utilisateurController = new UtilisateurController();
//            Utilisateur utilisateur = new Utilisateur();
//            utilisateur.setEmail("lucien@gmail.com");
//            utilisateur.setPassword("qwerty");
//            utilisateurController.setUtilisateur(utilisateur);
//            connection = conn.openConnection();
//            ModelView login = utilisateurController.login(utilisateurController);
            ContenuController contenuController = new ContenuController();
            Contenu contenu = new Contenu();
//            contenu.setIdutilisateur(1);
//            contenu.setTypecontenu("pv");
            contenuController.setContenu(contenu);
            contenuController.setIdUtilisateur(1);
            ModelView login = contenuController.rechercheFichier(contenuController);
            
            List<Object> utilisateurLogin = (List<Object>) login.getData().get("utilisateur");
            List<Object> listTypeContenu = (List<Object>) login.getData().get("listTypeContenu");
            System.out.println("\n-------------UTILISATEUR CONNECTE----------------\n");
            System.out.println("utilisateur : \n" +((Utilisateur) utilisateurLogin.get(0)).toString());
            System.out.println("taille liste contenu "+listTypeContenu.size());
            System.out.println("\n-------------CONTENU----------------\n");
            for (int i=0; i<listTypeContenu.size(); i++)
                System.out.println("list type contenu : \n" +((TypeContenu) listTypeContenu.get(i)).toString());
            System.out.println("\n-------------LIST UTILISATEUR----------------\n");
            List<Object> allUtil = (List<Object>) login.getData().get("allUtil");
            for(int i=0;  i<allUtil.size(); i++){
                Utilisateur u = (Utilisateur) allUtil.get(i);
                System.out.print(u.toString()+"\n");
            }
            System.out.println("\n-------------LIST RESULTAT----------------\n");
            List<Object> resultat = (List<Object>) login.getData().get("contenuResultat");
            System.out.println("Taille resultat "+resultat.size());
            for (int i=0; i<resultat.size(); i++){
                Contenu temp = (Contenu) resultat.get(i);
                System.out.println("Contenu resultat : \n"+temp.getTitre());
            }
           
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.closeConnection(
                        connection);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

}
