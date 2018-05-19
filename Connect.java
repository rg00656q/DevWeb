/*
 * b3QHt(Y@OqQlGsh(
 * Host : sql7.freesqldatabase.com
 * User : sql7238609
 * Password : HmnGnlpMyP
 * Port : 3306
 */
package Projet2;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Guillaume
 */
public class Connect {
    private Connection cnx;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private String req;
    private int idu;

    // A utiliser en début de session (cad avant tout le reste)
    public Connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://sql7.freesqldatabase.com/sql7238609", "sql7238609", "HmnGnlpMyP");
            st = cnx.createStatement();
        }
        catch(SQLException sqle){
            System.out.println("Erreur acces à la base de données");
            sqle.printStackTrace();
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("elle est ou la class??");
            cnfe.printStackTrace();
        }
    }

    // Ajoute un nouvel utilisateur
    public int nouvUtil(String mail, String pw){
        int result = 1;
        try {
            // Verification d'existance de l'email
            String req_verif = "Select COUNT(*) FROM Utilisateurs WHERE email = ?";
            ps = cnx.prepareStatement(req_verif);
            ps.setString(1, mail);
            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt(1) >= 1){
                System.out.println(mail+" déjà utilisé");
                rs.close();
                ps.close();
                cnx.close();
                return -1;
            }
            rs.close();
            st.close();

            // Ajout à la BDD du nouvel utilisateur
            req = "INSERT INTO Utilisateurs(email,mdp) VALUES (?,?)";
            ps = cnx.prepareStatement(req);
            ps.setString(1, mail);
            ps.setString(2, pw);
            result = ps.executeUpdate();
            if(result == 0) {
                System.out.println("erreur d'insertion");
                return -1;
            }
            ps.close();

            // récupération de l'id de l'utilisateur
            req = "SELECT id_u FROM Utilisateurs WHERE email = ?";
            ps = cnx.prepareStatement(req);
            ps.setString(1, mail);
            rs = ps.executeQuery();
            rs.next();
            idu = rs.getInt(1);
            rs.close();
            ps.close();
            cnx.close();
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Permet a un utilisateur connu de se co
    public int connection(String mail, String pw){
        try {
            // Verification du mail et mdp
            String req = "SELECT id_u FROM Utilisateurs WHERE email = ? AND mdp = ?";
            ps = cnx.prepareStatement(req);
            ps.setString(1, mail);
            ps.setString(2, pw);
            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt(1) >=1){
                idu = rs.getInt(1);
                rs.close();
                st.close();
                return 1;
            }
            return -1;

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    // Fait deconnecter du serveur (pour déconnexion)
    public void deconnexion(){
        try{
            idu = 0;
            cnx.close();
        }catch (SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Création d'un nouveau fichier
    public int nouvFich(String title){
        int result = 1;
        try {
            // Ajout du fichier
            req = "INSERT INTO Fichiers(titre,contenu) VALUES (?,?)";
            ps = cnx.prepareStatement(req, ps.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setString(2, "Ceci est un test");
            result = ps.executeUpdate();
            if(result == 0) {
                System.out.println("erreur d'ajout du fichier");
                return -1;
            }
            ResultSet fid = ps.getGeneratedKeys();
            fid.next();
            int idf = fid.getInt(1);
            ps.close();

            // Liaison du fichier à l'utilisateur
            req = "INSERT INTO Appartient(id_u,id_f) VALUES (?,?)";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            ps.setInt(2, idf);
            result = ps.executeUpdate();
            if(result == 0){
                System.out.println("Liaison impossible, une erreur est survenue");
                ps.close();
                cnx.close();
                return -1;
            }
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Retourne la liste des textes (sous forme d'ArrayList)
    public ArrayList recupTxts(){
        ArrayList<String> list = new ArrayList<>();
        try {
            if(idu == 0){
                System.out.println("Non Connecté");
                return null;
            }

            req = "SELECT titre FROM Fichiers WHERE id_f IN (SELECT id_f FROM Appartient WHERE id_u = ?)";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Retourne le fichier selectionné par l'utilisateur (on utilise getKey et getValue pour le titre et le contenu)
    public Pair<String,String> affTxt(String titre){
        try{
            if(idu == 0){
                System.err.println("Non connecté");
            }
            req = "SELECT titre,contenu FROM Fichiers WHERE (titre = ? AND id_f IN ( SELECT id_f FROM Appartient WHERE id_u = ? ))";
            ps = cnx.prepareCall(req);
            ps.setString(1, titre);
            ps.setInt(2, idu);
            rs = ps.executeQuery();
            rs.next();
            String title = rs.getString(1);
            String content = rs.getString(2);
            rs.close();
            ps.close();
            return new Pair(title,content);
            
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Sauvegarde du fichier
    public int sauvegarde(String titre, String contenu){
        try{
            req = "SELECT id_f FROM Fichiers WHERE (titre = ? AND id_f IN (SELECT id_f FROM Appartient WHERE id_u = ?) )";
            ps = cnx.prepareStatement(req);
            ps.setString(1, titre);
            ps.setInt(2, idu);
            rs = ps.executeQuery();
            rs.next();
            int idf = rs.getInt(1);
            rs.close();
            ps.close();
            
            req = "UPDATE Fichiers SET contenu = ? WHERE id_f = ?";
            ps = cnx.prepareStatement(req);
            ps.setString(1, contenu);
            ps.setInt(2, idf);
            int resultat = ps.executeUpdate();
            if(resultat != 1){
                System.err.println("Une erreur est survenue");
            }
            ps.close();
            
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    // Ajout de nouveaux participants au texte f
    public int ajoutPersonne(String fichier, String mail){
        try{
            // Existance de la personne a ajouter dans la bdd
            req = "SELECT id_u FROM Utilisateurs WHERE email = ?";
            ps = cnx.prepareStatement(req);
            ps.setString(1, mail);
            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt(1) < 1){
                System.out.println("Cette personne n'a pas encore de compte chez nous.");
                rs.close();
                ps.close();
                return -1;
            }
            int id_ajout = rs.getInt(1);
            rs.close();
            ps.close();
            
            // Existance du lien
            req = "SELECT COUNT(*) FROM Appartient WHERE (id_u = ? AND id_f IN (SELECT id_f FROM Fichiers WHERE titre = ?) )";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id_ajout);
            ps.setString(2, fichier);
            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt(1) >= 1){ // si le lien existe
                System.err.println("Persone déjà ajoutée");
                rs.close();
                ps.close();
                return 1;
            }
            rs.close();
            ps.close();
            
            // récupération de l'id du fichier
            req = "SELECT id_f FROM Appartient WHERE (id_u = ? AND id_f IN (SELECT id_f FROM Fichiers WHERE titre = ?) )";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            ps.setString(2, fichier);
            rs = ps.executeQuery();
            rs.next();
            int id_f = rs.getInt(1);
            rs.close();
            ps.close();
            
            req = "INSERT INTO Appartient(id_u,id_f) VALUES (?,?)";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id_ajout);
            ps.setInt(2, id_f);
            ps.executeUpdate();
            rs.close();
            ps.close();
            
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    // Suppression d'un fichier
    public int suppFich(String titre){
        try{
            // On verifie que c'est une personne qui utilise le fichier qui compte le supprimer
            req = "SELECT COUNT(*) FROM Appartient WHERE (id_u = ? AND id_f IN (SELECT id_f FROM Fichiers WHERE titre = ?))";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            ps.setString(2, titre);
            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt(1) < 1 ){
                System.err.println("Vous n'avez pas accès à cet élément");
                rs.close();
                ps.close();
                return -1;
            }
            
            // On récupère l'id du fichier
            req = "SELECT id_f FROM Appartient WHERE (id_u = ? AND id_f IN (SELECT id_f FROM Fichiers WHERE titre = ?))";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            ps.setString(2, titre);
            rs = ps.executeQuery();
            rs.next();
            int idf = rs.getInt(1);
            rs.close();
            ps.close();
            
            // ON supprime les liens
            req = "DELETE FROM Appartient WHERE id_f = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idf);
            int resultat = ps.executeUpdate();
            rs.close();
            ps.close();
            
            // On supprime l'élément
            req = "DELETE FROM Fichiers WHERE id_f = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idf);
            resultat = ps.executeUpdate();
            rs.close();
            ps.close();
                        
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    // Suppression d'un compte
    public void suppCompte(){
        try{
            // ON supprime les liens
            req = "DELETE FROM Appartient WHERE id_u = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            int resultat = ps.executeUpdate();
            rs.close();
            ps.close();
            
            // On supprime la personne
            req = "DELETE FROM Utilisateurs WHERE id_u = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, idu);
            resultat = ps.executeUpdate();
            rs.close();
            ps.close();
            cnx.close();
            
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
