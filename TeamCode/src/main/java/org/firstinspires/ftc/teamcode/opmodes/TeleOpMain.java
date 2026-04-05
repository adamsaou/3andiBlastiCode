@TeleOp(name="Main Manual", group="Linear Opmode")
public class TeleOpMain extends LinearOpMode {
    
    HardwareBot robot = new HardwareBot();
    Drivetrain drive;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap); // Pulls from your single config file
        drive = new Drivetrain(robot);

        waitForStart();

        while (opModeIsActive()) {
            // Just read the gamepad and pass it to the separated file!
            double y = -gamepad1.left_stick_y; 
            double x = gamepad1.left_stick_x; 
            double rx = gamepad1.right_stick_x;

            drive.moveTeleOp(y, x, rx); 
        }
    }
}