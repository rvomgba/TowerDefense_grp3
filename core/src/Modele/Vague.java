package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Modele.Monstre.Monstre;
import Modele.Monstre.MonstrePuissant;
import Modele.Monstre.MonstreBasique;
import Vue.EcranJeu;

public class Vague {
	//CASEX et CASEY SONT INVERSï¿½ : EN REALITï¿½ CASEX EQUIVANT A LA CASE Y (trop facile sinon)!
	@SuppressWarnings("unused")
	private int nb;
	private int nbMax=30;
	private HashMap<Integer, ArrayList<Monstre>> vague;
	//Dï¿½part de la vague pour crï¿½er les monstre dans la arraylist.
	private int caseX = 0;
	private int caseY = 0;
	Random rand = new Random();

	public Vague() {
		vague = new HashMap<Integer, ArrayList<Monstre>>();
		nb = 1;
		createAllVague();
		
	}
	
	public void createAllVague() {
		ArrayList<Monstre> vague1 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague2 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague3 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague4 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague5 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague6 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague7 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague8 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague9 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague10 = new ArrayList<Monstre>();
		/*
		ArrayList<Monstre> vague11 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague12 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague13 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague14 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague15 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague16 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague17 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague18 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague19 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague20 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague21 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague22 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague23 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague24 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague25 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague26 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague27 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague28 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague29 = new ArrayList<Monstre>();
		ArrayList<Monstre> vague30 = new ArrayList<Monstre>();
		*/
		//de 0 ï¿½ 9 (boucle 10 fois)
		for (int i = 0; i < 10; i ++) {
			//10 monstres basiques
			int alea = rand.nextInt(19 - 1 + 1) + 1;
			caseX = alea;
			//caseX = 5;
			vague1.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			
			//10 basiques + 2 puissants
			if (i >= 8) {
				vague2.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
			}
			vague2.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			
			//10 basiques + 3 puissants
			if (i >= 7) {
				vague3.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
			}
			vague3.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			
			//15 basiques
			vague4.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			if (i >= 5) {
				vague4.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
				
				//5 puissants + 5 basiques
				vague5.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
				vague5.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			}
			
			//7 basiques + 7 puissants
			if (i >= 3) {
				vague6.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
				vague6.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			}
			
			//10 puissants
			vague7.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
			
			//8 puissants + 10 basiques
			vague8.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			if (i >= 2) {
				vague8.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
			}
			
			//20 basiques + 3 puissants
			if (i >= 7) {
				vague9.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
			}
			vague9.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			vague9.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			
			//10 puissants + 10 basiques
			vague10.add(new MonstreBasique(caseX,caseY,EcranJeu.donnerclé()));
			vague10.add(new MonstrePuissant(caseX,caseY,EcranJeu.donnerclé()));
			/*
			//10 + 10... etc etc. Pas le time pour la suite. A faire plus tard.
			vague11.add(new MonstrePuissant(caseX,caseY));
			vague11.add(new MonstreBasique(caseX,caseY));
			
			vague12.add(new MonstrePuissant(caseX,caseY));
			vague12.add(new MonstreBasique(caseX,caseY));
			
			vague13.add(new MonstreBasique(caseX,caseY));
			vague14.add(new MonstreBasique(caseX,caseY));
			vague15.add(new MonstreBasique(caseX,caseY));
			
			vague16.add(new MonstreBasique(caseX,caseY));
			vague17.add(new MonstreBasique(caseX,caseY));
			vague18.add(new MonstreBasique(caseX,caseY));
			vague19.add(new MonstreBasique(caseX,caseY));
			vague20.add(new MonstreBasique(caseX,caseY));
			vague21.add(new MonstreBasique(caseX,caseY));
			vague22.add(new MonstreBasique(caseX,caseY));
			vague23.add(new MonstreBasique(caseX,caseY));
			vague24.add(new MonstreBasique(caseX,caseY));
			vague25.add(new MonstreBasique(caseX,caseY));
			vague26.add(new MonstreBasique(caseX,caseY));
			vague27.add(new MonstreBasique(caseX,caseY));
			vague28.add(new MonstreBasique(caseX,caseY));
			vague29.add(new MonstreBasique(caseX,caseY));
			vague30.add(new MonstreBasique(caseX,caseY));
			*/
			vague.put(1, vague1);
			vague.put(2, vague2);
			vague.put(3, vague3);
			vague.put(4, vague4);
			vague.put(5, vague5);
			vague.put(6, vague6);
			vague.put(7, vague7);
			vague.put(8, vague8);
			vague.put(9, vague9);
			vague.put(10, vague10);
			/*
			vague.put(11, vague11);
			vague.put(12, vague12);
			vague.put(13, vague13);
			vague.put(14, vague14);
			vague.put(15, vague15);
			
			vague.put(16, vague16);
			vague.put(17, vague17);
			vague.put(18, vague18);
			vague.put(19, vague19);
			vague.put(20, vague20);
			vague.put(21, vague21);
			vague.put(22, vague22);
			vague.put(23, vague23);
			vague.put(24, vague24);
			vague.put(25, vague25);
			vague.put(26, vague26);
			vague.put(27, vague27);
			vague.put(28, vague28);
			vague.put(29, vague29);
			vague.put(30, vague30);
			*/
			
		}

	}

	public ArrayList<Monstre> getVague(int nbVague) {
		if (nbVague <= nbMax && nbVague >= 1) {
			return vague.get(nbVague);
		}
		else if (nbVague < 1) {
			return vague.get(1);
		}
		else {
			return vague.get(nbMax);
		}
	}
}
