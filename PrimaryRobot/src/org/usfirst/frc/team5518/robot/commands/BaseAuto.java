package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public abstract class BaseAuto extends Command {

	public double range; public double total; public double avg;
	public double min; public double max; public double prev;
	public int count;
	public Timer drivingTime;
	public long startTime;
	public final double forwardTimeMsec = RobotMap.FORWARD_TIME;
	public final double turnTimeMsec = RobotMap.TURN_TIME;
	
	public boolean movingForward;
	public boolean firstTime = true;
	
    public BaseAuto() {
    	movingForward = true;
    	System.out.println("BaseAuto constructor");
		requires(Robot.driveTrain);
		count = 0; total = 0; avg = -1;
		min = 1000; max = 0; prev = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("CALL VISION PROCESSING");
    	Robot.driveTrain.visionProcessing();
		startTime = System.currentTimeMillis();
		//Robot.driveTrain.visionThread.start(); //MOVE THIS BACK DOWN WHEN WE ARE DONE TESTING THE VISION
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
    
    public void placeGear() {
    	//Robot.driveTrain.visionImplement(); //MOVE THIS BACK DOWN WHEN WE ARE DONE TESTING THE VISION
    	
    	range = Robot.ultra.getRangeInches();

		if (range > max) {
			max = range;
		}
		else if (range < min) {
			min = range;
		}

		total += range;
		count++;
		//avg = range;

		// gets 10 ultrasonic samples and averages them
		if (count == 3) {
			total -= (min + max);
			avg = total / (count - 2);
			prev = avg;
			count = 0;
			total = 0;
			min = 1000;
			max = 0;
		}

		SmartDashboard.putNumber("Raw Range (inches): ", (range));
		SmartDashboard.putNumber("Average Range (inches): ", (avg));

		if (movingForward && avg != -1) { //going forwards; putting ON the gear
			if (avg >RobotMap.FAST_DISTANCE) {
				System.out.println("DRIVE FAST count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.FAST_SPEED, 0);
			}
			else if (avg <= RobotMap.FAST_DISTANCE && avg > RobotMap.ULTRA_DISTANCE) {
				System.out.println("DRIVE MEDIUM count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.MED_SPEED, 0);
			}
			else if (avg <= RobotMap.ULTRA_DISTANCE && avg > RobotMap.SLOW_DISTANCE) {
				if (firstTime) { //RUN VISION
        			Robot.driveTrain.visionThread.start();
        			firstTime = false;
        		}
				Robot.driveTrain.visionImplement(); //VISION IMPLEMENTATION
			}
			else if (avg < RobotMap.SLOW_DISTANCE && avg > RobotMap.STOP_DISTANCE) {
				Robot.driveTrain.visionThread.stop();
				System.out.println("DRIVE SLOW count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.SLOW_SPEED, 0);
			}
			else if (avg <= RobotMap.STOP_DISTANCE) {
				System.out.println("DRIVE STOP count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.FULLSTOP, 0);
				Robot.motorController.openDoors();
				Timer.delay(1);
				movingForward = false;
			}
		}
		else if (!movingForward && avg != -1){ //going backwards; easing OFF the peg
			if (avg <= 5.5) {
				System.out.println("Reverse Drive SLOW count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(-RobotMap.SLOW_SPEED, 0);
			}
			else if (avg <= 36 && avg > 5.5) {
				System.out.println("Reverse Drive MED count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(-RobotMap.MED_SPEED, 0);
				Robot.motorController.closeDoors();
			}
			else if (avg > 36) {
				System.out.println("Reverse Drive STOP count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(0.0, 0);
			}
		}
	}
    
    public void reset() {
    	movingForward = true;
    	count = 0; total = 0; avg = -1;
		min = 1000; max = 0; prev = 0;
		firstTime = true;
    }
}
