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
		
		intakeMotorSpeed = 1;
		shooterMotorSpeed = 1;
		//winchMotorSpeed = 1;
	}
	
	public void initDefaultCommand() {
	    // Set the default command for a subsystem here.
	    setDefaultCommand(new RunMotor());
	}
	
	public void runIntakeMotor(boolean go) {
		if (go) {
			intakeMotor.set(intakeMotorSpeed);
		}
		else {
			intakeMotor.set(0);
		}
	}
	public void runShooterMotor(boolean go) {
		if (go) {
			shooterMotor.set(shooterMotorSpeed);
		}
		else {
			shooterMotor.set(0);
		}
	}
	public void runWinchMotor(double speed, int dir, boolean slow) {
		System.out.println("Run winch motor");
		if (!slow) {
			winchMotor.set(speed * dir);
		}
		else if (slow) {
			winchMotor.set(speed / 5 * dir);
		}
	}
}

