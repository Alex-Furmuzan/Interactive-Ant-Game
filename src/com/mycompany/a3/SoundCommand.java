package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the Sound command is invoked.
 * @author Alex Furmuzan
 *
 */
public class SoundCommand extends Command{
	private GameWorld gw;
	
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.soundToggle();
		System.out.println("Sound command invoked");
	}
}
