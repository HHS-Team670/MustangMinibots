package frc.team670.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.*;
import frc.team670.robot.Robot;
import frc.team670.robot.commands.drive.BalancedDrive;
import frc.team670.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.junit.*;

public class BalancedDriveTest {

    @Test
    public void testMethodCalledOnDriveBase() {
        DriveBase mockedDriveBase = mock(DriveBase.class);
        BalancedDrive balancedDrive = new BalancedDrive(1, 50, 50, mockedDriveBase);
       
        distanceDrive.execute();

        verify(mockedDriveBase, times(1)).tankDrive(50, 50);

    }

    @Test
    public void testMethodCalled() {
        CommandScheduler mockedScheduler = mock(CommandScheduler.class);
        BalancedDrive balancedDrive = new BalancedDrive(1, 50, 50, mockedDriveBase);

        mockedScheduler.schedule(balancedDrive);
        mockedScheduler.run();

        verify(balancedDrive, times(1)).init();
        verify(balancedDrive, times(1)).end();
    }
}