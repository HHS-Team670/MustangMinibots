package frc.team670.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.*;
import frc.team670.robot.Robot;
import frc.team670.robot.commands.drive.DistanceDrive;
import frc.team670.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.junit.*;

public class DistanceDriveTest {

    @Test
    public void testMethodCalledOnDriveBase() {
        DriveBase mockedDriveBase = mock(DriveBase.class);
        DistanceDrive distanceDrive = new DistanceDrive(1, 50, 50, mockedDriveBase);
       
        distanceDrive.execute();

        verify(mockedDriveBase, times(1)).tankDrive(50, 50);
    }

    @Test
    public void testMethodCalled() {
        CommandScheduler mockedScheduler = mock(CommandScheduler.class);
        DistanceDrive distanceDrive = new DistanceDrive(1, 50, 50, mockedDriveBase);

        mockedScheduler.schedule(distanceDrive);
        mockedScheduler.run();

        verify(distanceDrive, times(1)).init();
        verify(distanceDrive, times(1)).end();
    }
}