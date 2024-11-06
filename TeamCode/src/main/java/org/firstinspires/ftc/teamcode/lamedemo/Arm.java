package org.firstinspires.ftc.teamcode.lamedemo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private static int SHOULDER_BASKET_EXPAND_POSITION = 550;
    private static int SHOULDER_BASKET_RETRACT_POSITION = 5;
    private static int ELBOW_BASKET_EXPAND_POSITION = 350;
    private static int ELBOW_BASKET_RETRACT_POSITION = 1;

    private static int SHOULDER_SAMPLE_PICK_POSITION = 224;
    private static int ELBOW_SAMPLE_PICK_POSITION = 377;

    private static float SHOULDER_EXPAND_POWER = 0.5f;
    private static float ELBOW_EXPAND_POWER = 0.5f;
    private static float SHOULDER_RETRACT_POWER = 0.25f;
    private static float ELBOW_RETRACT_POWER = 0.25f;

    /*
     * Variables to store the speed the intake servo should be set at to intake, and
     * deposit game elements.
     */
    final double INTAKE_COLLECT = 0;
    final double INTAKE_OFF = 0.5;
    final double INTAKE_DEPOSIT = 1;

    /*
     * Variables to store the positions that the wrist should be set to when folding
     * in, or folding out.
     */
    final double WRIST_FOLDED_IN = 0.8333;
    final double WRIST_FOLDED_OUT = 0.5;

    private DcMotor elbow;
    private DcMotor shoulder;
    private Telemetry telemetry;
    private Servo intake = null; // the active intake servo
    private Servo wrist = null; // the wrist servo

    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        elbow = hardwareMap.get(DcMotor.class, "elbow");
        shoulder = hardwareMap.get(DcMotor.class, "shoulder");
        /* Define and initialize servos. */
        intake = hardwareMap.get(Servo.class, "intake");
        wrist = hardwareMap.get(Servo.class, "wrist");

        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setDirection(DcMotor.Direction.FORWARD);

        shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulder.setDirection(DcMotor.Direction.FORWARD);

        /* Make sure that the intake is off, and the wrist is folded in. */
        intake.setPosition(INTAKE_OFF);
        wrist.setPosition(WRIST_FOLDED_IN);
    }

    public void positionArmForSample() {
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setTargetPosition(SHOULDER_SAMPLE_PICK_POSITION);
        shoulder.setPower(SHOULDER_EXPAND_POWER);

        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setTargetPosition(ELBOW_SAMPLE_PICK_POSITION);
        elbow.setPower(ELBOW_EXPAND_POWER);

        wrist.setPosition(WRIST_FOLDED_OUT);
        // intake.setPower(INTAKE_COLLECT);
    }

    public void pickSampleIntake() {
        intake.setPosition(INTAKE_COLLECT);
    }
    
    public void stopSampleIntake() {
        intake.setPosition(INTAKE_OFF);
    }

    public void positionArmForFirstBasket() {
        shoulder.setTargetPosition(SHOULDER_BASKET_EXPAND_POSITION);
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setPower(SHOULDER_EXPAND_POWER);

        elbow.setTargetPosition(ELBOW_BASKET_EXPAND_POSITION);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setPower(ELBOW_EXPAND_POWER);
    }

    public void depositSampleInBasket() {
        intake.setPosition(INTAKE_DEPOSIT);
    }

    public void retractArm() {
        shoulder.setTargetPosition(SHOULDER_BASKET_RETRACT_POSITION);
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setPower(SHOULDER_RETRACT_POWER);

        elbow.setTargetPosition(ELBOW_BASKET_RETRACT_POSITION);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setPower(ELBOW_RETRACT_POWER);

        intake.setPosition(INTAKE_OFF);
        wrist.setPosition(WRIST_FOLDED_IN);
    }

    // Stop motor if not busy
    public void rest() {
        elbow.setPower(0);
        shoulder.setPower(0);
    }

    public void printTelemetry() {
        telemetry.addData("Elbow Motor Position", elbow.getCurrentPosition());
        telemetry.addData("Shoulder Motor Position", shoulder.getCurrentPosition());
        telemetry.addData("Wrist Servo Motor Position", wrist.getPosition());
        telemetry.addData("Intake Servo Motor Position", intake.getPosition());
        telemetry.update();
    }
}
