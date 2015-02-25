package Modele.Tour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Tour1 extends Tour{

	public Tour1(int IndexCaseH, int IndexCaseL) {
		super(IndexCaseH, IndexCaseL);
		pointdevie = 10;
		prix = 100;
		dmg = 50;
		Income = 1;
		portee = 2;
		Tex = new Texture(Gdx.files.internal("TourTD.png"));
		Sp = new Sprite(Tex);
		
	}


	
	
}
