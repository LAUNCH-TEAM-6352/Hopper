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
import frc.robot.RobotMap;
import frc.robot.commands.RunMotorControllerCalibrator;

/**
 * Add your docs here.
 */
public class MotorControllerCalibrator extends Subsystem
{
	private SpeedController controller;

	public MotorControllerCalibrator()
	{
		controller = new Spark(RobotMap.calibrationPwmChannel);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new RunMotorControllerCalibrator());
	}

	public void stop()
	{
		setSpeed(0);
	}

	public void setSpeed(double speed)
	{
		controller.set(speed);
	}
}
