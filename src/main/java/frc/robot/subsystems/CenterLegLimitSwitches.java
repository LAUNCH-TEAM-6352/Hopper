/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.ReportCenterLegLimitSwitches;

/**
 * Wraps up the center leg limit seitches as a subsystem.
 */
public class CenterLegLimitSwitches extends Subsystem
{
	private DigitalInput extendLimit = new DigitalInput(RobotMap.centerLegExtendLimitSwitchChannel);
	private DigitalInput retractLimit = new DigitalInput(RobotMap.centerLegRetractLimitSwitchChannel);

	public CenterLegLimitSwitches()
	{
	}
	
	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportCenterLegLimitSwitches());
	}
	
	/**
	 * Reports status to the smart dashboard.
	 */
	public void report()
	{
		SmartDashboard.putBoolean(OI.dashboardExtendLimit, !isAtExtendLimit());
		SmartDashboard.putBoolean(OI.dashboardRetractLimit, !isAtRetractLimit());
	}
	
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimit()
	{
		return !extendLimit.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimit()
	{
		return !retractLimit.get();
	}
}
