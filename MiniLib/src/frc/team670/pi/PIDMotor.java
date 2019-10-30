package frc.team670.pi;

import edu.wpi.first.wpilibj.controller.*;

public class PIDMotor {
	/** The motor that will be set based on the {@link PIDController} results. */
	public Motor motor;
	private double previousOutput = 0.0;
	private double rampBand;
	private double output;

	/**
	 * Constructor for a PID controlled motor, with a controllable multiplier.
	 *
	 * @param motor    The motor being set.
	 * @param rampBand The acceptable range for a motor change in one loop
	 */
	public PIDMotor(Motor motor, double rampBand) {
		this.motor = motor;
		this.rampBand = rampBand;
	}

	/**
	 * Sets motor output using input value from PID controller
	 * @param pidInput 
	 */
	public void write(double pidInput) {
		/*
		 * If the change is greater than what we want, set the output to be the previous
		 * output, but adjusted to be in the tolerable range + keeping the sign
		 */
		
		if ((Math.abs(pidInput - previousOutput)) > rampBand) { // If the change is greater that we want
			output = pidInput - previousOutput > 0 ? previousOutput + rampBand : previousOutput - rampBand;
		} else {
			output = pidInput;
		}
		motor.set(output);
		previousOutput = output;
	}
}