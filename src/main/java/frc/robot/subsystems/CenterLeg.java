/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CenterLeg extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController motor;
	private DigitalInput extendLimit = new DigitalInput(RobotMap.centerLegExtendLimitSwitchChannel);
	private DigitalInput retractLimit = new DigitalInput(RobotMap.centerLegRetractLimitSwitchChannel);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public CenterLeg()
	{
		motor = null;
		/***
		motor = RobotMap.isCompetitionRobot
			? new WPI_TalonSRX(RobotMap.centerLegMotorCanDeviceId)
			: new Spark(RobotMap.centerLegMotorPwmChannel);
			***/
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
		if ((!extendLimit.get() && speed > 0) || (!retractLimit.get() && speed < 0))
		{
			speed = 0;
		}
		motor.set(speed);
		SmartDashboard.putNumber("LA Speed", speed);
	}

	public void stop()
	{
		setSpeed(0);
	}
}
