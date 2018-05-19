package Projet2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main {
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame ("Menu");
		JButton co = new JButton(new ImageIcon("Ressources/Connection.PNG"));
		JButton ins = new JButton(new ImageIcon("Ressources/Inscription.PNG"));
		Menu menu = new Menu();
		Connection connection = new Connection();
		Inscription inscription = new Inscription();
		Site site = new Site();
		
		//création de la fenêtre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(150,150);
		frame.setPreferredSize(new Dimension (1500, 800));
		frame.setResizable(false);
		frame.setContentPane(menu);
		frame.setLayout(null);
		
		//bouton connection dans le menu
		co.setBounds(800, 250, 200,100);
		menu.add(co);
		co.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.remove(menu);
				frame.setContentPane(connection);
				frame.revalidate();
			};
		});
		
		//bouton inscription dans le menu
		ins.setBounds(500, 250, 200,100);
		menu.add(ins);
		ins.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.remove(menu);
				frame.setContentPane(inscription);
				frame.revalidate();
			}
		});
	
		//bouton de retour dans connection
	//	retourCo.setBounds(700,400,500,500);
		
//		connection.add(retourCo);
		connection.retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(connection);
				frame.setContentPane(menu);
				frame.revalidate();
			}
		});
		
		//bouton de retour dans inscription
		inscription.retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(inscription);
				frame.setContentPane(menu);
				frame.revalidate();
			}
		});		
		
		connection.valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//if(connection.identifient.getText()==null ){// && connection.mdp.getText()=="mdpbob") {
				
					frame.remove(inscription);
					frame.setContentPane(site);
					frame.revalidate();
			//	}
				//else
				//	JOptionPane.showInternalMessageDialog(connection, "Erreur, vous n'êtes pas inscrit", "Information", JOptionPane.ERROR_MESSAGE);

			}
		});
		
		inscription.valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(inscription.mdp == inscription.mdpv ) {
					JOptionPane.showInternalMessageDialog(inscription, "Bravo, vous êtes inscrit", "Information", JOptionPane.INFORMATION_MESSAGE);
					frame.remove(inscription);
					frame.setContentPane(menu);
					frame.revalidate();
				}
				else
					JOptionPane.showInternalMessageDialog(inscription, "Erreur, vos mots de passe sont différents", "Information", JOptionPane.ERROR_MESSAGE);
							
			}
		});
		
		frame.pack();
		frame.setVisible(true);	
	}
}
