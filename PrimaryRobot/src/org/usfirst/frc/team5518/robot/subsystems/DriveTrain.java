package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.commands.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new RobotDrive());
    }
}

