package org.usfirst.frc.team5518.robot.subsystems;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import org.usfirst.frc.team5518.robot.RetroTapePipeline;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.DriveForwardAuto;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *
 */
public class DriveTrainAuto extends Subsystem {

	RobotDrive driveTrain;
	public VictorSP frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

	private VisionThread visionThread;
	private double centerX = 0.0;
	private double centerX2 = 0.0;
	private final Object imgLock = new Object();
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveForwardAuto());
    }
    
    public DriveTrainAuto() {
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
    	//driveTrain = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor); //FOR HYPERION
    	driveTrain = new RobotDrive(frontLeftMotor, frontRightMotor); //FOR TEST BASE

    	//Enable safety on driveTrain and set the time period before safety locks down the motors
    	driveTrain.setSafetyEnabled(true);
    	driveTrain.setExpiration(0.5);
    }
    
    public void driveAuto(double moveValue, double rotValue) {
    	driveTrain.arcadeDrive(moveValue, rotValue);
    }
    
    public void visionProcessing() {
    	visionThread = new VisionThread(Robot.camera, new RetroTapePipeline(), pipeline -> {

    		if (!pipeline.filterContoursOutput().isEmpty()) { // if the output from the process has something in it

    			for (int i = 0; i < pipeline.filterContoursOutput().size(); i++) {
    				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i)); //get the first detected rect from the output
    				synchronized (imgLock) {
    					if (i == 0) {
    						centerX = r.x + (r.width / 2); //find the center of first rect and calculate
    						System.out.print("Rect 1 Center:  " + centerX);
    					}
    					else if (i == 1) {
    						centerX2 = r.x + (r.width / 2); //find the center of second rect and calculate
    						System.out.print("Rect 2 Center:  " + centerX2);
    					}

    				}
    			}
    		}
    		else {
    			System.out.println(visionThread.getName()+" The pipeline is empty");
    			centerX = 160;
    		}
    	});
    	visionThread.setName("T"+System.currentTimeMillis());

    	visionThread.start();
    }
    
    public void visionImplement() {
    	double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
			System.out.println(centerX);
		}
		double dist = centerX - (Robot.IMG_WIDTH / 2);
		
		System.out.println("CenterX =  " + centerX + "  dist =  " + dist);
		
		if (dist > 40) { //MODIFY THESE DEADZONE VALUES FOR THE POSITION OF THE ACTUAL CAMERA
			driveTrain.arcadeDrive(0, dist * -0.0025);
		}
		else if (dist < -40) {
			driveTrain.arcadeDrive(0, dist * 0.0025);
		}
		else {
			driveTrain.arcadeDrive(0.25, 0);
		}
    }
}

