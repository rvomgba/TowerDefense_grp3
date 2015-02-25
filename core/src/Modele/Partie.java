package Modele;

public class Partie {
	
	private int pointdevie = 20;
	private int Sous = 2000;
	private int income = 5;
	private int mana = 200;
	
	public Partie(){
		
	}
	
	public void DiminuerSous(int prix){
		this.Sous = this.Sous - prix;
	}
	
	public int getPointdevie() {
		return pointdevie;
	}

	public void setPointdevie(int pointdevie) {
		this.pointdevie = pointdevie;
	}

	public int getSous() {
		return Sous ;
	}

	public void setSous(int sous) {
		Sous = sous;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

}
