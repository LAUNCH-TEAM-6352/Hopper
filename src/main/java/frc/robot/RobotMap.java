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

	// Indicates if the robot is running in demo mode:
	public final static boolean isDemoMode = false;

	// Indicates if we should support USB  cameras:
	public static final boolean isUseUsbCameras = false;

	// Miscellaneous constants:
	public static final int rearLegsMotorTachPulsesPerRevolution = 15;

	// The following deal with the Limelight vision processing camera:
	public static final String limelightRawTableKey = "limelight-raw";
	public static final String limelightCookedTableKey = "limelight-cooked";
	public static final String limelightTargetAcquiredKey = "tv";
	public static final String limelightXPositionKey = "tx";
	public static final String limelightYPositionKey = "ty";
	public static final String limelightAreaKey = "ta";
	public static final String limelightCamModeKey = "camMode";
	public static final String limelightLedModeKey = "ledMode";

	public static final int limelightSampleCount = 100;

	// The following deal with the Diagnostics network table:
	public static final String diagnosticsTableKey = "diagnostics";
	public static final String diagnosticsPeriodKey = "period";

	// The following define Digital IO channels:
	public static final int frontLegLeftExtendLimitSwitchChannel = 9;
	public static final int frontLegLeftRetractLimitSwitchChannel = 8;
	public static final int frontLegRightExtendLimitSwitchChannel = 7;
	public static final int frontLegRightRetractLimitSwitchChannel = 6;

	public static final int rearLegsExtendLimitSwitchChannel = 4;
	public static final int rearLegsRetractLimitSwitchChannel = 5;

	public static final int liftExtendLimitSwitchChannel = 2;
	public static final int liftRetractLimitSwitchChannel = 3;
	public static final double liftExtendLimitDistance = 1050.0;

	public static final int fourBarLinkageDownLimitSwitchChannel = 1;

	// The following are for quadrature encoders:
	public static final int liftEncoderChannelA = 11; 
	public static final int liftEncoderChannelB = 12;
	public static final int liftEncoderPulsesPerRev = 1024;
	public static final boolean liftEncoderIsReversed = true;

	public static final int fourBarLinkageEncoderChannelA = 23; 
	public static final int fourBarLinkageEncoderChannelB = 24;
	public static final int fourBarLinkageEncoderPulsesPerRev = 1024;
	public static final boolean fourBarLinkageEncoderIsReversed = true;

	// The following are CAN channels:
	public static final int leftDriveMotor1CanDeviceId = 1;
	public static final int rightDriveMotor1CanDeviceId = 2;
	public static final int leftLegMotorCanDeviceId = 4;
	public static final int rightLegMotorCanDeviceId = 3;
	public static final int rearLegsLiftMotorCanDeviceId = 7;
	public static final int rearLegsDriveMotorCanDeviceId = 8;
	public static final int liftMotorCanDeviceId = 5;
	public static final int cargoMotorCanDeviceId = 6;
	public static final int leftDriveMotor2CanDeviceId = 9;
	public static final int rightDriveMotor2CanDeviceId = 10;
	public static final int fourBarLinkageMotorCanDeviceId = 11;

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
