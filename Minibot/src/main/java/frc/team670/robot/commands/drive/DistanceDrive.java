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
	

	// Called repeatedly when this Command is scheduled to run
	
	public void execute() { 
		driveBase.tankDrive(speedL, speedR);
		correct();
	}

	
	// Called once after isFinished returns true
	
	public void end() {
		driveBase.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	public void interrupted() {
		end();
	}	
	
	// Checks that the wheels are driving at the same speed, corrects the speed
	// so that the left/right are equal
	public void correct() {
		double currentTicksL = driveBase.getLeftEncoder().getTicks();
		double currentTicksR = driveBase.getRightEncoder().getTicks();
		
		if (Math.abs(currentTicksL - currentTicksR) < 5)
			return;
		
		else if (currentTicksL > currentTicksR)
				speedL -= 0.01;
		
		else if (currentTicksL < currentTicksR)
				speedR -= 0.01;
	}
	
	// Make this return true when this Command no longer needs to run execute()
		@Override
	public boolean isFinished() {
		return getDistance() > Math.abs(dist);
		//return (this.error <= 1);
	}
		
			
	public double getDistance()
	{
		double distance = driveBase.getLeftEncoder().getDistance();
		return Math.abs(distance);
	}

}

	

