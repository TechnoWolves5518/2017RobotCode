package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.Commands.CheckSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearTransfer extends Subsystem {

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DigitalInput limitSwitch;
	
	public GearTransfer(){
		
		limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_PORT);
		
	}
	
	public void log(){
		
	SmartDashboard.putBoolean("check_gear", isGearIn());
		
	}
	
	
	public boolean isGearIn(){
	
		return limitSwitch.get();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CheckSensor());
        
    }
}

