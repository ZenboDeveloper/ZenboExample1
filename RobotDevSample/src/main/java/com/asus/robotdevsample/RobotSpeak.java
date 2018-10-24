package com.asus.robotdevsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

public class RobotSpeak extends RobotActivity {
    private Button btn_start_speak, btn_stop_speak;
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_robot_speak);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.toolbar_title_subclass_robot_title));

        //ime hide
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btn_start_speak = (Button)findViewById(R.id.btn_speak_start);
        btn_stop_speak = (Button)findViewById(R.id.btn_speak_stop);

        mEdit = (EditText)findViewById(R.id.speak_edittext);

        btn_start_speak.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                robotAPI.robot.speak(mEdit.getText().toString());
            }
        });

        btn_stop_speak.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                robotAPI.robot.stopSpeak();
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
            Log.d("RobotDevSample", "speak Complete");
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

    public RobotSpeak() {
        super(robotCallback, robotListenCallback);
    }

}
