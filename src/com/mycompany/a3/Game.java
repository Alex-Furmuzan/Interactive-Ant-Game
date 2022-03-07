package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;
/**
 * This Class creates the game world and plays the game. It takes care of all of the user input and its logic.
 * It also creates the UI for the containers, adds mapview and scoreview as observers of gameworld.
 * @author Alex Furmuzan
 */
public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private boolean isPaused;
	private UITimer timer;
	
	private AccelerateCommand menuAccelerate;
	private AccelerateCommand myAccCommand;
	private LeftTurnCommand leftTurn;
	private BrakeCommand brakeCommand;
	private RightTurnCommand rightTurn;
	private CheckBox soundCheckBox;
	private SoundCommand mySoundCommand;
	private PositionCommand posCmd;
	
	private Button pauseB;
	private Button buttonAcc;
	private Button buttonLeft;
	private Button brake;
	private Button buttonRight;
	private Button posButton;
	
	
	/**
	 * Constructor for Game and where we initialize the Game World and call play()
	 */
	public Game() {
		this.setLayout(new BorderLayout());
		isPaused = false;
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
		
		menu();
		leftContainer();
		bottomContainer();
		rightContainer(); 
		timer = new UITimer(this);
		timer.schedule(20, true, this);
		
		posCmd.setEnabled(false);
		posButton.setEnabled(false);
		gw.init();
		this.show();
		//gw.createSounds();
	}
	/**
	 * Sets up the menu items including accelerate, sound check box, about, exit, and help gui operations
	 */
	private void menu() {
		Toolbar tb = new Toolbar();
		this.setToolbar(tb);
		
		tb.setTitle("OnTarget Game");
		
		//accelerate to side menu
		menuAccelerate = new AccelerateCommand(gw);
		tb.addCommandToLeftSideMenu(menuAccelerate);
		
		//Sound command
		soundCheckBox = new CheckBox();
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		mySoundCommand = new SoundCommand(gw);
		soundCheckBox.setCommand(mySoundCommand);
		tb.addComponentToSideMenu(soundCheckBox);
		
		// about
		AboutCommand abtCommand = new AboutCommand();
		tb.addCommandToLeftSideMenu(abtCommand);
		// exit
		ExitCommand exitCommand = new ExitCommand();
		tb.addCommandToLeftSideMenu(exitCommand);
		
		// help
		HelpCommand helpCommand = new HelpCommand();
		tb.addCommandToRightBar(helpCommand);
		
	}
	/**
	 * Sets up the WEST container adding an accelerate and left turn button
	 */
	private void leftContainer() {
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

		// MAKING ACCELERATE BUTTON\
		myAccCommand = new AccelerateCommand(gw);
		buttonAcc = new Button(myAccCommand);
		buttonAcc = styleButton(buttonAcc);
		leftContainer.add(buttonAcc);
		addKeyListener('a', myAccCommand);
		buttonAcc.getAllStyles().setMarginTop(100);
		
		// MAKING LEFT TURN BUTTON
		
		leftTurn = new LeftTurnCommand(gw);
		buttonLeft = new Button(leftTurn);
		buttonLeft = styleButton(buttonLeft);
		leftContainer.add(buttonLeft);
		addKeyListener('l', leftTurn);
		

		add(BorderLayout.WEST, leftContainer);

	}
	/**
	 * Sets up the EAST container adding brake and left turn buttons.
	 */
	private void rightContainer() {
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

		// MAKING Brake BUTTON
		brakeCommand = new BrakeCommand(gw);
		brake = new Button(brakeCommand);
		brake = styleButton(brake);
		rightContainer.add(brake);
		addKeyListener('b', brakeCommand);
		brake.getAllStyles().setMarginTop(100);
		
		// MAKING RIGHT TURN BUTTON
		
		rightTurn = new RightTurnCommand(gw);
		buttonRight = new Button(rightTurn);
		buttonRight = styleButton(buttonRight);
		rightContainer.add(buttonRight);
		addKeyListener('r', rightTurn);
		

		add(BorderLayout.EAST, rightContainer);

	}
	
	/**
	 * Sets up the SOUTH container adding collide with flag, collide with spider,
	 * collide with food station, and clock tick buttons.
	 */
	private void bottomContainer() {
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		// making collide with flag button
		
		PauseCommand pause = new PauseCommand(this);
		pauseB = new Button(pause);
		pauseB.getAllStyles().setMarginLeft(850);
		pauseB = styleButton(pauseB);
		bottomContainer.add(pauseB);
		
		posCmd = new PositionCommand(gw);
		posButton = new Button(posCmd);
		posButton = styleButton(posButton);
		bottomContainer.add(posButton);
		

		add(BorderLayout.SOUTH, bottomContainer);
		
	}
	
	/**
	 * Styles a button and returns it back styled.
	 * 
	 * @param myB
	 * @return myB the styled button
	 */
	private Button styleButton(Button myB) {
		myB.getAllStyles().setBgTransparency(255);
		myB.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		myB.getAllStyles().setFgColor(ColorUtil.WHITE);
		myB.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		myB.getAllStyles().setPadding(TOP, 5);
		myB.getAllStyles().setPadding(BOTTOM, 5);
		return myB;
	}
	
	/**
	 * Auto Ticks the clock.
	 */
	public void run() {
		gw.clockTick();
	}
	public void pressPause() {
		
		isPaused = !isPaused;
		if(isPaused == true) {
			timer.cancel();
			gw.setPaused(true);
			pauseB.setText("Play");
			//gw.getBGSound().pause();
			
			posCmd.setEnabled(true);
			posButton.setEnabled(true);
			
			mySoundCommand.setEnabled(false);
			soundCheckBox.setEnabled(false);
			
			
			removeKeyListener('a', myAccCommand);
			myAccCommand.setEnabled(false);
			buttonAcc.setEnabled(false);
			
			menuAccelerate.setEnabled(false);
			
			removeKeyListener('l', leftTurn);
			leftTurn.setEnabled(false);
			buttonLeft.setEnabled(false);
			
			
			removeKeyListener('b', brakeCommand);
			brakeCommand.setEnabled(false);
			brake.setEnabled(false);
			
			
			
			removeKeyListener('r', rightTurn);
			rightTurn.setEnabled(false);
			buttonRight.setEnabled(false);
			
		}
		else {
			timer.schedule(20, true, this);
			pauseB.setText("Pause");
//			if(gw.getSound() == true) {
//				gw.getBGSound().play();
//			}
			gw.setPaused(false);
			
			posCmd.setEnabled(false);
			posButton.setEnabled(false);
			mySoundCommand.setEnabled(true);
			soundCheckBox.setEnabled(true);
			
			menuAccelerate.setEnabled(true);
			
			
			addKeyListener('a', myAccCommand);
			myAccCommand.setEnabled(true);
			buttonAcc.setEnabled(true);
			
			addKeyListener('l', leftTurn);
			leftTurn.setEnabled(true);
			buttonLeft.setEnabled(true);
			
			addKeyListener('b', brakeCommand);
			brakeCommand.setEnabled(true);
			brake.setEnabled(true);
			
			addKeyListener('r', rightTurn);
			rightTurn.setEnabled(true);
			buttonRight.setEnabled(true);
			
		}
		
	}
}
