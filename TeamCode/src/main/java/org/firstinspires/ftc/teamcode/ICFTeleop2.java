package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "ICFTeleOp2", group = "practice")
public class ICFTeleOp2 extends LinearOpMode
{

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor blMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor brMotor = hardwareMap.dcMotor.get("backRight");
        DcMotor flMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frMotor = hardwareMap.dcMotor.get("frontRight");

frMotor.setDirection(DcMotor.Direction.REVERSE);
brMotor.setDirection(DcMotor.Direction.REVERSE);
        
         IMU imu = hardwareMap.get(IMU.class, "imu");
       
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y *-1; 
            double x = gamepad1.left_stick_x *1.1;
            double rx = gamepad1.right_stick_x;


            if (gamepad1.x) {
                imu.resetYaw();
            }
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            
            double rotX = x * (Math.cos(-botHeading) - y * Math.sin(-botHeading));
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing
        
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double flPower = (rotY + rotX + rx) / denominator;
            double blPower = (rotY - rotX + rx) / denominator;
            double frPower = (rotY - rotX - rx) / denominator;
            double brPower = (rotY + rotX - rx) / denominator;
            
        flMotor.setPower(flPower);
        frMotor.setPower(frPower);
        blMotor.setPower(blPower);
        brMotor.setPower(brPower); 
        
        flMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        blMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        brMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        

        
        
                                }
    
}
}


