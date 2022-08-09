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
		//setTimeout(seconds);
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s", 
				speedL, speedR, getDistance());
	}	

	// Called repeatedly when this Command is scheduled to run
	
	public void execute() { 

		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s TicksL: %s TicksR %s", 
				speedL, speedR, getDistance(), (driveBase.getLeftEncoder().getTicks()),driveBase.getRightEncoder().getTicks());		
		// if(!correcting){
			driveBase.tankDrive(speedL, speedR);


		// }
		// correct();

	
	}

	
	// Called once after isFinished returns true
	
	public void end(boolean interrupted) {
		driveBase.stop();
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s Ticks: %s", 
				speedL, speedR, getDistance(), driveBase.getRightEncoder().getTicks());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	public void interrupted() {
		end(true);
	}	
	
	// Checks that the wheels are driving at the same speed, corrects the speed
	// so that the left/right are equal
	public void correct() {
		double currentTicksL = driveBase.getLeftEncoder().getTicks();
		double currentTicksR = driveBase.getRightEncoder().getTicks();
		
		if (Math.abs(currentTicksL - currentTicksR) < 15)
			return;
		
		// else if (currentTicksL > currentTicksR)
		// 		speedL -= ;
		
		// else if (currentTicksL < currentTicksR)
		// 		speedR -= 0.05;
	
		else if (currentTicksL > currentTicksR){
			speedL -= 0.025;
			speedR += 0.025;
		}
			
		else if (currentTicksL < currentTicksR){
			speedR -= 0.025;
			speedL += 0.025;
		}
		speedL= speedL>1?1:speedL;
		speedL= speedL<-1?-1:speedL;

		speedR= speedR>1?1:speedR;
		speedR= speedR<-1?-1:speedR;
			
	}		
	
	// Make this return true when this Command no longer needs to run execute()
		@Override
		public boolean isFinished() {
			if(getDistance() > Math.abs(dist)){
				// double currentTicksL = driveBase.getLeftEncoder().getTicks()/1.16421;
				// double currentTicksR = -driveBase.getRightEncoder().getTicks();
				// if(Math.abs(currentTicksL - currentTicksR) <15){

				// 	return true;
				// }
				// if(currentTicksL>currentTicksR){
				// 	driveBase.tankDrive(0, 0.5);
				// }else{
				// 	driveBase.tankDrive(0.5, 0);

				// }
				// correcting=true;
				// return false;
				return true;
			}
			return false;
			// return getDistance() > Math.abs(dist);
			//return (this.error <= 1);
		}
		
			
		public double getDistance()
		{
			double distance = (driveBase.getRightEncoder().getDistance());
			return Math.abs(distance);
		}

}

	

