package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.DriveForwardAuto;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainAuto extends Subsystem {

	RobotDrive driveTrain;
	public VictorSP frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

    public void initDefaultCommand() {
        setDefaultCommand(new DriveForwardAuto());
    }
    
    public DriveTrainAuto() {
    	//Initialize motors to port numbers from RobotMap
    	frontLeftMotor = new VictorSP(RobotMap.FRONT_LEFT_PORT_NUMBER);
    	frontRightMotor = new VictorSP(RobotMap.FRONT_RIGHT_PORT_NUMBER);
    	backLeftMotor = new VictorSP(RobotMap.BACK_LEFT_PORT_NUMBER);
    	backRightMotor = new VictorSP(RobotMap.BACK_RIGHT_PORT_NUMBER);

    	//Enable the deadband elimination (the dead zone on the controller)
    	frontLeftMotor.enableDeadbandElimination(false);
    	frontRightMotor.enableDeadbandElimination(false);
    	backLeftMotor.enableDeadbandElimination(false);
    	backRightMotor.enableDeadbandElimination(false);

    	backLeftMotor.setInverted(false);
    	frontRightMotor.setInverted(false);
    	frontLeftMotor.setInverted(false);
    	backRightMotor.setInverted(false);

    	//Initialize driveTrain
    	//driveTrain = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor); //FOR HYPERION
    	driveTrain = new RobotDrive(frontLeftMotor, frontRightMotor); //FOR TEST BASE

    	//Enable safety on driveTrain and set the time period before safety locks down the motors
    	driveTrain.setSafetyEnabled(true);
    	driveTrain.setExpiration(0.5);
    }
    
    public void driveAuto(double moveValue, double rotValue) {
    	driveTrain.arcadeDrive(moveValue, rotValue);
    }
}

