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

/**
 * Represents a tank drive base.
 * 
 * @author lakshbhambhani
 */
public class DriveBase {  

	public DriveBase() {
		
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
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    tankDrive(leftSpeed, rightSpeed, false);
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
   */
  public void tankDrive(double leftSpeed, double rightSpeed, boolean squaredInputs) {
   // driveTrain.tankDrive(leftSpeed, rightSpeed, squaredInputs);
  }

  /**
   * Stops the motors on the drive base (sets them to 0).
   */
  public void stop() {
    tankDrive(0, 0);
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
}