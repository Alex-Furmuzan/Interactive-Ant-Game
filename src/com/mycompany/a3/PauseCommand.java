package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Pause Command to handle when the user pauses.
 * @author Alex Furmuzan
 *
 */
public class PauseCommand extends Command {
	private Game game;
	
	public PauseCommand(Game game2) {
		
		super("Pause  ");
		this.game = game2;
	}
	public void actionPerformed(ActionEvent e) {
		game.pressPause();
	}
}
