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
	public static final boolean isCompetitionRobot = true;

	// Indicates if we should support USB  cameras:
	public static final boolean isUseUsbCameras = false;

	// Miscellaneous constants:
	public static final int rearLegsMotorTachPulsesPerRevolution = 15;

	// The following deal with the Limelight vision processing camera:
	public static final String limelightTableKey = "limelight";
	public static final String limelightXPositionKey = "tx";
	public static final String limelightYPositionKey = "ty";
	public static final String limelightAreaKey = "ta";
	public static final String limelightCamModeKey = "camMode";
	public static final String limelightLedModeKey = "ledMode";

	// The following define Digital IO channels:
	public static final int frontLegLeftExtendLimitSwitchChannel = 9;
	public static final int frontLegLeftRetractLimitSwitchChannel = 8;
	public static final int frontLegRightExtendLimitSwitchChannel = 7;
	public static final int frontLegRightRetractLimitSwitchChannel = 6;

	public static final int rearLegsExtendLimitSwitchChannel = 4;
	public static final int rearLegsRetractLimitSwitchChannel = 5;

	public static final int liftExtendLimitSwitchChannel = 2;
	public static final int liftRetractLimitSwitchChannel = 3;

	// The following are NOT used:
	public static final int rearLegsEncoderChannelA = 90; 
	public static final int rearLegsEncoderChannelB = 91;
	public static final int rearLegsEncoderChannelX = 92;
	public static final int rearLegsEncoderPulsesPerRev = 512;
	public static final boolean rearLegsEncoderIsReversed = true;

	public static final int rearLegsWinchMotorDioChannel = 93;
	public static final int rearLegsWinchMotorTachDioChannel = 94;
	public static final int rearLegsWinchMotorDirDioChannel = 95;

	// The following are PWM channels:
	public static final int calibrationPwmChannel = 0;
	public static final int leftDriveMotorPwmChannel = 2;
	public static final int rightDriveMotorPwmChannel = 3;
	public static final int frontLegLeftMotorPwmChannel = 6;
	public static final int frontLegRightMotorPwmChannel = 9;
	public static final int rearLegsWinchMotorPwmChannel = 8;
	public static final int liftMotorPwmChannel = 7;
	public static final int cargoMotorPwmChannel = 5;
	public static final int rearLegsDriveMotorPwmChannel = 4;
	
	// The following are CAN channels:
	public static final int leftDriveMotorCanDeviceId = 1;
	public static final int rightDriveMotorCanDeviceId = 2;
	public static final int leftLegMotorCanDeviceId = 4;
	public static final int rightLegMotorCanDeviceId = 3;
	public static final int rearLegsLiftMotorCanDeviceId = 7;
	public static final int rearLegsDriveMotorCanDeviceId = 8;
	public static final int liftMotorCanDeviceId = 5;
	public static final int cargoMotorCanDeviceId = 6;

	// The following are for configuring the USB cameras.
	// Network bandwidth from the robot to the driver station is limited by the FMS.
	// When using multiple USB cameras, the image size and frame rate may
	// need to be adjusted to stay within the allowed bandwidth.
	// Information about bandwidth limitations can be found at
	// http://wpilib.screenstepslive.com/s/4485/m/24193/l/705152-fms-whitepaper.
	// According to the white paper, approximately 6.9 Mbps of bandwidth
	// is available for transmitting video images from the robot to the DS.
	//
	// The following resolutions are potentially supported by the
	// Microsoft LifeCam HD 3000:
	//  1280 x 720
	//  960 x 544
	//  800 x 600
	//  800 x 448
	//  640 x 480
	//  640 x 360
	//  424 x 140
	//  352 x 288
	//  320 x 240
	//  176 x 144
	//  160 x 120
	public final static int usbCameraImageWidth = 320;
	public final static int usbCameraImageHeight = 240;
	public final static int usbCameraFrameRate = 15;
}
