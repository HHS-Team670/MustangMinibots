/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team670.pi.Motor;
import frc.team670.pi.sensors.Encoder;
import frc.team670.robot.commands.drive.*;
import jpigpio.PigpioException;

/**
 * Represents a tank drive base.
 * 
 * @author lakshbhambhani, ctychen
 */
public class DriveBase extends SubsystemBase {

	private static int MOTOR_1_PIN_A = 4;
	private static int MOTOR_1_PIN_B = 5;
	private static int MOTOR_2_PIN_A = 0;
	private static int MOTOR_2_PIN_B = 1;

	private static Encoder le, re;

	Motor left;
	Motor right;

	double leftSpeed;
	double rightSpeed;

	public DriveBase() {
		// get a handle to the GPIO controller
		final GpioController gpio = GpioFactory.getInstance();
		// initialize your motors
		left = new Motor(MOTOR_1_PIN_A, MOTOR_1_PIN_B, RaspiPin.GPIO_06);
		right = new Motor(MOTOR_2_PIN_A, MOTOR_2_PIN_B, RaspiPin.GPIO_03);
		try {
			le = new Encoder(13, 26, false); //TODO  modify this based on motor direction
		} catch (PigpioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			re = new Encoder(5, 6, false); //TODO  modify this based on motor direction
		} catch (PigpioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Drives the Robot using a tank drive configuration (two joysticks, or auton).
	 * Squares inputs to linearize them.
	 * 
	 * @param leftSpeed  Speed for left side of drive base [-1, 1]. Automatically
	 *                   squares this value to linearize it.
	 * @param rightSpeed Speed for right side of drive base [-1, 1]. Automatically
	 *                   squares this value to linearize it.
	 * @throws InterruptedException
	 */
	public void tankDrive(double leftSpeed, double rightSpeed) {
		this.leftSpeed = leftSpeed;
		this.rightSpeed = -1*rightSpeed;
		tankDrive(this.leftSpeed, this.rightSpeed, false);
	}

	public void correct() {
		double error = Math.abs(this.le.getTicks()) - Math.abs(this.re.getTicks());
		while (Math.abs(error) > 5) {
			if (error > 0)
				this.leftSpeed -= 0.05;
			if (error < 0)
				this.rightSpeed -= 0.05;
			System.out.println(le.getTicks() + " " + re.getTicks());
		}
		/*
		 * error = left-right readings if e+: left>right, slow left if e-: left<right,
		 * slow right while !==, adjust until equal
		 */
//	  drive_straight_enc(power):
//		    error = left_encoder - right_encoder
//		    turn_power = kP * error
//		    drive.arcadeDrive(power, turn_power, squaredInputs=False)
	}

//  public void initBrakeMode() {
//    setMotorsBrakeMode(allMotors, IdleMode.kBrake);
//  }

	/**
	 * 
	 * Drives the Robot using a tank drive configuration (two joysticks, or auton)
	 * 
	 * @param leftSpeed     Speed for left side of drive base [-1, 1]
	 * @param rightSpeed    Speed for right side of drive base [-1, 1]
	 * @param squaredInputs If true, decreases sensitivity at lower inputs
	 * @throws InterruptedException
	 */
	public void tankDrive(double leftSpeed, double rightSpeed, boolean squaredInputs) {
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		//correct();
		left.set(this.leftSpeed);
		right.set(this.rightSpeed);
	}

	/**
	 * Stops the motors on the drive base (sets them to 0).
	 */
	public void stop() {
		left.set(0);
		right.set(0);
	}

//  public void sendEncoderDataToDashboard() {
//    // if (leftDIOEncoder != null) {
//    //   SmartDashboard.putNumber("Left DIO Encoder: ", leftMustangEncoder.getPositionInches());
//    // }
//
//    // if (rightDIOEncoder != null) {
//    //   SmartDashboard.putNumber("Right Encoder: ", rightDIOEncoder.get());
//    // }
//
//    // if (leftDIOEncoder == null) {
//    //   SmartDashboard.putString("Left DIO Encoder:", "LEFT DIO ENCODER IS NULL!");
//    // }
//    // if (rightDIOEncoder == null) {
//    //   SmartDashboard.putNumber("Right Encoder:", rightMustangEncoder.getPositionInches());
//    // }
//    if(leftMustangEncoder != null) {
//      SmartDashboard.putString("Left Encoder Inches", leftMustangEncoder.getPositionInches() + "");
//    } else {
//      SmartDashboard.putString("Left Encoder Inches", "null");
//    }
//    if(rightMustangEncoder != null) {
//      SmartDashboard.putString("Right Encoder Inches", rightMustangEncoder.getPositionInches() + "");
//    } else {
//      SmartDashboard.putString("Left Encoder Inches", "null");
//    }
//  }

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public Encoder getLeftEncoder() {
		// TODO Auto-generated method stub
		return this.le;
	}

	public Encoder getRightEncoder() {
		return this.re;
	}
}
