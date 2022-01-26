/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Contenu.Contenu;
import ModelView.ModelView;
import MyAnnotation.URLMapping;
import Page.Element;
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
public class ContenuController {

    //ILAINY MBA AKANY NY UTILISATEUR CONNECTE
    private Number idUtilisateur;
    private Contenu contenu = new Contenu();
    private TypeContenu typeContenu = new TypeContenu();
    private Page page = new Page();

    public ContenuController() {
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
    public ModelView rechercheFichier(ContenuController contenuController) throws Exception {
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
                
                String sqlPage = "select count(*) as totalElement from contenu "+ contenuController.getContenu().getCondition("=", "and");
                System.out.println("sql nombre page : "+sqlPage);
                Element element = new Element();
                List<Object> nombrePageDansBdd = element.findToTable(sqlPage, con);
                System.out.println("total element "+((Element) nombrePageDansBdd.get(0)).getTotalElement().intValue() );
                double totalPage = Math.ceil(((Element) nombrePageDansBdd.get(0)).getTotalElement().doubleValue() / Double.parseDouble(Integer.toString( contenuController.getPage().getNombreParPage())) );
                
                contenuController.getPage().setTotalPage((int) totalPage);
                contenuController.getPage().setTotalElement(((Element) nombrePageDansBdd.get(0)).getTotalElement().intValue());
                
                System.out.println("total page " + contenuController.getPage().getTotalPage() );
                
                String direction = contenuController.getPage().getDirection();
                System.out.println("direction "+direction);
                int offset = 0;
                if (direction != null) {
                //manindry bouton precedent ou suivant
                    if (direction.compareToIgnoreCase("Next") == 0) {
                        int next = contenuController.getPage().getArret_boucle();
                        contenuController.getPage().setArret_boucle(next+1);
                        contenuController.getPage().setPage_num(contenuController.getPage().getPage_num()+1);
                        System.out.println("arret boucle "+contenuController.getPage().getArret_boucle());
                        System.out.println("page num "+contenuController.getPage().getPage_num());
                    } else {
                        
                    }
                    
                }
                else{
                 // manidry bouton nombre page ho affichena
                    System.out.println("CLICK ON THE NUMBER");
                    System.out.println("Page courant "+contenuController.getPage().getPageCourant().intValue());
                    offset = contenuController.getPage().getOffset();
                }
                String sql = "select * from contenu " + contenuController.getContenu().getCondition("=", "and")
                        + " LIMIT " + contenuController.getPage().getNombreParPage() + " offset " + offset;
                
                
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
    public ModelView ajout(ContenuController contenuCont) throws Exception {
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
    public ModelView getInfo(ContenuController contenuController) throws Exception {
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
    public ModelView telecharger(ContenuController contenuController) throws Exception {
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
