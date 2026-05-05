package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.config.HardwareBot;
import org.firstinspires.ftc.teamcode.config.RobotConstants;

public class Outtake {
    private HardwareBot robot;

    public Outtake(HardwareBot robot) {
        this.robot = robot;
    }

    public void open()    { robot.outtake.setPosition(1.0); }
    public void close()   { robot.outtake.setPosition(0.0); }
    public void neutral() { robot.outtake.setPosition(0.5); }
}
