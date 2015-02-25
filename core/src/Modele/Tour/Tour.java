package Modele.Tour;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public abstract class Tour {

	protected int pointdevie;
	protected int prix;
	protected int dmg;
	protected int Income;
	protected int IndexCaseH;
	protected int IndexCaseL;
	protected boolean AttaqueEnCour;
	protected int cible;
	protected int level;
	protected int portee;
	protected Sprite Sp;
	protected Texture Tex;

	public Tour(int IndexCaseH, int IndexCaseL){
		this.IndexCaseH = IndexCaseH;
		this.IndexCaseL = IndexCaseL;
		AttaqueEnCour = false;
	}
	
	public void upgrade() {
		if (level < 3) {
			pointdevie *= 2;
			prix *= 2;
			dmg *= 2;
			Income *= 2;
		}
		level++;
	}

	
	public int getIndexCaseH() {
		return IndexCaseH;
	}

	public void setIndexCaseH(int indexCaseH) {
		IndexCaseH = indexCaseH;
	}

	public int getIndexCaseL() {
		return IndexCaseL;
	}

	public void setIndexCaseL(int indexCaseL) {
		IndexCaseL = indexCaseL;
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

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public int getDmg() {
		return dmg;
	}
	public int getIncome() {
		return Income;
	}

	public void setIncome(int income) {
		Income = income;
	}

	public boolean isAttaqueEnCour() {
		return AttaqueEnCour;
	}

	public void setAttaqueEnCour(boolean attaqueEnCour) {
		AttaqueEnCour = attaqueEnCour;
	}
	
	public int getCible() {
		return cible;
	}

	public void setCible(int cible) {
		this.cible = cible;
	}
	public Sprite getSp() {
		return Sp;
	}

	public void setSp(Sprite sp) {
		Sp = sp;
	}

	public Texture getTex() {
		return Tex;
	}

	public void setTex(Texture tex) {
		Tex = tex;
	}




}
