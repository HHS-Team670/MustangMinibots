<<<<<<< Updated upstream:MiniLib/src/frc/team670/robot/commands/drive/DistanceDrive.java
package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;

public class DistanceDrive extends Command {
	
	private double speedL, speedR, dist;
	
	/**
	 * 
	 * @param distance_in Target distance in inches
	 * @param lspeed Speed for left side
	 * @param rspeed Speed for right side
	 */
	public DistanceDrive(double distance_in, double lspeed, double rspeed) {
		this.speedL = lspeed;
		this.speedR = rspeed;
		//this.seconds = seconds;
		this.dist = distance_in;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//setTimeout(seconds);
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s", 
				speedL, speedR, getDistance());
	}	

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() { 
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s Ticks: %s", 
				speedL, speedR, getDistance(), Robot.driveBase.getLeftEncoder().getTicks());		
		Robot.driveBase.tankDrive(speedL, speedR);
		correct();
	}

	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveBase.stop();
		Logger.consoleLog("LeftSpeed: %s Right Speed: %s DistanceT: %s Ticks: %s", 
				speedL, speedR, getDistance(), Robot.driveBase.getRightEncoder().getTicks());
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
	
	// Make this return true when this Command no longer needs to run execute()
		@Override
		protected boolean isFinished() {
			return getDistance() > Math.abs(dist);
			//return (this.error <= 1);
		}
		
			
		public double getDistance()
		{
			double distance = Robot.driveBase.getLeftEncoder().getDistance();
			return Math.abs(distance);
		}

}

	

=======

package frc.team670.robot.commands;
import java.util.HashMap;
import java.util.Map;

//import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;
import frc.team670.robot.RobotConstants;

import frc.team670.pi.sensors.Encoder;


public class DistanceDrive extends CommandBase {
    DriveBase driveBase;
    double powerR, powerL;


    public DistanceDrive(DriveBase driveBase, double power, double seconds) {
        this.driveBase = driveBase;
        addRequirements(driveBase);
        this.powerL = power;
        this.powerR = power;

    }
    // public void init(){

    // }
    public void execute() {
        balance();
        driveBase.tankDrive(powerL, powerR);
        //Monitor the distance taravelled and ensure that the motors are balanced

    }
    public void balance(){
        Encoder le = driveBase.getLeftEncoder();   
        Encoder re = driveBase.getRightEncoder();
        double ticksLeft = le.getTicks();
        double ticksRight = re.getTicks();
        if(Math.abs(ticksLeft - ticksRight)> 20){
        if(ticksLeft > ticksRight){
            powerL -= 0.05;
        }else if(ticksRight> ticksLeft){
            powerR -= 0.05;
        }
        }
    }
    public boolean isFinished(){
        Encoder re = driveBase.getRightEncoder();
        double ticks = re.getTicks();

        int travelDistance = 36;
        double distance = ticks / RobotConstants.ENCODER_TICKS_PER_ROTATION * RobotConstants.DRIVE_BASE_WHEEL_DIAMETER * Math.PI;
        return distance >= travelDistance;
    }
    public void end(boolean isInteruppted) {
        driveBase.stop();
    }


}
>>>>>>> Stashed changes:Minibot/src/main/java/frc/team670/robot/commands/drive/DistanceDrive.java
