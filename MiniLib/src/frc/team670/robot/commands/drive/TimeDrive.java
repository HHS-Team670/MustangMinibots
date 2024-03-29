 
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.robot.Robot;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

/**
 * An example command.  You can replace me with your own command.
 */
public class TimeDrive extends Command {

    private double speed, seconds;
    private int executeCounter;
    private double powerL, powerR;
    

  public TimeDrive(double seconds, DriveBase driveBase) {
	  super(seconds);
	  this.powerL = 0.3;
	  this.powerR = 0.6;
    
    executeCounter = 0;
    requires(Robot.driveBase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(seconds);
    Logger.consoleLog("Speed: %s Seconds: %s", speed, seconds);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 
	  Logger.consoleLog();
    Robot.driveBase.tankDrive(speed, speed, false);
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
    Logger.consoleLog("Speed: %s Seconds: %s", speed, seconds);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
  
  
}
