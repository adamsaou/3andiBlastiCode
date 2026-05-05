package org.firstinspires.ftc.teamcode.opmodes;

//Imports for the modes
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// imports for the Hardware and Drive train
import org.firstinspires.ftc.teamcode.config.HardwareBot;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Autonomous(name="Main Auto", group="Linear Opmode")
public class AutoMain extends LinearOpMode {
    
    HardwareBot robot = new HardwareBot();
    Drivetrain drive;
    String startPosition = "NONE"; // Added this to fix your telemetry error

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        drive = new Drivetrain(robot);

        // Handling position selection
        while (!isStarted() && !isStopRequested()) {
            if (gamepad1.dpad_left) {
                startPosition = "LEFT";
            } else if (gamepad1.dpad_right) {
                startPosition = "RIGHT";
            }
            telemetry.addData("Selected Position: ", startPosition);
            telemetry.update();
        }

        waitForStart();
        //todo Add movement code
    }
}