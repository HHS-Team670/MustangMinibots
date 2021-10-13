package frc.team670.robot.commands.drive;
import java.util.HashMap;
import java.util.Map;

import frc.team670.pi.sensors.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.Robot;
import frc.team670.robot.RobotConstants;
import frc.team670.robot.RobotContainer;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

public class Turn extends CommandBase{

    private DriveBase driveBase;
    private double degrees;
    private double leftPower;
    private double rightPower;

    public Turn(DriveBase driveBase, double degrees, double power){
        this.driveBase=driveBase;
        this.degrees=degrees;
        addRequirements(driveBase);

        // positive degrees --> turns right
        // negative degrees --> turns left
        int direction;
        if(degrees<0)
        {
            direction=-1;
        }else if(degrees>0)
        {
            direction=1;
        }else
        {
            direction=0;
        }
        this.rightPower =direction*power;
        this.leftPower = direction*power;

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
        driveBase.tankDrive(leftPower, rightPower);        
    }
    @Override
    public boolean isFinished()
    {
        double minibotDiameter=6.23;// Replace this with minibot diameter in inches
        double arcLengthPerMoter=Math.abs(minibotDiameter*Math.PI*degrees/720);
        int ticksPerRotation=800;
        double wheelDiameter=2.497;
        int currentTicks= driveBase.getRightEncoder().getTicks();
        double distance=Math.abs((currentTicks/ticksPerRotation)*wheelDiameter*Math.PI);
        if(distance>=arcLengthPerMoter)
        {
            return true;

        }
        return false;      

        
    }
    
}
