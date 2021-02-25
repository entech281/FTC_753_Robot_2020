package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

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
    public BNO055IMU imu;
    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

    // Create new Servo Objects
    //public Servo servoTest; // Probably don't need = null since the variable is null by default

    // Import the HardwareMap Class (Defined here because defining in a function will have it limited to that function)
    HardwareMap hwMap       = null; // Probably don't need = null since the variable is null by default

    // Constructor
    public DevReg(){} // I only stuck the brackets here because there's literally NOTHING HERE

    public void init(HardwareMap ahwMap)
    {
        hwMap = ahwMap;
        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
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
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    public void driveForward (double power, int duration) {
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int encoderR = rightDrive.getCurrentPosition();
        int encoderL = leftDrive.getCurrentPosition();
        leftDrive.setPower(-power);
        rightDrive.setPower(-power);
        while (encoderL > duration && encoderR > duration) {
            encoderR = rightDrive.getCurrentPosition();
            encoderL = leftDrive.getCurrentPosition();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void driveBackward (double power, int duration) {
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int encoderR = rightDrive.getCurrentPosition();
        int encoderL = leftDrive.getCurrentPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(power);
        while (encoderL < duration && encoderR < duration) {
            encoderR = rightDrive.getCurrentPosition();
            encoderL = leftDrive.getCurrentPosition();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void turnRight (double power, int duration) {
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int encoderR = rightDrive.getCurrentPosition();
        int encoderL = leftDrive.getCurrentPosition();
        leftDrive.setPower(-power);
        rightDrive.setPower(power);
        while (encoderL > duration) {
            encoderR = rightDrive.getCurrentPosition();
            encoderL = leftDrive.getCurrentPosition();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void turnLeft (double power, int duration) {
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int encoderR = rightDrive.getCurrentPosition();
        int encoderL = leftDrive.getCurrentPosition();
        leftDrive.setPower(power);
        rightDrive.setPower(-power);
        while (encoderR > duration) {
            encoderR = rightDrive.getCurrentPosition();
            encoderL = leftDrive.getCurrentPosition();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

   /*   public void raiseDrawbridge (double power, int duration) {
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int encoderR = rightDrive.getCurrentPosition();
        leftDrive.setPower(power);
        while (encoderL > duration && encoderR > duration) {
            encoderL = leftDrive.getCurrentPosition();
        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);
     }
*/

}

