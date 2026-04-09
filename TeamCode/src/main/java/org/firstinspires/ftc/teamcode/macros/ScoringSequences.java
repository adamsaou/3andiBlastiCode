package org.firstinspires.ftc.teamcode.macros;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outake;

public class ScoringSequences {
    private Intake intake;
    private Outake outtake;

    public ScoringSequences(Intake i, Outake o) {
        this.intake = i;
        this.outtake = o;
    }

    public void quickDeposit() {
        intake.stop();
        outtake.open();
        // Add timers/logic here for full automation
    }
}