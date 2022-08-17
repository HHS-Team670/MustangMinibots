package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.subsystems.DriveBase;

public class Turn extends CommandBase {
    private double speed;
    private DriveBase driveBase;
    private double goal;
    private double radians;
    private final static double radius=8;

    public Turn (double radians,double speed,DriveBase drivebase){
        this.speed=speed;
        this.driveBase=drivebase;
        this.radians=radians;
        goal=radians*radius;
        addRequirements(drivebase);
    }

    public void initialize() {
		// //setTimeout(seconds);
		// Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s", 
		// 		speedL, speedR, getDistance());
	}	

	// Called repeatedly when this Command is scheduled to run
	
	public void execute() { 

		// Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s TicksL: %s TicksR %s", 
		// 		speedL getDistance(), (driveBase.getLeftEncoder().getTicks()),driveBase.getRightEncoder().getTicks());		
		// if(!correcting){
			driveBase.tankDrive(radians<0?0.1:speed, radians<0?speed:0.1);


		// }
		// correct();

	
	}

	
	// Called once after isFinished returns true
	
	public void end(boolean interrupted) {
		driveBase.stop();
		// Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s Ticks: %s", 
				// speed, getDistance(), driveBase.getRightEncoder().getTicks());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	public void interrupted() {
		end(true);
	}	
	
	
	
	// Make this return true when this Command no longer needs to run execute()
		@Override
		public boolean isFinished() {
			if(getDistance() > Math.abs(goal)){
				
				return true;
			}
			return false;

		}
		
			
		public double getDistance()
		{
			double distance = radians>0?(driveBase.getRightEncoder().getDistance()):(driveBase.getLeftEncoder().getDistance());
			return Math.abs(distance);
		}
    
    
}
