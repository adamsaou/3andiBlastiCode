package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.config.HardwareBot;
import org.firstinspires.ftc.teamcode.config.RobotConstants;

public class Drivetrain {
    private HardwareBot robot;

    public Drivetrain(HardwareBot robot) {
        this.robot = robot;
    }

    public void drive(double y, double x, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
        
        robot.leftFront.setPower(((y + x + rx) / denominator) * RobotConstants.DRIVE_SPEED);
        robot.leftBack.setPower(((y - x + rx) / denominator) * RobotConstants.DRIVE_SPEED);
        robot.rightFront.setPower(((y - x - rx) / denominator) * RobotConstants.DRIVE_SPEED);
        robot.rightBack.setPower(((y + x - rx) / denominator) * RobotConstants.DRIVE_SPEED);
    }
}