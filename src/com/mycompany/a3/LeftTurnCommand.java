package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the Left Turn command is invoked.
 * @author Alex Furmuzan
 *
 */
public class LeftTurnCommand extends Command{

	private GameWorld gw;
	
	
	public LeftTurnCommand(GameWorld gw) {
		super("Turn Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.leftTurn();
		System.out.println("Left Turn command is invoked");
	}
	
	
	
}
