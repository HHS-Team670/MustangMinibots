package frc.team670.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.*;
import frc.team670.robot.Robot;
import frc.team670.robot.commands.drive.TimeDrive;
import frc.team670.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.junit.*;

public class TimeDriveTest {

    @Test
    public void testMethodCalledOnDriveBase() {
        DriveBase mockedDriveBase = mock(DriveBase.class);
        TimeDrive timedDrive = new TimeDrive(1, 50, mockedDriveBase);
       
        timedDrive.execute();

        verify(mockedDriveBase, times(1)).tankDrive(50, 50);

    }

    @Test
    public void testMethodCalled() {
        CommandScheduler mockedScheduler = mock(CommandScheduler.class);
        TimeDrive timedDrive = new TimeDrive(1, 50, mockedDriveBase);

        mockedScheduler.schedule(timedDrive);
        mockedScheduler.run();

        verify(timedDrive, times(1)).init();
        verify(timedDrive, times(1)).end();
    }
}