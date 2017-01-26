package org.usfirst.frc.team5518.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.OI;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	RobotDrive driveTrain;
	public VictorSP frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
	Joystick driveController, sfController;
	
	public DriveTrain() {
		driveController = OI.driveController;
		sfController = OI.sfController;
		
		//Initialize motors to port numbers from RobotMap
		frontLeftMotor = new VictorSP(RobotMap.FRONT_LEFT_PORT_NUMBER);
		frontRightMotor = new VictorSP(RobotMap.FRONT_RIGHT_PORT_NUMBER);
		backLeftMotor = new VictorSP(RobotMap.BACK_LEFT_PORT_NUMBER);
		backRightMotor = new VictorSP(RobotMap.BACK_RIGHT_PORT_NUMBER);
		
    	//Enable the deadband elimination (the dead zone on the controller)
		frontLeftMotor.enableDeadbandElimination(true);
		frontRightMotor.enableDeadbandElimination(true);
		backLeftMotor.enableDeadbandElimination(true);
		backRightMotor.enableDeadbandElimination(true);
		
		//Initialize driveTrain
    	driveTrain = new RobotDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    	
    	//Enable safety on driveTrain and set the time period before safety locks down the motors
    	driveTrain.setSafetyEnabled(true);
    	driveTrain.setExpiration(0.5);
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
	
	public void drive(double moveValue, double rotValue, boolean fineControl) {
		driveTrain.arcadeDrive(moveValue, rotValue, fineControl);
	}
	
	/*public void invert(boolean buttonPressed) {
		if (buttonPressed)
		{
			
		}
	}*/
	
}
