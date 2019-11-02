package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;

public class DistanceDrive extends Command {
	
	private double speedL, speedR, dist;
	
	public DistanceDrive(double distance_cm, double lspeed, double rspeed) {
		this.speedL = lspeed;
		this.speedR = rspeed;
		//this.seconds = seconds;
		this.dist = distance_cm;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//setTimeout(seconds);
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s Distance: %s traveled more than dist: %s", 
				speedL, speedR, dist, getDistance() > Math.abs(dist));
	}	

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() { 
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s Distance: %s traveled more than dist: %s", 
				speedL, speedR, dist, getDistance() > Math.abs(dist));
		Robot.driveBase.tankDrive(speedL, speedR, false);
		correct();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return getDistance() > Math.abs(dist);
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveBase.stop();
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s Distance: %s", 
				speedL, speedR, dist);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}	
	
	// Checks that the wheels are driving at the same speed, corrects the speed
	// so that the left/right are equal
	public void correct() {
		double currentTicksL = Robot.driveBase.getLeftEncoder().getTicks();
		double currentTicksR = Robot.driveBase.getRightEncoder().getTicks();
		
		if (Math.abs(currentTicksL - currentTicksR) < 5)
			return;
		
		else if (currentTicksL > currentTicksR)
				speedL -= 0.01;
		
		else if (currentTicksL < currentTicksR)
				speedR -= 0.01;
	}
	
	public double getDistance()
	{
		double distance = Robot.driveBase.getLeftEncoder().getDistance();
		return Math.abs(distance);
	}

}