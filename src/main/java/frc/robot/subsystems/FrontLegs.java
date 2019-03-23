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
		if (!RobotMap.isCompetitionRobot)
		{
			motorLeft = new Spark(RobotMap.frontLeg1MotorPwmChannel);
			motorRight = new Spark(RobotMap.frontLeg2MotorPwmChannel);
		}
	}

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new RunFrontLegs());
	}

	public void setSpeed(double speedLeft, double speedRight)
	{
		// Check for being at limit:
		if ((Robot.frontLegLimitSwitches.isAtExtendLimitLeft() && speedLeft > 0) ||
			(Robot.frontLegLimitSwitches.isAtRetractLimitLeft() && speedLeft < 0))
		{
			speedLeft = 0;
		}
		motorLeft.set(speedLeft);

		// Check for being at limit:
		if ((Robot.frontLegLimitSwitches.isAtExtendLimitRight() && speedRight > 0) ||
			(Robot.frontLegLimitSwitches.isAtRetractLimitRight() && speedRight < 0))
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
