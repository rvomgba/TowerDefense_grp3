package Modele.Monstre;


public abstract class Monstre {
	
	protected int pointdevie;
	protected int prix;
	protected int dmg;
	protected int cout;
	protected int income;
	protected boolean Ciblable;
	protected int key;


	
	protected int IndexCaseH;
	protected int IndexCaseL;
	
	
	public Monstre(int IndexCaseH, int IndexCaseL , int key){
		this.IndexCaseH = IndexCaseH;
		this.IndexCaseL = IndexCaseL;		
		Ciblable = true;
		this.key = key;
		}	
	
	
	
	public int getIndexCaseH() {
		return IndexCaseH;
	}
	public void setIndexCaseH(int H){
		   this.IndexCaseH=H;
	   }
   public void setIndexCaseL(int L){
	   this.IndexCaseL=L;
   }
	public int getIndexCaseL() {
		return IndexCaseL;
	}


	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public void setCout(int cout) {
		this.cout = cout;
	}
	
	public void setIncome(int income){
		this.income = income;
	}
	public int getPointdevie() {
		return pointdevie;
	}

	public void setPointdevie(int pointdevie) {
		this.pointdevie = pointdevie;
	}

	public int getPrix() {
		return prix;
	}

	public int getDmg() {
		return dmg;
	}

	public int getCout() {
		return cout;
	}

	public int getIncome() {
		return income;
	}

	public void remove() {		
		
	}
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	
	/*
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public abstract void chercherChemin(Position destination);//algo de pathfinding appel� a chaque ajout ou destruction de tour, remplissant la liste chemin vers un joueur ou une tour
	
	public void avancer(){
		//M�thode appel�e lors du tick du timer pour faire avancer le monstre d'une case
		if (!(this.chemin.size()==1)){//Si il n'a pas atteint sa destination
			this.position = this.chemin.get(0);
			this.chemin.remove(this.chemin.get(0));
		} else {//si il a atteint sa destination(joueur ou tour)
			attaquer(this.chemin.get(0));
		}
	}
	
	public abstract void attaquer(Position cible);//Permet d'attaquer la cible(joueur ou tour) donn�e en parametre
	*/
}
