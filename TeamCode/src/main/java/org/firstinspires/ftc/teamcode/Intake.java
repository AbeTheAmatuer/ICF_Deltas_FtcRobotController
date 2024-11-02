package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class Intake extends LinearOpMode{

    public void runOpMode(){
    Servo intake = hardwareMap.get(Servo.class, "intake");
    Servo pivot = hardwareMap.get(Servo.class, "pivot");
    
    pivot.setPosition(0);
    //pivot.setDirection(Servo.Direction.REVERSE);
    
    waitForStart();
    
    while(opModeIsActive()){
        
        double currPivotPos = (double) pivot.getPosition();
        if(gamepad1.x){
                intake.setPosition(1);
        }else if(gamepad1.y){
                intake.setPosition(0);
        }else{
            intake.setPosition(0.5);
        }
        
        if(gamepad1.a){
            pivot.setPosition(1);
        }
        if(gamepad1.b){
            pivot.setPosition(0);
        }
        
        telemetry.addData("intake position", intake.getPosition() );
        telemetry.addData("Pivot Pos", pivot.getPosition());
        telemetry.update();
    }
        
    }
}
