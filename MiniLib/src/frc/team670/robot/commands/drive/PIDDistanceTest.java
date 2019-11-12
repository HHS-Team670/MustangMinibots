package frc.team670.robot.commands.drive;

import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.team670.robot.Robot;
import frc.team670.robot.utils.Logger;
import frc.team670.robot.utils.MathUtils;

public class PIDDistanceTest extends Command {

	PIDController m_controller;
	double m_target;
	//TODO: Find values
	// proportional speed constant
	double kP = 7.0;

	// integral speed constant
	double kI = 0.018;

	// derivative speed constant
	double kD = 1.5;

	/**
	 * 
	 * @param target Target distance in inches
	 */
	public PIDDistanceTest(double target) {
		m_target = target;
		m_controller = new PIDController(kP, kI, kD);
		m_controller.setTolerance(0.05);
		requires(Robot.driveBase);
	}

	public void initialize() {
		Logger.consoleLog("TicksL: %s TicksR: %s", Robot.driveBase.getLeftEncoder().getTicks(), Robot.driveBase.getRightEncoder().getTicks());
		m_controller.setSetpoint(MathUtils.convertInchesToEncoderTicks(m_target));
	}

	public void execute() {
		Logger.consoleLog("TicksL: %s TicksR: %s", Robot.driveBase.getLeftEncoder().getTicks(), Robot.driveBase.getRightEncoder().getTicks());
		Robot.driveBase.tankDrive(m_controller.calculate(Robot.driveBase.getLeftEncoder().getTicks()), Robot.driveBase.getRightEncoder().getTicks());
	}

	public boolean isFinished() {
		return m_controller.atSetpoint();
	}

	public void end() {
		Robot.driveBase.stop();
	}
}
