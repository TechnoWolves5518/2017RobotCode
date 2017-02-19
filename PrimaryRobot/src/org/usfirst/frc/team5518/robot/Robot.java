
package org.usfirst.frc.team5518.robot;

import org.usfirst.frc.team5518.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5518.robot.subsystems.FuelShooter;
import org.usfirst.frc.team5518.robot.OI;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	Ultrasonic ultra = new Ultrasonic(1,0);
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final FuelShooter shooter = new FuelShooter();
	
	public static AnalogInput ultrasonicPort0;
	private ADXRS450_Gyro gyro;
	public NetworkTable table;
	public UsbCamera camera;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		ultrasonicPort0 = new AnalogInput(0);
		gyro = new ADXRS450_Gyro();
		table = NetworkTable.getTable("datatable");
		
		camera = CameraServer.getInstance().startAutomaticCapture("TestRigCamera",0);
		camera.setResolution(1280, 720);
		ultra.setAutomaticMode(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
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

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		

		
//		double[] defaultValue = new double[0];
//		while (true) {
//			double[] areas = table.getNumberArray("area", defaultValue);
//			System.out.print("areas: ");
//			for (double area : areas) {
//				System.out.print(area + " ");
//			}
//			System.out.println();
//		}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		gyro.calibrate();
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		double avgVoltage = ultrasonicPort0.getAverageVoltage();
		//System.out.println(avgVoltage);
		SmartDashboard.putNumber("Ultrasonic", avgVoltage);
		
		double angle = gyro.getAngle();
		//System.out.println(angle);
		SmartDashboard.putNumber("Gyro Angle", angle);
		
		double srDistance = ultra.getRangeInches();
		SmartDashboard.putNumber("SR04 Range", srDistance);
		Scheduler.getInstance().run();
	
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
