
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** Point.java *************************************** */

/*
* La classe Point agit comme un noeud dans une liste chainée.
*/
public class Point  
{
	/*************************************                PROPRIÉTÉS               *********************************************/
	
	private int x;    //Position en x selon la grille virtuelle.
	private int y;    //Position en y selon la grille virtuelle.
	private Point suivant;   //Point suivant dans la liste.
	
	
	/*************************************                CONSTRUCTEURS               *****************************************/
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
		suivant = null;
	}
	
	
	/*************************************                MÉTHODES                *********************************************/
	
	/*ACCESSEURS*/
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Point getSuivant()
	{
		return suivant;
	}
	
	
	/*MUTATEUR*/
	
	public void setSuivant(Point p)
	{
		suivant = p;
	}
	
	
	/*AUTRE*/
	
	/*
	* La méthode egal() permet de savoir si le Point courant et un autre Point sont à la même position sur la grille virtuelle.
	* Elle est appelée par la méthode contient() de la classe Trace.
	*/
	public boolean egal(Point p)
	{
		return(p.getX() == this.x && p.getY() == this.y);
	}
}