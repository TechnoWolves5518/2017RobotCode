package org.usfirst.frc.team5518.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	RobotDrive driveTrain;
	Victor leftMotor, rightMotor;
	Joystick driveStick;
	
	public DriveTrain() {
		leftMotor = new Victor(RobotMap.LEFT_PORT_NUMBER);
    	rightMotor = new Victor(RobotMap.RIGHT_PORT_NUMBER);
    	
    	driveTrain = new RobotDrive(leftMotor, rightMotor);
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
}