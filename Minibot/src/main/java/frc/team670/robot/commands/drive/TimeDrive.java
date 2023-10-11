/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot.commands.drive;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj2.command.WaitCommand; //https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj2/command/WaitCommand.html
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class TimeDrive extends WaitCommand {

  private DriveBase driveBase;
  private double powerL, powerR;

  public TimeDrive(double seconds, double powerL, double powerR, DriveBase driveBase) {
    super(seconds);
    this.powerL = powerL;
    this.powerR = powerR;
    addRequirements(driveBase);
    this.driveBase = driveBase;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    driveBase.tankDrive(powerL, powerR);
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean isInteruppted) {
    driveBase.stop();
  }
}