package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.config.HardwareBot;
import org.firstinspires.ftc.teamcode.config.RobotConstants;

public class Outake {
    private HardwareBot robot;

    public Outake(HardwareBot robot) {
        this.robot = robot;
    }

    // Add this method to fix the ScoringSequences error!
    public void open() {
        robot.outake.setPosition(1.0); // Or setPosition(1.0) if it's a servo
    }

    public void start() { robot.outake.setPosition(1.0); }
    public void reverse() { robot.outake.setPosition(0.0); }
    public void stop() { robot.outake.setPosition(0.5); }
}