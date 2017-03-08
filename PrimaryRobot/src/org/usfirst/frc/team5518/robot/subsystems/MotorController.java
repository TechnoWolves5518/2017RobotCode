package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RunMotor;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class MotorController extends Subsystem {
	public VictorSP intakeMotor;
	public VictorSP shooterMotor;
	public VictorSP winchMotor;
	public VictorSP loadMotor;
	
	public double intakeMotorSpeed;
	public double shooterMotorSpeed;
	public double loadMotorSpeed;
	
	public MotorController() {
		intakeMotor = new VictorSP(RobotMap.INTAKE_PORT_NUMBER);
		shooterMotor = new VictorSP(RobotMap.SHOOTER_PORT_NUMBER);
		winchMotor = new VictorSP(RobotMap.WINCH_PORT_NUMBER);
		loadMotor = new VictorSP(RobotMap.LOAD_PORT_NUMBER);
		
		intakeMotorSpeed = 2/3;
		shooterMotorSpeed = 2/3;
		loadMotorSpeed = 1;
	}
	
	public void initDefaultCommand() {
	    // Set the default command for a subsystem here.
	    setDefaultCommand(new RunMotor());
	}
	
	public void runWinchMotor(double speed, boolean slow) { //WINCH
		System.out.println("Run winch motor");
		if (!slow) {
			winchMotor.set(speed);
		}
		else if (slow) {
			winchMotor.set(speed / 5);
		}
	}
	
	public void runIntakeMotor(double speed, boolean reverse) { //INTAKE
		if (!reverse) {
			intakeMotor.set(speed);
		}
		else if (reverse) {
			intakeMotor.set(speed * -1);
		}
	}

	public void runShooterMotor(double speed) { //SHOOTER
		shooterMotor.set(speed * 0.8);
	}
	
	public void runLoadingMotor(int go) { //LOAD
		loadMotor.set(loadMotorSpeed * go);
	}
	
	public void getData() {
		SmartDashboard.putNumber("Intake motor speed", intakeMotor.get());
		SmartDashboard.putNumber("Shooter motor speed", shooterMotor.get());
		SmartDashboard.putNumber("Load motor speed", loadMotor.get());
		SmartDashboard.putNumber("Winch motor speed", winchMotor.get());
		//SmartDashboard.getData("Shooter motor speed");
	}
}

