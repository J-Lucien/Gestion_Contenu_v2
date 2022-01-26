/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ModelView.ModelView;
import MyAnnotation.URLMapping.Urlmapping;
import Page.Page;
import TypeContenu.TypeContenu;
import Utilisateur.Utilisateur;
import java.util.HashMap;
import database.MyConnexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lucien
 */
public class UtilisateurController {

    Utilisateur utilisateur = new Utilisateur();
    String pageInclude;
    Number idContenu;
    Page page = new Page();

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getPageInclude() {
        return pageInclude;
    }

    public void setPageInclude(String pageInclude) {
        this.pageInclude = pageInclude;
    }

    public Number getIdContenu() {
        return idContenu;
    }

    public void setIdContenu(Number idContenu) {
        this.idContenu = idContenu;
    }

   

    @Urlmapping(value = "ajout", url = "UtilisateurController/ajout")
    public ModelView ajout(UtilisateurController utilisateurAdd) throws SQLException, Exception {
        //OUVERTURE CONNECTION
        MyConnexion myconn = new MyConnexion();

        Connection con = null;
        try {
            con = myconn.openConnection();
            utilisateurAdd.getUtilisateur().insertToTable(con);
            con.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            myconn.closeConnection(con);
        }

        ModelView modelView = new ModelView();
        modelView.setPage("/Utilisateur/ajout.jsp");

        return modelView;
    }

    @Urlmapping(value = "ListUtilisateur", url = "UtilisateurController/list")
    public ModelView list() throws Exception {
        MyConnexion myconn = new MyConnexion();
        ModelView modelView = new ModelView();
        Connection con = null;
        try {

            con = myconn.openConnection();

            List<Object> listUtilisateur = this.getUtilisateur().findToTable(con);
            HashMap hs = new HashMap();
            hs.put("list", listUtilisateur);

            modelView.setData(hs);
            modelView.setPage("/Utilisateur/list.jsp");

        } catch (Exception ex) {
            throw ex;
        } finally {
            myconn.closeConnection(con);
        }
        return modelView;
    }
    
    @Urlmapping(value = "logOut", url = "UtilisateurController/logOut")
    public ModelView logOut(){
        ModelView modelView = new ModelView();
        modelView.setPage("/index.jsp");
        
        return modelView;
    }

    @Urlmapping(value = "login", url = "UtilisateurController/login")
    public ModelView login(UtilisateurController utilisateurCont) throws Exception {
        MyConnexion myconn = new MyConnexion();
        ModelView modelView = new ModelView();
        //modelView.setPage("/Contenu/index.jsp");
        modelView.setPage("LoginSession");
        Connection con = null;
        HashMap hs = new HashMap();
        try {
            con = myconn.openConnection();
            String sql = "SELECT * FROM Utilisateur WHERE email ='"+utilisateurCont.getUtilisateur().getEmail()+"' AND password = sha1('"+utilisateurCont.getUtilisateur().getPassword()+"')";
            System.out.println("Controller.UtilisateurController.login() <==> sql :: "+sql);
            List<Object> utilisat = utilisateurCont.getUtilisateur().findToTable(sql, con);
            System.out.println("taille utilisateur "+utilisat.size());
            if (utilisat.size() == 1) {

                //return modelView;
                TypeContenu typeContenu = new TypeContenu();
                List<Object> listTypeContenu = typeContenu.findToTable(con);
                ((Utilisateur) utilisat.get(0)).setPassword("");
                
                Utilisateur temp = new Utilisateur();
                List<Object> allUtil = temp.findToTable(con);
                hs.put("utilisateur", utilisat);
                hs.put("listTypeContenu", listTypeContenu);
                hs.put("allUtil", allUtil);
                hs.put("pageInc", "/pageInclude.jsp");
                modelView.setData(hs);
            } else {
                hs.put("erreur", "Login incorrect!");
                modelView.setData(hs);
                modelView.setPage("/index.jsp");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            myconn.closeConnection(con);
        }
        return modelView;
    }
    
    @Urlmapping(value = "setPage", url = "UtilisateurController/page")
    public ModelView page(UtilisateurController utilisateurCont) throws Exception {
        MyConnexion myconn = new MyConnexion();
        ModelView modelView = new ModelView();
        modelView.setPage("/Contenu/home.jsp");
        Connection con = null;
        try {
            con = myconn.openConnection();
            List<Object> utilisateur = utilisateurCont.getUtilisateur().findToTable(con);
            if (utilisateur.size() == 1) {

                //return modelView;
                TypeContenu typeContenu = new TypeContenu();
                
                List<Object> listContenu = typeContenu.findToTable(con);
                ((Utilisateur) utilisateur.get(0)).setPassword("");
                HashMap hs = new HashMap();
                Utilisateur allUtilisateur = new Utilisateur();
                List<Object> allUtil = allUtilisateur.findToTable(con);
                hs.put("utilisateur", utilisateur);
                hs.put("listTypeContenu", listContenu);
                hs.put("allUtil", allUtil);
                hs.put("page", utilisateurCont.getPage());
                String pageInc = utilisateurCont.getPageInclude();
                if(pageInc == null) pageInc = "/pageInclude.jsp";
                hs.put("pageInc", pageInc);
                modelView.setData(hs);
            } else {
                modelView.setPage("/index.jsp");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            myconn.closeConnection(con);
        }
        return modelView;
    }


}
