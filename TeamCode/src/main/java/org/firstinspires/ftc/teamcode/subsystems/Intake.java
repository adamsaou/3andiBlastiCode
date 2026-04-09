package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.config.HardwareBot;
import org.firstinspires.ftc.teamcode.config.RobotConstants;

public class Intake {
    private HardwareBot robot;

    public Intake(HardwareBot robot) {
        this.robot = robot;
    }

    public void start() { robot.intake.setPower(RobotConstants.INTAKE_POWER); }
    public void reverse() { robot.intake.setPower(-RobotConstants.INTAKE_POWER); }
    public void stop() { robot.intake.setPower(0); }
}