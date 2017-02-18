package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.Commands.CheckSensor;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearTransfer extends Subsystem {

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DigitalInput limitSwitch;
	Ultrasonic ultra;
	AnalogInput ai;
	double range;
	
	public GearTransfer() {
		
		limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_PORT);
		ultra = new Ultrasonic(1, 1);
		ultra.setAutomaticMode(true);
		ai = new AnalogInput(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CheckSensor());
    }
    
    public void checkUltrasonic() { //Checks value of digital us and returns in feet
    	range = ultra.getRangeInches();
    	range *= 1/12;
    	SmartDashboard.putNumber("Ultrasonic range (ft): ", range);
    }
    
    public void checkAnalogUltrasonic() {
    	
    }
}

