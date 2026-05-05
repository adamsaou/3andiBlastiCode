package org.firstinspires.ftc.teamcode.macros;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

public class ScoringSequences {
    private Intake intake;
    private Outtake outtake;

    public ScoringSequences(Intake i, Outtake o) {
        this.intake = i;
        this.outtake = o;
    }

    public void quickDeposit() {
        intake.stop();
        outtake.open();
        // todo: timers/logic here for full automation
    }
}