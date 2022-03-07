package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
/**
 * This class holds the logic when the About command is invoked.
 * @author Alex Furmuzan
 *
 */
public class AboutCommand extends Command {
	
	public AboutCommand() {
		super("About");
	}
	
    public void actionPerformed(ActionEvent e)
    {
    	String result = "";
    	
    	result = "Name: Alex Furmuzan " +
				"Course: CSC 133 " +
				"Version: 3.0";
        Dialog.show("About", result, "Ok", null);
    }
}
