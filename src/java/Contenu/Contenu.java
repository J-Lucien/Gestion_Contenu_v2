/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contenu;


/**
 *
 * @author Lucien
 */
public class Contenu extends bdtable.BDTable{

    private Number id;
    private Number idutilisateur;
    private String titre;
    private String lieu;
    private String typecontenu;
    private String description;
    private String remarque;
    private String nomfichier;
    private String datecreation;
    private String datefin;

    public Contenu() {
    }

    public Contenu(Number id) {
        this.id = id;
    }

    public Contenu(Number id, int idutilisateur, String titre, String typecontenu, String datecreation) {
        this.id = id;
        this.idutilisateur = idutilisateur;
        this.titre = titre;
        this.typecontenu = typecontenu;
        this.datecreation = datecreation;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(Number idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTypecontenu() {
        return typecontenu;
    }

    public void setTypecontenu(String typecontenu) {
        this.typecontenu = typecontenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNomfichier() {
        return nomfichier;
    }

    public void setNomfichier(String nomfichier) {
        this.nomfichier = nomfichier;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Contenu)) {
//            return false;
//        }
//        Contenu other = (Contenu) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "Contenu.Contenu[ id=" + id + " ]";
//    }
    
}
