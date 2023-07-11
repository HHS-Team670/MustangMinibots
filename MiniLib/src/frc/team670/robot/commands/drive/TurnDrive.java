
package frc.team670.robot.commands;
import java.util.HashMap;
import java.util.Map;

//import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;
import frc.team670.robot.RobotConstants;

import frc.team670.pi.sensors.Encoder;


public class TurnDrive extends CommandBase {
    DriveBase driveBase;
    double powerR, powerL;


    public TurnDrive(DriveBase driveBase, double power, double seconds) {
        this.driveBase = driveBase;
        addRequirements(driveBase);
        this.powerL = power;
        this.powerR = power;

    }
    // public void init(){

    // }
    public void execute() {
       // balance();
        driveBase.tankDrive(powerL, powerR);
        //Monitor the distance taravelled and ensure that the motors are balanced

    }
    /*public void balance(){
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
    }*/
    
    //set wheels to 0 except 1;
    public void turnLeft(){
        //calculate how far the minibot turns using arc
        //minibots are 10 inch x 10 inches
        //2.5PI
        Encoder re = driveBase.getRightEncoder();
        Encoder le = driveBase.getLeftEncoder();
        double ticksR = re.getTicks();
        double ticksL = le.getTicks();
        double distanceR = ticksR / RobotConstants.ENCODER_TICKS_PER_ROTATION * RobotConstants.DRIVE_BASE_WHEEL_DIAMETER * Math.PI;
        double distanceL = ticksL / RobotConstants.ENCODER_TICKS_PER_ROTATION * RobotConstants.DRIVE_BASE_WHEEL_DIAMETER * Math.PI;
        while(distanceR > 10 && distanceR-distanceL < (2.5*Math.PI)){
            powerR = 1;
            powerL = 0.2;
        } 
    }
    public boolean isFinished(){
        Encoder re = driveBase.getRightEncoder();
        double ticks = re.getTicks();

        int travelDistance = 120;
        double distance = ticks / RobotConstants.ENCODER_TICKS_PER_ROTATION * RobotConstants.DRIVE_BASE_WHEEL_DIAMETER * Math.PI;
        return distance >= travelDistance;
    }
    /*public void turnLeft(){

    }*/

    public void end(boolean isInteruppted) {
        driveBase.stop();
    }
}