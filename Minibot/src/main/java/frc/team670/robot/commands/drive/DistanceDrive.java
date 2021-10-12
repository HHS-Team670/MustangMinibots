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

	public DistanceDrive(double distance_in, double lspeed, double rspeed, DriveBase driveBase) {
		this.speedL = lspeed;
		this.speedR = rspeed;
		this.dist = distance_in;
		this.driveBase = driveBase;
		addRequirements(driveBase);
	}
	

	public void execute() { 
		driveBase.tankDrive(speedL, speedR);
		// correct();
	}



	public void end(boolean isInterupted) {
		driveBase.stop();
	}

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
	
	public boolean isFinished() {
		return getDistance() > Math.abs(dist);
	}
		
			
	public double getDistance()
	{
		double distance = driveBase.getRightEncoder().getDistance();
		return Math.abs(distance);
	}

}

	

