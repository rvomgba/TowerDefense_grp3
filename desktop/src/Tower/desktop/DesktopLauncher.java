package Tower.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import Tower.TowerDefense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 690 ;
		config.width = 1000;
		config.resizable = false ;
		
		new LwjglApplication(new TowerDefense(), config);
	}
}
