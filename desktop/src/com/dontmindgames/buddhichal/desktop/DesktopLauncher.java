package com.dontmindgames.buddhichal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dontmindgames.buddhichal.BuddhiChal;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Buddhichal";
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new BuddhiChal(), config);
	}
}
