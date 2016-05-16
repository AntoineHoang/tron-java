
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** TronPanel.java *************************************** */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

/*
* La classe TronPanel est une sous-classe de JPanel. Elle est le "contentpane" du JFrame initialiser dans le main de Tron.
* Elle organise l'arène et le TronControlPanel. Elle gère également le déroulement du jeu. 
* Il y a deux classes internes. Soit Key qui implémente l'interface KeyListener et MyListener qui implémente l'interface ActionListener.
*/
public class TronPanel extends JPanel
{
	/*************************************                PROPRIÉTÉS               *********************************************/
	
	private Arene a;
	private TronControlPanel tcp;
	private Timer timer;
	private int victoireJoueur1;  //Le nombre de victoire pour chaque joueurs est mémorisé ici. 
	private int victoireJoueur2;
	private int victoireOrdinateur;
	
	
	/*************************************                CONSTRUCTEUR               *****************************************/
	
	public TronPanel()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		setFocusable(true);
		//Créer notre Arene.
		a = new Arene();
		a.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
		//Lui ajouter notre classe Key pour qu'elle puisse réagir aux touches du clavier.
		a.addKeyListener(new Key());
		
		//Créer le TronControlPanel.
		tcp = new TronControlPanel(this);
		//Lui ajouter également la classe Key.
		tcp.addKeyListener(new Key());
		
		//Ajouter l'arène et le TronControlPanel dans le TronPanel.
		this.add(a, BorderLayout.CENTER);
		this.add(tcp, BorderLayout.SOUTH);
		this.addKeyListener(new Key());
		
		//Initialiser le nombre de victoire à 0.
		victoireJoueur1 = 0;
		victoireJoueur2 = 0;
		victoireOrdinateur = 0;
	}
	
	
		
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*ACCESSEURS*/
	
	public Arene getArene(){  //pour le focus dans Tron.java
		return a;
	}
	
	public Timer getTimer()
	{
		return timer;
	}
	
	
	/*AUTRE*/
	
	/*
	* La méthode newGame() gère le début d'un nouvelle partie. Elle est appelée depuis la classe TronControlPanel lorsqu'on appuie sur
	* le bouton "Nouvelle Partie".
	*/
	public void newGame(String s, int v) //(type de partie, vitesse)
	{
		if(timer != null){timer.stop();}  //On arrête le timer précédent s'il existe.
		a.newGame(s);      //Créer les joueurs.
		tcp.setEtat("EN COURS");
		tcp.setStartPause("PAUSE");
		Mylistener listener = new Mylistener();
		timer = new Timer(v, listener);   //Créer le Timer selon la vitesse demandée.
		a.requestFocusInWindow();        //Redonner le focus à l'arène.
		a.getEnceinte().setColor(Color.CYAN);  
		timer.start();      //Activer le Timer.
	}
	
	
	
	/*************************************                CLASSES INTERNES                *********************************************/
	
	/*
	* La classe interne Key implémente l'interface KeyListener et nous permet de choisir les actions réalisés lorsqu'une touche est appuyée.
	*/
	class Key implements KeyListener
	{
		public void keyTyped(KeyEvent e)
		{
			char c = e.getKeyChar();   //Récuppérer le caractère.
			if(c == 'a' || c == 's' || c == 'd' || c == 'w')   //Vérifier le caractère correspond au contrôles du joueur 1.
			{
				char n = 'N';
				switch(c)     //Selon le caractère on détermine la direction.
				{
					case 'a':
						n = 'O';
						break;
					case 's':
						n = 'S';
						break;
					case 'd':
						n = 'E';
						break;
					case 'w':
						break;
				}
				((JoueurHumain) a.getJoueurs()[0]).setNouvelle_direction(n);  //On modifie la propriété nouvelle_direction du joueur 1.
			}
			if(c == 'j' || c == 'k' || c == 'l' || c == 'i') //Vérifier le caractère correspond au contrôles du joueur 2.
			{
				
				Joueur j = a.getJoueurs()[1];   
				if(j instanceof JoueurHumain)  //On s'assure que c'est pas un joueurOrdinateur.
				{	
					char n = 'N';
					switch(c)       //Selon le caractère on détermine la direction.
					{
						case 'j':
							n = 'O';
							break;
						case 'k':
							n = 'S';
							break;
						case 'l':
							n = 'E';
							break;
						case 'i':
							n = 'N';
							break;
					}
					((JoueurHumain)j).setNouvelle_direction(Character.toUpperCase(n)); //On modifie la propriété nouvelle_direction du joueur 2.
				}
			}
		}
	
		public void keyPressed(KeyEvent e){}	
			
		public void keyReleased(KeyEvent e){}
		
	}
	
	
	/*
	* La classe interne MyListener implémente l'interface ActionListener pour nous permettre de spécifier les actions à effectuées
	* en lien avec les ticks du Timer.
	*/
	class Mylistener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(a.tour() == false)  //On appel la méthode tour() de Arene tant qu'elle retourne vrai la partie n'est pas terminée.
			{
				timer.stop();      //La partie est terminée.
				a.getEnceinte().setColor(Color.WHITE);
				String s = a.gagnant();
				tcp.setEtat(s);
				//Selon l'état annoncé par TronControlPanel on détermine quel joueur a gagné. 
				//On incrémente alors son compteur de victoire et on affiche le nouveau score.
				if(s.equals("JOUEUR 1 A GAGNÉ!"))
				{
					victoireJoueur1 ++;
					tcp.setVictoireJoueur1(victoireJoueur1);
				}
				else if(s.equals("JOUEUR 2 A GAGNÉ!"))
				{
					victoireJoueur2 ++;
					tcp.setVictoireJoueur2(victoireJoueur2);
				}
				else if(s.equals("ORDINATEUR A GAGNÉ!"))
				{
					victoireOrdinateur ++;
					tcp.setVictoireOrdinateur(victoireOrdinateur);
				}
			}
			a.repaint();	
		}
	}
}




