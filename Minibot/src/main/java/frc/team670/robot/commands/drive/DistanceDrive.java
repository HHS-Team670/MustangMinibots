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
    
    public DistanceDrive(DriveBase driveBase,double distance,double leftPower, double rightPower) {
        this.driveBase=driveBase;
        this.leftPower=leftPower;
        this.rightPower=rightPower;
        this.distance=distance;
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
        
        int tickLimit=  (int) ( distance / (2.497 * Math.PI / 800));// In inches
        //int tickLimit=  (int) ( distance / (2.497 *2,54* Math.PI / 800));// In cm
        //int tickLimit=  (int) ( distance*12 / ((2.497)*Math.PI / 800));// In feet
        
        int currentTicks=(leftEncoder.getTicks()+rightEncoder.getTicks())/2;
        if(currentTicks>=tickLimit)
        {
            return true;
        }
        return false;
    }

    public void end() {
        driveBase.stop();
    }

    //800 ticks per rotation
    //diameter 2.497 inches
    


}
