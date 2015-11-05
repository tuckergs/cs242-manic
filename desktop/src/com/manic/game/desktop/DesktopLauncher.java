/*
 * 
 */

package com.manic.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.manic.game.Manic;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = Manic.TITLE;
		config.width = Manic.V_WIDTH * Manic.SCALE;
		config.height = Manic.V_HEIGHT * Manic.SCALE;
		
		new LwjglApplication(new Manic(), config);
	}
}
