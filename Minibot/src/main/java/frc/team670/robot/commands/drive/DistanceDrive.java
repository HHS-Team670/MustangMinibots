package frc.team670.robot.commands.drive;
import frc.team670.pi.sensors.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.Robot;
import frc.team670.robot.RobotConstants;
import frc.team670.robot.RobotContainer;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;


public class DistanceDrive extends CommandBase {

    private DriveBase driveBase;
    private double distance;
    private double leftPower;
    private double rightPower;
    
    public DistanceDrive(DriveBase driveBase, double distance, String unit, double leftPower, double rightPower) {
        this.driveBase=driveBase;
        this.leftPower=leftPower;
        this.rightPower=rightPower;
        this.distance=distance;
        this.unit = unit; // "i" for inch, "f" for feet, "c" for centimeter
        addRequirements(driveBase);
    }
    
    public void correct() {
        Encoder leftEncoder = driveBase.getLeftEncoder();
        Encoder rightEncoder = driveBase.getRightEncoder();

        if (Math.abs(leftEncoder.getTicks() - rightEncoder.getTicks()) < 5) 
            return;

        if (leftEncoder.getTicks() < rightEncoder.getTicks()){
            rightPower -= 0.01;
        } else if (leftEncoder.getTicks() > rightEncoder.getTicks()){
            leftPower -= 0.01;
        }

        Logger.consoleLog("TicksL: %s TicksR: %s, SpeedL: %s SpeedR: %s", leftEncoder.getTicks(), rightEncoder.getTicks(), leftPower, rightPower);
    }

    @Override
    public void execute() {
        driveBase.tankDrive(leftPower,rightPower);
        correct();
    }

    @Override
    public boolean isFinished() {
        Encoder leftEncoder = driveBase.getLeftEncoder();
        Encoder rightEncoder = driveBase.getRightEncoder(); 
        /*       
        int tickLimit = (distance / (2.497 * Math.PI / 800));// In inches
        int tickLimit = (distance / (2.497 *2.54* Math.PI / 800));// In cm
        int tickLimit = (distance*12 / ((2.497)*Math.PI / 800));// In feet
        */

        double ticksPerInch = 800/(2.497*Math.PI);
        double ticksPerFoot = ticksPerInch/12;
        double ticksPerCm = ticksPerInch*2.54;

        double tickLimit = distance*(unit=="f"?ticksPerFoot:unit=="c"?ticksPerCm:ticksPerInch) // default unit is inch
        Logger.consoleLog("tickLimit: %s", tickLimit);
        int currentTicks=(leftEncoder.getTicks()+rightEncoder.getTicks())/2;
        Logger.consoleLog("currentTicks: %s", currentTicks);

        if(currentTicks>=tickLimit) {
            
            return true;
        }
        return false;
        
        

    }

    public void end(boolean isInteruppted) {
        driveBase.stop();
    }

    //800 ticks per rotation
    //diameter 2.497 inches
    


}
