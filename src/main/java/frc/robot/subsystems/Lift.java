/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
		motor = RobotMap.isCompetitionRobot
			? new WPI_TalonSRX(RobotMap.liftMotorCanDeviceId)
			: new Spark(RobotMap.liftMotorPwmChannel);
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
		motor.set(speed);
		SmartDashboard.putNumber("Lift Speed", speed);
	}

	public void stop()
	{
		setSpeed(0);
	}
}
