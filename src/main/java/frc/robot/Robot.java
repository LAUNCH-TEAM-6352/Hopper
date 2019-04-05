/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.UsbCameraInfo;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Accelerometer;
import frc.robot.subsystems.CargoMover;
import frc.robot.subsystems.FrontLegs;
import frc.robot.subsystems.LimitSwitches;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.MotorControllerCalibrator;
import frc.robot.subsystems.RearLegsExternalEncoder;
import frc.robot.subsystems.RearLegsInternalEncoder;
import frc.robot.subsystems.RearLegs;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
	public static OI oi;

	private UsbCamera usbCameras[];

	// The following deal with the Limelight vision processing camera:
	public static NetworkTable limelightTable;
	public static NetworkTableEntry limelightXPositionEntry;
	public static NetworkTableEntry limelightYPositionEntry;
	public static NetworkTableEntry limelightAreaEntry;
	public static NetworkTableEntry limelightCamModeEntry;
	public static NetworkTableEntry limelightLedModeEntry;

	public static double limelightXPosition;
	public static double limelightYPosition;
	public static double limelightArea;

	// Define some subsystems:
	public static DriveTrain driveTrain = null;
	public static Lift lift = null;
	public static FrontLegs frontLegs = null;
	public static LimitSwitches limitSwitches = null;
	public static RearLegs rearLegs = null;
	public static RearLegsExternalEncoder rearLegsExternalEncoder = null;
	public static RearLegsInternalEncoder rearLegsInternalEncoder = null;
	public static CargoMover cargoMover = null;
	public static MotorControllerCalibrator motorControllerCalibrator = null;
	public static Accelerometer accelerometer = null;

	// Static initialization
	static
	{
		if (RobotMap.isCompetitionRobot)
		{
			lift = new Lift();
			cargoMover = new CargoMover();
			driveTrain = new DriveTrain();
			frontLegs = new FrontLegs();
			rearLegs = new RearLegs();
			limitSwitches = new LimitSwitches();
		}
		else
		{
			lift = new Lift();
			driveTrain = new DriveTrain();
			frontLegs = new FrontLegs();
			limitSwitches = new LimitSwitches();
			cargoMover = new CargoMover();
			rearLegs = new RearLegs();
			//rearLegsExternalEncoder = new RearLegsExternalEncoder();
			//rearLegsInternalEncoder = new RearLegsInternalEncoder();
			//motorControllerCalibrator = new MotorControllerCalibrator();
			//accelerometer = new Accelerometer();
		}
	}


	/**
	 * The following deal with the REV Digit Board:
	 */
	// The board itself:
	private REVDigitBoard digitBoard;

	// Keeps track on when to refresh the voltage display:
	private int voltageRefreshCounter = 0;
	private static final int voltageRefreshCount = 10;

	// Keeps track on when to refresh the POT display:
	private int potRefreshCounter = 0;
	private static final int potRefreshCount = 10;

	// REV digit board button states:
	private boolean buttonA = false;
	private boolean buttonB = false;

	// User options selected via the REV digit board:
	private static final String[] options = { "STAY", "LVL1", "LVL2" };
	private int optionIndex = 0;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		oi = new OI();

		// Set up digit board:
		digitBoard = new REVDigitBoard();

		// Set up Limelight network table access:
		limelightTable = NetworkTableInstance.getDefault().getTable(RobotMap.limelightTableKey);
		limelightXPositionEntry = limelightTable.getEntry(RobotMap.limelightXPositionKey);
		limelightYPositionEntry = limelightTable.getEntry(RobotMap.limelightYPositionKey);
		limelightAreaEntry = limelightTable.getEntry(RobotMap.limelightAreaKey);
		limelightCamModeEntry = limelightTable.getEntry(RobotMap.limelightCamModeKey);
		limelightLedModeEntry = limelightTable.getEntry(RobotMap.limelightLedModeKey);

		if (RobotMap.isUseUsbCameras)
		{
			// Set up USB cameras.
			// Do not delete the following line!
			CameraServer.getInstance();
			initializeUsbCameras();
		}

		// chooser.addOption("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic()
	{
		pollDigitBoard();
		//pollLimelight();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit()
	{
	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		//m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		// if (m_autonomousCommand != null)
		// {
		// 	m_autonomousCommand.start();
		// }
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		// if (m_autonomousCommand != null)
		// {
		// 	m_autonomousCommand.cancel();
		// }
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{
	}

	/**
	 * Polls the digit board.
	 */
	private void pollDigitBoard()
	{
		/**
		 * The following code interacts with the REV Digit Board. By default, the
		 * current battery voltage is displayed on the digit board. Pushing the A button
		 * cycles through the autonomous options.
		 */
		if (digitBoard.getButtonA() != buttonA)
		{
			buttonA = !buttonA;
			if (buttonA)
			{
				if (++optionIndex >= options.length)
				{
					optionIndex = 0;
				}
				SmartDashboard.putString("Autonomous", options[optionIndex]);
			}
		}

		if (digitBoard.getButtonB() != buttonB)
		{
			buttonB = !buttonB;
		}

		if (buttonA)
		{
			digitBoard.display(options[optionIndex]);
		} else if (buttonB)
		{
			if (++potRefreshCounter > potRefreshCount)
			{
				potRefreshCounter = 0;
			}
			if (potRefreshCounter == 0)
			{
				digitBoard.display(digitBoard.getPot());
			}
		} else
		{
			if (++voltageRefreshCounter > voltageRefreshCount)
			{
				voltageRefreshCounter = 0;
			}
			if (voltageRefreshCounter == 0)
			{
				digitBoard.display(RobotController.getBatteryVoltage());
			}
		}
	}

	/**
	 * Polls the Limelight vision processing camera.
	 */
	private void pollLimelight()
	{
		// read values periodically
		limelightXPosition = limelightXPositionEntry.getDouble(0.0);
		limelightYPosition = limelightYPositionEntry.getDouble(0.0);
		limelightArea = limelightAreaEntry.getDouble(0.0);

		// post to smart dashboard periodically
		SmartDashboard.putNumber("LimelightX", limelightXPosition);
		SmartDashboard.putNumber("LimelightY", limelightYPosition);
		SmartDashboard.putNumber("LimelightArea", limelightArea);
	}
	
	/**
	 * Starts up USB cameras, starting capture on each one.
	 */
	private void initializeUsbCameras()
	{
		UsbCameraInfo infos[] = UsbCamera.enumerateUsbCameras();
		usbCameras = new UsbCamera[infos.length];
		for (int i = 0; i < usbCameras.length; i++)
		{
			usbCameras[i] = new UsbCamera("USB" + i, infos[i].path);
			boolean setRes = usbCameras[i].setResolution(RobotMap.usbCameraImageWidth, RobotMap.usbCameraImageHeight);
			boolean setFps = usbCameras[i].setFPS((int) SmartDashboard.getNumber(OI.dashboardUsbCameraFps, RobotMap.usbCameraFrameRate));
			System.out.println("Created USB camera " + i + ": " + usbCameras[i].getPath() + ", setRes=" + setRes + ", setFps=" + setFps);
			CameraServer.getInstance().startAutomaticCapture(usbCameras[i]);
		}
	}
}
