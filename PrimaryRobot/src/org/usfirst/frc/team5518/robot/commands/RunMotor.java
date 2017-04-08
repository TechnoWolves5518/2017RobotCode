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
	public boolean reverse;
	public boolean load;
	public boolean slow;
	public boolean doors, ldoors;
	public boolean toggle;
	public boolean lock; public boolean llock; public boolean isLocked;
	
	public RunMotor() {
	    requires(Robot.motorController);
	    winchSpeed = 0;
		shooter = 0;
		intake = 0;
		slow = false;
		reverse = false;
		doors = false; ldoors = false; toggle = false;
		lock = false; llock = false; isLocked = false;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//System.out.println("Run Motor Execute");
		//Get all inputs

		// CHANGE HERE
		winchSpeed = OI.driveController.getRawAxis(RobotMap.XBOX_LTRIGGER);
		
		//  CHANGE HERE
		intake = OI.driveController.getRawAxis(RobotMap.XBOX_RTRIGGER) * 2 / 3;
		
		// CHANGE HERE
		load = OI.driveController.getRawButton(RobotMap.XBOX_ABTN);
		
		slow = OI.driveController.getRawButton(RobotMap.XBOX_RBUMPER);
		
		// CHANGE HERE
		reverse = OI.driveController.getRawButton(RobotMap.XBOX_BBTN);
		
		// CHANGE HERE
		doors = OI.driveController.getRawButton(RobotMap.XBOX_XBTN);
		
		// CHANGE HERE
		lock = OI.driveController.getRawButton(RobotMap.XBOX_LSTICK);
		
		Robot.motorController.getData(isLocked);
		
//		if (lock != llock && lock == true) {
//			isLocked = true;
//		}
//		else if (lock != llock && lock == false) {
//			isLocked = false;
//		}
//		llock = lock;
		
		//WINCH
//		if (!isLocked) {
			if (winchSpeed != 0) {
				Robot.motorController.runWinchMotor(winchSpeed, slow);
			}
			else {
				Robot.motorController.runWinchMotor(0, slow);
			}
//		}
//		else {
//			Robot.motorController.runWinchMotor(0.2, slow);
//		}
		
		Robot.motorController.runIntakeMotor(intake, reverse);
		
		//LOADING
		if (load) {
			Robot.motorController.runLoadingMotor(1);
		}
		else {
			Robot.motorController.runLoadingMotor(0);
		}
		
		if (doors != ldoors && doors == true) {
			toggle = !toggle;
			Robot.motorController.toggleDoors(toggle);
		}
		ldoors = doors;
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
