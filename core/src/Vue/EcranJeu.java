package Vue;

import java.util.ArrayList;
import java.util.Hashtable;

import Modele.Case;
import Modele.Partie;
import Modele.Terrain;
import Modele.Vague;
import Modele.Monstre.Monstre;
import Modele.Monstre.MonstreVolant;
import Modele.Tour.Tour;
import Modele.Tour.Tour1;
import Modele.Tour.Tour2;
import Tower.TowerDefense;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class EcranJeu implements Screen, InputProcessor {
	//CLE MONSTRE
	public static int key = 0;
	//VARIABLES
	
	private BitmapFont font;
	int Switcher = 1;
	
    Stage stage;
    TowerDefense game; 

    Vague vague = new Vague();
    ArrayList<Monstre> vagueInc;
    int cptVague = 0;
    boolean vagueStart = false;
    int compteurMob = 0;
	boolean amelioreTour = false;
	int tourH;
	int tourL;
  
	//TEXTURES
    Texture background;
    Texture btnUp,btnDown,btnChecked;
    Texture img;
    Texture textureTour1;
    Texture textureTour2;
    Texture textureMonstreBasic;
    Texture textureMonstrePuissant;
    Texture textureMonstreVolant;
    Texture textureNextVague;
    Texture textureUpgrade;
    
    //SPRITEBATCH
    SpriteBatch sb;
    
    //SPRITES
    Sprite spriteTour2;
    Sprite spriteMonstreBasic;
    Sprite spriteMonstrePuissant;
    Sprite spriteMonstreVolant;
    
    //SKINS
    Skin skin = new Skin(Gdx.files.internal("uiskin.json")); 
    Skin lblTower = new Skin(Gdx.files.internal("uiskin.json"));
    
    //LABELS
	Label descTour1 = new Label("Tour basique, peu couteuse.\nPrix: 100", lblTower);
	Label descTour2 = new Label("Tour puissante, couteuse.\nPrix: 350", lblTower);
	Label descUpgrade = new Label("Double la puissance. Prix: 2000", lblTower);
	
	//IMAGES
	Image btnTour1;
	Image btnTour2;
	Image nextVague;
	Image btnUpgrade;
	
	Timer Time = new Timer();
    Partie P = new Partie();
    Terrain T = new Terrain(0, 90, 750, 700, 25, 20);
    
    ArrayList<Tour> TabTour = new ArrayList<Tour>();
    ArrayList<Monstre> TabMonstre = new ArrayList<Monstre>();

    public EcranJeu(TowerDefense lancement) {
    	this.game = lancement;
    }
    Hashtable chemin; 
    Hashtable cheminOK;
    //SHOW OBLIGATOIR DANS UN SCREEN (REMPLACE CREATE)
    @Override
    public void show() {
    	
    	background = new Texture(Gdx.files.internal("Terrain.jpg"));
    		
    	stage = new Stage();
    	Gdx.input.setInputProcessor(this);
    		
 		sb = new SpriteBatch();
 		
 		textureTour1 = new Texture(Gdx.files.internal("TourTD.png"));
 		textureTour2 = new Texture(Gdx.files.internal("2.png"));
 		textureMonstreBasic = new Texture(Gdx.files.internal("Monstre2TD.png"));
 		textureMonstrePuissant = new Texture(Gdx.files.internal("monster2.png"));
 		textureMonstreVolant = new Texture(Gdx.files.internal("Monstre1TD.png"));
 		spriteTour2 = new Sprite(textureTour2);
 		spriteMonstreBasic = new Sprite(textureMonstreBasic);
 		spriteMonstrePuissant = new Sprite(textureMonstrePuissant);
 		spriteMonstreVolant = new Sprite(textureMonstreVolant);
      
 		btnTour1 = new Image(textureTour1);
 		btnTour1.setPosition(770, 550);
 		btnTour1.setHeight(30);
 		btnTour1.setWidth(30);
 		descTour1.setPosition(805, 545);
 		
 		btnTour2 = new Image(textureTour2);
 		btnTour2.setPosition(770, 515);
 		btnTour2.setHeight(30);
 		btnTour2.setWidth(30);
 		descTour2.setPosition(805, 510);
 		
 		textureNextVague = new Texture("LancerVague.png");
 		nextVague = new Image(textureNextVague);
 		nextVague.setPosition(850, 130);
 		
 		lblTower.getFont("default-font").setScale(0.80f);
 		
 		stage.addActor(btnTour1);
 		stage.addActor(btnTour2);
 		stage.addActor(descTour1);
 		stage.addActor(descTour2);
 		stage.addActor(nextVague);
 		
 		textureUpgrade = new Texture("upgrade.png");
		btnUpgrade = new Image(textureUpgrade);
		descUpgrade.setPosition(780, 240);
		btnUpgrade.setPosition(850, 210);
		descUpgrade.setVisible(false);
		btnUpgrade.setVisible(false);


		stage.addActor(descUpgrade);
		stage.addActor(btnUpgrade);
 		
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        
        Time.scheduleTask(new Task() {
    			@Override
    			public void run () {     				
    				AvancerMonstre();   				
    				AttaquerMonstre(); 				
    				if (vagueStart) {
    					vagueInc = vague.getVague(cptVague);
    					AjouterSousViaIncome();
    					TabMonstre.add(vagueInc.get(compteurMob));
    					compteurMob++;
    					if (compteurMob >= vagueInc.size() ) {
    						vagueStart = false;
    						compteurMob = 0;
    					}		
    					
    				}

    				}
    		},2,1);
       	
    }
    public static int donnerclé(){
    	key++;
		return key-1;
    	
    }
    
    // AVANCER DE 1 CASE LES MONSTRES
    public void AvancerMonstre(){
    	Point positionActuelle = new Point(0,0);
    	
    	for (int i=0 ; i < TabMonstre.size();i++){
				if(T.getNbCaseLong()-1 != TabMonstre.get(i).getIndexCaseL()){
					
					positionActuelle.setH(TabMonstre.get(i).getIndexCaseH());
					positionActuelle.setL(TabMonstre.get(i).getIndexCaseL());
					
					
					if (CalculNouvellePosition(positionActuelle,T.getNbCaseLong(),T.getNbCaseHaut(),T.TabCase)) {
						TabMonstre.get(i).setIndexCaseL(positionActuelle.getL());
						TabMonstre.get(i).setIndexCaseH(positionActuelle.getH());
					}
					
				}
				
				
    	}
    	    	
    }
    //T.  Case TabCase[][]
    // T.getNbCaseLong  (L)
    //  T.getNbCaseHaut() (H)
    private boolean CalculNouvellePosition(Point positionActuelle,int NbCaseLong,int NbCaseHaut,Case TabCase[][]) {
    	// on teste si la position + 1 est bonne
    	Case unecase= TabCase[positionActuelle.getH()] [positionActuelle.getL()+1];
    	if (unecase.isVide()== false) {
    		positionActuelle.setL(positionActuelle.getL()+1);  
    		return true;
    	}
    	
    	ParcoursEcran ParcourirEcran;
    	ParcourirEcran = new ParcoursEcran(positionActuelle,T.TabCase);
    	Point NewPoint = ParcourirEcran.ChercheChemins();
    	positionActuelle.setL(NewPoint.getL());
    	positionActuelle.setH(NewPoint.getH());
    	    	return true;
    }
    
    public boolean AttaquerMonstre(){


    	for (int i=0 ; i < TabTour.size();i++){
    		for (int j=0 ; j < TabMonstre.size();j++){

    			if(!TabTour.get(i).isAttaqueEnCour()){
    				if(TabTour.get(i).getIndexCaseH()-2 <= TabMonstre.get(j).getIndexCaseH() && TabTour.get(i).getIndexCaseH()+2 >= TabMonstre.get(j).getIndexCaseH()){
    					if(TabTour.get(i).getIndexCaseL()-2 <= TabMonstre.get(j).getIndexCaseL() && TabTour.get(i).getIndexCaseL()+2 >= TabMonstre.get(j).getIndexCaseL()){  
    						if(TabMonstre.get(j).getPointdevie()-TabTour.get(i).getDmg() > 0){						
    							TabMonstre.get(j).setPointdevie(TabMonstre.get(j).getPointdevie()-TabTour.get(i).getDmg());
    							TabTour.get(i).setCible(TabMonstre.get(j).getKey());
    							TabTour.get(i).setAttaqueEnCour(true);
    							System.out.println("ATTAQUE1 "+ TabMonstre.get(j).getPointdevie()+" / "+TabMonstre.get(j).getKey()+" / "+TabTour.get(i).getCible());
    						}
    						else{
    							for(int ab = 0 ; ab < TabTour.size() ; ab++){
    								if(TabTour.get(ab).getCible() == TabMonstre.get(j).getKey()){
    									TabTour.get(ab).setAttaqueEnCour(false);
    								}
    							}
    							System.out.println("ATTAQUE1 et destru "+ TabMonstre.get(j).getPointdevie()+" / "+TabMonstre.get(j).getKey()+" / "+TabTour.get(i).getCible());
    							TabTour.get(i).setAttaqueEnCour(false);
    							P.setSous(P.getSous()+TabMonstre.get(j).getPrix());
    							TabMonstre.remove(j);
    						}				
    					}
    					else{			
    					}
    				}
    				else{ 					
    				}
    			
    			}
    			else if(TabTour.get(i).isAttaqueEnCour()){
    				
    				int temp = TabTour.get(i).getCible();
    				
    				if (TabMonstre.get(j).getKey() == temp ){
    					
    					if(TabTour.get(i).getIndexCaseH()-2 <= TabMonstre.get(j).getIndexCaseH() && TabTour.get(i).getIndexCaseH()+2 >= TabMonstre.get(j).getIndexCaseH()){
    						if(TabTour.get(i).getIndexCaseL()-2 <= TabMonstre.get(j).getIndexCaseL() && TabTour.get(i).getIndexCaseL()+2 >= TabMonstre.get(j).getIndexCaseL()){ 
    							
    							if(TabMonstre.get(j).getPointdevie()-TabTour.get(i).getDmg() > 0){
    								TabMonstre.get(j).setPointdevie(TabMonstre.get(j).getPointdevie()-TabTour.get(i).getDmg());
    								System.out.println("ATTAQUE2 "+TabMonstre.get(j).getPointdevie()+" / "+TabMonstre.get(j).getKey()+" / "+TabTour.get(i).getCible());
    							}
    							
    							else if (TabMonstre.get(j).getPointdevie()-TabTour.get(i).getDmg() <= 0){
    								for(int ab = 0 ; ab < TabTour.size() ; ab++){
    									if(TabTour.get(ab).getCible() == temp){
    	    								TabTour.get(ab).setAttaqueEnCour(false);
    									}
    								}
    								System.out.println("ATTAQUE2 et destru"+TabMonstre.get(j).getPointdevie()+" / "+TabMonstre.get(j).getKey()+" / "+TabTour.get(i).getCible());
    								TabTour.get(i).setAttaqueEnCour(false);
    								P.setSous(P.getSous()+TabMonstre.get(j).getPrix());
    								TabMonstre.remove(j);
    							}	
    							else{
    								for(int ab = 0 ; ab < TabTour.size() ; ab++){
    									if(TabTour.get(ab).getCible() == temp){
    	    								TabTour.get(ab).setAttaqueEnCour(false);
    									}
    								}
    								System.out.println("ERREUR");
    								TabTour.get(i).setAttaqueEnCour(false);
    							}
    						}
    						else{
    							TabTour.get(i).setAttaqueEnCour(false);
    							System.out.println("HORS DE PORTEE");
    						}
    					}
    					else{
							TabTour.get(i).setAttaqueEnCour(false);
							System.out.println("HORS DE PORTEE");
						}

    				}
    			}
    		}
    	}
    	return false;
    }
    
    public void VerifVie(){
    	for (int i=0 ; i < TabMonstre.size();i++){
			for (int j=0 ; j < TabTour.size();j++){
				if (TabMonstre.get(i).getPointdevie() <= 0 && TabTour.get(j).getCible() == i){
					TabTour.get(j).setAttaqueEnCour(false);
				}				
			}
    	}
    }
    
    public void VerifAtta(){
    	for(int ab = 0 ; ab < TabTour.size() ; ab++){
	
			System.out.println(TabTour.get(ab).isAttaqueEnCour()+" / "+TabTour.get(ab).getCible());
			
		}
    }
    public void VerifMonstre(){
    	for(int ab = 0 ; ab < TabMonstre.size() ; ab++){
	
			System.out.println("AH"+TabMonstre.get(ab).getKey());
			
		}
    }
    
    public void VerifArriver(){
    	for (int i=0 ; i < TabMonstre.size();i++){
			if (T.getNbCaseLong()-1 == TabMonstre.get(i).getIndexCaseL()){
				if (P.getPointdevie() > 1){
					P.setPointdevie(P.getPointdevie()-1);
					TabMonstre.remove(TabMonstre.get(i));
				}
				else{
					game.setScreen(new EcranFin(game));
				}
			}	
    	}
    }
    
    
    // AJOUT SOUS INCOME
    public void AjouterSousViaIncome(){
    	P.setSous(P.getSous()+P.getIncome());
    }
    
    
    //DESSIN DES ECRITURE
    public void UpdateDonnees(SpriteBatch batch,BitmapFont font){ 
    	 font.draw(batch, " "+P.getSous(), 150,653);
    	 font.draw(batch, " "+P.getIncome(), 370,653);
    	 font.draw(batch, " "+P.getMana(), 545,653);
    	 font.draw(batch, " "+P.getPointdevie(), 745,653); 
    	 font.draw(batch, "Tours: ", 770, 600);
    	 font.draw(batch, "Lancer la vague suivante : ", 770,200);
    }
    
    //RENDER FLOAT OBLIGATOIRE (REMPLACE RENDER())
	@Override
	public void render(float delta) {
		
			VerifArriver();
			VerifVie();
	
			//REMISE A ZERO DE L'ECRAN
			stage.getBatch().begin();
			stage.getBatch().draw(background, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			stage.getBatch().end();        
			stage.draw();
			//System.out.println("Appel render") ;
			//DESSIN DES DONNEES
			sb.begin();
			this.UpdateDonnees(sb, font);
			sb.end();
			
			//DESSIN DES TOURS
			
			for (int i=0 ; i < TabTour.size();i++){
				if(TabTour.get(i).getClass().toString().equals("class Modele.Tour.Tour1")){
					int PointX = T.TabCase[(TabTour.get(i).getIndexCaseH())][TabTour.get(i).getIndexCaseL()].getP1x() ;
					int PointY = T.TabCase[(TabTour.get(i).getIndexCaseH())][TabTour.get(i).getIndexCaseL()].getP1y() ;
				
					sb.begin();
					TabTour.get(i).getSp().setPosition(PointX, 600-PointY );
					TabTour.get(i).getSp().draw(sb);
					sb.end();
				}
				else if(TabTour.get(i).getClass().toString().equals("class Modele.Tour.Tour2")){
					int PointX = T.TabCase[(TabTour.get(i).getIndexCaseH())][TabTour.get(i).getIndexCaseL()].getP1x() ;
					int PointY = T.TabCase[(TabTour.get(i).getIndexCaseH())][TabTour.get(i).getIndexCaseL()].getP1y() ;
				
					sb.begin();
					spriteTour2.setPosition(PointX, 600-PointY );
					spriteTour2.draw(sb);
					sb.end();
				}
			}
			
			//DESSIN DES MONSTRES
			
			for (int i=0 ; i < TabMonstre.size();i++){
				int PointX = T.TabCase[(TabMonstre.get(i).getIndexCaseH())][TabMonstre.get(i).getIndexCaseL()].getP1x() ;
				int PointY = T.TabCase[(TabMonstre.get(i).getIndexCaseH())][TabMonstre.get(i).getIndexCaseL()].getP1y() ;

				if(TabMonstre.get(i).getClass().toString().equals("class Modele.Monstre.MonstreVolant")){					
					sb.begin();
					spriteMonstreVolant.setPosition(PointX, 600-PointY );
					spriteMonstreVolant.draw(sb);
					sb.end();
				}
				else if(TabMonstre.get(i).getClass().toString().equals("class Modele.Monstre.MonstreBasique")) {
					sb.begin();
					spriteMonstreBasic.setPosition(PointX, 600-PointY );
					spriteMonstreBasic.draw(sb);
					sb.end();
				}
				else if(TabMonstre.get(i).getClass().toString().equals("class Modele.Monstre.MonstrePuissant")) {
					sb.begin();
					spriteMonstrePuissant.setPosition(PointX, 600-PointY );
					spriteMonstrePuissant.draw(sb);
					sb.end();
				}
				
			}			
		}

    //LIBERATION RESSOURCE
    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        btnUp.dispose();
        btnDown.dispose();
        btnChecked.dispose();
    }

    //QUAND CLICK (N'IMPORTE LEQUELS)
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	
    	
    	//VARIABLE POUR CASE
    	double X = screenX/30;
		int X1 = (new Double(X).intValue())*30;
		
		double Y = (screenY-60)/30;
		int Y1 = (new Double(Y).intValue())*30;
		
		if (amelioreTour && screenX >= 850 && screenX <= 880 && screenY >= 450 && screenY <= 480) {
			if(P.getSous() >= 2000) {
				int m = 0;
				while (tourH != TabTour.get(m).getIndexCaseH() && tourL != TabTour.get(m).getIndexCaseL() && m <= TabTour.size()) {
					
				}
				TabTour.get(m).upgrade();
				P.DiminuerSous(2000);
			}
		}
		
		amelioreTour = false;
		descUpgrade.setVisible(false);
		btnUpgrade.setVisible(false);
		
    	//PERMET DE CHANGER LA VALEUR DE SWITCHER QUI PERMETTRA D'EFFECTUER DIFFERENTES FONCTIONS CELON SA VALEUR
		//ICI CHANGER LORS D'UN CLICK SUR UN CERTAIN ENDROIT
    	if (screenX >= 770 && screenX <= 800 && screenY >= 110 && screenY <= 140){
    		Switcher = 2;
    	}
    	
    	if (screenX >= 770 && screenX <= 800 && screenY >= 145 && screenY <= 175){
    		Switcher = 3;
    	}
    	if (screenX >= 850 && screenX <= 880 && screenY >= 530 && screenY <= 560) {
    		if (!vagueStart) {
    			vagueStart = true;
        		cptVague++;
    		}
    	}
    	
		
    	for (int i=0 ; i < T.getNbCaseHaut()+1;i++){
    		for (int j=0 ; j < T.getNbCaseLong();j++){
    			if (T.TabCase[i][j].getP1x() == X1 && T.TabCase[i][j].getP1y() == Y1){
    				if(!T.TabCase[i][j].isVide()){
    					if (Switcher == 2){
    						Tour Temp = new Tour1(i, j);
    						if (Temp.getPrix() <= P.getSous()){
    							TabTour.add(Temp);
    							P.DiminuerSous(Temp.getPrix());
    							T.TabCase[i][j].setVide(true);
    							P.setIncome(P.getIncome()+Temp.getIncome());
    						}
    					}
    					if (Switcher == 3){
    						Tour Temp = new Tour2(i, j);
    						if (Temp.getPrix() <= P.getSous()){
    							TabTour.add(Temp);
    							P.DiminuerSous(Temp.getPrix());
    							T.TabCase[i][j].setVide(true);
    							P.setIncome(P.getIncome()+Temp.getIncome());
    						}
    					}
    					if (Switcher == 4){
    						Monstre Temp = new MonstreVolant(i, j, donnerclé());
    						TabMonstre.add(Temp);
    						
    						
    					}
    				}
    				else{
						amelioreTour = true;
						descUpgrade.setVisible(true);
						btnUpgrade.setVisible(true);
						tourH = i;
						tourL = j;
					}
    				
    			}
    		}
    	}
    	return true;
    }
    
    
    //QUAND APPUIE SUR TOUCHE
	@Override
	public boolean keyDown(int keycode) {
		//PERMET DE CHANGER LA VALEUR DE SWITCHER QUI PERMETTRA D'EFFECTUER DIFFERENTES FONCTIONS CELON SA VALEUR
		//ICI CHANGER LORS D'UN CLICK SUR UNE CERTAINE TOUCHE
		if (keycode == Input.Keys.A){
			Switcher = 2;
		}
		if (keycode == Input.Keys.Z){
			Switcher = 3;
		}
		if (keycode == Input.Keys.E){
			Switcher = 4;
		}
		if (keycode == Input.Keys.R){
			  Time.stop();     
		}
		if (keycode == Input.Keys.T){
			  Time.start();     
		}
		
		if (keycode == Input.Keys.ENTER){
			VerifMonstre();
			VerifAtta();
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	 
    @Override
    public void hide() {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void pause() {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void resume() {
        // TODO Auto-generated method stub
         
    }
 
 
}