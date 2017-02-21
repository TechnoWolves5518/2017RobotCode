package org.usfirst.frc.team5518.robot;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

//Robot vision branch

public class Robot extends IterativeRobot {
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private VisionThread visionThread;
	private double centerX = 0.0;
	private RobotDrive drive;
	private final Object imgLock = new Object();

	@Override
	public void robotInit() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		visionThread = new VisionThread(camera, new RetroTapePipeline(), pipeline -> {
			
			if (!pipeline.filterContoursOutput().isEmpty()) { // if the output from the process has something in it
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0)); //get the first detected rect from the output
				synchronized (imgLock) {
					centerX = r.x + (r.width / 2); //find the center of that rect and calculate
					System.out.print(centerX);
				}
			}
			else {
				System.out.println(visionThread.getName()+" The pipeline is empty");
				centerX = 160;
			}
		});
		visionThread.setName("T"+System.currentTimeMillis());
		
		visionThread.start();
		drive = new RobotDrive(0, 1);
	}

	@Override
	public void autonomousPeriodic() {
		//drive.arcadeDrive(0, 1);
		
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
			System.out.println(centerX);
		}
		double dist = centerX - (IMG_WIDTH / 2);
		
		System.out.println("CenterX =  " + centerX + "  dist =  " + dist);
		
		if (dist > 40) {
			drive.arcadeDrive(0, dist * -0.0025);
		}
		else if (dist < -40) {
			drive.arcadeDrive(0, dist * 0.0025);
		}
		else {
			drive.arcadeDrive(0, 0);
		}
	}
	
	public void disabledInit() {
		System.out.println("Default IterativeRobot.disabledInit() method... Overload me!");
		//visionThread.interrupt();
	}
}

//package org.usfirst.frc.team5518.robot;
//
//import org.usfirst.frc.team5518.robot.RetroTapePipeline;
//
//import edu.wpi.cscore.CvSink;
//import edu.wpi.cscore.CvSource;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.IterativeRobot;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//
///**
// * This is a demo program showing the use of OpenCV to do vision processing. The
// * image is acquired from the USB camera, then a rectangle is put on the image and
// * sent to the dashboard. OpenCV has many methods for different types of
// * processing.
// */
//public class Robot extends IterativeRobot {
//	Thread visionThread;
//
//	@Override
//	public void robotInit() {
//		visionThread = new Thread(() -> {
//			// Get the UsbCamera from CameraServer
//			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//			// Set the resolution
//			camera.setResolution(640, 480);
//
//			// Get a CvSink. This will capture Mats from the camera
//			CvSink cvSink = CameraServer.getInstance().getVideo();
//			// Setup a CvSource. This will send images back to the Dashboard
//			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
//
//			// Mats are very memory expensive. Lets reuse this Mat.
//			Mat mat = new Mat();
//
//			// This cannot be 'true'. The program will never exit if it is. This
//			// lets the robot stop this thread when restarting robot code or
//			// deploying.
//			while (!Thread.interrupted()) {
//				// Tell the CvSink to grab a frame from the camera and put it
//				// in the source mat.  If there is an error notify the output.
//				if (cvSink.grabFrame(mat) == 0) {
//					// Send the output the error.
//					outputStream.notifyError(cvSink.getError());
//					// skip the rest of the current iteration
//					continue;
//				}
//				// Put a rectangle on the image
//				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
//						new Scalar(255, 255, 255), 5);
//				// Give the output stream a new image to display
//				outputStream.putFrame(mat);
//			}
//		});
//		visionThread.setDaemon(true);
//		visionThread.start();
//	}
//}
