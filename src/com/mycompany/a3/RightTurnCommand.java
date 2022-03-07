package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the Right command is invoked.
 * @author Alex Furmuzan
 *
 */
public class RightTurnCommand extends Command{
	
	private GameWorld gw;
	
	public RightTurnCommand(GameWorld gw) {
		super("Turn Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.rightTurn();
		System.out.println("Right Turn command is invoked");
	}
}
