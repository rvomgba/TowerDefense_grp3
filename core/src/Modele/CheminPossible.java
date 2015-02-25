package Modele;

import java.util.ArrayList;

import Modele.Monstre.Monstre;

public class CheminPossible {
	private Terrain T;
	private Monstre Monstre;
	private ArrayList<Case> TabChemin;
	
	public CheminPossible(){
		
	}
	
	public void updateChemin(Terrain T,Monstre Monstre,ArrayList<Case> TabChemin){
		this.Monstre = Monstre;
		this.T = T;
		this.TabChemin = TabChemin;
	}
	
	
}
