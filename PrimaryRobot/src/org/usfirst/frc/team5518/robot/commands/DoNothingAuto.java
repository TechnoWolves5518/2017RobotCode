package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DoNothingAuto extends BaseAuto {

    public DoNothingAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    protected void execute() {
    	if (System.currentTimeMillis() - startTime < forwardTimeMsec) {
			System.out.println("Drive Forward");
			Robot.driveTrain.driveAuto(RobotMap.FAST_SPEED, 0);
		}
    }
}
