package org.usfirst.frc.team5518.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5518.robot.Robot;

/**
 *
 */
public class InvertMotors extends Command {

	public boolean isInverted;
	
    public InvertMotors() {
    	isInverted = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isInverted = !isInverted; //Swap the inverted bool and then apply to all motor controllers
		
		Robot.driveTrain.frontLeftMotor.setInverted(isInverted);
		Robot.driveTrain.frontRightMotor.setInverted(isInverted);
		Robot.driveTrain.backLeftMotor.setInverted(isInverted);
		Robot.driveTrain.backRightMotor.setInverted(isInverted);
		
		end();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
