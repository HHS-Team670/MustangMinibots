package frc.team670.robot.commands.drive;

import com.pi4j.io.gpio.RaspiPin;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team670.pi.sensors.Encoder;
import frc.team670.robot.Robot;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;
import jpigpio.PigpioException;

public class CurveStraightDrive extends Command {

	private double speedL, speedR, dist, time;

	private Encoder lEncoder, rEncoder;
	
	private DriveBase  drivebase = Robot.driveBase;

	public CurveStraightDrive(double dist, double lspeed, double rspeed, double secs) throws PigpioException {
		this.speedL = lspeed;
		this.speedR = rspeed;
		this.time = dist;
		this.dist = secs;
		lEncoder = new Encoder(7, 1);
		rEncoder = new Encoder(22, 21);
		requires(drivebase);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		setTimeout(dist);
		Logger.consoleLog();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		drivebase.tankDrive(speedL, speedR);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		stop();
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s", speedL, speedR, getDistance());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(isTimedOut()) {
			end();
			return isTimedOut();
		}
		else {
			return false;
		}
		// return (this.error <= 1);
	}

	public double getDistance() {
		double distance = Robot.driveBase.getLeftEncoder().getDistance();
		return Math.abs(distance);
	}

	public void tankDrive() {
		while (RobotState.isEnabled()) {
			Robot.driveBase.tankDrive(speedL, speedL);
		}
	}

	public void stop() {
		speedL = 0;
	}

}
