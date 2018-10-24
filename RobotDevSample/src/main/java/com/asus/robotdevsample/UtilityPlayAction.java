package com.asus.robotdevsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

public class UtilityPlayAction extends RobotActivity {

    private Button btn_start;
    private Button btn_stop;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_utility_playaction);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.utility_playAction_full));

        //hide ime
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        btn_start = (Button) findViewById(R.id.btn_actionsetstart);
        btn_stop = (Button) findViewById(R.id.btn_actionsetstop);
        mEditText = (EditText) findViewById(R.id.actionset_edittext);
        mEditText.setText("22");      //#22 action

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(mEditText.getText().length() > 0) {
                    int iCuttentNumberPickerValue = Integer.parseInt(mEditText.getText().toString());
                    robotAPI.utility.playAction(iCuttentNumberPickerValue);
                    Toast.makeText(arg0.getContext(), "Start Action #ID = " + iCuttentNumberPickerValue, Toast.LENGTH_SHORT).show();
                }

            }
        });


        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                robotAPI.cancelCommand(RobotCommand.MOTION_PLAY_ACTION.getValue());
            }
        });


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


    public UtilityPlayAction() {
        super(robotCallback, robotListenCallback);
    }
}
