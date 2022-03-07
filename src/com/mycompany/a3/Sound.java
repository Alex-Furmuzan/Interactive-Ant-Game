package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Sound class to play the game sounds.
 * @author Alex Furmuzan
 *
 */
public class Sound {
	private Media m;
	
	public Sound(String filename) {
		if(Display.getInstance().getCurrent()== null) {
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" +filename);
			m = MediaManager.createMedia(is, "audio/wav");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays sound, sets time to 0.
	 */
	public void play() {
		m.setTime(0);
		m.play();
	}
	
}
