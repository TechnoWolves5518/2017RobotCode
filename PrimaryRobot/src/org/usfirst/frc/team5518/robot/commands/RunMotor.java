package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.OI;
import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunMotor extends Command {
	public double winchSpeed, shooter, intake;
	public boolean load;
	public boolean slow;
	
	public RunMotor() {
	    requires(Robot.motorController);
	    winchSpeed = 0;
		shooter = 0;
		intake = 0;
		slow = false;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Run Motor Execute");
		//Get all inputs

		winchSpeed = -OI.sfController.getRawAxis(RobotMap.XBOX_LSTICKY);
		shooter = OI.sfController.getRawAxis(RobotMap.XBOX_RTRIGGER);
		intake = OI.sfController.getRawAxis(RobotMap.XBOX_LTRIGGER);
		load = OI.sfController.getRawButton(RobotMap.XBOX_ABTN);
		//WINCH
		if (winchSpeed > 0.1) {
			Robot.motorController.runWinchMotor(winchSpeed, slow);
		}
		else {
			Robot.motorController.runWinchMotor(0, slow);
		}
		//SHOOTER
		if (shooter > 0.1) {
			Robot.motorController.runShooterMotor(1);
		}
		else {
			Robot.motorController.runShooterMotor(0);
		}
		//INTAKE
		if (intake > 0.1) {
			Robot.motorController.runIntakeMotor(1);
		}
		else {
			Robot.motorController.runIntakeMotor(0);
		}
		//LOADING
		if (load) {
			Robot.motorController.runLoadingMotor(1);
		}
		else {
			Robot.motorController.runLoadingMotor(0);
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
