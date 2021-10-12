package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.Robot;
import frc.team670.robot.RobotConstants;
import frc.team670.robot.RobotContainer;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class DistanceDrive extends CommandBase {
	
	private double speedL, speedR, dist;

	private DriveBase driveBase;

	/**
	 * 
	 * @param distance_in Target distance in inches
	 * @param lspeed Speed for left side
	 * @param rspeed Speed for right side
	 */
	public DistanceDrive(double distance_in, double lspeed, double rspeed, DriveBase driveBase) {
		this.speedL = lspeed;
		this.speedR = rspeed;
		this.dist = distance_in;
		this.driveBase = driveBase;
		addRequirements(driveBase);
	}


				
	public double getDistance()
	{
		double distance = driveBase.getRightEncoder().getDistance();
		return Math.abs(distance);
	}
	

	// Called repeatedly when this Command is scheduled to run
	
	public void execute() { 
		driveBase.tankDrive(speedL, speedR);
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s LeftTicks: %s  RightTicks: %s DistanceT: %s", 
				speedL, speedR, driveBase.getLeftEncoder().getTicks(), driveBase.getRightEncoder().getTicks(), driveBase.getRightEncoder().getDistance());
		// correct();
	}

	
	// // Checks that the wheels are driving at the same speed, corrects the speed
	// // so that the left/right are equal
	// public void correct() {
	// 	double currentTicksL = driveBase.getLeftEncoder().getTicks();
	// 	double currentTicksR = driveBase.getRightEncoder().getTicks();
		
	// 	if (Math.abs(currentTicksL - currentTicksR) < 5)
	// 		return;
		
	// 	else if (currentTicksL > currentTicksR)
	// 			speedL -= 0.01;
		
	// 	else if (currentTicksL < currentTicksR)
	// 			speedR -= 0.01;
	// }
	
	// Make this return true when this Command no longer needs to run execute()
		@Override
	public boolean isFinished() {
		Logger.consoleLog("isFinished: %s", getDistance() > Math.abs(dist));
		return getDistance() > Math.abs(dist);
		//return (this.error <= 1);
	}
	

	// Called once after isFinished returns true
	
	@Override
	public void end(boolean isInteruppted) { // DO NOT CHANGE
		Logger.consoleLog("Stopping drivebase!");
		driveBase.stop();
	}

}

	

