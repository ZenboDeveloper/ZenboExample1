package com.asus.robotdevsample;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.asus.robotframework.API.MotionControl;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;


public class MotionMoveBodyHead extends RobotActivity {

    private EditText mEditTextMoveBodyX;
    private EditText mEditTextMoveBodyY;
    private EditText mEditTextMoveBodyTheta;
    private EditText mEditTextMoveBodyLevelX;
    private EditText mEditTextMoveBodyLevelY;
    private EditText mEditTextMoveBodyLevelTheta;
    private EditText mEditText_head_yaw;
    private EditText mEditText_head_pitch;
    private Spinner mSpinnerMoveBodyLevel;
    private Spinner mSpinnerMoveHeadLevel;

    private Button mBtn_MoveBody;
    private Button mBtn_moveHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_motion_movebodyhead);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.toolbar_title_subclass_motion_title));

        //hide ime
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //init spinner
        initBodySpinner();
        initHeadSpinner();

        //----moveBody----
        mEditTextMoveBodyX = (EditText) findViewById(R.id.motion_moveBody_X);
        mEditTextMoveBodyY = (EditText) findViewById(R.id.motion_moveBody_Y);
        mEditTextMoveBodyTheta = (EditText) findViewById(R.id.motion_moveTheta);
        mEditTextMoveBodyX.setText("1.2");      //1.2m
        mEditTextMoveBodyY.setText("0.8");      //0.8m
        mEditTextMoveBodyTheta.setText("1.57"); //about 90 degree

        mBtn_MoveBody = (Button) findViewById(R.id.moveButton);
        mBtn_MoveBody.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = Float.valueOf(mEditTextMoveBodyX.getText().toString());
                float y = Float.valueOf(mEditTextMoveBodyY.getText().toString());
                float theta = Float.valueOf(mEditTextMoveBodyTheta.getText().toString());
                robotAPI.motion.moveBody(x, y, theta);
            }
        });



        //----moveBody by level----
        mEditTextMoveBodyLevelX = (EditText) findViewById(R.id.editText_motion_moveBody_level_x);
        mEditTextMoveBodyLevelY = (EditText) findViewById(R.id.editText_motion_moveBody_level_Y);
        mEditTextMoveBodyLevelTheta = (EditText) findViewById(R.id.editText_motion_moveBody_level_theta);
        mSpinnerMoveBodyLevel = (Spinner) findViewById(R.id.spinner_motion_moveSpeedLevel);
        mEditTextMoveBodyLevelX.setText("1.2");      //1.2m
        mEditTextMoveBodyLevelY.setText("0.8");      //0.8m
        mEditTextMoveBodyLevelTheta.setText("1.57"); //about 90 degree

        Button mBtn_moveBodyLevel = (Button) findViewById(R.id.btn_motion_moveBody_speedlevel);
        mBtn_moveBodyLevel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                float x = Float.valueOf(mEditTextMoveBodyLevelX.getText().toString());
                float y = Float.valueOf(mEditTextMoveBodyLevelY.getText().toString());
                float theta = Float.valueOf(mEditTextMoveBodyLevelTheta.getText().toString());
                int selectedLevel = mSpinnerMoveBodyLevel.getSelectedItemPosition() + 1;


                MotionControl.SpeedLevel.Body level = MotionControl.SpeedLevel.Body.getBody(selectedLevel);


                robotAPI.motion.moveBody(x, y, theta, level);

            }
        });



        //----moveBody by level----
        mEditText_head_yaw = (EditText) findViewById(R.id.editText_motion_headYaw);
        mEditText_head_pitch = (EditText) findViewById(R.id.editText_motion_headPitch);
        mSpinnerMoveHeadLevel = (Spinner) findViewById(R.id.spinner_motion_moveHead_speedlevel);
        mEditText_head_yaw.setText("-0.52");      //about -30 degree
        mEditText_head_pitch.setText("0.26");      //about 15 degree

        mBtn_moveHead = (Button) findViewById(R.id.btn_moveHead);
        mBtn_moveHead.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tmp = mEditText_head_pitch.getText().toString();
                float pitch = TextUtils.isEmpty(tmp) ? 0 : (float) Math.toRadians(Float.valueOf(tmp));

                tmp = mEditText_head_yaw.getText().toString();
                float yaw = TextUtils.isEmpty(tmp) ? 0 : (float) Math.toRadians(Float.valueOf(tmp));

                int selectedLevel = mSpinnerMoveHeadLevel.getSelectedItemPosition() + 1;

                MotionControl.SpeedLevel.Head level = MotionControl.SpeedLevel.Head.getHead(selectedLevel);

                robotAPI.motion.moveHead(yaw, pitch, level);

            }
        });


        Button stop = (Button) findViewById(R.id.stopButton);
        stop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                robotAPI.motion.stopMoving();
            }
        });
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


    private void initBodySpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_motion_moveSpeedLevel);
        String[] level = {"L1", "L2", "L3", "L4", "L5", "L6", "L7"};

        ArrayAdapter<String> speedList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, level);

        spinner.setAdapter(speedList);
    }

    private void initHeadSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_motion_moveHead_speedlevel);
        String[] level = {"L1", "L2", "L3"};

        ArrayAdapter<String> speedList = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, level);

        spinner.setAdapter(speedList);
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


    public MotionMoveBodyHead() {
        super(robotCallback, robotListenCallback);
    }
}
