package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

/**
 *
 */
public class RightAuto extends BaseAuto {
	
	public final double forwardTimeMsec = RobotMap.RIGHT_FORWARD_TIME;
	public final double turnTimeMsec = RobotMap.RIGHT_TURN_TIME;
	
	public RightAuto() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if (System.currentTimeMillis() - startTime < forwardTimeMsec) {
//			System.out.println("Drive Forward");
			Robot.driveTrain.driveAuto(RobotMap.FAST_SPEED, -0.1);
		}
		else if (System.currentTimeMillis() - (startTime) < (turnTimeMsec + forwardTimeMsec)) {
			//Turn for some time
//			System.out.println("Turning RIGHT");
			Robot.driveTrain.driveAuto(0, RobotMap.TURN_SPEED);
		}
		else {
			// Place gear
			//System.out.println("Timer Expired");
			//Timer.delay(0.25);
			placeGear();
		}
	}
}