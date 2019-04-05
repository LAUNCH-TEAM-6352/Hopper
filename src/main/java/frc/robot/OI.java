/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MoveCargo;
import frc.robot.commands.MoveFrontLegs;
import frc.robot.commands.MoveLift;
import frc.robot.commands.MoveRearLegs;
import frc.robot.commands.ToggleLimelight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public final XboxController gameController = new XboxController(0);
	public final XboxController gameController1 = new XboxController(1);

	public final Joystick joystickLeft = new Joystick(2);
	public final Joystick joystickRight = new Joystick(3);

	// Give meaningful names to the game controller buttons:
	public final static int gameControllerButtonA = 1;
	public final static int gameControllerButtonB = 2;
	public final static int gameControllerButtonX = 3;
	public final static int gameControllerButtonY = 4;
	public final static int gameControllerButtonBumperLeft = 5;
	public final static int gameControllerButtonBumperRight = 6;
	public final static int gameControllerButtonBack = 7;
	public final static int gameControllerButtonStart = 8;
	public final static int gameControllerButtonStickLeft = 9;
	public final static int gameControllerButtonStickRight = 10;

	public static final RumbleType frontLegsRumbleType = RumbleType.kLeftRumble;
	public static final RumbleType rearLegsRumbleType = RumbleType.kRightRumble;
	public static final RumbleType liftRumbleType = RumbleType.kLeftRumble;

	private Button cargoInButton = new JoystickButton(gameController, gameControllerButtonX);
	private Button cargoOutButton = new JoystickButton(gameController, gameControllerButtonB);

	private Button toggleLimelightButton = new JoystickButton(gameController, gameControllerButtonA);

	private Button liftUpButton = new JoystickButton(gameController, gameControllerButtonBumperLeft);
	private Button liftDownButton = new JoystickButton(gameController, gameControllerButtonBumperRight);

	private Button frontLegExtendButton = new JoystickButton(gameController, gameControllerButtonBack);
	private Button rearLegsExtendButton = new JoystickButton(gameController, gameControllerButtonStart);

	// SmartDashboard keys:
	public final static String dashboardCargoMoverInSpeed = "Cargo In Speed";
	public final static String dashboardCargoMoverOutSpeed = "Cargo Out Speed";

	public final static String dashboardLiftUpSpeed = "Lift Up Speed";
	public final static String dasboardLiftDownSpeed = "Lift Down Speed";

	public final static String dashboardLeftLegRetractSpeed = "Left Leg Retract Speed";
	public final static String dashboardLeftLegExtendSpeed = "Left Leg Extend Speed";

	public final static String dashboardRightLegRetractSpeed = "Right Leg Retract Speed";
	public final static String dashboardRightLegExtendSpeed = "Right Leg Extend Speed";

	public final static String dashboardRearLegsExtendSpeed = "Rear Legs Extend Speed";
	public final static String dashboardRearLegsRetractSpeed = "Rear Legs Retract Speed";

	public final static String dashboardExtendLimitLeftLeg = "Ext Limit L Leg";
	public final static String dashboardRetractLimitLeftLeg = "Ret Limit L Leg"; 

	public final static String dashboardExtendLimitRightLeg = "Ext Limit R Leg";
	public final static String dashboardRetractLimitRightLeg = "Ret Limit R Leg";

	public final static String dashboardExtendLimitRearLegs = "Ext Limit Rear Legs";
	public final static String dashboardRetractLimitRearLegs = "Ret Limit Rear Legs";

	public final static String dashboardExtendLimitLift = "Ext Limit Lift";
	public final static String dashboardRetractLimitLift = "Ret Limit Lift";

	public final static String dashboardUsbCameraFps = "USB Camera FPS";

	public static final String dashboardRearLegsExtScale = "Rear Legs Ext scale";
	public static final String dashboardRearLegsExtCount = "Rear Legs Ext cnt";
	public static final String dashboardRearLegsExtDistance = "Rear Legs Ext dst";

	public static final String dashboardRearLegsWinchMotorDirection = "Rear Legs Winch dir";
	public static final String dashboardRearLegsWinchMotorRpm = "Rear Legs Winch rpm";
	public static final String dashboardRearLegsWinchMotorCount = "Rear Legs Winch cnt";
	public static final String dashboardRearLegsWinchMotorDistance = "Rear Legs Winch dst";
	public static final String dashbaordRearLegsWinchMotorSpeed = "Rear Legs Winch Speed";

	public static final String dashboardAccelerometerX = "Accel X";
	public static final String dashboardAccelerometerY = "Accel Y";
	public static final String dashboardAccelerometerZ = "Accel Z";

	public final static String dashboardFrontLegsRumblePower = "Front Legs Rumble Power";
	public final static String dashboardRearLegsRumblePower = "Rear Legs Rumble Power";
	public final static String dashboardLiftRumblePower = "Lift Rumble Power";

	// Constructor:
	public OI()
	{
		// Bind buttons to commands:
		cargoInButton.whileHeld(new MoveCargo(dashboardCargoMoverInSpeed));
		cargoOutButton.whileHeld(new MoveCargo(dashboardCargoMoverOutSpeed));

		liftUpButton.whileHeld(new MoveLift(dashboardLiftUpSpeed));
		liftDownButton.whileHeld(new MoveLift(dasboardLiftDownSpeed));

		frontLegExtendButton.whileHeld(new MoveFrontLegs(dashboardLeftLegExtendSpeed, dashboardRightLegExtendSpeed));
		rearLegsExtendButton.whileHeld(new MoveRearLegs(dashboardRearLegsExtendSpeed));

		toggleLimelightButton.whenPressed(new ToggleLimelight());

		// Put default values on SmartDashboard:
		SmartDashboard.putNumber(dashboardCargoMoverInSpeed, 1.0);
		SmartDashboard.putNumber(dashboardCargoMoverOutSpeed, -0.5);

		SmartDashboard.putNumber(dashboardLiftUpSpeed, 1.0);
		SmartDashboard.putNumber(dasboardLiftDownSpeed, -1.0);

		SmartDashboard.putNumber(dashboardLeftLegExtendSpeed, 1.0);
		SmartDashboard.putNumber(dashboardLeftLegRetractSpeed, -1.0);

		SmartDashboard.putNumber(dashboardRightLegExtendSpeed, 0.9);
		SmartDashboard.putNumber(dashboardRightLegRetractSpeed, -0.9);

		SmartDashboard.putNumber(dashboardRearLegsExtendSpeed, 0.55);
		SmartDashboard.putNumber(dashboardRearLegsRetractSpeed, -1.0);

		SmartDashboard.putData("Toggle Limelight", new ToggleLimelight());

		SmartDashboard.putNumber(dashboardUsbCameraFps, RobotMap.usbCameraFrameRate);

		SmartDashboard.putNumber(dashboardFrontLegsRumblePower, 1.0);
		SmartDashboard.putNumber(dashboardRearLegsRumblePower, 1.0);
		SmartDashboard.putNumber(dashboardLiftRumblePower, 1.0);
	}
}
