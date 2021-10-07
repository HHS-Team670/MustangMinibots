/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team670.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team670.robot.commands.drive.BalancedDrive;
import frc.team670.robot.commands.drive.DistanceDrive;
import frc.team670.robot.commands.drive.TimeDrive;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {


  private static DriveBase driveBase = new DriveBase();
  private static OI oi;


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }

  public void robotInit() {
    oi = new OI();

    try {
      Logger.CustomLogger.setup();
    } catch (Throwable e) {
      Logger.logException(e);
    }
    
    Logger.consoleLog();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new DistanceDrive(10, 1, 1, driveBase);
  }


  public static void autonomousInit(){
    
  }

  public static void teleopInit() {
    Logger.consoleLog("Teleop Started");

  }

  public static void disabled(){
    Logger.consoleLog("Robot Disabled");
  }

  public static void periodic() {
    
  }

}
