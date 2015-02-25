package Tower;

import com.badlogic.gdx.Game;
import Vue.EcranPrincipal;

public class TowerDefense extends Game  {  
		
	@Override
	public void create () {
		setScreen(new EcranPrincipal(this));
		
	}

}


