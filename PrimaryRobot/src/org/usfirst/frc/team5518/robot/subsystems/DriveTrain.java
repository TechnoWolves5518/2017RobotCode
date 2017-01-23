package org.usfirst.frc.team5518.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	RobotDrive driveTrain;
	VictorSP frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
	Joystick driveStick;
	public boolean isInverted;
	
	public DriveTrain() {
		frontLeftMotor = new VictorSP(RobotMap.FRONT_LEFT_PORT_NUMBER);
		frontRightMotor = new VictorSP(RobotMap.FRONT_RIGHT_PORT_NUMBER);
		backLeftMotor = new VictorSP(RobotMap.BACK_LEFT_PORT_NUMBER);
		backRightMotor = new VictorSP(RobotMap.BACK_RIGHT_PORT_NUMBER);
    	
    	driveTrain = new RobotDrive(leftMotor, rightMotor);
    	
    	isInverted = false;
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
}