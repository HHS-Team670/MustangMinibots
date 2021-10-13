package frc.team670.robot.commands.drive;

public class Turn extends InstantCommand{

    private DriveBase driveBase;
    private double degrees;
    private double leftPower;
    private double rightPower;

    public Turn(DriveBase driveBase, double degrees, double power){

        this.driveBase=driveBase;
        addRequirements(driveBase);

        // positive degrees --> turns right
        // negative degrees --> turns left
        int direction = degrees/abs(degrees) == 1 // 1 --> turning right
        this.rightPower = -1*direction*power
        this.leftPower = direction*power

    }
    
    public void execute() {
        ;
    }
}
