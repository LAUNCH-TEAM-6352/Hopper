/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RunOuterLegs;

/**
 * Add your docs here.
 */
public class OuterLegs extends Subsystem
{
	private SpeedController motor;

	public OuterLegs()
	{
		motor = RobotMap.isCompetitionRobot
			? new WPI_TalonSRX(RobotMap.outerLegsMotorCanDeviceId)
			: new Spark(RobotMap.outerLegsMotorPwmChannel);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunOuterLegs());
	}

	public void stop()
	{
		setSpeed(0);
	}

	public void move(double speed)
	{
		setSpeed(speed);
	}

	public void setSpeed(double speed)
	{
		motor.set(speed);
	}
}
