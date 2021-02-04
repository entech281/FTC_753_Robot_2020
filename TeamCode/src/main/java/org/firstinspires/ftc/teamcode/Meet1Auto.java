/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Meet1Auto", group="Meet1")
public class Meet1Auto extends LinearOpMode {

    /* Declare OpMode members. */
    DevReg         robot   = new DevReg();   // Call the DevReg
    private ElapsedTime     runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "MEET 1 GOOO");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Lower Arms to Grab Wobble Goal
        robot.drawBridge.setPower(.45);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.drawBridge.setPower(0);

        // Step 2:  Drive Forward until Wobble goal is on middle square
        robot.leftDrive.setPower(-1);
        robot.rightDrive.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.75)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        // Step 3:  Drive Backwards until behind firing line
        robot.leftDrive.setPower(0.85);
        robot.rightDrive.setPower(0.65);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .75)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        // Step 4:  Turn Slightly
        robot.leftDrive.setPower(1.0);
        robot.rightDrive.setPower(0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .40)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        // Step 5:  Raise the Drawbridge
        robot.drawBridge.setPower(-.45);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.45)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.drawBridge.setPower(0);

        //Step 6: Spin the wheels and push hopper
        robot.leftSpin.setPower(-1);
        robot.rightSpin.setPower(-1);
        sleep(1000);
        robot.spinHopper.setPower(.75);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4.0)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftSpin.setPower(0);
        robot.rightSpin.setPower(0);
        robot.spinHopper.setPower(0);


        // Step 7:  Drive Forward until robot is over line.
        robot.leftDrive.setPower(-1);
        robot.rightDrive.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }
}




