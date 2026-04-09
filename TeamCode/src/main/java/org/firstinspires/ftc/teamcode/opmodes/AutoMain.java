package org.firstinspires.ftc.teamcode.opmodes;

// These 2 lines fix the "LinearOpMode" and "Autonomous" errors
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// These lines fix the "HardwareBot" and "Drivetrain" errors
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

        // This loop handles your position selection before the match starts
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
        // Add your movement code here (e.g., drive.drive(1, 0, 0);)
    }
}