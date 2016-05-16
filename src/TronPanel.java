
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
* Elle organise l'ar�ne et le TronControlPanel. Elle g�re �galement le d�roulement du jeu. 
* Il y a deux classes internes. Soit Key qui impl�mente l'interface KeyListener et MyListener qui impl�mente l'interface ActionListener.
*/
public class TronPanel extends JPanel
{
	/*************************************                PROPRI�T�S               *********************************************/
	
	private Arene a;
	private TronControlPanel tcp;
	private Timer timer;
	private int victoireJoueur1;  //Le nombre de victoire pour chaque joueurs est m�moris� ici. 
	private int victoireJoueur2;
	private int victoireOrdinateur;
	
	
	/*************************************                CONSTRUCTEUR               *****************************************/
	
	public TronPanel()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		setFocusable(true);
		//Cr�er notre Arene.
		a = new Arene();
		a.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
		//Lui ajouter notre classe Key pour qu'elle puisse r�agir aux touches du clavier.
		a.addKeyListener(new Key());
		
		//Cr�er le TronControlPanel.
		tcp = new TronControlPanel(this);
		//Lui ajouter �galement la classe Key.
		tcp.addKeyListener(new Key());
		
		//Ajouter l'ar�ne et le TronControlPanel dans le TronPanel.
		this.add(a, BorderLayout.CENTER);
		this.add(tcp, BorderLayout.SOUTH);
		this.addKeyListener(new Key());
		
		//Initialiser le nombre de victoire � 0.
		victoireJoueur1 = 0;
		victoireJoueur2 = 0;
		victoireOrdinateur = 0;
	}
	
	
		
	
	/*************************************                M�THODES                *********************************************/
	
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
	* La m�thode newGame() g�re le d�but d'un nouvelle partie. Elle est appel�e depuis la classe TronControlPanel lorsqu'on appuie sur
	* le bouton "Nouvelle Partie".
	*/
	public void newGame(String s, int v) //(type de partie, vitesse)
	{
		if(timer != null){timer.stop();}  //On arr�te le timer pr�c�dent s'il existe.
		a.newGame(s);      //Cr�er les joueurs.
		tcp.setEtat("EN COURS");
		tcp.setStartPause("PAUSE");
		Mylistener listener = new Mylistener();
		timer = new Timer(v, listener);   //Cr�er le Timer selon la vitesse demand�e.
		a.requestFocusInWindow();        //Redonner le focus � l'ar�ne.
		a.getEnceinte().setColor(Color.CYAN);  
		timer.start();      //Activer le Timer.
	}
	
	
	
	/*************************************                CLASSES INTERNES                *********************************************/
	
	/*
	* La classe interne Key impl�mente l'interface KeyListener et nous permet de choisir les actions r�alis�s lorsqu'une touche est appuy�e.
	*/
	class Key implements KeyListener
	{
		public void keyTyped(KeyEvent e)
		{
			char c = e.getKeyChar();   //R�cupp�rer le caract�re.
			if(c == 'a' || c == 's' || c == 'd' || c == 'w')   //V�rifier le caract�re correspond au contr�les du joueur 1.
			{
				char n = 'N';
				switch(c)     //Selon le caract�re on d�termine la direction.
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
				((JoueurHumain) a.getJoueurs()[0]).setNouvelle_direction(n);  //On modifie la propri�t� nouvelle_direction du joueur 1.
			}
			if(c == 'j' || c == 'k' || c == 'l' || c == 'i') //V�rifier le caract�re correspond au contr�les du joueur 2.
			{
				
				Joueur j = a.getJoueurs()[1];   
				if(j instanceof JoueurHumain)  //On s'assure que c'est pas un joueurOrdinateur.
				{	
					char n = 'N';
					switch(c)       //Selon le caract�re on d�termine la direction.
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
					((JoueurHumain)j).setNouvelle_direction(Character.toUpperCase(n)); //On modifie la propri�t� nouvelle_direction du joueur 2.
				}
			}
		}
	
		public void keyPressed(KeyEvent e){}	
			
		public void keyReleased(KeyEvent e){}
		
	}
	
	
	/*
	* La classe interne MyListener impl�mente l'interface ActionListener pour nous permettre de sp�cifier les actions � effectu�es
	* en lien avec les ticks du Timer.
	*/
	class Mylistener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(a.tour() == false)  //On appel la m�thode tour() de Arene tant qu'elle retourne vrai la partie n'est pas termin�e.
			{
				timer.stop();      //La partie est termin�e.
				a.getEnceinte().setColor(Color.WHITE);
				String s = a.gagnant();
				tcp.setEtat(s);
				//Selon l'�tat annonc� par TronControlPanel on d�termine quel joueur a gagn�. 
				//On incr�mente alors son compteur de victoire et on affiche le nouveau score.
				if(s.equals("JOUEUR 1 A GAGN�!"))
				{
					victoireJoueur1 ++;
					tcp.setVictoireJoueur1(victoireJoueur1);
				}
				else if(s.equals("JOUEUR 2 A GAGN�!"))
				{
					victoireJoueur2 ++;
					tcp.setVictoireJoueur2(victoireJoueur2);
				}
				else if(s.equals("ORDINATEUR A GAGN�!"))
				{
					victoireOrdinateur ++;
					tcp.setVictoireOrdinateur(victoireOrdinateur);
				}
			}
			a.repaint();	
		}
	}
}




