package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

/**
 *
 */
public class DriveForwardAuto extends BaseAuto {
	
    public DriveForwardAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    protected void execute() {
    	if (System.currentTimeMillis() - startTime < 3500) {
			System.out.println("Drive Forward");
			Robot.driveTrain.driveAuto(RobotMap.FAST_SPEED, 0);
		}
    }
}
