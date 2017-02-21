package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.OI;
import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RunMotor;

import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearTransfer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
//	Servo leftServo;
//	Servo rightServo;
	
	public GearTransfer() {
//		rightServo = new Servo(RobotMap.RIGHT_DOOR_SERVO);
//		leftServo = new Servo(RobotMap.LEFT_DOOR_SERVO);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new RunMotor());
    }
    
    public void toggleDoors(boolean open) {
    	if (open) {
//    		leftServo.set(0.5);
//    		rightServo.set(0.5);
    		//System.out.println("doors open");
    	}
    	else {
//    		leftServo.set(0);
//    		rightServo.set(0);
    		//System.out.println("doors closed");
    	}
    }
}

