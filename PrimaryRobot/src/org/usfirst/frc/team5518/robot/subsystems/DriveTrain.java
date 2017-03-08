package org.usfirst.frc.team5518.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

//import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.BasicDrive;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5518.robot.OI;
import org.usfirst.frc.team5518.robot.RetroTapePipeline;
import org.usfirst.frc.team5518.robot.Robot;

//import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	RobotDrive driveTrain;
	public VictorSP frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
	Joystick driveController, sfController;
	Joystick wingmanJoystick;
	public static boolean isInverted;
	public static boolean toggle;
	public VisionThread visionThread;
	private double centerWhole = 0.0;
	private double centerX = 0.0;
	private double centerX2 = 0.0;
	private final Object imgLock = new Object();
	
	public DriveTrain() {
		//System.out.println("DriveTrain()");
		driveController = OI.driveController;
		sfController = OI.sfController;
		wingmanJoystick = OI.wingmanController;
		isInverted = false;
		toggle = false;
		
		//Initialize motors to port numbers from RobotMap
		frontLeftMotor = new VictorSP(RobotMap.FRONT_LEFT_PORT_NUMBER);
		frontRightMotor = new VictorSP(RobotMap.FRONT_RIGHT_PORT_NUMBER);
		backLeftMotor = new VictorSP(RobotMap.BACK_LEFT_PORT_NUMBER);
		backRightMotor = new VictorSP(RobotMap.BACK_RIGHT_PORT_NUMBER);
		
    	//Enable the deadband elimination (the dead zone on the controller)
		frontLeftMotor.enableDeadbandElimination(false);
		frontRightMotor.enableDeadbandElimination(false);
		backLeftMotor.enableDeadbandElimination(false);
		backRightMotor.enableDeadbandElimination(false);
		
		backLeftMotor.setInverted(false);
		frontRightMotor.setInverted(false);
		frontLeftMotor.setInverted(false);
		backRightMotor.setInverted(false);
		
		//Initialize driveTrain
    	driveTrain = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
		//driveTrain = new RobotDrive(frontLeftMotor, frontRightMotor); //FOR TEST BASE
    	
    	//Enable safety on driveTrain and set the time period before safety locks down the motors
    	driveTrain.setSafetyEnabled(true);
    	driveTrain.setExpiration(0.5);
	}

	public void initDefaultCommand() {
		//System.out.println("DriveTrain.setDefaultCommand()");
        // Set the default command for a subsystem here.
		setDefaultCommand(new BasicDrive());
    }
	
	public void drive(double moveValue, double rotValue, boolean fineControl, boolean slowMove) {
		//System.out.println("DriveTrain.drive()");
		if (moveValue < 0) { //fourth power curve
			moveValue *= moveValue;
			moveValue = -moveValue;
		}
		else if (moveValue > 0) { //hi
			moveValue *= moveValue;
		}
		
		System.out.println("DriveTrain moveValue="+moveValue+" turnValue="+rotValue);
		
		//driveTrain.arcadeDrive(0.5, 0, false);
		
		if (!slowMove) {
			driveTrain.arcadeDrive(moveValue, rotValue, fineControl); //normal drive
		}
		else if (slowMove) {
			driveTrain.arcadeDrive(moveValue / 6, rotValue / 5, !fineControl); //slow drive and turn
		}
		else {
			System.out.println("FINE TURN ERROR");
			driveTrain.arcadeDrive(0, 0, !fineControl); //Don't move; this should never be called
		}
		
		//driveTrain.arcadeDrive(wingmanJoystick, true);
	}
	
    public void driveAuto(double moveValue, double rotValue) {
    	driveTrain.arcadeDrive(moveValue, rotValue);
    }
    
    public void visionProcessing() {
    	
    	
    	visionThread = new VisionThread(Robot.camera, new RetroTapePipeline(), pipeline -> {
    		//System.out.println("VISION PROCESSING");
    		if (!pipeline.filterContoursOutput().isEmpty()) { // if the output from the process has something in it

    			for (int i = 0; i < pipeline.filterContoursOutput().size(); i++) {
    				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i)); //get the first detected rect from the output
    				synchronized (imgLock) {
    					if (i == 0) {
    						centerX = r.x + (r.width / 2); //find the center of first rect and calculate
    						System.out.print("Rect 1 Center:  " + centerX);
    						centerWhole = centerX;
    					}
    					else if (i == 1) {
    						centerX2 = r.x + (r.width / 2); //find the center of second rect and calculate
    						System.out.print("Rect 2 Center:  " + centerX2);
    						centerWhole = ((centerX + centerX2) / 2);
    					}
    					else {
    						System.out.println("Getting some weird random output");
    					}
    				}
    			}
    		}
    		else {
    			System.out.println(visionThread.getName()+" The pipeline is empty");
    			centerX = 160;
    			centerX2 = 160;
    			centerWhole = 160;
    		}
    	});
    	visionThread.setName("T"+System.currentTimeMillis());

    	//visionThread.start();
    }
    
    public void visionImplement() {
    	double m_centerX;
		synchronized (imgLock) {
			m_centerX = centerWhole;
			System.out.println(m_centerX);
		}
		double dist = m_centerX - (Robot.IMG_WIDTH / 2);
		
		System.out.println("CenterX =  " + centerX + "  CenterX2 =  " + centerX2 + "  centerWhole =  " + centerWhole + "  dist =  " + dist);
		
		if (dist > 20) { //MODIFY THESE DEADZONE VALUES FOR THE POSITION OF THE ACTUAL CAMERA
			//driveTrain.arcadeDrive(0, dist * -0.0025);
			driveTrain.arcadeDrive(0, -RobotMap.TURN_SPEED);
			System.out.println("TURN LEFT, dist = " + dist);
		}
		else if (dist < -20) {
			//driveTrain.arcadeDrive(0, dist * 0.0025);
			driveTrain.arcadeDrive(0, RobotMap.TURN_SPEED);
			System.out.println("TURN RIGHT, dist = " + dist);
		}
		else {
			//driveTrain.arcadeDrive(RobotMap.MED_SPEED, 0.0);
			driveTrain.arcadeDrive(0.2, 0.0);
			System.out.println("CENTER OR NULL, dist = " + dist);
		}
    }
	
}
