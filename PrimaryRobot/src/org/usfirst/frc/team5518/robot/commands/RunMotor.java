package org.usfirst.frc.team5518.robot.commands;
//package org.usfirst.team5518.robot.subsystems.MotorController;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;
//import org.usfirst.frc.team5518.robot.subsystems.MotorController;
import org.usfirst.frc.team5518.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunMotor extends Command {

	public double LTval;
	public double RTval;
	public boolean runWinch;
	
    public RunMotor() {
        requires(Robot.motorController);
    	
    	LTval = 0;
    	RTval = 0;
    	runWinch = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	LTval = OI.sfController.getRawAxis(RobotMap.XBOX_LTRIGGER);
    	RTval = OI.sfController.getRawAxis(RobotMap.XBOX_RTRIGGER);
    	runWinch = OI.getButton(OI.sfController, RobotMap.XBOX_XBTN);
    	
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
    		Robot.motorController.runWinchMotor(true);
    	}
    	else if (!runWinch) {
    		Robot.motorController.runWinchMotor(false);
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
