/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
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
		winchMotor = new WPI_VictorSPX(RobotMap.rearLegsLiftMotorCanDeviceId);

		driveMotor = new WPI_VictorSPX(RobotMap.rearLegsDriveMotorCanDeviceId);

		winchMotor.setInverted(false);
		driveMotor.setInverted(false);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand()
	{
		setDefaultCommand(new RunRearLegs());
	}

	public void stopWinch()
	{
		setWinchSpeed(0);
	}

	public void setWinchSpeed(double speed)
	{
		// Assume we will be turning off rumble:
		double rumblePower = 0;

		// Check for being at limit:
		if (speed > 0 && Robot.limitSwitches.isAtExtendLimitRearLegs())
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardRearLegsRumblePower, 0.0);
		}
		else if (speed < 0 && Robot.limitSwitches.isAtRetractLimitRearLegs())
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardRearLegsRumblePower, 0.0);
		}

		Robot.oi.gameController.setRumble(OI.rearLegsRumbleType, rumblePower);
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
