package Projet2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Site extends JPanel{
	
	JTextArea  text = new JTextArea();
	JTextArea  dialogue = new JTextArea();
	JTextArea  dialogueMessage = new JTextArea();
//	JTextField text = new JTextField();
	JScrollPane scroll = new JScrollPane(text);
	JButton retour = new JButton(new ImageIcon("Ressources/Retour.PNG"));
	JButton envoyer = new JButton(new ImageIcon("Ressources/Retour.PNG"));

	public void AffSite(Graphics g) {	
		setBackground(Color.GRAY);
		
		g.setColor(Color.cyan);
		Font font = new Font("Arial",Font.BOLD,70);
		g.setFont(font);
		g.drawString("SITE", 700, 100);
		
		text.setBounds(300, 130, 1000, 600);
		dialogue.setBounds(0, 130, 150, 300);
		dialogue.setBounds(0, 530, 150, 100);
		retour.setBounds(0, 0, 180, 50);
		envoyer.setBounds(0, 630, 150, 50);
		
		//add(text);
		add(dialogue);
		add(dialogueMessage);
		add(retour);
		add(envoyer);
		add(scroll);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		AffSite(g);   
	}
}
