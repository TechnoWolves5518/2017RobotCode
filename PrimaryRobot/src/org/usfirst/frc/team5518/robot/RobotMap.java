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

	                                      
	final int JOYSTICK_PORT_NUMBER = 0;   
	
	
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
	public static int XBOX_RBUMBER = 6;
	public static int XBOX_LBUMBER = 5;
	public static int XBOX_LSTICK = 9;
	public static int XBOX_RSTICK = 10;
	public static int XBOX_START = 8;
	public static int XBOX_BACK = 7;
}
