package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Jeu {

	public static void main(String[] args) throws Exception {
		boolean fin, quitterJeu;
		String reponse, texte;
		
		//pour gérer le jeu
		GestionJeu jeu = new GestionJeu();
		
		quitterJeu = false;
		do
		{	//initialisation des points
			jeu.Init();
			
			fin = false;
			do
			{ //saisie du nombre du joueur: 0, 1, 2 ou 3
				do
				{  //le joueur donne un nombre
					System.out.println("Votre nombre: ");
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					texte = in.readLine();
					jeu.set_nombreJoueur(Integer.parseInt(texte));
				}while ((texte.length() != 1) || (jeu.get_nombreJoueur() <0) || (jeu.get_nombreJoueur()>3));
				if (jeu.get_nombreJoueur() == 3)
					//le joueur a donné 3: il faut arrêter le jeu
					fin = true;
				else
				{  //choix aléatoire du nombre de l'ordinateur : 0, 1 ou 2
					jeu.ChoixOrdinateur();
					System.out.println("nbord = " + jeu.get_nombreOrdi());
					jeu.CalculerPoints();
					
					System.out.println(" ptsj = "+ jeu.get_pointsJoueur() + " et ptsord = " + jeu.get_pointsOrdi());
				}
			}
			while (!jeu.Gagnant() && (!fin));

			//le jeu s'arrête: affichage du résultat
			if (! fin) 
				if (jeu.JoueurGagne())
					System.out.println("Bravo, vous avez atteint 10 points");
				else
					System.out.println("L'ordinateur a atteint 10 points");
			else if (jeu.get_pointsJoueur() < jeu.get_pointsOrdi()) 
				System.out.println("L'ordinateur a plus de points que vous!");
			else if (jeu.get_pointsJoueur() > jeu.get_pointsOrdi()) 
				System.out.println("Bravo, vous avez plus de points que l'ordinateur!");
			else
				System.out.println("Egalité!");
		    
			//on demande au joueur s'il veut faire une autre partie
			System.out.println("Voulez-vous rejouer (o pour oui)?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			reponse = in.readLine();
		    quitterJeu = (reponse.compareTo("o") != 0);
		}
		while (!quitterJeu);
	}

}
