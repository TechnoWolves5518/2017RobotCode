package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RunMotor;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MotorController extends Subsystem {
	public VictorSP intakeMotor;
	public VictorSP shooterMotor;
	public VictorSP winchMotor;
	
	public double intakeMotorSpeed;
	public double shooterMotorSpeed;
	//public double winchMotorSpeed;
	
	public MotorController() {
		intakeMotor = new VictorSP(RobotMap.INTAKE_PORT_NUMBER);
		shooterMotor = new VictorSP(RobotMap.SHOOTER_PORT_NUMBER);
		winchMotor = new VictorSP(RobotMap.WINCH_PORT_NUMBER);
		
		intakeMotorSpeed = 2/3;
		shooterMotorSpeed = 2/3;
	}
	
	public void initDefaultCommand() {
	    // Set the default command for a subsystem here.
	    setDefaultCommand(new RunMotor());
	}
	
	public void runWinchMotor(double speed, boolean slow) {
		System.out.println("Run winch motor");
		if (!slow) {
			winchMotor.set(speed);
		}
		else if (slow) {
			winchMotor.set(speed / 5);
		}
	}
	
	public void runIntakeMotor(int go) {
		intakeMotor.set(intakeMotorSpeed * go);
	}

	public void runShooterMotor(int go) {
		shooterMotor.set(shooterMotorSpeed * go);
	}
	
}

