/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Contenu.Contenu;
import ModelView.ModelView;
import MyAnnotation.URLMapping;
import Page.Page;
import TypeContenu.TypeContenu;
import Utilisateur.Utilisateur;
import database.MyConnexion;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lucien
 */
public class ContenuControllerCpoy {

    //ILAINY MBA AKANY NY UTILISATEUR CONNECTE
    private Number idUtilisateur;
    private Contenu contenu = new Contenu();
    private TypeContenu typeContenu = new TypeContenu();
    private Page page = new Page();

    public ContenuControllerCpoy() {
    }

    public Contenu getContenu() {
        return contenu;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setContenu(Contenu contenu) {
        this.contenu = contenu;
    }

    public TypeContenu getTypeContenu() {
        return typeContenu;
    }

    public void setTypeContenu(TypeContenu typeContenu) {
        this.typeContenu = typeContenu;
    }

    public Number getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Number idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @URLMapping.Urlmapping(value = "recherche", url = "ContenuController/rechercheFichier")
    public ModelView rechercheFichier(ContenuControllerCpoy contenuController) throws Exception {
        ModelView modelView = new ModelView();
        Connection con = null;
        MyConnexion myconn = new MyConnexion();
        try {
            con = myconn.openConnection();
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(contenuController.getIdUtilisateur());
            List<Object> utilisateurConnecte = utilisateur.findToTable(con);
            System.out.println("idUtilisateur " + contenuController.getIdUtilisateur());

            modelView.setPage("/Contenu/home.jsp");
            if (utilisateurConnecte.size() == 1) {
                System.out.println("nombre utilisateurConnecte " + utilisateurConnecte.size());
                //return modelView;
                TypeContenu typeCont = new TypeContenu();
                List<Object> listTypeContenu = typeCont.findToTable(con);
                ((Utilisateur) utilisateurConnecte.get(0)).setPassword("");
                System.out.println("id utilisateur " + contenuController.getContenu().getIdutilisateur());
                
                System.out.println("\n\n--------------------------------------------------");
                //TRATEMENT POUR GERER LE PAGINATION
                System.out.println("Page current " + contenuController.getPage().getPageCurrent());
                int pageCurrent = contenuController.getPage().getPageCurrent();
                String direction = contenuController.getPage().getDirection();
                System.out.println("direction :" + direction);
                String sqlPage = "select count(*) as nombrePage from contenu "+ contenuController.getContenu().getCondition("=", "and");
                //System.out.println("sql nombre page : "+sqlPage);
                List<Object> nombrePage = contenuController.getPage().findToTable(sqlPage, con);
                double totalPage = Math.ceil(((Page) nombrePage.get(0)).getNombrePage().doubleValue() / Double.parseDouble(Integer.toString( contenuController.getPage().getNombreParPage())) );
                contenuController.getPage().setNombrePage(( (Page) nombrePage.get(0)).getNombrePage());
                contenuController.getPage().setTotalPage((int)totalPage);
                System.out.println("nombre page " + ( (Page) nombrePage.get(0)).getNombrePage() );
                System.out.println("total page " + contenuController.getPage().getTotalPage() );
                int min = 0;
                int max = 0;
                if (direction != null) {

                    if (direction.compareToIgnoreCase("Previous") == 0) {
                        System.out.println("Previous");
                        if (pageCurrent - 1 <= 0) {
                            contenuController.getPage().setPageCurrent(Integer.toString(0));
                            //max = contenuController.getPage().getNombreParPage();
                        } else {
                            contenuController.getPage().setPageCurrent(Integer.toString(pageCurrent - 1));
                            //max = contenuController.getPage().getNombreParPage()-1;
                            //contenuController.getPage().setNombreParPage(Integer.toString(contenuController.getPage().getNombreParPage() - 1));
                        }
                    } else {
                        //if(contenuController.getPage().getPageSuivante())
                        System.out.println("next");
                        if(pageCurrent == 0){
                            contenuController.getPage().setPageCurrent(Integer.toString(pageCurrent + 1));
                            System.out.println("PageCurrent next : "+contenuController.getPage().getPageCurrent());
                        }
                        else{
                            contenuController.getPage().setPageCurrent(Integer.toString(pageCurrent + 1));
                            contenuController.getPage().setNombreParPage(Integer.toString(contenuController.getPage().getNombreParPage()+1));
                            System.out.println("PageCurrent next 2 : "+contenuController.getPage().getPageCurrent());
                        }
                        
                        //contenuController.getPage().setNombreParPage(Integer.toString(contenuController.getPage().getNombreParPage() + 1));
                    }
                    max = contenuController.getPage().getNombreParPage();
                    if (max > contenuController.getPage().getNombrePage().intValue()){
                        max = contenuController.getPage().getNombrePage().intValue();
                    }
                }else{
                    System.out.println("page current : "+contenuController.getPage().getPageCurrent()+" nombre de page "+contenuController.getPage().getNombreParPage());
                    
                    min = contenuController.getPage().getPageCurrent()+ contenuController.getPage().getNombreParPage();
                    System.out.println("min : "+min +" par : "+contenuController.getPage().getNombrePage().intValue());
                    
                    if(min <= contenuController.getPage().getNombrePage().intValue()){
                        max = contenuController.getPage().getPageCurrent() + contenuController.getPage().getNombreParPage();
                    }else{
                        max = contenuController.getPage().getNombrePage().intValue();
                    }
                    System.out.println("max : "+max);
                }
                //System.out.println("Page info : " + contenuController.getPage().toString());
                //String sql = "select u.nom , u.prenom, u.email, u.addresse, u.contact, c.* from utilisateur u join contenu c on u.id=c.idutilisateur " + contenuController.getContenu().getCondition("=", "and") + "";
                String sql = "select * from contenu " + contenuController.getContenu().getCondition("=", "and")
                        + " LIMIT " + contenuController.getPage().getPageCurrent() + "," + max;
                
                System.out.println("sql fichier" + sql);
                List<Object> contenuResultat = contenuController.getContenu().findToTable(sql, con);
               
                System.out.println("total fichier trouve " + contenuResultat.size());
                Utilisateur empAll = new Utilisateur();
                List<Object> allEmp = empAll.findToTable(con);
                HashMap hs = new HashMap();
                hs.put("utilisateur", utilisateurConnecte);
                hs.put("listTypeContenu", listTypeContenu);
                hs.put("contenuResultat", contenuResultat);
                hs.put("allUtil", allEmp);
                hs.put("page", contenuController.getPage());
                hs.put("pageInc", "/pageInclude.jsp");
                modelView.setData(hs);
                System.out.println("nombrepar page "+contenuController.getPage().getNombreParPage());
                System.out.println("\n\n--------------------------------------------------");
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

    @URLMapping.Urlmapping(value = "ajoutContenu", url = "ContenuController/ajout")
    public ModelView ajout(ContenuControllerCpoy contenuCont) throws Exception {
        MyConnexion myconn = new MyConnexion();
        Connection con = null;

        ModelView modelView = new ModelView();
        modelView.setPage("FichierService");
        HashMap hs = new HashMap();
        try {

            con = myconn.openConnection();
            //contenuCont.getContenu().insertToTable(con);
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(contenuCont.getContenu().getIdutilisateur());
            List<Object> utilisateurConnecte = utilisateur.findToTable(con);
            if (utilisateurConnecte.size() == 1) {

                //return modelView;
                TypeContenu typeContenu = new TypeContenu();
                List<Object> listTypeContenu = typeContenu.findToTable(con);
                ((Utilisateur) utilisateurConnecte.get(0)).setPassword("");

                hs.put("utilisateur", utilisateurConnecte);
                hs.put("listTypeContenu", listTypeContenu);
                hs.put("contenuToAdd", contenuCont.getContenu());
                modelView.setData(hs);
            } else {
                modelView.setPage("/index.jsp");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            myconn.closeConnection(con);
        }

        String page = contenuCont.getTypeContenu().getPage();
        hs.put("pageInc", "/" + page + "/" + page + ".jsp");
        modelView.setData(hs);
        //modelView.setPage("/Mission/ajoutMission.jsp");

        return modelView;
    }

    public boolean addContenu(Contenu contenu) throws Exception {
        MyConnexion myconn = new MyConnexion();
        Connection con = null;
        try {
            con = myconn.openConnection();
            contenu.insertToTable(con);
            con.commit();
            return true;
        } catch (Exception e) {
            throw e;
        } finally {
            myconn.closeConnection(con);
        }
    }

    @URLMapping.Urlmapping(value = "info", url = "ContenuController/getInfo")
    public ModelView getInfo(ContenuControllerCpoy contenuController) throws Exception {
        ModelView modelView = new ModelView();
        Connection con = null;
        MyConnexion myconn = new MyConnexion();

        try {
            con = myconn.openConnection();
            modelView.setPage("/Contenu/home.jsp");
            HashMap hs = new HashMap();

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(contenuController.getIdUtilisateur());
            System.out.println("id utilisateur : " + contenuController.getIdUtilisateur());
            List<Object> utilisateurConnecte = utilisateur.findToTable(con);
            if (utilisateurConnecte.size() == 1) {
                System.out.println("\n\n nombre utilisateurConnecte " + utilisateurConnecte.size() + "\n\n");
                Contenu contenu = new Contenu();
                TypeContenu listTypeContenu = new TypeContenu();
                List<Object> listContenu = listTypeContenu.findToTable(con);
                ((Utilisateur) utilisateurConnecte.get(0)).setPassword("");

                hs.put("utilisateur", utilisateurConnecte);
                hs.put("listTypeContenu", listContenu);

                hs.put("pageInc", "/detail.jsp");

                contenu.setId(contenuController.getContenu().getId());
                List<Object> contenuById = contenu.findToTable(con);

                //hs.put("responsable", (Utilisateur) utilisateurConnecte.get(0));
                hs.put("contenuById", (Contenu) contenuById.get(0));
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

    @URLMapping.Urlmapping(value = "telecharger", url = "ContenuController/telecharger")
    public ModelView telecharger(ContenuControllerCpoy contenuController) throws Exception {
        ModelView modelView = new ModelView();
        Connection con = null;
        MyConnexion myconn = new MyConnexion();
        try {
            con = myconn.openConnection();
            modelView.setPage("TelechargerFichier");
            HashMap hs = new HashMap();

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(contenuController.getIdUtilisateur());
            System.out.println("id utilisateur : " + contenuController.getIdUtilisateur());
            List<Object> utilisateurConnecte = utilisateur.findToTable(con);
            if (utilisateurConnecte.size() == 1) {
                System.out.println("\n\n nombre utilisateurConnecte " + utilisateurConnecte.size() + "\n\n");
                Contenu contenu = new Contenu();
                TypeContenu listTypeContenu = new TypeContenu();
                List<Object> listContenu = listTypeContenu.findToTable(con);
                ((Utilisateur) utilisateurConnecte.get(0)).setPassword("");

                hs.put("utilisateur", utilisateurConnecte);
                hs.put("listTypeContenu", listContenu);

                hs.put("pageInc", "/detail.jsp");

                contenu.setId(contenuController.getContenu().getId());
                List<Object> contenuById = contenu.findToTable(con);

                //hs.put("responsable", (Utilisateur) utilisateurConnecte.get(0));
                hs.put("contenuById", (Contenu) contenuById.get(0));
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
