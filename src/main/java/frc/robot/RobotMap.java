/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// The following indicates if we are running the competition robot:
	public static final boolean isCompetitionRobot = false;

	// The following deal with the Limelight vision processing camera:
	public static final String limelightTableKey = "limelight";
	public static final String limelightXPositionKey = "tx";
	public static final String limelightYPositionKey = "ty";
	public static final String limelightAreaKey = "ta";
	public static final String limelightCamModeKey = "camMode";
	public static final String limelightLedModeKey = "ledMode";

	// The following define Digital IO channels:
	public static final int centerLegExtendLimitSwitchChannel = 9;
	public static final int centerLegRetractLimitSwitchChannel = 8;

	public static final int outerLegsEncoderChannelA = 0;
	public static final int outerLegsEncoderChannelB = 1;
	public static final int outerLegsEncoderChannelX = 2;
	public static final int outerLegsEncoderPulsesPerRev = 512;
	public static final boolean outerLegsEncoderIsReversed = true;

	// The following are PWM channels:
	public static final int leftDriveMotorPwmChannel = 2;
	public static final int rightDriveMotorPwmChannel = 3;
	public static final int centerLegMotorPwmChannel = 0;
	public static final int outerLegsMotorPwmChannel = 1;

	// The following are CAN channels:
	public static final int leftDriveMotorCanDeviceId = 1;
	public static final int rightDriveMotorCanDeviceId = 2;
	public static final int centerLegMotorCanDeviceId = 4;
	public static final int outerLegsMotorCanDeviceId = 3;
	public static final int liftMotorCanDeviceId = 5;
	public static final int cargoMotorCanDeviceId = 6;
}
