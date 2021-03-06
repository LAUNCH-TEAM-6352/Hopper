/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.ReportRearLegsInternalEncoder;

/**
 * Wraps up the Nidec motor interla "encoder" circutry.
 */
public class RearLegsInternalEncoder extends Subsystem
{
	DigitalInput direction = new DigitalInput(RobotMap.rearLegsWinchMotorDirDioChannel);
	Counter counter = null;
			
	public RearLegsInternalEncoder()
	{
		counter = new Counter();
		counter.setUpSource(new DigitalInput(RobotMap.rearLegsWinchMotorTachDioChannel));
		counter.setDownSource(direction);
		counter.setExternalDirectionMode();
		counter.setDistancePerPulse(1.0 / RobotMap.rearLegsMotorTachPulsesPerRevolution);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportRearLegsInternalEncoder());
	}

	/**
	 * Resets the counter to 0.
	 */
	public void reset()
	{
		counter.reset();
	}
	
	public void report()
	{
		SmartDashboard.putBoolean(OI.dashboardRearLegsWinchMotorDirection, counter.getDirection());
		SmartDashboard.putNumber(OI.dashboardRearLegsWinchMotorRpm, counter.getRate() * 60.0 * (direction.get() ? 1 : -1));
		SmartDashboard.putNumber(OI.dashboardRearLegsWinchMotorCount, counter.get());
		SmartDashboard.putNumber(OI.dashboardRearLegsWinchMotorDistance, counter.getDistance());
	}
}