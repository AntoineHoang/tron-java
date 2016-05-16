
/* ************************* Devoir 2 Partie 2: Jeu de Tron local *************************
*	AUTEURS:
*		Nom: Rosalie Clavel
*		Matricule: 20041198
*
*		Nom: Antoine Hoang
*		Matricule: 982978
*
******************************** Tron.java *************************************** */

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;

/*
* La classe Tron contient le main. Elle genère le JFrame qui contiendra un TronPanel et le place au millieu de l'écran.
*/

public class Tron
{
	/****************************************                MAIN                ***********************************************/
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Jeu de Tron");
        TronPanel p = new TronPanel();
		frame.addWindowFocusListener(new WindowAdapter(){
			public void windowGainedFocus(WindowEvent e){
				p.getArene().requestFocusInWindow();
			}
		});
        frame.setContentPane(p);
        frame.pack(); 
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (screenSize.width - frame.getWidth()) / 2;
		int h = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(w,h); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}