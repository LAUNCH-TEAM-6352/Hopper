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
import frc.robot.commands.ReportLimitSwitches;

/**
 * Wraps up all limit switches as a subsystem.
 */
public class LimitSwitches extends Subsystem
{
	private DigitalInput extendLimitLeftLeg = new DigitalInput(RobotMap.frontLegLeftExtendLimitSwitchChannel);
	private DigitalInput retractLimitLeftLeg = new DigitalInput(RobotMap.frontLegLeftRetractLimitSwitchChannel);
	private DigitalInput extendLimitRightLeg = new DigitalInput(RobotMap.frontLegRightExtendLimitSwitchChannel);
	private DigitalInput retractLimitRightLeg = new DigitalInput(RobotMap.frontLegRightRetractLimitSwitchChannel);
	private DigitalInput extendLimitRearLegs = new DigitalInput(RobotMap.rearLegsExtendLimitSwitchChannel);
	private DigitalInput retractLimitRearLegs = new DigitalInput(RobotMap.rearLegsRetractLimitSwitchChannel);
	private DigitalInput extendLimitLift = new DigitalInput(RobotMap.liftExtendLimitSwitchChannel);
	private DigitalInput retractLimitLift = new DigitalInput(RobotMap.liftRetractLimitSwitchChannel);

	public LimitSwitches()
	{
	}
	
	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportLimitSwitches());
	}
	
	/**
	 * Reports status to the smart dashboard.
	 */
	public void report()
	{
		SmartDashboard.putBoolean(OI.dashboardExtendLimitLeftLeg, !isAtExtendLimitLeftLeg());
		SmartDashboard.putBoolean(OI.dashboardRetractLimitLeftLeg, !isAtRetractLimitLeftLeg());
		SmartDashboard.putBoolean(OI.dashboardExtendLimitRightLeg, !isAtExtendLimitRightLeg());
		SmartDashboard.putBoolean(OI.dashboardRetractLimitRightLeg, !isAtRetractLimitRightLeg());
		SmartDashboard.putBoolean(OI.dashboardExtendLimitRearLegs, !isAtExtendLimitRearLegs());
		SmartDashboard.putBoolean(OI.dashboardRetractLimitRearLegs, !isAtRetractLimitRearLegs());
		SmartDashboard.putBoolean(OI.dashboardExtendLimitLift, !isAtExtendLimitLift());
		SmartDashboard.putBoolean(OI.dashboardRetractLimitLift, !isAtRetractLimitLift());
	}
	
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimitLeftLeg()
	{
		return !extendLimitLeftLeg.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimitLeftLeg()
	{
		return !retractLimitLeftLeg.get();
	}
	
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimitRightLeg()
	{
		return !extendLimitRightLeg.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimitRightLeg()
	{
		return !retractLimitRightLeg.get();
	}
	
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimitRearLegs()
	{
		return !extendLimitRearLegs.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimitRearLegs()
	{
		return !retractLimitRearLegs.get();
	}
	
	/**
	 * Returns an indication of if we are at the extend limit.
	 */
	public boolean isAtExtendLimitLift()
	{
		return !extendLimitLift.get();
	}
	
	/**
	 * Returns an indication of if we are at the retract limit.
	 */
	public boolean isAtRetractLimitLift()
	{
		return !retractLimitLift.get();
	}
}
