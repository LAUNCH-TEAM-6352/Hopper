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
	private DigitalInput extendLimitLeft = new DigitalInput(RobotMap.centerLeg1ExtendLimitSwitchChannel);
	private DigitalInput retractLimitLeft = new DigitalInput(RobotMap.centerLeg1RetractLimitSwitchChannel);
	private DigitalInput extendLimitRight = new DigitalInput(RobotMap.centerLeg2ExtendLimitSwitchChannel);
	private DigitalInput retractLimitRight = new DigitalInput(RobotMap.centerLeg2RetractLimitSwitchChannel);

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
		SmartDashboard.putBoolean(OI.dashboardExtendLimitLeft, !isAtExtendLimitLeft());
		SmartDashboard.putBoolean(OI.dashboardRetractLimitLeft, !isAtRetractLimitLeft());
		SmartDashboard.putBoolean(OI.dashboardExtendLimitRight, !isAtExtendLimitRight());
		SmartDashboard.putBoolean(OI.dashboardRetractLimitRight, !isAtRetractLimitRight());
	}
	
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimitLeft()
	{
		return !extendLimitLeft.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimitLeft()
	{
		return !retractLimitLeft.get();
	}
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimitRight()
	{
		return !extendLimitRight.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimitRight()
	{
		return !retractLimitRight.get();
	}
}
