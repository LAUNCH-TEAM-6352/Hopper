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
import frc.robot.commands.ReportFourBarLinkageEncoder;

/**
 * An encoder for the lift.
 */
public class FourBarLinkageEncoder extends Subsystem
{
	private Encoder encoder;

	public FourBarLinkageEncoder()
	{
		// Create the encoder:
		encoder = new Encoder(
				RobotMap.fourBarLinkageEncoderChannelA,
				RobotMap.fourBarLinkageEncoderChannelB,
				RobotMap.fourBarLinkageEncoderIsReversed);
		
		// This sets each unit of distance to one revolution:
		encoder.setDistancePerPulse(1.0 / RobotMap.fourBarLinkageEncoderPulsesPerRev);

		encoder.setReverseDirection(RobotMap.fourBarLinkageEncoderIsReversed);
		
		// Reset the encoder:
		reset();
	}
	
	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportFourBarLinkageEncoder());
	}
	
	/**
	 * Reports status to the smart dashboard.
	 */
	public void report()
	{
		SmartDashboard.putNumber("4-bar scale:", encoder.getEncodingScale());
		SmartDashboard.putNumber("4-bar count:", get());
		SmartDashboard.putNumber(" 4-bar dist:", getDistance());
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
