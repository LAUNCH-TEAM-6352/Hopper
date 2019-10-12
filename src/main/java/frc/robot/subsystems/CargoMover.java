/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoMover extends Subsystem
{
	private double lastCount = 0;

	private boolean hasRunNegativeOnce = false;

	private SpeedController motor;

	public CargoMover()
	{
		motor = RobotMap.isCompetitionRobot
			? new WPI_VictorSPX(RobotMap.cargoMotorCanDeviceId)
			: new Spark(RobotMap.cargoMotorPwmChannel);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void stop()
	{
		setSpeed(0);
	}

	public void move(double speed)
	{
		boolean resetEncoder = false;
		double currentCount = Robot.rearLegsExternalEncoder.get();

		if (speed < 0)
		{
			if (hasRunNegativeOnce && Math.abs(currentCount - lastCount) < 50)
			{
				resetEncoder = true;
				speed = 0;
			}
			hasRunNegativeOnce = true;
		}
		else
		{
			hasRunNegativeOnce = false;
		}

		setSpeed(speed);

		if (resetEncoder)
		{
			Robot.rearLegsExternalEncoder.reset();
			currentCount = 0;
		}

		lastCount = currentCount;
	}

	public void setSpeed(double speed)
	{
		motor.set(speed);
	}
}
