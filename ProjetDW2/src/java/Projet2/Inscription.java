package Projet2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Inscription extends JPanel{
	JButton valider = new JButton(new ImageIcon("Ressources/Valider.PNG"));
	JButton retour = new JButton(new ImageIcon("Ressources/Retour.PNG"));
	JTextField identifient = new JTextField(20);
	JPasswordField mdp = new JPasswordField(20);
	JPasswordField mdpv = new JPasswordField(20);
			
	public void AffIns(Graphics g) {	

		setBackground(Color.GRAY);
		
		g.setColor(Color.cyan);
		Font font = new Font("Arial",Font.BOLD,70);
		g.setFont(font);
		g.drawString("INSCRIPTION", 550, 100);
		
		g.setColor(Color.black);
		Font font2 = new Font("Arial",Font.BOLD,30);
		g.setFont(font2);
		
		g.drawString("identifient", 670, 200);		
		identifient.setBounds(650, 220, 180, 30);
		add(identifient);
		
		g.drawString("Mot de passe", 650, 300);
		mdp.setBounds(650, 320, 180, 30);
		add(mdp);
		
		g.drawString("Confirmer votre mot de passe", 550, 400);
		mdpv.setBounds(650, 420, 180, 30);
		add(mdpv);
		
		g.setColor(Color.WHITE);
		g.drawRect(10, 140, 1473, 350);
		

		retour.setBounds(100,600,180,50);
		add(retour);
		
		valider.setBounds(780, 500,200,50);
		add(valider);
		valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					
			}
		});
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		AffIns(g);   
	}
}
