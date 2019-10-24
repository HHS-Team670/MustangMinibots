package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;

public class DistanceDrive extends Command{
	private double speed, target, traveled;

	public DistanceDrive(double target, double speed) {
    this.speed = speed;
    this.target = target;
    this.traveled = 0;
    requires(Robot.driveBase);
  }

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Logger.consoleLog("Speed: %s Distance traveled: %s", speed, traveled);
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
		return ((Math.abs(this.traveled - this.target)) < 0.1);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveBase.stop();
		Logger.consoleLog("Speed: %s Distance traveled: %s", speed, target);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
