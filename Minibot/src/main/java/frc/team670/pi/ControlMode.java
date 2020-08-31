package frc.team670.pi;

/**
 * Possible control modes for a motor.
 * @author ctychen
 *
 */
public enum ControlMode {
	/**
	 * Percent output [-1.0, +1.0]
	 */
	PercentOutput(0),
	/**
	 * Position closed loop
	 */
	Position(1),
	/**
	 * Velocity closed loop
	 */
	Velocity(2),
	/**
	 * Input current closed loop
	 */
	Current(3),
	/**
	 * Follow other motor controller
	 */
	Follower(5),
	/**
	 * Motion Profile
	 */
	MotionProfile(6),

	/**
	 * Disable Motor Controller
	 */
	Disabled(7);

	/**
	 * Value of control mode
	 */
	public final int value;

	/**
	 * Create ControlMode of initValue
	 * 
	 * @param initValue Value of ControlMode
	 */
	ControlMode(int initValue) {
		this.value = initValue;
	}
};