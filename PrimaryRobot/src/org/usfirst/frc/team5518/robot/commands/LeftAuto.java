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
public class LeftAuto extends Command {

	public double range; public double total; public double avg;
	public double min; public double max; public double prev;
	public int count;
	public Timer drivingTime;
	private long startTime;
	private final int forwardTimeMsec = 3000;
	private final int turnTimeMsec    = forwardTimeMsec+1000;

	public boolean movingForward;

	public LeftAuto() {
		System.out.println("Robot.driveAuto = "+Robot.driveTrain);
		requires(Robot.driveTrain);
		count = 0; total = 0; avg = -1;
		min = 1000; max = 0; prev = 0;
		movingForward = true;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.visionProcessing();

//		drivingTime = new Timer();
//		drivingTime.start();
		startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//The robot must move 4 ft 9.25 in forward to reach the baseline.
		//The max velocity is about 10.39 ft/s
		//We will run the robot straight forward for
		//		3 seconds
		//at a speed of
		//		1.59 ft/s

		//if (!drivingTime.hasPeriodPassed(3.00)) {
		if (System.currentTimeMillis()-startTime < forwardTimeMsec){
			System.out.println("Drive Forward");
			Robot.driveTrain.driveAuto(RobotMap.FAST_SPEED, 0);
		} else if (System.currentTimeMillis()-startTime < turnTimeMsec) {
			//Turn for some time
			System.out.println("Turning RIGHT");
			Robot.driveTrain.driveAuto(0, 0.5);
		}
		else
		{
			// Place gear
			System.out.println("Timer Expired");
			
			placeGear();
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

	public void placeGear() {
		range = Robot.ultra.getRangeInches();

		if (range > max) {
			max = range;
		}
		else if (range < min) {
			min = range;
		}

		total += range;
		count++;


		// gets 10 ultrasonic samples and averages them
		if (count == 10) {
			total -= (min + max);
			avg = total / (count - 2);
			prev = avg;
			count = 0;
			total = 0;
			min = 1000;
			max = 0;
		}

		SmartDashboard.putNumber("Raw Range (feet): ", (range / 12));
		SmartDashboard.putNumber("Average Range (feet): ", (avg / 12));

		if (movingForward && avg != -1) { //going forwards; putting ON the gear
			if (avg >RobotMap.FAST_DISTANCE) {
				System.out.println("DRIVE FAST count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.FAST_SPEED, 0);
			}
			else if (avg <= RobotMap.FAST_DISTANCE && avg > RobotMap.SLOW_DISTANCE) {
				System.out.println("DRIVE SLOW count="+count+"  avg="+avg);
				Robot.driveTrain.driveAuto(RobotMap.MED_SPEED, 0);
			}
			else if (avg <= RobotMap.SLOW_DISTANCE && avg > RobotMap.STOP_DISTANCE) {
				//Robot.driveTrain.visionThread.start();
				//Robot.driveTrain.visionImplement();
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
		else if (avg != -1){ //going backwards; easing OFF the peg
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
}

