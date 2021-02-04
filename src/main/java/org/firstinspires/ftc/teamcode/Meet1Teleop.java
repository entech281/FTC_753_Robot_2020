package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Meet1Teleop", group="Meet1")
public class Meet1Teleop extends OpMode
{

    /* Declare Teleop members. */
    DevReg robot       = new DevReg(); // use the class created to define a Pushbot's hardware

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap); // We don't change this hardware map

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Welcome to Meet 1");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;
        double lTrigger;
        double rTrigger;


        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        // Apply variables to motors
        robot.leftDrive.setPower(left);
        robot.rightDrive.setPower(right);

    /*    // Use gamepad left & right Bumpers to open and close the claw
        if (gamepad1.right_bumper)
            clawOffset += CLAW_SPEED;
        else if (gamepad1.left_bumper)
            clawOffset -= CLAW_SPEED;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
        robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);
*/
        // Use the x button to shoot.
        // Use the b button to intake.
        if (gamepad2.b) {
            robot.leftSpin.setPower(.5);
            robot.rightSpin.setPower(.5);
        }
        else if (gamepad2.x) {
            robot.rightSpin.setPower(-1);
            robot.leftSpin.setPower(-1);
        }
        else {
            robot.leftSpin.setPower(0.0);
            robot.rightSpin.setPower(0.0);
        }

        // Use d-pad up to push the hopper forward
        // Use d-pad down to push the hopper back
        if (gamepad2.dpad_up) {
            robot.spinHopper.setPower(.75);
        }
        else if (gamepad2.dpad_down) {
            robot.spinHopper.setPower(-.75);
        }
        else {
            robot.spinHopper.setPower(0.0);
        }

        // Use the right trigger to raise the drawbridge.
        // Use the left trigger to lower the drawbridge.
        if (gamepad2.right_trigger > 0.2 && gamepad2.left_trigger < 0.2) {
            rTrigger = -gamepad2.right_trigger;
            robot.drawBridge.setPower(rTrigger);
        }
        else if (gamepad2.left_trigger > 0.2 && gamepad2.right_trigger < 0.2) {
            lTrigger = gamepad2.left_trigger;
            robot.drawBridge.setPower(lTrigger*.35);
        }
        else {
            robot.drawBridge.setPower(0.0);
        }


        // Send telemetry message to signify robot running;
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}





