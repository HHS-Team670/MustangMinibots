package frc.team670.robot.utils;

import frc.team670.robot.RobotConstants;

public class MathUtils {
	/**
	 * 
	 * @param ticks
	 * @return corresponding inch value for number of ticks
	 */
	public static double convertEncoderTicksToInches(int ticks) {
		return ticks*((Math.PI * RobotConstants.DRIVE_BASE_WHEEL_DIAMETER)/RobotConstants.ENCODER_TICKS_PER_ROTATION);
	}
	
	public static double convertInchesToEncoderTicks(double inches) {
		return inches*(RobotConstants.ENCODER_TICKS_PER_ROTATION/(Math.PI*RobotConstants.DRIVE_BASE_WHEEL_DIAMETER));
	}
}
