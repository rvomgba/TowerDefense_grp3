package Modele;

public class Case {
	
	private boolean vide;

	private int P1x;
	private int P1y; 
	private int P2x; 
	private int P2y;
	
	public Case(boolean v , int p1x ,int p1y ,int p2x ,int p2y ){
		
		this.setVide(v);
		this.setP1x(p1x);
		this.setP1y(p1y);
		this.setP2x(p2x);
		this.setP2y(p2y);
		
	}
	
	public boolean isVide() {
		return vide;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
	}

	public int getP1x() {
		return P1x;
	}

	public void setP1x(int p1x) {
		P1x = p1x;
	}

	public int getP1y() {
		return P1y;
	}

	public void setP1y(int p1y) {
		P1y = p1y;
	}

	public int getP2x() {
		return P2x;
	}

	public void setP2x(int p2x) {
		P2x = p2x;
	}

	public int getP2y() {
		return P2y;
	}

	public void setP2y(int p2y) {
		P2y = p2y;
	}

	
}

