package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class ArmControl extends LinearOpMode{
    @Override
    //CHANGE FROM ANDROID STUDIO Yaya
   public void runOpMode() throws InterruptedException {
        DcMotor leftMotor = hardwareMap.dcMotor.get("left_motor");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

         while (opModeIsActive()) {
            double CPR = 537.7;

            double diameter = 1.0; // Replace with your wheel/spool's diameter
            double circumference = Math.PI * diameter;

            // Get the current position of the motor
            int position = leftMotor.getCurrentPosition();
            double revolutions = position/CPR;

            double angle = revolutions * 360;
            double angleNormalized = angle % 360;

            double distance = circumference * revolutions;

            //leftMotor.setPower((revolutions < 1) ? 0.25 : 0);
            //leftMotor.setPower((gamepad1.b && (Math.abs(revolutions)) > 0.0446) ? 1 : 0);
            //leftMotor.setPower((gamepad1.x && (Math.abs(revolutions) < 4.9117)) ? -0.75 : 0);

           //CLICKING X FULLY EXTENDS IT, CLICKING B FULLY RETRACTS IT
            if(gamepad1.x){
               leftMotor.setTargetPosition(2660);
               leftMotor.setPower(0.75);
            }
            if(gamepad1.b){
               leftMotor.setTargetPosition(0);
               leftMotor.setPower(-1);
            }
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            /*
            if(gamepad1.y && position > -5){
                while(position > 0);
            }
            */
            //Show the position of the motor on telemetry
            telemetry.addData("Encoder Position", position);
            telemetry.addData("Encoder Revolutions", revolutions);
            telemetry.addData("Encoder Angle (Degrees)", angle);
            telemetry.addData("Encoder Angle - Normalized (Degrees)", angleNormalized);
            telemetry.addData("Linear Distance", distance);
            telemetry.update();
      }
   }    
    
}
