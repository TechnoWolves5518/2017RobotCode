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
	
	public RunMotor() {
	    requires(Robot.motorController);
	    winchSpeed = 0;
		shooter = 0;
		intake = 0;
		slow = false;
		reverse = false;
		doors = false; ldoors = false; toggle = false;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//System.out.println("Run Motor Execute");
		//Get all inputs

		winchSpeed = OI.sfController.getRawAxis(RobotMap.XBOX_LSTICKY);
		shooter = OI.sfController.getRawAxis(RobotMap.XBOX_RTRIGGER);
		intake = OI.sfController.getRawAxis(RobotMap.XBOX_LTRIGGER);
		load = OI.sfController.getRawButton(RobotMap.XBOX_ABTN);
		slow = OI.sfController.getRawButton(RobotMap.XBOX_RBUMPER);
		reverse = OI.sfController.getRawButton(RobotMap.XBOX_BBTN);
		System.out.println("Getting door states from controller");
		doors = OI.sfController.getRawButton(RobotMap.XBOX_XBTN);
		
		Robot.motorController.getData();
		
		//WINCH
		if (winchSpeed != 0) {
			Robot.motorController.runWinchMotor(winchSpeed, slow);
		}
		else {
			Robot.motorController.runWinchMotor(0, slow);
		}
		//SHOOTER
		Robot.motorController.runShooterMotor(shooter);
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
		
//		if (doors) {
//			toggle = true;
//		}
//		else if (!doors) {
//			toggle = false;
//		}
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
