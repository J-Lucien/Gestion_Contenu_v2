/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilisateur;

/**
 *
 * @author Lucien
 */
public class Utilisateur extends bdtable.BDTable{
    private Number id;
    private String nom;
    private String prenom;
    private String email;
    private String addresse;
    private String password;
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Utilisateur() {
    }
    
    

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Nom : "+this.getNom()+"\n" +
                "Prenom : "+this.getPrenom()+"\n"+
                "Eamil : "+this.getEmail()+"\n"+
                "Addresse : "+this.getAddresse()+"\n"+
                "Contact : "+this.getContact().toString();
    }
    
    
}
