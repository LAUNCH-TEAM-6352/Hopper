/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.RunLinearActuator;

/**
 * Add your docs here.
 */
public class LinearActuator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX motor = new TalonSRX(RobotMap.testTalonSrxDeviceId);
  private DigitalInput extendLimit = new DigitalInput(9);
  private DigitalInput retractLimit = new DigitalInput(8);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand()
  {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	  setDefaultCommand(new RunLinearActuator());
  }

  public void setSpeed(double speed)
  {
	  // Check for being at limit:
	  if ((!extendLimit.get() && speed > 0) || (!retractLimit.get() && speed < 0))
	  {
		  speed = 0;
	  }
	  motor.set(ControlMode.PercentOutput, speed);
	  SmartDashboard.putNumber("LA Speed", speed);
  }

  public void stop()
  {
	  setSpeed(0);
  }
}
