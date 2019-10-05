/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
		motor = new WPI_TalonSRX(RobotMap.liftMotorCanDeviceId);
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

		// Check for being at limit:
		if (speed > 0 && Robot.limitSwitches.isAtExtendLimitLift())
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardLiftRumblePower, 0.0);
		}
		else if (speed < 0 && Robot.limitSwitches.isAtRetractLimitLift())
		{
			speed = 0;
			rumblePower = SmartDashboard.getNumber(OI.dashboardLiftRumblePower, 0.0);
		}

		Robot.oi.gameController.setRumble(OI.liftRumbleType, rumblePower);
	
		motor.set(speed);
		SmartDashboard.putNumber("Lift Speed", speed);
	}

	public void stop()
	{
		setSpeed(0);
	}
}
