package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@TeleOp


public class TeleOp extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
   
public void runOpMode() {

    //Instantiating all the motors with the names from the config
    backLeft = hardwareMap.get (DcMotor.class,"backleft");
    backRight = hardwareMap.get (DcMotor.class,"backright");
    frontLeft= hardwareMap.get (DcMotor.class,"frontleft");
    frontRight= hardwareMap.get (DcMotor.class,"frontright");
   
    backLeft.setDirection(DcMotor.Direction.REVERSE);
   // frontLeft.setDirection(DcMotor.Direction.REVERSE);
    waitForStart();


    while (opModeIsActive()) {
       
     /*  
      float fwdBackPower = -gamepad1.right_stick_y;
       float strafePower= gamepad1.right_stick_x;
       float turnPower = gamepad1.left_stick_x; 
       

       float leftFrontPower = fwdBackPower + turnPower + strafePower;
       float rightFrontPower = fwdBackPower - turnPower - strafePower;
       float leftBackPower = fwdBackPower + turnPower - strafePower;
       float rightBackPower = fwdBackPower - turnPower + strafePower; 

   
    backLeft.setPower(leftBackPower);
    backRight.setPower(rightBackPower);
    frontLeft.setPower(leftFrontPower);
    frontRight.setPower(rightFrontPower); */
    
          
                //Setting x and y to the values returned by the left joystick on the x and y axes  
                float x = this.gamepad1.left_stick_x;
                float y = this.gamepad1.left_stick_y * -1;
                //Turn x is the right gamepad x value, move the right joystick left to rotate in place clockwise, move it left to rotate in place counterclockwise
                float turnX = this.gamepad1.right_stick_x;
            
            
                frontRight.setPower((-x+y) + (turnX * -1)); 
                frontLeft.setPower((x+y) + turnX);
            
                backRight.setPower(x+y + (turnX * -1));
                backLeft.setPower(-x+y + turnX);
          
  telemetry.addData("data", "x: " + x + "y: " +y + "turnX" + turnX);
   telemetry.update();
}
    }
  }
