/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;

/**
 * Drive for a set time
 */
public class TimeDrive extends Command {

	private double speed, seconds;
	private int executeCounter;
	private double elapsed;
	/**
	 * 
	 * @param seconds
	 * @param speed   speed for the drive base: [-1.0, +1.0]
	 */
	public TimeDrive(double seconds, double speed) {
		this.speed = speed;
		this.seconds = seconds;
		executeCounter = 0;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//setTimeout(seconds);
		elapsed = 0;
		System.out.println("Speed: " + speed + " Time: " + seconds);
		//Logger.consoleLog("Speed: %s Seconds: %s", speed, seconds);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.driveBase.tankDrive(speed, speed);
		System.out.println(timeSinceInitialized());
		elapsed = timeSinceInitialized();
		    try {
				Thread.sleep((long)(seconds*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//Logger.consoleLog();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (elapsed == seconds);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveBase.stop();
		System.out.println("Speed: " + speed + " Time: " + seconds);
		Logger.consoleLog("Speed: %s Seconds: %s", speed, seconds);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
