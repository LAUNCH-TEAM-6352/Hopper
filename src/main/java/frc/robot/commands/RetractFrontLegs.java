/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RetractFrontLegs extends Command
{
	private String speedKeyLeft;
	private String speedKeyRight;

	private double speedLeft;
	private double speedRight;

	public RetractFrontLegs(String speedKeyLeft, String speedKeyRight)
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.frontLegs);
		this.speedKeyLeft = speedKeyLeft;
		this.speedKeyRight = speedKeyRight;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		speedLeft = SmartDashboard.getNumber(speedKeyLeft, -1.0);
		speedRight = SmartDashboard.getNumber(speedKeyRight, -1.0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		double power = Robot.oi.gameController.getTriggerAxis(Hand.kLeft) > 0.2
			? 1.0
			: 0.0;
		Robot.frontLegs.setSpeed(power * speedLeft, power * speedRight);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.frontLegs.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}
