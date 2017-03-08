package org.usfirst.frc.team5518.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

//import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.BasicDrive;
import org.usfirst.frc.team5518.robot.OI;

//import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
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
<<<<<<< HEAD
=======
		//driveTrain = new RobotDrive(frontLeftMotor, frontRightMotor); //FOR TEST BASE
>>>>>>> branch 'DriveTrain' of https://github.com/TechnoWolves5518/2017RobotCode.git
    	
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
		
<<<<<<< HEAD
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
		
=======
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
>>>>>>> branch 'DriveTrain' of https://github.com/TechnoWolves5518/2017RobotCode.git
		
		//driveTrain.arcadeDrive(wingmanJoystick, true);
	}
	
}
