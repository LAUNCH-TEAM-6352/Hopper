/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.awt.geom.Ellipse2D;

import com.ctre.phoenix.motorcontrol.WPI_MotorSafetyImplem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.RetractFrontLegs;

/**
 * Add your docs here.
 */
public class FrontLegs extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController motorLeft;
	private SpeedController motorRight;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public FrontLegs()
	{
		if (RobotMap.isCompetitionRobot)
		{
			motorLeft = new WPI_TalonSRX(RobotMap.leftLegMotorCanDeviceId);
			motorRight = new WPI_TalonSRX(RobotMap.rightLegMotorCanDeviceId);
			//motorLeft.setInverted(true);
			//motorRight.setInverted(true);
		}
		else
		{
			motorLeft = new Spark(RobotMap.frontLegLeftMotorPwmChannel);
			motorRight = new Spark(RobotMap.frontLegRightMotorPwmChannel);
			motorRight.setInverted(false);
		}
	}

	@Override
	public void initDefaultCommand()
	{
		setDefaultCommand(new RetractFrontLegs(OI.dashboardLeftLegRetractSpeed, OI.dashboardRightLegRetractSpeed));
	}

	public void setSpeed(double speedLeft, double speedRight)
	{
		// Assume we are going to rumble:
		boolean isLeftAtLimit = false;
		boolean isRightAtLimit = false;

		// Handle left leg:
		if (speedLeft > 0 && Robot.limitSwitches.isAtExtendLimitLeftLeg())
		{
			speedLeft = 0;
			isLeftAtLimit = true;
		}
		else if (speedLeft < 0 && Robot.limitSwitches.isAtRetractLimitLeftLeg())
		{
			speedLeft = 0;
			isLeftAtLimit = true;
		}
		motorLeft.set(speedLeft);

		// Handle right leg:
		if (speedRight > 0 && Robot.limitSwitches.isAtExtendLimitRightLeg())
		{
			speedRight = 0;
			isRightAtLimit = true;
		}
		else if (speedRight < 0 && Robot.limitSwitches.isAtRetractLimitRightLeg())
		{
			speedRight = 0;
			isRightAtLimit = true;
		}
		motorRight.set(speedRight);

		double rumblePower = isLeftAtLimit && isRightAtLimit
			? SmartDashboard.getNumber(OI.dashboardFrontLegsRumblePower, 0.0)
			: 0;
		Robot.oi.gameController.setRumble(OI.frontLegsRumbleType, rumblePower);

		SmartDashboard.putNumber(" Left Leg Speed", speedLeft);
		SmartDashboard.putNumber("Right Leg Speed", speedRight);
	}

	public void stop()
	{
		setSpeed(0, 0);
	}
}
