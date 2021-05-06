package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GestionJeu {
	
	//Points de l'ordinateur
	private int _pointsOrdi;
	
	//Points du joueur
	private int _pointsJoueur;
	
	//Nombre choisi par le joueur
	private int _nombreJoueur;
	
	//Nombre choisi par l'ordinateur
	private int _nombreOrdi;
	
	//Maximum pour gagner
	private int _maxPointsGagnants;
	
	//générateur de nombre aléatoire
	private Random _alea; 



    
	///////////////////////////////////////////////////////////////////////////////////////////////
    //Constructeur
	///////////////////////////////////////////////////////////////////////////////////////////////
	public GestionJeu() {
    	this._maxPointsGagnants = 10;
    	this._nombreJoueur = 0;
    	this._nombreOrdi = 0;
    	this._pointsJoueur = 0;
    	this._pointsOrdi = 0;
    	this._alea = new Random();
    }
    
    
	///////////////////////////////////////////////////////////////////////////////////////////////
    //Les accesseurs/modifieurs de données membres
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the _pointsOrdi
	 */
	public int get_pointsOrdi() {
		return _pointsOrdi;
	}


	/**
	 * @param _pointsOrdi the _pointsOrdi to set
	 */
	public void set_pointsOrdi(int _pointsOrdi) {
		this._pointsOrdi = _pointsOrdi;
	}


	/**
	 * @return the _pointsJoueur
	 */
	public int get_pointsJoueur() {
		return _pointsJoueur;
	}


	/**
	 * @param _pointsJoueur the _pointsJoueur to set
	 */
	public void set_pointsJoueur(int _pointsJoueur) {
		this._pointsJoueur = _pointsJoueur;
	}


	/**
	 * @return the _nombreJoueur
	 */
	public int get_nombreJoueur() {
		return _nombreJoueur;
	}


	/**
	 * @param _nombreJoueur the _nombreJoueur to set
	 */
	public void set_nombreJoueur(int _nombreJoueur) {
		this._nombreJoueur = _nombreJoueur;
	}


	/**
	 * @return the _nombreOrdi
	 */
	public int get_nombreOrdi() {
		return _nombreOrdi;
	}


	/**
	 * @param _nombreOrdi the _nombreOrdi to set
	 */
	public void set_nombreOrdi(int _nombreOrdi) {
		this._nombreOrdi = _nombreOrdi;
	}


	/**
	 * @return the _maxPointsGagnants
	 */
	public int get_maxPointsGagnants() {
		return _maxPointsGagnants;
	}


	/**
	 * @param _maxPointsGagnants the _maxPointsGagnants to set
	 */
	public void set_maxPointsGagnants(int _maxPointsGagnants) {
		this._maxPointsGagnants = _maxPointsGagnants;
	}


	/**
	 * @return the _alea
	 */
	public Random get_alea() {
		return _alea;
	}


	/**
	 * @param _alea the _alea to set
	 */
	public void set_alea(Random _alea) {
		this._alea = _alea;
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////
    //Gestion du jeu
	///////////////////////////////////////////////////////////////////////////////////////////////


	public void Init() {
    	this._nombreJoueur = 0;
    	this._nombreOrdi = 0;
 		this._pointsJoueur = 0;
		this._pointsOrdi = 0;
    	this._alea = new Random();
	}

	public boolean Gagnant() {
		return ((this._pointsJoueur==_maxPointsGagnants) || (this._pointsOrdi==_maxPointsGagnants)); 
	}

	public boolean JoueurGagne() {
		return (this._pointsJoueur == this._maxPointsGagnants);
	}   

	public void ChoixOrdinateur() {
		this._nombreOrdi = this._alea.nextInt(3);		
	}


	public int CalculerPoints() {
		//mise à jour des points
		//retourne -1 si l'ordinateur gagne la manche
		//          0 si égalité
		//          1 si le joueur gagne la manche
		int res = 0;
		switch ((int)(Math.abs((double)this._nombreJoueur - this._nombreOrdi)))
		{	
			case 2:
				if (this._nombreJoueur > this._nombreOrdi)
					{
						this._pointsJoueur++;
						res = 1;
					}
					else
					{
						this._pointsOrdi++;
						res = -1;
					}
					break;
			case 1:
				if (this._nombreJoueur < this._nombreOrdi)
					{
						this._pointsJoueur++;
						res = 1;
					}
					else
					{						
						this._pointsOrdi++;
						res = -1;
					}
					break;
		}
		return res;
				
	}

}

	
	