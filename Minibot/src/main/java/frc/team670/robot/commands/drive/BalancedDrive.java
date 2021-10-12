package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team670.robot.Robot;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class BalancedDrive extends WaitCommand {

  private double speedL, speedR, seconds;
  public DriveBase driveBase;

  private double ticksL, ticksR;

	public BalancedDrive(double seconds, double lspeed, double rspeed, DriveBase driveBase) {
        super(seconds);
	    this.speedL = lspeed;
	    this.speedR = rspeed;
	    this.seconds = seconds;
        addRequirements(driveBase);
        this.driveBase = driveBase;
	}

	
	public void correct() {
		double currentTicksL = Math.abs(driveBase.getLeftEncoder().getTicks());
    double currentTicksR = Math.abs(driveBase.getRightEncoder().getTicks());
    Logger.consoleLog("Left Ticks: %s Right Ticks: %s", currentTicksL, currentTicksR);
		if (Math.abs(currentTicksL - currentTicksR) < 5)
			return;
		else if (currentTicksL > currentTicksR){
			speedL -= 0.01;
		}
				
		else if (currentTicksL < currentTicksR){
			speedR -= 0.01;
		}
		Logger.consoleLog("Left Speed: %s Right Speed: %s Seconds: %s", speedL, speedR, seconds);
		this.ticksL = currentTicksL;
		this.ticksR = currentTicksR;

	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override

	public void execute() {
		driveBase.tankDrive(speedL, speedR, false);
		correct();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interupted) {
		driveBase.stop();
		Logger.consoleLog("Left Speed: %s Right Speed: %s Seconds: %s", speedL, speedR, seconds);
		System.out.println(ticksL);
		System.out.println(ticksR);
		System.out.println(speedL);
		System.out.println(speedR);

	}

}