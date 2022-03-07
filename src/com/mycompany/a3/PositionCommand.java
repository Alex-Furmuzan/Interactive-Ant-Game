package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Position command to handle when the user clicks an object.
 * @author Alex Furmuzan
 *
 */
public class PositionCommand extends Command{
	private GameWorld gw;
	
	public PositionCommand(GameWorld gw) {
        super("Position");
        this.gw = gw;
    }
	
	
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	if(gw.getPaused() == true) {
    		gw.allowPositioning();
    	}
    }
}
