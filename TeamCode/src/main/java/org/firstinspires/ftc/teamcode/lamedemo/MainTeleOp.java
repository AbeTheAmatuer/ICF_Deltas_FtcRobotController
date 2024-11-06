package org.firstinspires.ftc.teamcode.lamedemo;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.lamedemo.DriveTrain;
import org.firstinspires.ftc.teamcode.lamedemo.Arm;

@TeleOp(name = "LameDuckDemo", group = "LameDuckDemo")
public class MainTeleOp extends LinearOpMode {

    private DriveTrain drivetrain;
    private Arm arm;
    private boolean armInDepositPosition = false;

    @Override
    public void runOpMode() {
        // Initialize the drivetrain and Arm
        drivetrain = new DriveTrain(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            // Read gamepad inputs
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            // Drive the robot
            drivetrain.drive(drive, strafe, rotate);

            if (gamepad1.b) {
                arm.positionArmForSample();
                armInDepositPosition = false;
            } else if (gamepad1.right_trigger > 0.4) {
                arm.pickSampleIntake();
                armInDepositPosition = false;
            } else if (gamepad1.x) {
                arm.positionArmForFirstBasket();
                armInDepositPosition = true;
            } else if (gamepad1.left_trigger > 0.4 && armInDepositPosition) {
                arm.depositSampleInBasket();
            } else if (gamepad1.b) {
                arm.retractArm();
                armInDepositPosition = false;
            } else {
                arm.stopSampleIntake();
                armInDepositPosition = false;
            }

            /*
             * else if (!leftMotor.isBusy()) {
             * arm.rest();
             * }
             */
            telemetry.addData("ArmInDepositPosition", armInDepositPosition);
            // drivetrain.printTelemetry();
            arm.printTelemetry();
            telemetry.update();
        }
    }
}
