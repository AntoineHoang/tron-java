
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** Segment.java *************************************** */

/*
* La classe Segment agit à la fois comme une liste chainée de Point et comme un noeud pour Trace.
* Un Segment correspond à une liste de Points allant dans une même direction.
*/
public class Segment   
{ 
	
	/*************************************                PROPRIÉTÉS               *********************************************/
	
	private Point debut;   //Début du Segment
	private Point fin;     //Fin du Segment
	private Segment next;
	private char direction;  //Direction: 'N' Nord, 'S' Sud, 'E' Est, 'O' Ouest
	
	
	/*************************************                CONSTRUCTEURS               *****************************************/
	
	public Segment(Point a, char d)  //Un Point et une direction. Utile pour le début d'une Trace.
	{
		debut = a;
		fin = a;
		next = null;
		direction = d;
	}
	
	public Segment(Point a, Point b, char d)  //Deux Points et une direction.
	{
		debut = a;
		fin = b;
		next = null;
		a.setSuivant(b);
		direction = d;
	}
	
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*ACCESSEURS*/
	
	public Point getFirst()
	{
		return debut;
	}
	
	public Point getLast()
	{
		return fin;
	}
	
	public Segment getNext()
	{
		return next;
	}
	
	public char getDirection()
	{
		return direction;
	}
	
	
	/*MUTATEUR*/
	
	public void setNext(Segment s)
	{
		next = s;
	}
	
	/*AUTRE*/
	
	//Ajoute un Point à la fin.
	public void append(Point p)
	{
		fin.setSuivant(p);    //lier les points
		fin = p;
	}
	
}