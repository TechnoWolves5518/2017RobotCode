package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

/**
 *
 */
public class CenterAuto extends BaseAuto {

    public CenterAuto() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (System.currentTimeMillis() - startTime < 1000) {
//			System.out.println("Drive Forward");
//			Robot.driveTrain.driveAuto(RobotMap.MED_SPEED, 0);
//		}
//    	else {
//    		placeGear();
//    	}
    	placeGear();
    }
}
