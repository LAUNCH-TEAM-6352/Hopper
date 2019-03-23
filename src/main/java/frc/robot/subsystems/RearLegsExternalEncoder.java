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
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.ReportRearLegsExternalEncoder;

/**
 * Wraps up the rear legs lift encoders as a subsystem.
 * This talks to an external encoder.
 */
public class RearLegsExternalEncoder extends Subsystem
{
	private Encoder encoder;

	public RearLegsExternalEncoder()
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
		setDefaultCommand(new ReportRearLegsExternalEncoder());
	}
	
	/**
	 * Reports status to the smart dashboard.
	 */
	public void report()
	{
		SmartDashboard.putNumber(OI.dashboardRearLegsExtScale, encoder.getEncodingScale());
		SmartDashboard.putNumber(OI.dashboardRearLegsExtCount, get());
		SmartDashboard.putNumber(OI.dashboardRearLegsExtDistance, getDistance());
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
