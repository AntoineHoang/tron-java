
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** Arene.java *************************************** */

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

/*
* La classe Arene est une sous-classe de JComponent. Elle contient les Traces des joueurs et de l'enceinte ainsi qui les m�thodes
* pour les redessiner. Elle s'occupe notamment de g�n�rer les joueurs et en partie de l'ex�cution d'un tour(tick). 
*/
public class Arene extends JComponent
{
	/*************************************                PROPRI�T�S               *********************************************/
	
	private int largeur_grille;  //Largeur de la grille virtuelle. 
	private int hauteur_grille;  //Longeur de la grille virtuelle.
	final int largeur_pixel = 500;  //Taille en pixel par d�faut.
	final int hauteur_pixel= 500;
	private Joueur[] joueurs;
	private Trace enceinte;
	
	
	/*************************************                CONSTRUCTEUR               *****************************************/
	
	public Arene() 
	{
		largeur_grille = 100;  //Largeur de la grille virtuelle.
		hauteur_grille = 100;  //Longeur de la grille virtuelle.
		setPreferredSize(new Dimension(largeur_pixel,hauteur_pixel));
		enceinte = creerEnceinte();
	}
	
	
	/*************************************                M�THODES                *********************************************/
	
	/*ACCESSEURS*/
	
	public Joueur[] getJoueurs()
	{
		return joueurs;
	}
	
	public Trace getEnceinte()
	{
		return enceinte;
	}
	
	
	/*AUTRES*/
	
	/*
	* La m�thode creerEnceinte() est appel�e lors de l'initialisation de l'ar�ne. Elle cr�e une Trace suivant le contour de la
	* grille virtuelle.
	*/
	public Trace creerEnceinte()
	{
		Trace mur = new Trace(new Segment(new Point(1, 1), 'S'), 'S', Color.WHITE);
		
		while(mur.getLast().getLast().getY() != hauteur_grille)  //mur de gauche
		{
			mur.allonge('S');
		}
		while(mur.getLast().getLast().getX() != largeur_grille)  //mur du bas
		{
			mur.allonge('E');
		}
		while(mur.getLast().getLast().getY() != 1)  //mur de droite
		{
			mur.allonge('N');
		}
		while(mur.getLast().getLast().getX() != 1)  //mur du haut
		{
			mur.allonge('O');
		}
		return mur;
	}
	
	/*
	* La m�thode newGame() est appel�e par la m�thode du m�me nom de la classe TronPanel lors de la cr�ation d'une nouvelle partie.
	* Elle cr�e les joueurs n�cessaires selon le type de partie et les met dans un tableau de joueurs. 	
	*/
	public void newGame(String s)
	{
		if(s.equals("Humain VS Humain")) //Cr�er deux JoueurHumain.
		{
			Joueur joueur1 = new JoueurHumain(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.BLUE,
											  directionAleatoire());
			Joueur joueur2 = new JoueurHumain(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.GREEN,
											  directionAleatoire());
			joueurs = new Joueur[]{joueur1, joueur2};								  
		}
		else if(s.equals("Humain VS Humain VS COMP"))  //Cr�er deux JoueurHumain et un JoueurOrdinateur.
		{
			Joueur joueur1 = new JoueurHumain(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.BLUE,
											  directionAleatoire());
			Joueur joueur2 = new JoueurHumain(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.GREEN,
											  directionAleatoire());
			Joueur ordinateur = new JoueurOrdinateur(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.RED,
											  directionAleatoire());
			joueurs = new Joueur[]{joueur1, joueur2, ordinateur};
		}
		else if(s.equals("Humain VS COMP"))    //Cr�er un JoueurHumain et un JoueurOrdinateur.
		{
			Joueur joueur1 = new JoueurHumain(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.BLUE,
											  directionAleatoire());
			Joueur ordinateur = new JoueurOrdinateur(new Point(positionXAleatoire(), positionYAleatoire()), 
											  Color.RED,
											  directionAleatoire());
			joueurs = new Joueur[]{joueur1, ordinateur}; 
		}
	}
	
	/*
	* La m�thode directionAleatoire() g�n�re une direction (sous forme de caract�re) parmis les quatres possibilit�s.
	* Elle est utilis�e lorsqu'on cr�e les nouveaux joueurs dans newGame() pour leur donner une direction de d�part al�atoire.
	*/
	private char directionAleatoire()
	{
		char d;
		int i = (int)(Math.random()*4);
		switch(i)
		{
			case 0:
				d = 'N';
				break;
			case 1:
				d = 'E';
				break;
			case 2:
				d = 'S';
				break;
			case 3:
				d = 'O';
				break;
			default:
				d = 'N';
		}
		return d;
	}
	
	//G�n�re une position en x al�atoire selon la taille de la grille virtuelle.
	private int positionXAleatoire()
	{
		int x = (int)(Math.random()*(largeur_grille-4)+2);
		return x;
	}
	
	//G�n�re une position en y al�atoire selon la taille de la grille virtuelle.
	private int positionYAleatoire()
	{
		int y = (int)(Math.random()*(hauteur_grille-4)+2);
		return y;
	}
	
	/*
	* La m�thode tour() ex�cute les actions qui doivent �tre faite � chaque tick du Timer: d�placer les joueurs, v�rifier s'il
	* y a colision et v�rifier qu'il y a plus d'un joueur en vie  � la fin du tour.
	* Retourne True s'il reste plus d'un joueur. False sinon.
	*/
	public boolean tour()
	{
		//D�placement
		for(int i=0;i<joueurs.length;i++)  //pour chaque joueurs
		{
			if(joueurs[i] instanceof JoueurOrdinateur)
			{
				 //L'ordi doit choisir sa direction avant le d�placement.
				((JoueurOrdinateur)joueurs[i]).choixDirection(joueurs, enceinte);
			}
			joueurs[i].deplacement();   //se d�placer
			
		//V�rifier s'il y a colision avec un autre joueur ou lui m�me.	
			Point tete = joueurs[i].getTrace().tete();
			
			for(int j=0;j<joueurs.length;j++) 
			{
				if(joueurs[j].getTrace().contient(tete))
				{
					joueurs[i].setEnVie('M');  //Si colision, on signal que le joueur est mort.
				}
			}
		//V�rifier s'il y a colision avec le mur d'enceinte.
			if(enceinte.contient(tete))
			{
				joueurs[i].setEnVie('M');
			} 
		}
		
		//Calculer le nombre de joueur en vie.
		int nb  = 0;
		for(int m=0;m<joueurs.length;m++)
		{
			if(joueurs[m].getEnVie() == 'V'){nb ++;}	
		}
		return(nb > 1);
	}
	
	/*
	* La m�thode gagnant() d�termine la String a afficher � la fin d'une partie selon le joueur qui est toujours en vie.
	*/
	public String gagnant()
	{
		String s = "?";
		Joueur j;
		int n;
		for(int i=0;i<joueurs.length;i++)
		{
			if(joueurs[i].getEnVie() == 'V')
			{
				j = joueurs[i]; 
				n = i+1;
				if(j instanceof JoueurHumain){s = "JOUEUR "+n+" A GAGN�!";}
				if(j instanceof JoueurOrdinateur){s = "ORDINATEUR A GAGN�!";}
			}
		}
		return(s);
	}
	
	
	
	/*RED�FINITION DE PAINTCOMPONENT*/
	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.fillRect(0,0,getWidth(), getHeight());
		int largeurTraitV = (int)(getWidth()/largeur_grille);
		int largeurTraitH = (int)(getHeight()/largeur_grille);
		
		if(joueurs != null)
		{   
	    //Dessiner les traces des joueurs.
			for(int i=0; i<joueurs.length; i++)
			{
				Segment seg = joueurs[i].getTrace().getFirst();
				Color c = joueurs[i].getTrace().getColor();
				while(seg != null)
				{
					Point a = seg.getFirst();  //Premier Point du Segment.
					Point b = seg.getLast();   //Dernier Point du Segment.
					//Changement d'�chelle
					int positionPixelX = a.getX()*largeurTraitH;
					int positionPixelY = a.getY()*largeurTraitV;
					int width = b.getX()*largeurTraitV - a.getX()*largeurTraitV;
					int height = b.getY()*largeurTraitH - a.getY()*largeurTraitH;
					//D�terminer la couleur
					g.setColor(c);
					//Ajustements
					if(width == 0){width = largeurTraitV;}
					if(height == 0){height = largeurTraitH;}
					if(seg.getDirection() == 'E'){width += largeurTraitV;}
					if(seg.getDirection() == 'S'){height += largeurTraitH;}
					//Dessiner
					g.fillRect(positionPixelX, positionPixelY, width , height);
					//Prochain Segment
					seg = seg.getNext();
				}
			}
		}
		//Dessiner l'enceinte.
		Segment seg = enceinte.getFirst();
		while(seg != null)
		{
			Point a = seg.getFirst();  
			Point b = seg.getLast();
			//Changement d'�chelle
			int positionPixelX = a.getX()*largeurTraitH;
			int positionPixelY = a.getY()*largeurTraitV;
			int width = b.getX()*largeurTraitV - a.getX()*largeurTraitV;
			int height = b.getY()*largeurTraitH - a.getY()*largeurTraitH;
			//D�terminer la couleur
			g.setColor(enceinte.getColor());
			//Ajustements
			if(width == 0){width = largeurTraitV;}
			if(height == 0){height = largeurTraitH;}
			if(seg.getDirection() == 'E'){width += largeurTraitV;}
			if(seg.getDirection() == 'S'){height += largeurTraitH;}
			//Dessiner
			g.fillRect(positionPixelX, positionPixelY, width , height);  
			//Prochain Segment
			seg = seg.getNext();
		}
		
	}
	
}