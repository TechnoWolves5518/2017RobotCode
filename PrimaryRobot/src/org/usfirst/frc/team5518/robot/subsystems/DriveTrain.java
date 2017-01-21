package org.usfirst.frc.team5518.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team5518.robot.RobotMap;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	RobotDrive driveTrain;
	VictorSP frontLMotor, frontRMotor, backLMotor, backRMotor;
	Joystick driveStick;
	
	public DriveTrain() {
		 frontLMotor = new VictorSP(RobotMap.FRONT_LEFT_PORT_NUMBER);
    	 frontRMotor = new VictorSP(RobotMap.FRONT_RIGHT_PORT_NUMBER);
    	 backLMotor = new VictorSP(RobotMap.BACK_LEFT_PORT_NUMBER);
    	 backRMotor = new VictorSP(RobotMap.BACK_RIGHT_PORT_NUMBER);
    	
    	driveTrain = new RobotDrive(frontLMotor, frontRMotor, backLMotor, backRMotor);
    	
	}
	
	
	public void robotInit() {
		
	}
	
	
	public void autonomousPeriodic() {
		
	}
	
	
	public void operatorControl(){
		
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		
    }
}

