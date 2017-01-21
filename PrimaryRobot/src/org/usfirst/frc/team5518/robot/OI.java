package org.usfirst.frc.team5518.robot;/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	
	// Joystick stick = new Joystick(port);
	Joystick[] controller = new Joystick[]{
			new Joystick(RobotMap.XBOX_DRIVER),
			new Joystick(RobotMap.XBOX_SPECIAL_FUNCTION)
	};
	
	// Button button = new JoystickButton(stick, buttonNumber);
	Button[] driverButtons = new JoystickButton[] {
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_YBTN),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_XBTN),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_ABTN),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_BBTN),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_LBUMPER),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_RBUMPER),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_LSTICK),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_RSTICK),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_START),
			new JoystickButton(controller[RobotMap.XBOX_DRIVER], RobotMap.XBOX_BACK)
	};
	
	Button[] specialFunctionButtons = new JoystickButton[] {
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_YBTN),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_XBTN),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_ABTN),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_BBTN),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_LBUMPER),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_RBUMPER),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_LSTICK),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_RSTICK),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_START),
			new JoystickButton(controller[RobotMap.XBOX_SPECIAL_FUNCTION], RobotMap.XBOX_BACK)
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
	public double getAxis(int controllerIndex, int axis) {
		double rawAxis = 0;
		rawAxis = controller[controllerIndex].getRawAxis(axis);
		
		return rawAxis;
	}
	
	/**
	 * 	returns button boolean value from specified controller
	 * 
	 * 	controllerIndex 0 is DRIVER controller
	 *  controllerIndex 1 is SPECIAL FUNCTION controller
	 */
	public boolean getButton(int controllerIndex, int button) {
		boolean rawButton = controller[controllerIndex].getRawButton(button);
		
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
	public void setRumble(int controllerIndex, Joystick.RumbleType type, float value) {
		controller[controllerIndex].setRumble(type, value);
	}
	
	/**
	 * checks if a controller is an Xbox controller
	 */
	public boolean isXboxController(int controllerIndex){
		return controller[controllerIndex].getIsXbox();
	}
}