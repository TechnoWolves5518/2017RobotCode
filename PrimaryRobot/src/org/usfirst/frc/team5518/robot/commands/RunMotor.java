package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.OI;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunMotor extends Command {
	public double LTval;
	public double RTval;
	public boolean shooter, intake;
	
	public RunMotor() {
	    requires(Robot.motorController);
		
		LTval = 0;
		RTval = 0;
		shooter = false;
		intake = false;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		LTval = -OI.sfController.getRawAxis(RobotMap.XBOX_LTRIGGER);
		RTval = -OI.sfController.getRawAxis(RobotMap.XBOX_RTRIGGER);
		shooter = OI.driveController.getRawButton(RobotMap.XBOX_XBTN);
		intake = OI.driveController.getRawButton(RobotMap.XBOX_ABTN);
		
		if (RTval > 0) {
			Robot.motorController.runWinchMotor(RTval, 1);
		}
		else if (LTval > 0) {
			Robot.motorController.runWinchMotor(LTval, -1);
		}
		
		if (intake) {
			Robot.motorController.runIntakeMotor();
		}
		if (shooter) {
			Robot.motorController.runShooterMotor();
		}
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
