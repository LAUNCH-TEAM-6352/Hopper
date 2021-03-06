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

public class MoveLift extends Command
{
	private String speedKey;
	private double speed;

	public MoveLift()
	{
		requires(Robot.lift);
	}

	public MoveLift(String speedKey)
	{
		this();
		this.speedKey = speedKey;
	}

	public MoveLift(double speed)
	{
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
		if (speedKey != null)
		{
			speed = SmartDashboard.getNumber(speedKey, 0.0);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		Robot.lift.setSpeed(speed);
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
		Robot.lift.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
		end();
	}
}
