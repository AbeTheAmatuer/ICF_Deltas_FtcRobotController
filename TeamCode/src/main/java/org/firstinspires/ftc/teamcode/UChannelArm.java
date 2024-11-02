package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp

public class UChannelArm extends LinearOpMode{
    private Blinker control_Hub;
    private DcMotor eMotor;
    private IMU imu;
    private DcMotor sMotor;
    
    public void runOpMode(){
        DcMotor baseMotor = hardwareMap.dcMotor.get("sMotor");
        DcMotor elbowMotor = hardwareMap.dcMotor.get("eMotor");
        
        baseMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        baseMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbowMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        int sTargetPos = 0;
        int eTargetPos = 0;
        double power = 0.25;

            
        waitForStart();
        
        while(opModeIsActive()){
            
            //ENCODER DATA
            double CPR = 537.7;
            double diameter = 1.0;
            double circumference = Math.PI * diameter;
            int sPos = baseMotor.getCurrentPosition();
            int ePos = elbowMotor.getCurrentPosition();
            
            //sMotor variables
            double sRevs = sPos/CPR;
            double sAngle = sRevs * 360;
            double sAngleNormalized = sAngle % 360;
            
            //eMotor variables
            double eRevs = ePos/CPR;
            double eAngle = eRevs * 360;
            double eAngleNormalized = eAngle % 360;

            double sDistance = circumference * sRevs;
            double eDistance = circumference * eRevs;
            
        
            
            if(gamepad1.x){
                sTargetPos = 910;
                power = 1;
                eTargetPos = 402;
                power = 1;

            }else{
                power = 0.25;
            }
            if(gamepad1.b){
                sTargetPos = 0;
                eTargetPos = 0;

            }
            
            elbowMotor.setTargetPosition(eTargetPos);
            elbowMotor.setPower(power);
            elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            
            baseMotor.setTargetPosition(sTargetPos);
            baseMotor.setPower(power);
            baseMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            
            telemetry.addData("Changed Encoder Position", ePos);
            telemetry.addData(" Encoder Revolutions", eRevs);
            telemetry.addData(" Encoder Angle (Degrees)", eAngle);
            telemetry.addData(" Encoder Angle - Normalized (Degrees)", eAngleNormalized);
            telemetry.addData(" Linear Distance", eDistance);
            
            telemetry.addData("eTargetPos", eTargetPos);
            telemetry.addData("sTargetPos", sTargetPos);


            telemetry.update();
        }


    }
    // todo: write your code here
}
