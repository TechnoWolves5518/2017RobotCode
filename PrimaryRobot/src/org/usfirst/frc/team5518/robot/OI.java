package org.usfirst.frc.team5518.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	
	// Joystick stick = new Joystick(port); 0 is assigned to Driver Controller, 1 is to Special Functions
	
//	public static Joystick[] controller = new Joystick[]{
//			new Joystick(RobotMap.XBOX_DRIVER),
//			new Joystick(RobotMap.XBOX_SPECIAL_FUNCTION)
//	};
	
	public OI()
	{
		//driverButtons[4].whenPressed(new InvertMotors());
	}
	
	public static Joystick driveController = new Joystick(RobotMap.XBOX_DRIVER);
	public static Joystick sfController = new Joystick(RobotMap.XBOX_SPECIAL_FUNCTION);
	
	// Button button = new JoystickButton(stick, buttonNumber);
	public static Button[] driverButtons = new JoystickButton[] {
			new JoystickButton(driveController, RobotMap.XBOX_YBTN),
			new JoystickButton(driveController, RobotMap.XBOX_XBTN),
			new JoystickButton(driveController, RobotMap.XBOX_ABTN),
			new JoystickButton(driveController, RobotMap.XBOX_BBTN),
			new JoystickButton(driveController, RobotMap.XBOX_LBUMPER),
			new JoystickButton(driveController, RobotMap.XBOX_RBUMPER),
			new JoystickButton(driveController, RobotMap.XBOX_LSTICK),
			new JoystickButton(driveController, RobotMap.XBOX_RSTICK),
			new JoystickButton(driveController, RobotMap.XBOX_START),
			new JoystickButton(driveController, RobotMap.XBOX_BACK)
	};
	
	public static Button[] specialFunctionButtons = new JoystickButton[] {
			new JoystickButton(sfController, RobotMap.XBOX_YBTN),
			new JoystickButton(sfController, RobotMap.XBOX_XBTN),
			new JoystickButton(sfController, RobotMap.XBOX_ABTN),
			new JoystickButton(sfController, RobotMap.XBOX_BBTN),
			new JoystickButton(sfController, RobotMap.XBOX_LBUMPER),
			new JoystickButton(sfController, RobotMap.XBOX_RBUMPER),
			new JoystickButton(sfController, RobotMap.XBOX_LSTICK),
			new JoystickButton(sfController, RobotMap.XBOX_RSTICK),
			new JoystickButton(sfController, RobotMap.XBOX_START),
			new JoystickButton(sfController, RobotMap.XBOX_BACK)
	};

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	/**
	 * 	returns axis double value from specified controller
	 * 
	 * 	controllerIndex 0 is DRIVER controller
	 *  controllerIndex 1 is SPECIAL FUNCTION controller
	 */
//	static public double getAxis(Joystick passedController, int axis) {
//		double rawAxis = 0;
//		rawAxis = passedController.getRawAxis(axis);
//		
//		return rawAxis;
//	}
	
	/**
	 * 	returns button boolean value from specified controller
	 * 
	 * 	controllerIndex 0 is DRIVER controller
	 *  controllerIndex 1 is SPECIAL FUNCTION controller
	 */
	public static boolean getButton(Joystick passedController, int button) {
		boolean rawButton = passedController.getRawButton(button);
		
		return rawButton;
	}
	
	/**
	 * activate rumble motors on Xbox controller
	 * 
	 * controllerIndex 0 is DRIVER controller
	 * controllerIndex 1 is SPECIAL FUNCTION controller
	 * 
	 * @param type left or right rumble motor
	 * @param value rumble motor strength (0 to 1)
	 */
	public void setRumble(Joystick passedController, Joystick.RumbleType type, float value) {
		passedController.setRumble(type, value);
	}
	
	/**
	 * checks if a controller is an Xbox controller
	 */
	public boolean isXboxController(Joystick passedController){
		return passedController.getIsXbox();
	}
}