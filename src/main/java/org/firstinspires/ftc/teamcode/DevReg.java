package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/////////////////////
////// DevReg ///////
/////////////////////
// Device Registry //
/////////////////////

public class DevReg
{
    // Create new Motor Objects
    public DcMotor leftDrive;  // Probably don't need = null since the variable is null by default
    public DcMotor rightDrive;
    public DcMotor leftSpin;
    public DcMotor rightSpin;
    public DcMotor drawBridge;
    public DcMotor spinHopper;


    // Create new Servo Objects
    //public Servo servoTest; // Probably don't need = null since the variable is null by default

    // Import the HardwareMap Class (Defined here because defining in a function will have it limited to that function)
    HardwareMap hwMap       = null; // Probably don't need = null since the variable is null by default

    // Constructor
    public DevReg(){}; // I only stuck the brackets here because there's literally NOTHING HERE

    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;

        //
        // Initialize Devices from Phone Configs
        //

        // Motors
        leftDrive = hwMap.get(DcMotor.class, "LDc"); // Calls the config file
        rightDrive = hwMap.get(DcMotor.class, "RDc");
        leftSpin = hwMap.get(DcMotor.class, "LSc");
        rightSpin = hwMap.get(DcMotor.class, "RSc");
        drawBridge = hwMap.get(DcMotor.class, "DBc");
        spinHopper = hwMap.get(DcMotor.class, "SHc");

        // Servos
        // servoTest = hwMap.get(Servo.class, "ServTest"); // Calls the config file

        //
        // Set Device Modes
        //

        // Motors
        //leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // AndyMark Motors are opposite of this
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //leftSpin.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightSpin.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSpin.setDirection(DcMotor.Direction.FORWARD);
        rightSpin.setDirection(DcMotor.Direction.REVERSE);

        //drawBridge.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //spinHopper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //
        // Reset the Devices
        //

        // Motors
        leftDrive.setPower(0); // Stop motor power just in case ;)
        rightDrive.setPower(0);
        leftSpin.setPower(0);
        rightSpin.setPower(0);
        drawBridge.setPower(0);
        spinHopper.setPower(0);


        // Servos
        //servoTest.setPosition(0.0); // I'm pretty sure this takes a float/double as a parameter
    }
}

