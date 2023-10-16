package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team670.robot.Robot;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class CombinedDrive extends SequentialCommandGroup {
	
	/**
	 * 
	 * @param distance_in Target distance in inches
	 * @param lspeed Speed for left side
	 * @param rspeed Speed for right side
	 */
	public CombinedDrive(DriveBase drivebase) {
		addRequirements(drivebase);
        addCommands(
			new Turn(Math.PI, 0, drivebase),
			new TimeDrive(5, 10, 10, drivebase),
			new Turn(-(Math.PI*2), 0, drivebase),
			//new WaitCommand(4), 
			new TimeDrive(5, 10, 10, drivebase),
			//new WaitCommand(4),
			//new DistanceDrive(30, 1, 1, drivebase),
			//new WaitCommand(4),
			//new PIDDistanceDrive(10, drivebase)
        );
	}


}

	


