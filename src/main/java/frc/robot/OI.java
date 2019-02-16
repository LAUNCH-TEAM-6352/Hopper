/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MoveCargo;
import frc.robot.commands.MoveOuterLegs;
import frc.robot.commands.ResetOuterLegsEncoder;
import frc.robot.commands.ToggleLimelight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public final XboxController gameController = new XboxController(0);

	public final Joystick joystickLeft = new Joystick(2);
	public final Joystick joystickRight = new Joystick(3);

	// Give meaningful names to the game controller buttons:
	public final static int gameControllerButtonA = 1;
	public final static int gameControllerButtonB = 2;
	public final static int gameControllerButtonX = 3;
	public final static int gameControllerButtonY = 4;
	public final static int gameControllerButtonBumperLeft = 5;
	public final static int gameControllerButtonBumperRight = 6;
	public final static int gameControllerButtonBack = 7;
	public final static int gameControllerButtonStart = 8;
	public final static int gameControllerButtonStickLeft = 9;
	public final static int gameControllerButtonStickRight = 10;

	private Button outerLegsForwardButton = new JoystickButton(gameController, gameControllerButtonY);
	private Button outerLegsReverseButton = new JoystickButton(gameController, gameControllerButtonA);

	private Button cargoInButton = new JoystickButton(gameController, gameControllerButtonBumperLeft);
	private Button cargoOutButton = new JoystickButton(gameController, gameControllerButtonBumperRight);

	private Button resetOuterLegsEncoderButton = new JoystickButton(gameController, gameControllerButtonStart);

	// SmartDashboard keys:
	public final static String outerLegsForwardSpeed = "Outer Legs Fwd Speed";
	public final static String outerLegsReverseSpeed = "Outer Legs Rev Speed";

	// Constructor:
	public OI()
	{
		// Bind buttons to commands:
		//outerLegsForwardButton.whileHeld(new MoveOuterLegs(outerLegsForwardSpeed));
		//outerLegsReverseButton.whileHeld(new MoveOuterLegs(outerLegsReverseSpeed));
		cargoInButton.whileHeld(new MoveCargo(1));
		cargoOutButton.whileHeld(new MoveCargo(-1));

		resetOuterLegsEncoderButton.whenPressed(new ResetOuterLegsEncoder());

		// Put default values on SmartDashboard:
		SmartDashboard.putNumber(outerLegsForwardSpeed, 0.75);
		SmartDashboard.putNumber(outerLegsReverseSpeed, -0.5);

		SmartDashboard.putData(new ResetOuterLegsEncoder());
		SmartDashboard.putData("Toggle Limelight", new ToggleLimelight());
	}
}
