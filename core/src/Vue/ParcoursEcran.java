package Vue;

import java.util.Hashtable;

import Modele.Case;




public class ParcoursEcran {

	// Départ dans le jeu.
	Point Depart;
	Case TableauCase[][];
	// Directions.
	static final int DROITE = 0;
	static final int BAS = 1;	
	static final int HAUT = 2;
	static final int START = 3;
	// Direction courante.
	int direction;
	Hashtable chemin; 
	Hashtable cheminOK;
	int NumChemin =0;
	int NumMinHaut =0;
	int NumMinBas=0;
	int NumMinEnCours =0;
	
	public ParcoursEcran(Point positionActuelle,Case TabCase[][]) {
		Depart = new Point(positionActuelle.getL(),positionActuelle.getH());		
		TableauCase =TabCase;
		chemin =new Hashtable();
		cheminOK =new Hashtable();
	}
	public Point ChercheChemins() {
		// On part du principe que la demande du calcul des chemins
		// s'effectue par ce que l'on ne peut plus aller tout droit
		// est-ce que je peux monter ?
		if (versHaut(Depart)) {
			ParcoursLeChemin(Depart, ParcoursEcran.HAUT);
			NumMinHaut=NumMinEnCours;
		}
		// est-ce que je peux descendre
		if (versBas(Depart)) {
			// on incrémente le numero de chemin
			NumChemin = NumChemin+1;
			NumMinEnCours=0;
			ParcoursLeChemin(Depart, ParcoursEcran.BAS);
			NumMinBas =NumMinEnCours;
		}
		//analyse résultats
				
		if (NumMinHaut == 0){
			if (NumMinBas > 0){
				 // on part vers le bas
				   return new Point(Depart.getL(),Depart.getH()+1);
			}
		}
		if (NumMinBas == 0){
			if (NumMinHaut > 0){
				// on part vers le haut
				   return new Point(Depart.getL(),Depart.getH()-1);
			}
		}
		if ((NumMinHaut >0)&&(NumMinBas >0)){
			if (NumMinHaut > NumMinBas) {
				   // on part vers le bas
				   return new Point(Depart.getL(),Depart.getH()+1);
			   }else
			   {
				   // on part vers le haut
				   return new Point(Depart.getL(),Depart.getH()-1);
			   }
		}
		// sinon on bouge pas
		return Depart;
	}
	private boolean versDroite(Point P){
		Case unecase;
		if (P.getL()< 24){
			unecase= TableauCase[P.getH()] [P.getL()+1];
			if (unecase.isVide()== false) {
				return true;
			}			
		}
		return false;
	}
	private boolean versHaut(Point P){
		Case unecase;
		if (P.getH()> 0){
			unecase= TableauCase[P.getH()-1] [P.getL()];
			if (unecase.isVide()== false) {
				return true;
			}			
		}
		return false;
	}
	private boolean versBas(Point P){
		Case unecase;
		if (P.getH()< 19 ){
			unecase= TableauCase[P.getH()+1] [P.getL()];
			if (unecase.isVide()== false) {
				return true;
			}			
		}
		return false;
	}
	private void OnaTermine() {
		int NbrCase;
		cheminOK.put(NumChemin, "OK");
		NbrCase = (Integer) chemin.get(NumChemin);
		if ( NumMinEnCours== 0 ){
			NumMinEnCours= NbrCase;
		}else {
			if (NbrCase < NumMinEnCours){
				NumMinEnCours= NbrCase;
			}
		}
	}
	private boolean ParcoursLeChemin(Point positionActuelle,int Direction){
		
		Point NewPosition;
		int NbrCase;
		int NbrCaseAvantBifurcation=0;
		switch (Direction) {
		case ParcoursEcran.HAUT:
			  if (versDroite(positionActuelle)){
				  IncrementeChemin();
				  if (positionActuelle.getL()+1== 24){
						// on a terminé
					  OnaTermine();										
					  return true;
					}
				  NewPosition = new Point(positionActuelle.getL()+1,positionActuelle.getH());
				  NbrCaseAvantBifurcation = (Integer) chemin.get(NumChemin);
				  ParcoursLeChemin(NewPosition,ParcoursEcran.DROITE);
				  if (versHaut(positionActuelle)) {
					  // création d'un nouveau chemin					  
					  NumChemin= NumChemin+1;
					  chemin.put(NumChemin,NbrCaseAvantBifurcation);
					  NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()-1);
					  ParcoursLeChemin(NewPosition,ParcoursEcran.HAUT);
				  }				  				  
			  }else if (versHaut(positionActuelle)) {
				  IncrementeChemin();
				  NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()-1);
				  ParcoursLeChemin(NewPosition,ParcoursEcran.HAUT);
			  }			
			break;
		case ParcoursEcran.DROITE:
			if (versDroite(positionActuelle)){
				// on avnce simplement
				IncrementeChemin();
				if (positionActuelle.getL()+1== 24){
					// on a terminé
					OnaTermine();
					return true;
				}
				 NewPosition = new Point(positionActuelle.getL()+1,positionActuelle.getH());
				  ParcoursLeChemin(NewPosition,ParcoursEcran.DROITE);
				
			}else {
				if (versHaut(positionActuelle)) {
					NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()-1);
					IncrementeChemin();
					 NbrCaseAvantBifurcation = (Integer) chemin.get(NumChemin);
					  ParcoursLeChemin(NewPosition,ParcoursEcran.HAUT);					
					// si versbas possible nouveau chemin
					  if (versBas(positionActuelle)) {						  
						  NumChemin= NumChemin+1;
						  chemin.put(NumChemin,NbrCaseAvantBifurcation);
						  NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()+1);
						  ParcoursLeChemin(NewPosition,ParcoursEcran.BAS);
					  }
				}else if (versBas(positionActuelle)) {
					IncrementeChemin();
					 NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()+1);
					  ParcoursLeChemin(NewPosition,ParcoursEcran.BAS);
				}
			}
			
			break;
		case ParcoursEcran.BAS:
			 if (versDroite(positionActuelle)){
				  IncrementeChemin();
				  if (positionActuelle.getL()+1== 24){
						// on a terminé
					  OnaTermine();
						return true;
					}
				  NewPosition = new Point(positionActuelle.getL()+1,positionActuelle.getH());
				  NbrCaseAvantBifurcation = (Integer) chemin.get(NumChemin);
				  ParcoursLeChemin(NewPosition,ParcoursEcran.DROITE);
				  if (versBas(positionActuelle)) {
					  // création d'un nouveau chemin					  
					  NumChemin= NumChemin+1;
					  chemin.put(NumChemin,NbrCaseAvantBifurcation);
					  NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()+1);
					  ParcoursLeChemin(NewPosition,ParcoursEcran.BAS);
				  }				  				  
			  }else if (versBas(positionActuelle)) {
				  IncrementeChemin();
				  NewPosition = new Point(positionActuelle.getL(),positionActuelle.getH()+1);
				  ParcoursLeChemin(NewPosition,ParcoursEcran.BAS);
			  }			
			
			break;
		}
		return false;
	}
	private void IncrementeChemin() {
		int NbrCase;
		if (chemin.containsKey(NumChemin)) {
   			NbrCase = (Integer) chemin.get(NumChemin);
   			NbrCase= NbrCase +1;
   			chemin.put(NumChemin,NbrCase);
		
   		}else{
   			NbrCase= 1;
   			chemin.put(NumChemin,NbrCase);			
   		}
	}

	
}
