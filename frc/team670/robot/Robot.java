/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team670.robot.commands.arm.zero.SafelyResetExtension;
import frc.team670.robot.commands.drive.teleop.XboxRocketLeagueDrive;
import frc.team670.robot.constants.RobotConstants;
import frc.team670.robot.dataCollection.MustangCoprocessor;
import frc.team670.robot.dataCollection.MustangSensors;
import frc.team670.robot.subsystems.Arm;
import frc.team670.robot.subsystems.Arm.HeldItem;
import frc.team670.robot.subsystems.Claw;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.subsystems.Intake;
import frc.team670.robot.subsystems.MustangLEDs_2019;
import frc.team670.robot.subsystems.elbow.Elbow;
import frc.team670.robot.subsystems.extension.Extension;
import frc.team670.robot.subsystems.wrist.Wrist;
import frc.team670.robot.utils.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi;
  public static DriveBase driveBase = new DriveBase();

  private Notifier updateArbitraryFeedForwards;

  private Command autonomousCommand, operatorControl;
  private SendableChooser<Command> auton_chooser = new SendableChooser<>();

  private boolean firstTimeEnteringTeleop;
  
  public Robot() {
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // auton_chooser.addDefault("Default Auto", new TimeDrive());
    // chooser.addObject("My Auto", new MyAutoCommand());

    NetworkTableInstance networkTables = NetworkTableInstance.getDefault();
    networkTables.setUpdateRate(NETWORK_TABLES_UPDATE_RATE);

    oi = new OI();

    try
    {
        Logger.CustomLogger.setup();
    }
    catch (Throwable e) { Logger.logException(e);}
    
    Logger.consoleLog();

    SmartDashboard.putData("Auto mode", auton_chooser);
    Logger.consoleLog();
    System.out.println("Robot init");

    leds.socketSetup(RobotConstants.LED_PORT);    
    System.out.println("LED Setup Run");

    // autonomousCommand = oi.getSelectedAutonCommand();
    leds.setStillDrive(true);

    elbow.stop();
    wrist.stop();
    extension.stop();

    // operatorControl = new ControlOperatorController(oi.getOperatorController());
    updateArbitraryFeedForwards = new Notifier(new Runnable() {
      public void run() {
        wrist.updateArbitraryFeedForward();
        elbow.updateArbitraryFeedForward();
        extension.updateArbitraryFeedForward();
        intake.updateArbitraryFeedForward();
      }
    });

    updateArbitraryFeedForwards.startPeriodic(0.01);

    SmartDashboard.putNumberArray("reflect_tape_vision_data", new double[]{RobotConstants.VISION_ERROR_CODE,RobotConstants.VISION_ERROR_CODE,RobotConstants.VISION_ERROR_CODE});
    
    SmartDashboard.putString("vision-camera", "front");
    SmartDashboard.putString("vision-enabled", "disabled");
    SmartDashboard.putString("vision-status", "");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */  
 @Override
  public void robotPeriodic() {
    driveBase.sendEncoderDataToDashboard();

  }
  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    Logger.consoleLog("Robot Disabled");
    // autonomousCommand = oi.getSelectedAutonCommand();
    // driveBase.initCoastMode();
  }

  @Override
  public void disabledPeriodic() {

    driveBase.sendEncoderDataToDashboard();

    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

    if(DriverStation.getInstance().getAlliance().equals(Alliance.Red)) {
      leds.changeAlliance(false);
    } else if (DriverStation.getInstance().getAlliance().equals(Alliance.Blue)) {
      leds.changeAlliance(true);
    } else {
      leds.changeAlliance(true);
    }
    leds.setForwardData(true);

    driveBase.initBrakeMode();

    Logger.consoleLog("Auton Started");

    Scheduler.getInstance().add(new SafelyResetExtension());

    if (autonomousCommand != null) {
      autonomousCommand.start();
    }

    // if (operatorControl != null) {
    //   operatorControl.start();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    driveBase.initBrakeMode();

    if(DriverStation.getInstance().getMatchTime() < 130) {
      Scheduler.getInstance().add(new SafelyResetExtension());
    }

    Logger.consoleLog("Teleop Started");
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }

}