package Minibots;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.*;
import Minibots.*;

public class RunIntakeTimed extends TimedCommand {

    private double seconds;
    private boolean reverse;
    private boolean accelerate;
    private SampleIntake intake;

    private RunIntakeTimed(boolean accel, boolean reserve, double secs, SampleIntake intak) {
        super("yee", secs, intak);
        this.seconds = secs;
        this.intake = intak;
        addRequirements(this.intake);
        this.reverse = reserve;
        this.accelerate = accel;
    }

    public RunIntakeTimed(boolean accelerated, boolean reversed, double seconds) {
        RunIntakeTimed(accelerated, reversed, seconds, new SampleIntake());
    }

    @Override
    protected void initialize() {
        intake.setAccelerate(this.accelerate);
    }

    @Override
    protected void execute() {
        intake.roll(this.reverse);
    }

    @Override
    protected boolean isFinished() {
        if (intake.checkHealth() != HealthState.GREEN) {
            return true;
        }
        if (intake.isJammed()) {
            return true;
        }
        if (super.isFinished()) {
            return true;
        }
        return false;
    }

    @Override
    protected void end() {
        intake.stop();
    }
    
}
