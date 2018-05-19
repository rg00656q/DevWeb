/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet2;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Guillaume
 */
public class test {
    public static void main(String[] args) {
        Connect con = new Connect();
        con.connection("guillaumeromero@free.fr", "MagikarpIsBest");
        ArrayList l = con.recupTxts();
        System.out.println(l.get(0));
        System.out.println(l.get(1));
        System.out.println(l.get(2));
        con.sauvegarde("BlahBlah", "Je ne sais plus trop quoi écrire alors je souris :)");
        con.deconnexion();
    }
}