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

	private double lastDistance = 0.0;

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

		if (speed > 0 &&
			(Robot.fourBarLinkageEncoder.getDistance() > RobotMap.fourBarLinkageUpLimitDistance))
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardFourBarLinkageRumblePower, 0.0);
		}
		else if (speed < 0 &&
			(Math.abs(Robot.fourBarLinkageEncoder.getDistance() - lastDistance) < RobotMap.fourBarLinkageStoppedDeltaDistance))
		{
			speed = 0;
			Robot.fourBarLinkageEncoder.reset();
			rumblePower = SmartDashboard.getNumber(OI.dashboardFourBarLinkageRumblePower, 0.0);
		}

		Robot.oi.gameController.setRumble(OI.fourBarLinkageRumbleType, rumblePower);

		setSpeed(speed);
	}

	public void setSpeed(double speed)
	{
		if (speed == 0.0)
		{
			lastDistance = 0.0;
		}
		motor.set(speed);
	}
}
