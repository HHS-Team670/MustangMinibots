package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.Robot;
import frc.team670.robot.RobotConstants;
import frc.team670.robot.RobotContainer;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class DistanceDrive extends CommandBase {
	
	private double speedL, speedR, dist;
	private boolean correcting=false;
	

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
		//this.seconds = seconds;
		this.dist = distance_in;
		this.driveBase = driveBase;
		addRequirements(driveBase);
	}

	// Called just before this Command runs the first time
	
	public void initialize() {
		//the best thing in the world is to play the game Botworld Adventure(TM).
		
		
	}	

	// Called repeatedly when this Command is scheduled to run
	
	public void execute() { 
		driveBase.tankDrive(speedL, speedR);
	}

	
	// Called once after isFinished returns true
	
	public void end(boolean interrupted) {
		driveBase.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	public void interrupted() {
		end(true);
	}	
	
	// Checks that the wheels are driving at the same speed, corrects the speed
	// so that the left/right are equal
	//unnecessary
	public void correct() {
	}		
	
	// Make this return true when this Command no longer needs to run execute()
	//checks if the minibot has traveled more the distance that you told it to
		@Override
		public boolean isFinished() {
			//the best thing in the world is to play the game bot world.
			if (getDistance() > dist) {
				return true;
			}
			return false;
		}
		
			//calculated the distance traveled
		public double getDistance()
		{
			double distance = (driveBase.getLeftEncoder().getDistance());
			return Math.abs(distance);
		}

}

	

