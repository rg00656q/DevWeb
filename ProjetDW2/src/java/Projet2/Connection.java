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

public class Connection extends JPanel {
	JButton retour = new JButton(new ImageIcon("Ressources/Retour.PNG"));
	JButton valider = new JButton(new ImageIcon("Ressources/Valider.PNG"));
	JTextField identifient = new JTextField(20);
	JPasswordField mdp = new JPasswordField(20);
	
	public void AffCo(Graphics g) {	
		setBackground(Color.GRAY);
		g.setColor(Color.CYAN);
		Font font = new Font("Arial",Font.BOLD,70);
		g.setFont(font);
		g.drawString("CONNECTION", 520, 100);
		
		g.setColor(Color.black);
		Font font2 = new Font("Arial",Font.BOLD,30);
		g.setFont(font2);
		
		g.drawString("Identifient", 670, 200);		
		identifient.setBounds(650, 220, 180, 30);
		add(identifient);
		
		g.drawString("Mot de passe", 645, 300);
		mdp.setBounds(650, 320, 180, 30);
		add(mdp);		

		g.setColor(Color.WHITE);
		g.drawRect(10, 140, 1473, 280);
		
		valider.setBounds(780, 500,200,50);
		add(valider);
		
		retour.setBounds(100,600,180,50);
		add(retour);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		AffCo(g);   
	}
}
