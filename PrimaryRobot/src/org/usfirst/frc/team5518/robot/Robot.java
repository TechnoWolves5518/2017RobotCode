
package org.usfirst.frc.team5518.robot;

import org.usfirst.frc.team5518.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5518.robot.subsystems.FuelShooter;
import org.usfirst.frc.team5518.robot.subsystems.GearTransfer;
import org.usfirst.frc.team5518.robot.subsystems.MotorController;
//import org.usfirst.frc.team5518.robot.OI;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static OI oi;
	
	public static DriveTrain driveTrain;
	public static FuelShooter shooter;
	public static MotorController motorController;
	public static GearTransfer gearTransfer;
	public static AnalogInput ultraPort0;
	
//	private Servo leftServo, rightServo;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//oi = new OI();
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		System.out.println("robotInit()");
		SmartDashboard.putData("Auto mode", chooser);
		driveTrain = new DriveTrain();
		shooter = new FuelShooter();
		motorController = new MotorController();
		gearTransfer = new GearTransfer();
		ultraPort0 = new AnalogInput(2);
		
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		System.out.println("disableInit()");
	}

	@Override
	public void disabledPeriodic() {
		//System.out.println("disablePeriodic()");
		Scheduler.getInstance().run();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		System.out.println("autonomousInit()");
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		System.out.println("autoPeriodic()");
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		System.out.println("teleopInit()");
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
//		rightServo.set(1.0);
//		leftServo.set(0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/*try{
		Thread.sleep(500);
		}catch(Exception ex){}
		System.out.println("teleopPeriodic()");*/
		
		// If you don't call this the commands won't run. The commands are registered
		// when the subsystems are created.
		Scheduler.getInstance().run();
		double avgVoltage = ultraPort0.getAverageVoltage();
		SmartDashboard.putNumber("ultra", avgVoltage);
				System.out.println("avgv= " + avgVoltage);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		/*try{
		Thread.sleep(500);
		}catch(Exception ex){}
		System.out.println("testPeriodic()");*/
		LiveWindow.run();
	}
}
