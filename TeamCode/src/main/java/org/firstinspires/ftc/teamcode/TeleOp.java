package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class IbrahimTeleOp extends LinearOpMode {
    @Override
    
public void runOpMode() throws InterruptedException{

    //Instantiating all the motors with the names from the config
    DcMotor backLeft = hardwareMap.get (DcMotor.class,"backleft");
    DcMotor backRight = hardwareMap.get (DcMotor.class,"backright");
    DcMotor frontLeft= hardwareMap.get (DcMotor.class,"frontleft");
    DcMotor frontRight= hardwareMap.get (DcMotor.class,"frontright");
   
    backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    
    waitForStart();
    
    if(isStopRequested()) return;

    while (opModeIsActive()) {
       double y = -gamepad1.left_stick_y;
       double x = gamepad1.left_stick_x * 1.1;
       double rx = -gamepad1.right_stick_x;
       
       double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
       
       double fL = (y + x + rx);
       double bL = (y - x + rx);
       double fR = (y - x - rx);
       double bR = (y + x - rx);
       
       frontRight.setPower(fL);
       backLeft.setPower(bL);
       frontLeft.setPower(fR);
       backRight.setPower(bR);
     
          
  //telemetry.addData("data", "x: " + x + "y: " +y + "turnX" + );
   //telemetry.update();
        }
    }
}    
