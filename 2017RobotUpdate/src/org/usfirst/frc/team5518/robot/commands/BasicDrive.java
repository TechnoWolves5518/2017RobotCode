package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.OI;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BasicDrive extends Command {
	
	public double moveValue, turnValue;
	public double oldMoveValue = 0.0;
	public double maxMoveValue = 0.5;
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
//    	System.out.println("BasicDrive Command execute()");
    	moveValue = -OI.driveController.getRawAxis(RobotMap.XBOX_LSTICKY);
    	turnValue = -OI.driveController.getRawAxis(RobotMap.XBOX_RSTICKX);
    	slowMove = OI.driveController.getRawButton(RobotMap.XBOX_RBUMPER);
    	double diff = moveValue - oldMoveValue;
    	double abs_diff = Math.abs(diff);
    	if (abs_diff >= maxMoveValue){
    		if (diff < 0){
    			moveValue = moveValue - maxMoveValue;
    		}
    		else{
    			
    			moveValue = moveValue + maxMoveValue;
    		}
//    		System.out.println("Max delta reached" + oldMoveValue + ":" + moveValue + ":" + diff);
    	}
    	//System.out.println("drive stats: " + oldMoveValue + ":" + moveValue + ":" + diff);
    	isInverted = OI.getButton(OI.driveController, RobotMap.XBOX_LBUMPER);
    	if (isInverted != wasInverted && isInverted == true)
    	{
    		toggle = !toggle;
    		//OI.driveController.setRumble(kLeftRumble, value);
    		OI.driveController.setRumble(RumbleType.kLeftRumble, 1);
    	}
    	else if (isInverted != wasInverted && isInverted == false) {
    		OI.driveController.setRumble(RumbleType.kLeftRumble, 0);
    	}
    	wasInverted = isInverted;
    	
    	if (!toggle) //If the invert is off
    	{
    		//System.out.println("BasicDrive moveValue="+moveValue+" turnValue="+turnValue);
    		Robot.driveTrain.drive(moveValue, turnValue, fineControl, true/*slowMove*/); //Drive the robot with base move values
//    		System.out.println("MoveValue:  "  + moveValue);
    	}
    	else if (toggle) //If the invert is on
    	{
    		//System.out.println("BasicDrive [inverted] moveValue="+moveValue+" turnValue="+turnValue);
    		Robot.driveTrain.drive(-moveValue, turnValue, fineControl, true/*slowMove*/); //Drive the robot with negative move values
    	}
    	oldMoveValue = moveValue;
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
