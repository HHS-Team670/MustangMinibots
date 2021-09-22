 
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.pi.sensors.Encoder;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;

/**
 * An example command.  You can replace me with your own command.
 */
public class BalancedDrive extends Command {

    private double leftspeed, rightspeed, seconds, ogspeed;
    private int executeCounter;

  public BalancedDrive(double seconds, double speed) {
    this.leftspeed = speed;
    this.rightspeed = speed;
    this.ogspeed = speed;
    this.seconds = seconds;
    executeCounter = 0;
    requires(Robot.driveBase);
  }

  protected double actualSpeed(Encoder m) {
      return Math.abs(m.getTicks());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(seconds);
    Logger.consoleLog("Speed: %s Seconds: %s", this.ogspeed, seconds);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
	  Logger.consoleLog();
    if (actualSpeed(Robot.driveBase.getLeftEncoder()) > actualSpeed(Robot.driveBase.getRightEncoder())) {
        this.leftspeed -= 0.5;
    } else if (actualSpeed(Robot.driveBase.getLeftEncoder()) < actualSpeed(Robot.driveBase.getRightEncoder())) {
        this.rightspeed -= 0.5;
    }
    Robot.driveBase.tankDrive(this.leftspeed, this.rightspeed, false);
   // Logger.consoleLog();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveBase.stop();
    Logger.consoleLog("Speed: %s Seconds: %s", this.ogspeed, seconds);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
