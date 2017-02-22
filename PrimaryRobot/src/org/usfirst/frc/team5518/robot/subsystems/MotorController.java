package org.usfirst.frc.team5518.robot.subsystems;

import org.usfirst.frc.team5518.robot.RobotMap;
import org.usfirst.frc.team5518.robot.commands.RunMotor;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class MotorController extends Subsystem { //for motors
	public VictorSP intakeMotor;
	public VictorSP shooterMotor;
	public VictorSP winchMotor;
	public VictorSP loadMotor;
	
	public double intakeMotorSpeed;
	public double shooterMotorSpeed;
	public double loadMotorSpeed;
	
	Servo leftServo;
	Servo rightServo;
	
	public boolean doorState;
	
	public MotorController() {
		intakeMotor = new VictorSP(RobotMap.INTAKE_PORT_NUMBER);
		shooterMotor = new VictorSP(RobotMap.SHOOTER_PORT_NUMBER);
		winchMotor = new VictorSP(RobotMap.WINCH_PORT_NUMBER);
		loadMotor = new VictorSP(RobotMap.LOAD_PORT_NUMBER);
		
		intakeMotorSpeed = 2/3;
		shooterMotorSpeed = .65;
		loadMotorSpeed = -0.25;
		
		rightServo = new Servo(RobotMap.RIGHT_DOOR_SERVO);
		leftServo = new Servo(RobotMap.LEFT_DOOR_SERVO);
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
	
	public void toggleDoors(boolean open) {
    	if (open) {
    		// open
    		leftServo.setAngle(0);
    		rightServo.setAngle(180);
    		System.out.println("doors open left: " + leftServo.getAngle());
    		System.out.println("doors open right: " + rightServo.getAngle());
    		doorState = true;
    	}
    	else {
    		// close
    		leftServo.setAngle(90);
    		rightServo.setAngle(90);
    		System.out.println("doors closed left: " + leftServo.getAngle());
    		System.out.println("doors closed right: " + rightServo.getAngle());
    		doorState = false;
    	}
    }

	public void runShooterMotor(double speed) { //SHOOTER
		shooterMotor.set(speed * shooterMotorSpeed);
	}
	
	public void runLoadingMotor(int go) { //LOAD
		loadMotor.set(loadMotorSpeed * go);
	}
	
	public void getData() {
		SmartDashboard.putNumber("Intake motor speed", intakeMotor.get());
		SmartDashboard.putNumber("Shooter motor speed", shooterMotor.get());
		SmartDashboard.putNumber("Load motor speed", loadMotor.get());
		SmartDashboard.putNumber("Winch motor speed", winchMotor.get());
		if (doorState) {
			SmartDashboard.putString("Gear door state", "open");
		}
		else if (!doorState) {
			SmartDashboard.putString("Gear door state", "closed");
		}
		
	}
}

