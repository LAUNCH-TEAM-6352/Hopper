/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * A class for computing an average over a rolling window of values.
 */
public class Rolling
{
	private int size;
	private double total = 0d;
	private int index = 0;
	private double samples[];

	/**
	 * Creates a new rolling average for the indicatred number of samples.
	 * 
	 * @param size
	 */
	public Rolling(int size)
	{
		this.size = size;
		samples = new double[size];
		for (int i = 0; i < size; i++)
			samples[i] = 0d;
	}

	/**
	 * Adds a new value to the rolling average and returns the current average.
	 * 
	 * @param x The new value.
	 * @return The current average.
	 */
	public double add(double x)
	{
		total -= samples[index];
		samples[index] = x;
		total += x;
		if (++index == size)
			index = 0; // cheaper than modulus
		return getAverage();
	}

	public double getAverage()
	{
		return total / size;
	}
}