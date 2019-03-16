/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CenterLeg extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController motorLeft;
	private SpeedController motorRight;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public CenterLeg()
	{
		if (!RobotMap.isCompetitionRobot)
		{
			motorLeft = new Spark(RobotMap.centerLeg1MotorPwmChannel);
			motorRight = new Spark(RobotMap.centerLeg2MotorPwmChannel);
		}
	}

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new RunCenterLeg());
	}

	public void setSpeed(double speedLeft, double speedRight)
	{
		// Check for being at limit:
		if ((Robot.centerLegLimitSwitches.isAtExtendLimitLeft() && speedLeft > 0) ||
			(Robot.centerLegLimitSwitches.isAtRetractLimitLeft() && speedLeft < 0))
		{
			speedLeft = 0;
		}
		motorLeft.set(speedLeft);

		// Check for being at limit:
		if ((Robot.centerLegLimitSwitches.isAtExtendLimitRight() && speedRight > 0) ||
			(Robot.centerLegLimitSwitches.isAtRetractLimitRight() && speedRight < 0))
		{
			speedRight = 0;
		}
		motorRight.set(speedRight);

		SmartDashboard.putNumber(" Left Leg Speed", speedLeft);
		SmartDashboard.putNumber("Right Leg Speed", speedRight);
	}

	public void stop()
	{
		setSpeed(0, 0);
	}
}
