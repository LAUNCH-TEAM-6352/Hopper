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

/**
 * Add your docs here.
 */
public class FourBarLinkage extends Subsystem
{
	private SpeedController motor;

	// The following are used to detect when the 4-bar linkage hits its hard stop goind down:
	private double lastCount = 0.0;
	private boolean hasRunNegativeOnce = false;
	private boolean isAtDownLimit = false;

	public FourBarLinkage()
	{
		motor = new WPI_VictorSPX(RobotMap.fourBarLinkageMotorCanDeviceId);
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
		// Assume we will be turning off rumble:
		double rumblePower = 0;

		boolean resetEncoder = false;
		double currentCount = Robot.fourBarLinkageEncoder.get();

		if (speed > 0 || speed == 0)
		{
			hasRunNegativeOnce = false;
			isAtDownLimit = false;
		}

		if (speed > 0 &&
			(Robot.fourBarLinkageEncoder.getDistance() > RobotMap.fourBarLinkageUpLimitDistance))
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardFourBarLinkageRumblePower, 0.0);
			hasRunNegativeOnce = false;
		}
		else if (speed < 0)
		{
			if (isAtDownLimit ||
				(hasRunNegativeOnce && Math.abs(currentCount - lastCount) < RobotMap.fourBarLinkageStoppedDeltaCount))
			{
				resetEncoder = true;
				rumblePower = SmartDashboard.getNumber(OI.dashboardFourBarLinkageRumblePower, 0.0);
				isAtDownLimit = true;
				speed = 0;
			}
			hasRunNegativeOnce = true;
		}

		Robot.oi.gameController.setRumble(OI.fourBarLinkageRumbleType, rumblePower);

		setSpeed(speed);

		if (resetEncoder)
		{
			Robot.fourBarLinkageEncoder.reset();
			currentCount = 0;
		}

		lastCount = currentCount;
	}

	public void setSpeed(double speed)
	{
		motor.set(speed);
	}
}
