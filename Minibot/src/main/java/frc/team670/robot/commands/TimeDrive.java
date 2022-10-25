
package frc.team670.robot.commands;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class TimeDrive extends WaitCommand {
    DriveBase driveBase;
    double power;

    public TimeDrive(DriveBase driveBase, double power, double seconds) {
        super(seconds);
        this.driveBase = driveBase;
        addRequirements(driveBase);
        this.power = power;

    }
    // public void init(){

    // }
    public void execute() {
        driveBase.tankDrive(power, power);

    }

    public void end(boolean isInteruppted) {
        driveBase.stop();
    }

}