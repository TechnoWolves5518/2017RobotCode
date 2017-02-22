package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardAuto extends Command {

    public DriveForwardAuto() {
    	//requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//The robot must move 4 ft 9.25 in forward to reach the baseline.
    	//The max velocity is about 10.39 ft/s
    	//We will run the robot straight forward for
    	//		3 seconds
    	//at a speed of
    	//		1.59 ft/s
    	Robot.driveTrain.drive(0.153, 0, true, false);
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
