package Projet2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel{	
	
	public void AffMenu(Graphics g) {		

		setBackground(Color.GRAY);
		g.setColor(Color.cyan);
		Font font = new Font("Arial",Font.BOLD,50);
		g.setFont(font);
		g.drawString("Bonjour et Bienvenu(e) sur MultiText !!", 350, 100);		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		AffMenu(g);   
	}
}