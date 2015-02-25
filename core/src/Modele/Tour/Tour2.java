package Modele.Tour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tour2 extends Tour{

	public Tour2(int IndexCaseH, int IndexCaseL) {
		super(IndexCaseH, IndexCaseL);
		pointdevie = 10;
		prix = 350;
		dmg = 100;
		Income = 2;
		portee = 1;
		Tex = new Texture(Gdx.files.internal("2.png"));
		Sp = new Sprite(Tex);
	}
}
