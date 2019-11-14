package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;
import frc.team670.robot.utils.MathUtils;

public class PIDDistanceDrive extends Command {

	PIDController m_leftController, m_rightController;

	double m_targetInches;

	// TODO: Find values
	// proportional speed constant
	double kP = 0.0;

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
	public PIDDistanceDrive(double targetInches) {
		m_targetInches = targetInches;
		m_leftController = new PIDController(kP, kI, kD);
		double toleranceTicks = MathUtils.convertInchesToEncoderTicks(TOLERANCE_INCHES);
		m_leftController.setTolerance(toleranceTicks);
		m_rightController = new PIDController(kP, kI, kD);
		m_rightController.setTolerance(toleranceTicks);
		requires(Robot.driveBase);
	}

	public void initialize() {
	    double numTicks = MathUtils.convertInchesToEncoderTicks(m_targetInches);
		m_leftController.setSetpoint(numTicks);
		m_rightController.setSetpoint(numTicks);
		Logger.consoleLog("Target ticks: %s TicksL: %s TicksR: %s", m_leftController.getSetpoint(),
				Robot.driveBase.getLeftEncoder().getTicks(), Robot.driveBase.getRightEncoder().getTicks());
	}

	public void execute() {
	    int leftTicks = Robot.driveBase.getLeftEncoder().getTicks();
		int rightTicks = Robot.driveBase.getRightEncoder().getTicks();
		double leftSpeed = m_leftController.calculate(leftTicks);
		double rightSpeed = m_leftController.calculate(rightTicks);
		Logger.consoleLog("TicksL: %s TicksR: %s, SpeedL: %s SpeedR: %s", leftTicks, rightTicks, leftSpeed, rightSpeed);
		Robot.driveBase.tankDrive(leftSpeed, rightSpeed);
	}

	public boolean isFinished() {
		return m_leftController.atSetpoint() || m_rightController.atSetpoint();
	}

	public void end() {
		Robot.driveBase.stop();
	}
}
