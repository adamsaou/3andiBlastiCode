package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareBot {
    public DcMotor leftFront, rightFront, leftBack, rightBack;
    public DcMotor intake;
    public Servo outake;


    public void init(HardwareMap hwMap) {
        leftFront  = hwMap.get(DcMotor.class, RobotConstants.LF_NAME);
        rightFront = hwMap.get(DcMotor.class, RobotConstants.RF_NAME);
        leftBack   = hwMap.get(DcMotor.class, RobotConstants.LB_NAME);
        rightBack  = hwMap.get(DcMotor.class, RobotConstants.RB_NAME);
        
        intake = hwMap.get(DcMotor.class, RobotConstants.INTAKE_MOTOR);
        outake = hwMap.get(Servo.class, RobotConstants.OUTTAKE_SERVO);

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        
        // Zero Power Behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}