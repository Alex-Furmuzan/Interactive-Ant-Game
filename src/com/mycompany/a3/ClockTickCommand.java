package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * This class holds the logic when the Tick command is invoked.
 * @author Alex Furmuzan
 *
 */
public class ClockTickCommand extends Command{
	private GameWorld gw;
	
	public ClockTickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.clockTick();
		System.out.println("Clock tick command invoked");
	}

}
