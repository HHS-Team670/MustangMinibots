/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj2.command.*;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team670.pi.Motor;
import frc.team670.pi.sensors.Encoder;
import jpigpio.PigpioException;

/**
 * Add your docs here.
 */
public class DistanceDrive extends RunCommand {

    private DriveBase base;
    private double leftPower;
    private double rightPower;
    private final double TICKS_PER_ROTATION = 800;
    private final double WHEEL_DIAM = 2.497; // In inches
    private final double TICKS_PER_INCH;
    private double targetTicks;
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    public DistanceDrive(double power, double target, DriveBase base){

        this.leftPower = power;
        this.rightPower = power;
        addRequirements(base);
        this.base = base;
        this.leftEncoder = this.base.getLeftEncoder();
        this.rightEncoder = this.base.getRightEncoder();
        this.TICKS_PER_INCH = this.TICKS_PER_ROTATION / (3.1415926535 * this.WHEEL_DIAM);
        this.targetTicks = this.target;
    }

    public void initialize() {
        this.leftEncoder.reset();
        this.rightEncoder.reset();
    }

    public void correct() {

        if (Math.abs(leftEncoder.getTicks() - rightEncoder.getTicks()) < 5) 
            return;

        if (leftEncoder.getTicks() < rightEncoder.getTicks()){
            rightPower -= 0.01;
        } else if (leftEncoder.getTicks() > rightEncoder.getTicks()){
            leftPower -= 0.01;
        }

        Logger.consoleLog("TicksL: %s TicksR: %s, SpeedL: %s SpeedR: %s", leftEncoder.getTicks(), rightEncoder.getTicks(), leftPower, rightPower);
    }

    public void execute() {
        this.base.tankDrive(leftPower, rightPower);
        this.correct();
    }

    public boolean isFinished() {
        if (this.base.checkHealth() != HealthState.GREEN) {
            return true;
        }
        if (this.leftEncoder.getTicks() >= this.targetTicks && this.rightEncoder.getTicks() >= this.targetTicks) {
            return true;
        }
        return false;
    }

    public void end() {
        base.stop();
    }

}