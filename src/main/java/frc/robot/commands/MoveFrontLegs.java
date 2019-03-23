/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class MoveFrontLegs extends Command
{
	private String speedKeyLeft;
	private String speedKeyRight;
	private double speedLeft;
	private double speedRight;

	public MoveFrontLegs()
	{
		requires(Robot.frontLegs);
	}

	public MoveFrontLegs(String speedKeyLeft, String speedKeyRight)
	{
		this();
		this.speedKeyLeft = speedKeyLeft;
		this.speedKeyRight = speedKeyRight;
	}

	public MoveFrontLegs(double speedLeft, double speedRight)
	{
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		if (speedKeyLeft != null)
		{
			speedLeft = SmartDashboard.getNumber(speedKeyLeft, 0.0);
		}
		if (speedKeyRight != null)
		{
			speedRight = SmartDashboard.getNumber(speedKeyRight, 0.0);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.frontLegs.setSpeed(speedLeft, speedRight);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		return isCanceled();
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
