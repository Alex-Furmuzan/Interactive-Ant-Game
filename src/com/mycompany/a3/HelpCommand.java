package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the Help command is invoked.
 * @author Alex Furmuzan
 *
 */
public class HelpCommand extends Command {
	public HelpCommand() {
        super("Help");
    }
	
	
    @Override
    public void actionPerformed(ActionEvent ev) {
		Command cOk = new Command("Ok");
		String result = "Happy to Help! You can press:\n "
				+ "'a' to Accelerate,\n'b' to Brake,\n"
				+ "'l' to turn left,\n or 'r' to turn right!";
		Dialog.show("Help", result, cOk);
		
	}
}
