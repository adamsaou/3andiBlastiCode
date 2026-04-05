@Autonomous(name="Main Auto", group="Linear Opmode")
public class AutoMain extends LinearOpMode {
    
    HardwareBot robot = new HardwareBot();
    Drivetrain drive;
    
    // Default starting position
    String startPosition = "LEFT"; 

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        drive = new Drivetrain(robot);

        // This loops BEFORE you press Play
        while (!isStarted() && !isStopRequested()) {
            if (gamepad1.dpad_left) {
                startPosition = "LEFT";
            } else if (gamepad1.dpad_right) {
                startPosition = "RIGHT";
            }
            telemetry.addData("Selected Position: ", startPosition);
            telemetry.update();
        }

        // --- PLAY IS PRESSED ---
        if (startPosition.equals("LEFT")) {
            // Execute Left Path
            drive.driveForward(1000); // 1000ms or ticks
        } else {
            // Execute Right Path
            drive.driveForward(500);
            drive.turnRight(90);
        }
    }
}