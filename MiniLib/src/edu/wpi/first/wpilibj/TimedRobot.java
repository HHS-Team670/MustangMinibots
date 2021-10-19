/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

//import edu.wpi.first.hal.FRCNetComm.tInstances;
//import edu.wpi.first.hal.FRCNetComm.tResourceType;
//import edu.wpi.first.hal.HAL;
//import edu.wpi.first.hal.NotifierJNI;

/**
 * TimedRobot implements the IterativeRobotBase robot program framework.
 *
 * <p>The TimedRobot class is intended to be subclassed by a user creating a robot program.
 *
 * <p>periodic() functions from the base class are called on an interval by a Notifier instance.
 */
public class TimedRobot extends IterativeRobotBase {
  public static final double kDefaultPeriod = 0.2;

  // The C pointer to the notifier object. We don't use it directly, it is
  // just passed to the JNI bindings.
  //private final int m_notifier = NotifierJNI.initializeNotifier();

  // The absolute expiration time
  private double m_expirationTime;

  /**
   * Constructor for TimedRobot.
   */
  protected TimedRobot() {
    this(kDefaultPeriod);
  }

  /**
   * Constructor for TimedRobot.
   *
   * @param period Period in seconds.
   */
  protected TimedRobot(double period) {
    super(period);

    //HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Timed);
  }

  @Override
  @SuppressWarnings("NoFinalizer")
  protected void finalize() {
//    NotifierJNI.stopNotifier(m_notifier);
//    NotifierJNI.cleanNotifier(m_notifier);
  }

  /**
   * Provide an alternate "main loop" via startCompetition().
 * @throws Exception 
   */
  @Override
  @SuppressWarnings("UnsafeFinalization")
  public void startCompetition() throws Exception {
    robotInit();

    // Tell the DS that the robot is ready to be enabled
//    HAL.observeUserProgramStarting();
//
//    m_expirationTime = RobotController.getFPGATime() * 1e-6 + m_period;
//    updateAlarm();
//

    // using currentTime/nextTime to replace the NotifierJNI - we could probably get NotifierJNI to work if it ever mattered
    long millisToNextTime = (long)(1000.0 * m_period);
    long nextTime = System.currentTimeMillis() + millisToNextTime;
    // Loop forever, calling the appropriate mode-dependent function
    autonomousInit();
    while (true) {
//      long curTime = NotifierJNI.waitForNotifierAlarm(m_notifier);
//      if (curTime == 0) {
//        break;
//      }
//
//      m_expirationTime += m_period;
//      updateAlarm();

      long currentTime = System.currentTimeMillis();
      while (currentTime < nextTime) {
        Thread.sleep(nextTime - currentTime);
        currentTime = System.currentTimeMillis();
      }
      loopFunc();
      nextTime = currentTime + millisToNextTime;
    }
  }

  /**
   * Get time period between calls to Periodic() functions.
   */
//  public double getPeriod() {
//    return m_period;
//  }

  /**
   * Update the alarm hardware to reflect the next alarm.
   */
//  @SuppressWarnings("UnsafeFinalization")
//  private void updateAlarm() {
//   // NotifierJNI.updateNotifierAlarm(m_notifier, (long) (m_expirationTime * 1e6));
//  }
}
