
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** JoueurHumain.java *************************************** */

import java.awt.Color;

/*
* La classe JoueurHumain est une sous-classe de la classe abstraite Joueur. Elle ajoute un contructeur et une méthode. 
*/

public class JoueurHumain extends Joueur
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
	*  de créer les joueurs pour celle-ci.
	*/
	public JoueurHumain(Point p, Color c, char d) //(position de départ, couleur, direction)
	{
		t = new Trace(new Segment(p, d), d, c); 
		couleur = c;
		enVie = 'V';
		direction_courante = d;
		nouvelle_direction = d;
	}
	
	/*************************************                MÉTHODE                *********************************************/
	
	/*MUTATEUR*/
	
	public void setNouvelle_direction(char d)
	{
		nouvelle_direction = d;
	}
}