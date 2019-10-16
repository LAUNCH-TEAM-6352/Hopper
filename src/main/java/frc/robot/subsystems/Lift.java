/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem
{
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController motor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public Lift()
	{
		motor = new WPI_VictorSPX(RobotMap.liftMotorCanDeviceId);
	}

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new RunLift());
	}

	public void setSpeed(double speed)
	{
		// Assume we will be turning off rumble:
		double rumblePower = 0;

		boolean resetEncoder = false;

		// Check for being at limit:
		if (speed > 0 &&
			(Robot.liftEncoder.getDistance() >= RobotMap.liftExtendLimitDistance || Robot.limitSwitches.isAtExtendLimitLift()))
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardLiftRumblePower, 0.0);
		}
		else if (speed < 0 &&
			(Robot.liftEncoder.getDistance() <= RobotMap.liftRetractLimitDistance || Robot.limitSwitches.isAtRetractLimitLift()))
		{
			speed = 0;
			resetEncoder = true;
			rumblePower = SmartDashboard.getNumber(OI.dashboardLiftRumblePower, 0.0);
		}
	
		motor.set(speed);
		SmartDashboard.putNumber("Lift Speed", speed);
		Robot.oi.gameController.setRumble(OI.liftRumbleType, rumblePower);

		if (resetEncoder)
		{
			Robot.liftEncoder.reset();
		}
	}

	public void stop()
	{
		setSpeed(0);
	}
}
