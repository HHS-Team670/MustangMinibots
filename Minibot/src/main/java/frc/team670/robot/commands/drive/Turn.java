package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.subsystems.DriveBase;

public class Turn extends CommandBase {
    private double speedR,speedL;
    private DriveBase driveBase;
    private final static double radius=7.07;

    public Turn (double radians,double speed,DriveBase drivebase){
        
        addRequirements(drivebase);
    }
    
}
