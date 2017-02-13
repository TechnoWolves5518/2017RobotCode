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
	public boolean runWinch, runWinchBack;
	
	public RunMotor() {
	    requires(Robot.motorController);
		
		LTval = 0;
		RTval = 0;
		runWinch = false;
		runWinchBack = false;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		LTval = -OI.sfController.getRawAxis(RobotMap.XBOX_LTRIGGER);
		RTval = -OI.sfController.getRawAxis(RobotMap.XBOX_RTRIGGER);
		runWinch = OI.getButton(OI.driveController, RobotMap.XBOX_XBTN);
		runWinchBack = OI.getButton(OI.driveController, RobotMap.XBOX_ABTN);
		
		if (LTval > 0.15) {
			Robot.motorController.runIntakeMotor(true);
		}
		else if (LTval < 0.15) {
			Robot.motorController.runIntakeMotor(false);
		}
		
		if (RTval > 0.15) {
			Robot.motorController.runShooterMotor(true);
		}
		else if (RTval < 0.15) {
			Robot.motorController.runShooterMotor(false);
		}
		
		if (runWinch) {
			Robot.motorController.runWinchMotor(true, 1);
		}
		else if (runWinchBack) {
			Robot.motorController.runWinchMotor(true, -1);
		}
		if (!runWinch && !runWinchBack) {
			Robot.motorController.runWinchMotor(false, 0);
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
