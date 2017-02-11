package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicDrive extends Command {
	
	public double moveValue, turnValue;
	public boolean fineControl, slowMove;
	public boolean isInverted, wasInverted, toggle;
	
    public BasicDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    	moveValue = 0;
    	turnValue = 0;
    	fineControl = true;
    	slowMove = false;
    	isInverted = false;
    	wasInverted = false;
    	toggle = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // This method runs repeatedly while the robot
    protected void execute() {
    	System.out.println("BasicDrive Command execute()");
    	moveValue = -OI.driveController.getRawAxis(RobotMap.XBOX_LSTICKY);
    	turnValue = -OI.driveController.getRawAxis(RobotMap.XBOX_RSTICKX);
    	slowMove = OI.driveController.getRawButton(RobotMap.XBOX_RBUMPER);
    	
    	isInverted = OI.getButton(OI.driveController, RobotMap.XBOX_LBUMPER);
    	if (isInverted != wasInverted && isInverted == true)
    	{
    		toggle = !toggle;
    	}
    	wasInverted = isInverted;
    	
    	if (!toggle) //If the invert is off
    	{
    		System.out.println("BasicDrive moveValue="+moveValue+" turnValue="+turnValue);
    		Robot.driveTrain.drive(moveValue, turnValue, fineControl, slowMove); //Drive the robot with base move values
    	}
    	else if (toggle) //If the invert is on
    	{
    		System.out.println("BasicDrive [inverted] moveValue="+moveValue+" turnValue="+turnValue);
    		Robot.driveTrain.drive(-moveValue, turnValue, fineControl, slowMove); //Drive the robot with negative move values
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0, true, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
