package Modele;


public class Terrain {
	
	int DebPx;
	int DebPy;
	int FinPx;
	int FinPy;
	int nbCaseLong;
	int nbCaseHaut;
	public Case TabCase[][];
   // 0, 90, 750, 700, 25, 20
	public Terrain(int Debx, int Deby, int Finx, int Finy, int nbCaseL , int nbCaseH){
		int longu = ((Finx-Debx)/nbCaseL);
		int haut = ((Finy-Deby)/nbCaseH);
		TabCase = new Case [nbCaseH+1][nbCaseL+1];
		for(int H = 0 ; H < nbCaseH+1 ; H ++ ){

			for(int L = 0 ; L < nbCaseL ; L ++ ){
				int P1x = ((L)*longu);
				int P1y = ((H)*haut);
			    int P2x = ((L+1)*longu);
				int P2y = ((H+1)*haut);
				TabCase[H][L] = new Case(false, P1x, P1y+30, P2x, P2y);

			}
		}
		this.setNbCaseHaut(nbCaseH);
		this.setNbCaseLong(nbCaseL);
	}
	/*public void AfficherCase(){
		for(int H = 0 ; H < nbCaseHaut ; H ++ ){
			for(int L = 0 ; L < nbCaseLong-1 ; L ++ ){
				System.out.println(TabCase[H][L].getP1x()+"/"+TabCase[H][L].getP1y());
				System.out.println(TabCase[H][L].getP2x()+"/"+TabCase[H][L].getP2y());
			}
		}
	}*/
	
	public int getDebPx() {
		return DebPx;
	}
	public void setDebPx(int debPx) {
		DebPx = debPx;
	}
	public int getDebPy() {
		return DebPy;
	}
	public void setDebPy(int debPy) {
		DebPy = debPy;
	}
	public int getFinPx() {
		return FinPx;
	}
	public void setFinPx(int finPx) {
		FinPx = finPx;
	}
	public int getFinPy() {
		return FinPy;
	}
	public void setFinPy(int finPy) {
		FinPy = finPy;
	}
	public int getNbCaseLong() {
		return nbCaseLong;
	}

	public void setNbCaseLong(int nbCaseLong) {
		this.nbCaseLong = nbCaseLong;
	}

	public int getNbCaseHaut() {
		return nbCaseHaut;
	}

	public void setNbCaseHaut(int nbCaseHaut) {
		this.nbCaseHaut = nbCaseHaut;
	}
	
	public Case[][] getTabCase() {
		return TabCase;
	}
	
	public void setTabCase(Case[][] tabCase) {
		TabCase = tabCase;
	}

	
	}
