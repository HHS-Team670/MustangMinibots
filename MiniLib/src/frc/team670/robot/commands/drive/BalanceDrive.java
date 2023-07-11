
package frc.team670.robot.commands;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;
import frc.team670.pi.sensors.Encoder;


public class BalanceDrive extends WaitCommand {
    DriveBase driveBase;
    double powerR, powerL;

    public BalanceDrive(DriveBase driveBase, double power, double seconds) {
        super(seconds);
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
        }else if(ticksRight > ticksLeft){
            powerR -= 0.05;
        }
        }
    }
    /*
     * stop when the distance traveled
     * is greater than the distance that you want it to travel
     * 
     * travelDistance = 40
     */

    public void end(boolean isInteruppted) {
        driveBase.stop();
    }

}