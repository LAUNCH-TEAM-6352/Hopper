/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class TestMotor extends Subsystem
{
	private TalonSRX motor = new TalonSRX(RobotMap.testTalonSrxDeviceId);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new RunTestMotor());
	}

	public void setSpeed(double speed)
	{
		SmartDashboard.putNumber("Test Motor Speed", speed);
		motor.set(ControlMode.PercentOutput, speed);
	}

	public void stop()
	{
		setSpeed(0);
	}
}
