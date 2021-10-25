package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team670.robot.Robot;
import frc.team670.robot.RobotContainer;
import frc.team670.robot.subsystems.DriveBase;
import frc.team670.robot.utils.Logger;
import frc.team670.robot.utils.MathUtils;

public class PIDDistanceDrive extends CommandBase {

	PIDController m_leftController, m_rightController;

	double m_targetInches;
	DriveBase driveBase;

	// TODO: Find values
	// proportional speed constant
	double kP = 0.1;

	// integral speed constant
	double kI = 0.0;

	// derivative speed constant
	double kD = 0.0;

	// margin of error
	double TOLERANCE_INCHES = 0.5;

	/**
	 * 
	 * @param target Target distance in inches
	 */
	public PIDDistanceDrive(double targetInches, DriveBase driveBase) {
		m_targetInches = targetInches;
		m_leftController = new PIDController(kP, kI, kD);
		double toleranceTicks = MathUtils.convertInchesToEncoderTicks(TOLERANCE_INCHES);
		m_leftController.setTolerance(toleranceTicks);
		m_rightController = new PIDController(kP, kI, kD);
		m_rightController.setTolerance(toleranceTicks);
		this.driveBase = driveBase;
		addRequirements(driveBase);
	}

	public void initialize() {
	    double numTicks = MathUtils.convertInchesToEncoderTicks(m_targetInches);
		m_leftController.setSetpoint(numTicks);
		m_rightController.setSetpoint(numTicks);
		Logger.consoleLog("Target ticks: %s TicksL: %s TicksR: %s", m_leftController.getSetpoint(),
				driveBase.getLeftEncoder().getTicks(), driveBase.getRightEncoder().getTicks());
	}

	public void execute() {
	    int leftTicks = driveBase.getLeftEncoder().getTicks();
		int rightTicks = driveBase.getRightEncoder().getTicks();
		double leftSpeed = m_leftController.calculate(leftTicks);
		double rightSpeed = m_rightController.calculate(rightTicks);
		Logger.consoleLog("L setpoint: %s", m_leftController.getSetpoint());
		Logger.consoleLog("R setpoint: %s", m_rightController.getSetpoint());
		Logger.consoleLog("TicksL: %s TicksR: %s, SpeedL: %s SpeedR: %s", leftTicks, rightTicks, leftSpeed, rightSpeed);
		driveBase.tankDrive(leftSpeed, rightSpeed);
		System.out.println(leftSpeed);
		System.out.println(rightSpeed);
	}

	public boolean isFinished() {
		Logger.consoleLog("L setpoint: %s, L at: %s", m_leftController.getSetpoint(), m_leftController.atSetpoint());
		Logger.consoleLog("R setpoint: %s, R at: %s", m_rightController.getSetpoint(), m_rightController.atSetpoint());
		return (m_leftController.atSetpoint() || m_rightController.atSetpoint());
	}

	public void end(boolean isFinished) {
		Logger.consoleLog("Is finished", m_leftController.getSetpoint());
		driveBase.stop();
	}
}
