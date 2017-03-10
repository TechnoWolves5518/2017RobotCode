package org.usfirst.frc.team5518.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	//WingMan Attack 2 Joystick
	public static int WINGMAN_JOYSTICK = 0;
	
	//Joystick Axes Assignments
	public static int JOYSTICK_XAXIS = 0;
	public static int JOYSTICK_YAXIS = 1;
	public static int JOYSTICK_ZAXIS = 2;
	
	//Joystick Buttons Assignments
	public static int JOYSTICK_TRIGGERBUTTON = 1;
	public static int JOYSTICK_BTN_02 = 2;
	public static int JOYSTICK_BTN_03 = 3;
	public static int JOYSTICK_BTN_04 = 4;
	public static int JOYSTICK_BTN_05 = 5;
	public static int JOYSTICK_BTN_06 = 6;
	
	public static int FRONT_LEFT_PORT_NUMBER = 0;
	public static int FRONT_RIGHT_PORT_NUMBER = 1;
	public static int BACK_LEFT_PORT_NUMBER = 2;
	public static int BACK_RIGHT_PORT_NUMBER = 3;
	
	public static int INTAKE_PORT_NUMBER = 4;
	public static int SHOOTER_PORT_NUMBER = 5;
	public static int WINCH_PORT_NUMBER = 6;
	public static int LOAD_PORT_NUMBER = 7; // inhibitor
	
	// servo pwm ports
	public static int RIGHT_DOOR_SERVO = 8;
	public static int LEFT_DOOR_SERVO = 9;

	                                      
	final int JOYSTICK_PORT_NUMBER = 0;   
	
	// Joysticks USB port numbers for driver and special function operators
	public static int XBOX_DRIVER = 0;
	public static int XBOX_SPECIAL_FUNCTION = 1;
	
	// Xbox 360/One controller mapping
	// controller axis
	public static int XBOX_LSTICKX = 0;
	public static int XBOX_LSTICKY = 1; 
	public static int XBOX_RSTICKX = 4;
	public static int XBOX_RSTICKY = 5;
	public static int XBOX_LTRIGGER = 2;
	public static int XBOX_RTRIGGER = 3;
	
	// controller buttons
	public static int XBOX_YBTN = 4;
	public static int XBOX_XBTN = 3;
	public static int XBOX_ABTN = 1;
	public static int XBOX_BBTN = 2; 
	public static int XBOX_RBUMPER = 6;
	public static int XBOX_LBUMPER = 5;
	public static int XBOX_LSTICK = 9;
	public static int XBOX_RSTICK = 10;
	public static int XBOX_START = 8;
	public static int XBOX_BACK = 7;
	
	
	// AUTONOMOUS CONSTS
	//public static double FORWARD_TIME = 1507;
	public static double FORWARD_TIME = 3250;
	/* We need to travel 94 inches to reach the pivot point (LEFT+RIGHT ONLY)
	 * We will be moving at half speed (fast speed), half speed is 5.195 ft/s
	 * We converted 94 inches to feet.
	 * Then we divide this by the speed to calculate the time we want to travel at this speed
	 */
	
	// Turn time and speed values are based on trial and error
	// Modify these values based on results
	public static double TURN_TIME = 900;
	public static double TURN_SPEED = 0.5;
	//public static double TURN_SPEED = 0.6; //FOR TEST RIG
	
	public static double FAST_SPEED = 0.5;
	//public static double FAST_SPEED = 0.5;
	public static double MED_SPEED  = 0.43;
	//public static double MED_SPEED  = 0.35;
	public static double SLOW_SPEED = 0.3;
	//public static double SLOW_SPEED = 0.25;
	public static double SUPER_SLOW_SPEED = 0.2;
	//public static double SUPER_SLOW_SPEED = 0.1;
	public static double FULLSTOP   = 0;
	
	public static double FAST_DISTANCE = 56;  // inches
	public static double ULTRA_DISTANCE = 36;  // inches
	public static double SLOW_DISTANCE = 30;  // inches
	public static double STOP_DISTANCE = 6.5; // inches
}
