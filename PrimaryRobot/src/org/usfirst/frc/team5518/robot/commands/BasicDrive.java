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
	public boolean fineControl;
	
    public BasicDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	moveValue = 0;
    	turnValue = 0;
    	fineControl = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // This method runs repeatedly while the robot
    protected void execute() {
    	moveValue = OI.driveController.getRawAxis(RobotMap.XBOX_LSTICKY);
    	turnValue = OI.driveController.getRawAxis(RobotMap.XBOX_RSTICKX);
    	
		Robot.driveTrain.drive(moveValue, turnValue, fineControl);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
