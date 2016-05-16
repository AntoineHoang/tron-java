
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** JoueurOrdinateur.java *************************************** */

import java.awt.Color;

/*
* La classe JoueurHumain est une sous-classe de la classe abstraite Joueur. Elle ajoute un contructeur et deux méthodes. 
*/

public class JoueurOrdinateur extends Joueur
{
	/* 
	* HÉRITÉ DE JOUEUR
	*
	* protected Trace t;
	* protected Color couleur;
	* protected char enVie;  //'V' vivant, 'M' mort
	* protected char direction_courante;   //'N' Nord, 'S' Sud, 'E' Est, 'O' Ouest 
	* protected char nouvelle_direction;
	*
	* public void deplacement()
	* public Trace getTrace()
	* public Color getColor()
	* public void setEnVie(char d)
	* public char getEnVie()
	*/
	
	/*************************************                CONSTRUCTEUR               *****************************************/
	
	/*
	* Le constructeur est appelée par la méthode newGame() de la classe Arene lors de la création d'une nouvelle partie afin 
	*  de créer le joueur ordinateur pour celle-ci.
	*/
	public JoueurOrdinateur(Point p, Color c, char d)  //(position de départ, couleur, direction)
	{
		t = new Trace(new Segment(p, d), d, c);
		couleur = c;
		enVie = 'V';
		direction_courante = d;
		nouvelle_direction = d;
	}
	
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*
	* La méthode choixDirection est appelée par la méthode tour() de la classe Arene a chaque tick du Timer.
	* Elle permet à l'ordinateur de choisir sa nouvelle direction. 
	* L'intelligence artificielle se contente de tourner dans le sans horaire lorsqu'elle détecte un mur sur le point suivant.
	*/
	public void choixDirection(Joueur[] joueurtab, Trace enceinte) 
	{
		if(checkColision(joueurtab, enceinte))  //Vérifier s'il y a risque de collision.
		{
			//Si oui, on doit changer de direction.
			if(direction_courante == 'N'){nouvelle_direction = 'O';}
			if(direction_courante == 'S'){nouvelle_direction = 'E';}
			if(direction_courante == 'E'){nouvelle_direction = 'N';}
			if(direction_courante == 'O'){nouvelle_direction = 'S';}
		}
	}
	
	/*
	* La méthode checkColision() est appelée par la méthode choixDirection(). Elle vérifie s'il y a un mur sur le prochain Point
	* supposé du trajet de l'ordinateur.
	*/
	private boolean checkColision(Joueur[] joueurtab, Trace enceinte)
	{
		Point p = t.nextPoint(direction_courante);   //Nous donne le prochain Point supposer.
		
		for(int i=0; i<joueurtab.length; i++)       //Vérifier si ce Point est dans l'une des Traces.
		{
			if(joueurtab[i].getTrace().contient(p))  
			{
				return true;
			}
		}
		if(enceinte.contient(p)){return true;}
		return false;	
	}
	
}





