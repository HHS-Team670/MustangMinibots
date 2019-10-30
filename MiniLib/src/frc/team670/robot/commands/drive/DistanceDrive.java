package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.pi.sensors.Encoder;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;

public class DistanceDrive extends Command{
	private double speed, target, traveled, error;
	private Encoder leftEncoder, rightEncoder;
	private int leftTickCount, rightTickCount;
	
	
	/**
	 * 
	 * @param target Distance to drive for, in centimeters
	 * @param speed [-1.0, 1.0]
	 */
	public DistanceDrive(double target, double speed) {
    this.speed = speed;
    this.target = target;
    requires(Robot.driveBase);
	leftEncoder = Robot.driveBase.getLeftEncoder();
	rightEncoder = Robot.driveBase.getRightEncoder();
  }

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.traveled = 0;
		Logger.consoleLog("Speed: %s Distance traveled: %s Target: %s", speed, traveled, target);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		leftTickCount = leftEncoder.getTicks();
		rightTickCount = rightEncoder.getTicks();
		this.traveled = Math.abs((leftEncoder.getDistance() + rightEncoder.getDistance())/2);
		this.error = Math.abs(this.target- this.traveled);
		System.out.println("Distance traveled: " + this.traveled);
		System.out.println("Error: " + this.error);
		if (leftTickCount < rightTickCount) {
			Robot.driveBase.tankDrive(speed, speed * 0.9, false);
		} else if (rightTickCount < leftTickCount) {
			Robot.driveBase.tankDrive(speed * 0.9, speed, false);
		} else {
			Robot.driveBase.tankDrive(speed, speed, false);
		}

		Logger.consoleLog();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (this.error <= 1);
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
