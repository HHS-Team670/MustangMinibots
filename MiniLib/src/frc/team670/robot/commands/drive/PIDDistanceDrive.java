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
		m_leftController.setTolerance(MathUtils.convertInchesToEncoderTicks(TOLERANCE_INCHES));
		m_rightController = new PIDController(kP, kI, kD);
		m_rightController.setTolerance(MathUtils.convertInchesToEncoderTicks(TOLERANCE_INCHES));
		requires(Robot.driveBase);
	}

	public void initialize() {
		m_leftController.setSetpoint(MathUtils.convertInchesToEncoderTicks(m_targetInches));
		m_rightController.setSetpoint(MathUtils.convertInchesToEncoderTicks(m_targetInches));
		Logger.consoleLog("Target ticks: %s TicksL: %s TicksR: %s", m_leftController.getSetpoint(),
				Robot.driveBase.getLeftEncoder().getTicks(), Robot.driveBase.getRightEncoder().getTicks());
	}

	public void execute() {
		Logger.consoleLog("TicksL: %s TicksR: %s", Robot.driveBase.getLeftEncoder().getTicks(),
				Robot.driveBase.getRightEncoder().getTicks());
		Robot.driveBase.tankDrive(m_leftController.calculate(Robot.driveBase.getLeftEncoder().getTicks()),
				m_rightController.calculate(Robot.driveBase.getRightEncoder().getTicks()));
	}

	public boolean isFinished() {
		return m_leftController.atSetpoint() || m_rightController.atSetpoint();
	}

	public void end() {
		Robot.driveBase.stop();
	}
}
