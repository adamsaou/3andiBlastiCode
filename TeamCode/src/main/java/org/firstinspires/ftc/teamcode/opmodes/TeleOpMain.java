package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.config.HardwareBot;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name="Main Manual")
public class TeleOpMain extends LinearOpMode {
    HardwareBot robot = new HardwareBot();
    Drivetrain drive;
    Intake intake;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        drive = new Drivetrain(robot);
        intake = new Intake(robot);

        waitForStart();

        while (opModeIsActive()) {
            drive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            
            if (gamepad1.a) intake.start();
            else if (gamepad1.b) intake.reverse();
            else intake.stop();
        }
    }
}