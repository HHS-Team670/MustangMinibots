package frc.team670.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.*;
import frc.team670.robot.Robot;
import frc.team670.robot.commands.drive.PIDDistanceDrive;
import frc.team670.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.junit.*;

public class BalancedDriveTest {

    @Test
    public void testMethodCalled() {
        CommandScheduler mockedScheduler = mock(CommandScheduler.class);
        PIDDistanceDrive pidDistancDrive = new PIDDistanceDrive(1, mockedDriveBase);

        mockedScheduler.schedule(pidDistancDrive);
        mockedScheduler.run();

        verify(pidDistancDrive, times(1)).init();
        verify(pidDistancDrive, times(1)).end();
    }
}