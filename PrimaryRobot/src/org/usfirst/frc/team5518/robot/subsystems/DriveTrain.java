package org.usfirst.frc.team5518.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class DriveTrain extends Subsystem  {
	
	RobotDrive driveTrain;
	Victor frontLeft, frontRight, rearLeft, rearRight;
	Joystick driveStick;
	
	
	public DriveTrain() {
		final int FRONT_LEFT_PORT_NUMBER = 0;
    	final int FRONT_RIGHT_PORT_NUMBER = 1;
    	final int BACK_LEFT_PORT_NUMBER = 2;
    	final int BACK_RIGHT_PORT_NUMBER = 3;
    	
    	final int JOYSTICK_PORT_NUMBER = 0;
    	
    	
		frontLeft = new Victor(FRONT_LEFT_PORT_NUMBER);
    	frontRight = new Victor(FRONT_RIGHT_PORT_NUMBER);
    	rearLeft = new Victor(BACK_LEFT_PORT_NUMBER);
    	rearRight = new Victor(BACK_RIGHT_PORT_NUMBER);
    	
    	driveTrain = new RobotDrive(frontLeft, frontRight, rearLeft, rearRight);
    	driveStick = new Joystick(JOYSTICK_PORT_NUMBER);
	}
	
	
	public void robotInit(){
		
		
    	
    	
	}
	
	private void arcadeDrive(Joystick driveStick0) {
		
		
	}
	public void autonomousPeriodic(){
		
	}
	
	private boolean isOperatorControl() {
		
		return true;
	}


	private boolean isEnabled() {
		
		return true;
	}
	
	public void operatorControl(){
		
		while(isOperatorControl() && isEnabled()) {
			driveTrain.arcadeDrive(driveStick);
			
		}
		
		
		
	}

    


	


	public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        
    }
}

