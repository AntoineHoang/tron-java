
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** Trace.java *************************************** */

import java.awt.Color;

/*
*La classe Trace est une liste chainée de Segments(les noeuds).
* Elle représente le trajet emprunté par un personnage et par l'enceinte.
*/
public class Trace  
{ 
	
	/*************************************                PROPRIÉTÉS               *********************************************/
	
	private Segment debut;
	private Segment fin;
	private char direction;
	private Color couleur;
	
	
	/*************************************                CONSTRUCTEUR               *****************************************/
	
	public Trace(Segment s, char d, Color c) //Un Segment, une direction et une couleur
	{
		debut = s;
		fin = s;
		direction = d;
		couleur = c;
	}
	
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*ACCESSEURS*/
	
	public Segment getFirst()
	{
		return debut;
	}
	
	public Segment getLast()
	{
		return fin;
	}
	
	public Color getColor()
	{
		return couleur;
	}
	
	public Point tete()
	{
		return fin.getLast();
	}
	
	
	/*MUTATEUR*/
	
	public void setColor(Color c)
	{
		couleur = c;
	}
	
	
	/*AUTRES*/
	
	/*
	* La méthode allonge() permet d'ajouter un Point dans la Trace selon une direction donnée.
	* Elle est appelée par la méthode creerEnceinte() de la classe Arene lors de l'initialisation du mur de l'arène de jeu.
	* Et également par la méthode deplacement() de la classe Joueur.
	*/
	public void allonge(char d)  // d = une direction
	{
		Point nouveau = nextPoint(d);
		
		if(d == direction) //Si l'on va déjà dans cette direction.
		{
			fin.append(nouveau);   //Ajouter un point à la Trace.
		}
		else               //Sinon
		{
			direction = d;  //Changer la direction de la Trace.
			Segment newseg = new Segment(fin.getLast(),nouveau , d); //Créer un nouveau Segment
			fin.setNext(newseg);    //L'ajouter a la suite de la Trace.
			fin = newseg;          //Modifier notre fin de trace
		}
	}
	
	/*
	* La méthode nextPoint() permet de déterminer quel serait le prochain Point d'une Trace.
	* Cette méthode est utiliséé par la méthode checkColision() de la classe JoueurOrdinateur. 
	*/
	public Point nextPoint(char d)
	{
		int positionX = fin.getLast().getX();  //Position actuelle
		int positionY = fin.getLast().getY();
		
		switch(d)     //Calculer la position du prochain Point
		{
			case 'N':
				positionY --;
				break;
			case 'S':
				positionY ++;
				break;
			case 'E':
				positionX ++;
				break;
			case 'O':
				positionX --;
				break;
		}
		
		Point nouveau = new Point(positionX, positionY);  //Créer le Point
		return nouveau;
	}
	
	/*
	* La méthode contient() permet de déterminer si un Point précis est dans la Trace. (selon ses coordonnées virtuelles)
	*/
	public boolean contient(Point p)
	{
		Segment seg = debut;
		Point pt = seg.getFirst();
		while(pt.getSuivant() != null) //On ne vérifie pas la tête de la Trace. Car sinon le joueur meurt dans sa propre tête de Trace.
		{
			if(pt.egal(p))
			{
				return true;
			}
			pt = pt.getSuivant();
		}
		return false;
	}
	
}