/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.NidecBrushless;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.RunRearLegs;

/**
 * Add your docs here.
 */
public class RearLegs extends Subsystem
{
	private SpeedController winchMotor;
	private SpeedController driveMotor;

	public RearLegs()
	{
		winchMotor = RobotMap.isCompetitionRobot
			? new WPI_TalonSRX(RobotMap.rearLegsWinchMotorCanDeviceId)
			: new Victor(RobotMap.rearLegsWinchMotorPwmChannel);

		driveMotor = RobotMap.isCompetitionRobot
			? new Victor(RobotMap.rearLegsDriveMotorPwmChannel)
			: new Victor(RobotMap.rearLegsDriveMotorPwmChannel);

		winchMotor.setInverted(true);
		driveMotor.setInverted(true);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunRearLegs());
	}

	public void stopWinch()
	{
		setWinchSpeed(0);
	}

	public void setWinchSpeed(double speed)
	{
		// Check for being at limit:
		if ((Robot.legLimitSwitches.isAtExtendLimitRear() && speed > 0) ||
			(Robot.legLimitSwitches.isAtRetractLimitRear() && speed < 0))
		{
			speed = 0;
		}
		winchMotor.set(speed);
		SmartDashboard.putNumber(OI.dashbaordRearLegsWinchMotorSpeed, speed);
	}

	public void stopDrive()
	{
		setDriveSpeed(0);
	}

	public void setDriveSpeed(double speed)
	{
		driveMotor.set(speed);
	}
}
