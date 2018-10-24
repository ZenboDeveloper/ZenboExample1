package com.asus.robotdevsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.asus.robotframework.API.MotionControl.Direction;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;


public class MotionRemoteControlBodyHead extends RobotActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_motion_remotecontrolbodyhead);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.toolbar_title_subclass_motion_title));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    public void buttonBodyForwardClicked(View view) {
        robotAPI.motion.remoteControlBody(Direction.Body.FORWARD);
    }

    public void buttonBodyBackwardClicked(View view) {
        robotAPI.motion.remoteControlBody(Direction.Body.BACKWARD);
    }

    public void buttonBodyRightClicked(View view) {
        robotAPI.motion.remoteControlBody(Direction.Body.TURN_RIGHT);
    }

    public void buttonBodyLeftClicked(View view) {
        robotAPI.motion.remoteControlBody(Direction.Body.TURN_LEFT);
    }

    public void buttonBodyStopClicked(View view) {
        robotAPI.motion.remoteControlBody(Direction.Body.STOP);
    }

    public void neckHeadUpClicked(View view) {
        robotAPI.motion.remoteControlHead(Direction.Head.UP);
    }

    public void neckHeadDownClicked(View view) {
        robotAPI.motion.remoteControlHead(Direction.Head.DOWN);
    }

    public void neckHeadRightClicked(View view) {
        robotAPI.motion.remoteControlHead(Direction.Head.RIGHT);
    }

    public void neckHeadLeftClicked(View view) {
        robotAPI.motion.remoteControlHead(Direction.Head.LEFT);
    }

    public void neckHeadStopClicked(View view) {
        robotAPI.motion.remoteControlHead(Direction.Head.STOP);
    }








    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);

            Log.d("RobotDevSample", "onResult:"
                    + RobotCommand.getRobotCommand(cmd).name()
                    + ", serial:" + serial + ", err_code:" + err_code
                    + ", result:" + result.getString("RESULT"));
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
        }

        @Override
        public void initComplete() {
            super.initComplete();

        }
    };

    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {

        }

        @Override
        public void onResult(JSONObject jsonObject) {

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };


    public MotionRemoteControlBodyHead() {
        super(robotCallback, robotListenCallback);
    }
}
