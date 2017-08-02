package org.usfirst.frc.team5518.robot.commands;

import org.usfirst.frc.team5518.robot.Robot;
import org.usfirst.frc.team5518.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public abstract class BaseAuto extends Command {

	public double range; public double total; public double avg;
	public double speed = 0;
	public double min; public double max; public double prev;
	public int count;
	public Timer drivingTime;
	public long startTime;
	
	public boolean movingForward;
	public boolean firstTime = true; public boolean doorsOpen = false;
	
	//public double pauseTime;
	
    public BaseAuto() {
    	movingForward = true;
//    	System.out.println("BaseAuto constructor");
		requires(Robot.driveTrain);
		count = 0; total = 0; avg = 1000;
		min = 1000; max = 0; prev = 0;
		firstTime = true; doorsOpen = false;
		
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("CALL VISION PROCESSING");
    	//Robot.driveTrain.visionProcessing();
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
    	
//    	System.out.println("placeGear called");
    	
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

//		SmartDashboard.putNumber("Raw Range (inches): ", (range));
//		SmartDashboard.putNumber("Average Range (inches): ", (avg));

		/*
		if (movingForward && avg != 1000) { //going forwards; putting ON the gear
			if (avg >RobotMap.FAST_DISTANCE) {
//				System.out.println("DRIVE FAST count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.CENTER_FAST_SPEED, 0.1);
			}
			else if (avg <= RobotMap.FAST_DISTANCE && avg > RobotMap.ULTRA_DISTANCE) {
//				System.out.println("DRIVE MEDIUM count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.CENTER_MED_SPEED, 0.1);
			}
			else if (avg <= RobotMap.ULTRA_DISTANCE && avg > RobotMap.SLOW_DISTANCE) {
				if (firstTime) { //RUN VISION
        			//Robot.driveTrain.visionThread.start();
        			firstTime = false;
        		}
				//Robot.driveTrain.visionImplement(); //VISION IMPLEMENTATION
				Robot.driveTrain.driveAuto(RobotMap.CENTER_MED_SPEED, 0.1);
			}
			else if (avg < RobotMap.SLOW_DISTANCE && avg > RobotMap.STOP_DISTANCE) {
				//Robot.driveTrain.visionThread.stop();
				System.out.println("DRIVE SLOW count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.CENTER_SLOW_SPEED, 0.1);
			}
			else if (avg <= RobotMap.STOP_DISTANCE && avg > 0) {
				System.out.println("DRIVE STOP count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.CENTER_FULLSTOP, 0.1);
				if (!doorsOpen) {
					Robot.motorController.openDoors();
//					System.out.println("-----------------OPEN DOORS-----------------");
					doorsOpen = true;
				}
				else {
//					System.out.println("!!!!!!!!!!!!!!!!!DOORS ALREADY OPEN!!!!!!!!!!!!!!!!!");
				}
				//final double pauseTime = System.currentTimeMillis();
//				if (System.currentTimeMillis() - pauseTime > 500) {
//					
//				}
				movingForward = false;
				//Timer.delay(1);
			}
		}
		else if (!movingForward && avg != 1000){ //going backwards; easing OFF the peg
			if (avg <= (RobotMap.STOP_DISTANCE + 1) && avg > 0) {
				System.out.println("Reverse Drive SUPER SLOW count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(-0.4, 0);
			}
			else if (avg <= RobotMap.SLOW_DISTANCE && avg > RobotMap.STOP_DISTANCE + 1) {
//				System.out.println("Reverse Drive MED count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(-RobotMap.CENTER_MED_SPEED, 0);
			}
			else if (avg > RobotMap.SLOW_DISTANCE) {
//				System.out.println("Reverse Drive STOP count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(0.0, 0);
				if (doorsOpen) {
					Robot.motorController.closeDoors();
//					System.out.println("-----------------CLOSE DOORS-----------------");
					doorsOpen = false;
				}
				else {
//					System.out.println("!!!!!!!!!!!!!!!!!DOORS ALREADY CLOSED!!!!!!!!!!!!!!!!!");
				}
			}
		}
		*/
		
		if (movingForward && avg != 1000) {
			speed = (Math.pow((avg-7), 0.5)) / 11;
			Robot.driveTrain.driveAuto(speed, 0.1);
			if (speed == 0) {
				movingForward = false;
			}
		}
		else if (!movingForward && avg != 1000) {
			speed = avg/35;
			if (speed > 0.5) {
				speed = 0.5;
			}
		}
		
	}
    
    public void reset() {
    	movingForward = true;
    	count = 0; total = 0; avg = -1;
		min = 1000; max = 0; prev = 0;
		firstTime = true; doorsOpen = false;
    }
}
