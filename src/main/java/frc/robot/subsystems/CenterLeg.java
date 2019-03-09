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

	private SpeedController motor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public CenterLeg()
	{
		if (!RobotMap.isCompetitionRobot)
		{
			motor = new Spark(RobotMap.centerLegMotorPwmChannel);
		}
	}

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new RunCenterLeg());
	}

	public void setSpeed(double speed)
	{
		// Check for being at limit:
		if ((Robot.centerLegLimitSwitches.isAtExtendLimit() && speed > 0) ||
			(Robot.centerLegLimitSwitches.isAtRetractLimit() && speed < 0))
		{
			speed = 0;
		}
		motor.set(speed);
		SmartDashboard.putNumber("Ceter Leg Speed", speed);
	}

	public void stop()
	{
		setSpeed(0);
	}
}
