
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** TronControlPanel.java *************************************** */

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout.*;
import java.awt.event.*;
import java.awt.Color;

/*
* La classe TronControlPanel est une sous-classe de JPanel. Elle contient et organise tous les éléments graphiques autre que l'arène.
*/
public class TronControlPanel extends JPanel
{
	/*************************************                PROPRIÉTÉS               *********************************************/
	
	private TronPanel tPanel;  //On fait une référence au TronPanel pour pouvoir appeler la méthode newGame() et gérer le Timer.
	private JLabel joueur1;
	private JLabel joueur2;
	private JLabel ordinateur;
	private JLabel victoireJoueur1;
	private JLabel victoireJoueur2;
	private JLabel touchej1;
	private JLabel touchej2;
	private JLabel victoireOrdinateur;
	private JLabel etat;
	private JButton startPause;
	private JComboBox<String> typePartie;
	private JButton nouvellePartie;
	private JTextField vitesse;
	
	
	/*************************************                CONSTRUCTEUR               *****************************************/
	
	public TronControlPanel(TronPanel t)
	{
		tPanel = t;  //Associer le TronPanel à ce TronControlPanel.
		
		//Construire les Labels pour les joueurs, le nombre de victoire et les touches de contrôles.
		joueur1 = new JLabel("JOUEUR 1", JLabel.CENTER);
		joueur1.setForeground(Color.BLUE);
		victoireJoueur1 =  new JLabel("VICTOIRE : 0");
		victoireJoueur1.setForeground(Color.BLUE);
		touchej1 = new JLabel("CONTRÔLES : W,S,A,D");
		touchej1.setForeground(Color.BLUE);
		joueur2 = new JLabel("JOUEUR 2");
		joueur2.setForeground(Color.GREEN);
		joueur2.setVerticalAlignment(SwingConstants.CENTER);;
		victoireJoueur2 = new JLabel("VICTOIRE : 0");
		victoireJoueur2.setForeground(Color.GREEN);
		touchej2 = new JLabel("CONTRÔLES : I,K,J,L");
		touchej2.setForeground(Color.GREEN);
		ordinateur =  new JLabel("ORDINATEUR");
		ordinateur.setForeground(Color.RED);
		victoireOrdinateur = new JLabel("VICTOIRE : 0");
		victoireOrdinateur.setForeground(Color.RED);
		
		//Construire le bouton Pause.
		startPause = new JButton("PAUSE");
		startPause.setFocusable(false);
		startPause.addActionListener(new ActionListener()    //Lui ajouter un Listener.
									{
										public void actionPerformed(ActionEvent e)
										{
											if((startPause.getText()).equals("PAUSE")) //Si on appuie sur "Pause".
											{
												startPause.setText("JOUER");       
												etat.setText("EN PAUSE");
												tPanel.getTimer().stop();     //stopper le timer
											}
											else      //Si on appuie sur "Jouer" pour continuer la partie.
											{
												startPause.setText("PAUSE");
												etat.setText("EN COURS");
												tPanel.getTimer().start();  //activer le timer
											}
										}
									});
		
		//Construire la combo-box pour le choix de type de partie.
		typePartie = new JComboBox<String>();
		typePartie.addItem("Humain VS Humain");
		typePartie.addItem("Humain VS Humain VS COMP");
		typePartie.addItem("Humain VS COMP");
		
		//Construire le bouton "Nouvelle partie".
		nouvellePartie = new JButton("NOUVELLE PARTIE");
		nouvellePartie.addActionListener(new ActionListener()   //Lui ajouter un Listener.
										{
											public void actionPerformed(ActionEvent e)  //Quand on appuie dessus
											{
												String s = (String)typePartie.getSelectedItem();  //on récuppère le type de partie
												String v = vitesse.getText();                   //on récupère la vitesse
												int vi = Integer.parseInt(v);
												tPanel.newGame(s, vi);         //On appel la fonction newGame() pour commencer un partie.
											}
										});
		
		//Construire le Label d'état.
		etat = new JLabel("APPUYER SUR NOUVELLE PARTIE POUR DÉBUTER");  
		etat.setForeground(Color.DARK_GRAY);
		
		//Construire le Label et le TextField pour la vitesse.
		JLabel vitesseLabel = new JLabel("VITESSE (ms): ");
		vitesseLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		vitesse = new JTextField("50", 4);
		
		
		//Construire les JPanels internes au TronControlPanel et leur ajouter leur composantes respectifs.
		JPanel topTronControlPanel = new JPanel();
		topTronControlPanel.setLayout(new BorderLayout());
		topTronControlPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		topTronControlPanel.setBackground(Color.LIGHT_GRAY);
		topTronControlPanel.add(etat, BorderLayout.LINE_START);
		topTronControlPanel.add(startPause, BorderLayout.LINE_END);
		
		JPanel joueur1WSAD = new JPanel();
		joueur1WSAD.setLayout(new BoxLayout(joueur1WSAD, BoxLayout.Y_AXIS));
		joueur1WSAD.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		joueur1WSAD.setBackground(Color.LIGHT_GRAY);
		joueur1WSAD.add(joueur1);
		joueur1WSAD.add(victoireJoueur1);
		joueur1WSAD.add(touchej1);
		
		JPanel joueur2IKJL = new JPanel();
		joueur2IKJL.setLayout(new BoxLayout(joueur2IKJL, BoxLayout.Y_AXIS));
		joueur2IKJL.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		joueur2IKJL.setBackground(Color.LIGHT_GRAY);
		joueur2IKJL.add(joueur2);
		joueur2IKJL.add(victoireJoueur2);
		joueur2IKJL.add(touchej2);
		
		JPanel ordinateurInfo = new JPanel();
		ordinateurInfo.setLayout(new BoxLayout(ordinateurInfo, BoxLayout.Y_AXIS));
		ordinateurInfo.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		ordinateurInfo.setBackground(Color.LIGHT_GRAY);
		ordinateurInfo.add(ordinateur);
		ordinateurInfo.add(victoireOrdinateur);
		
		JPanel joueursEtInstructions = new JPanel();
		joueursEtInstructions.setLayout(new GridLayout(1,3));
		joueursEtInstructions.add(joueur1WSAD);
		joueursEtInstructions.add(joueur2IKJL);
		joueursEtInstructions.add(ordinateurInfo);
		
		JPanel bottomTronControlPanel = new JPanel();
		bottomTronControlPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
		bottomTronControlPanel.setBackground(Color.LIGHT_GRAY);
		bottomTronControlPanel.add(typePartie);
		bottomTronControlPanel.add(vitesseLabel);
		bottomTronControlPanel.add(vitesse);
		bottomTronControlPanel.add(nouvellePartie);
		
		
		//Pour finir on ajoute les JPanels internes dans le TronControlPanel.
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
		this.add(topTronControlPanel);
		this.add(joueursEtInstructions);
		this.add(bottomTronControlPanel);
	}
	
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*MUTATEURS*/
	
	public void setEtat(String s)
	{
		etat.setText(s);
	}
	
	public void setVictoireJoueur1(int n)
	{
		victoireJoueur1.setText("VICTOIRE : "+ n);	
	}
	
	public void setVictoireJoueur2(int n)
	{
		victoireJoueur2.setText("VICTOIRE : "+ n);	
	}
	
	public void setVictoireOrdinateur(int n)
	{
		victoireOrdinateur.setText("VICTOIRE : "+ n);	
	}
	
	public void setStartPause(String s)
	{
		startPause.setText(s);
	}
}