
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** Joueur.java *************************************** */

import java.awt.Color;

/*
* La classe Joueur est une classe abstraite qui définit les propriétés et les méthodes communes aux classes JoueurHumain et 
* JoueurOrdinateur.
*/

abstract class Joueur
{
	/*************************************                PROPRIÉTÉS               *********************************************/
	
	protected Trace t;
	protected Color couleur;
	protected char enVie;  //'V' vivant, 'M' mort
	protected char direction_courante;   //'N' Nord, 'S' Sud, 'E' Est, 'O' Ouest 
	protected char nouvelle_direction;   //'N' Nord, 'S' Sud, 'E' Est, 'O' Ouest 
	
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*ACCESSEURS*/
	
	public Trace getTrace()
	{
		return t;
	}
	
	public Color getColor()
	{
		return couleur;
	}
	
	public char getEnVie()
	{
		return enVie;
	}
	
	
	/*MUTATEUR*/
	
	public void setEnVie(char d)
	{
		enVie = d;
	}
	
	
	/*AUTRES*/
	
	/*
	* La méthode deplacement() est appelée dans la méthode tour() de la classe Arene qui est elle-même appelée à chaque tick du Timer.
	*/
	public void deplacement()
	{
		if(enVie == 'V')   //On bouge uniquement si le joueur est en vie.
		{
			//On vérifie que l'utilisateur n'a pas entrer une touche contraire à sa direction actuelle.
			if( (direction_courante == 'N' && nouvelle_direction != 'S') ||  
				(direction_courante == 'S' && nouvelle_direction != 'N') ||
				(direction_courante == 'O' && nouvelle_direction != 'E') ||
				(direction_courante == 'E' && nouvelle_direction != 'O')   )
			{
				direction_courante = nouvelle_direction;  //On met à jour la direction.
			}
			t.allonge(direction_courante);    //Allonger la trace de un Point.
		}	
	}
}