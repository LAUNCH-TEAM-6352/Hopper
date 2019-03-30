/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.commands.AccelerometerReporter;

/**
 * Add your docs here.
 */
public class Accelerometer extends Subsystem
{
	private BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();


	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new AccelerometerReporter());
	}

	public void report()
	{
		SmartDashboard.putNumber(OI.dashboardAccelerometerX, accelerometer.getX());
		SmartDashboard.putNumber(OI.dashboardAccelerometerY, accelerometer.getY());
		SmartDashboard.putNumber(OI.dashboardAccelerometerZ, accelerometer.getZ());
	}
}
