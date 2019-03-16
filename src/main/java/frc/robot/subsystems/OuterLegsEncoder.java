/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ReportRearLegsEncoder;

/**
 * Wraps up the power cube lift encoder as a subsystem.
 */
public class OuterLegsEncoder extends Subsystem
{
	private Encoder encoder;

	public OuterLegsEncoder()
	{
		// Create the encoder:
		encoder = new Encoder(
				RobotMap.rearLegsEncoderChannelA,
				RobotMap.rearLegsEncoderChannelB,
				RobotMap.rearLegsEncoderIsReversed);
		
		// This sets each unit of distance to one revolution:
		encoder.setDistancePerPulse(1.0 / RobotMap.rearLegsEncoderPulsesPerRev);
		
		// Reset the encoder:
		reset();
	}
	
	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportRearLegsEncoder());
	}
	
	/**
	 * Reports status to the smart dashboard.
	 */
	public void report()
	{
		SmartDashboard.putNumber("Legs scale:", encoder.getEncodingScale());
		SmartDashboard.putNumber("Legs count:", get());
		SmartDashboard.putNumber(" Legs dist:", getDistance());

	}
	
	/**
	 * Returns the current count.
	 */
	public double get()
	{
		return encoder.get();
	}
	
	/**
	 * Returns the current distance.
	 */
	public double getDistance()
	{
		return encoder.getDistance();
	}
	
	/**
	 * Resets (zeroes out) the encoder.
	 */
	public void reset()
	{
		encoder.reset();
		report();
	}
	
}
