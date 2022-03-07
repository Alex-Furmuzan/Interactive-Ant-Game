package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the Exit command is invoked.
 * @author Alex Furmuzan
 *
 */
public class ExitCommand extends Command {
	
	public ExitCommand() {
		super("Exit");
	}

	
	public void actionPerformed(ActionEvent e)
    {
    	
        boolean bOk = Dialog.show("Confirm Quit", "Are you sure you want to quit?", "Ok", "Cancel");
        
        if (bOk) {
        	Display.getInstance().exitApplication();
        }
    }
}
