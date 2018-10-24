package com.asus.robotdevsample;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.WheelLights;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

public class WheelLightsTurnOff extends RobotActivity {

    private EditText EditText_active;


    private Spinner mSpinner;
    private ArrayAdapter<String> SpinnerList;
    private String[] wheelLightsID = {"SYNC_BOTH", "ASYNC_LEFT", "ASYNC_RIGHT"};

    private Button TurnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wheellights_turnoff);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.wheelLights_turnoff_full));

        //ime hide
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //spinner of wheel lights ID
        mSpinner = (Spinner)findViewById(R.id.wheellights_spinner_lights_id);
        SpinnerList = new ArrayAdapter<String>(WheelLightsTurnOff.this, android.R.layout.simple_spinner_item, wheelLightsID);
        mSpinner.setAdapter(SpinnerList);

        //edittext
        EditText_active = (EditText) findViewById(R.id.setbright_edittext_active);
        EditText_active.setText("0xff");


        TurnOff = (Button) findViewById(R.id.wheellights_button_turnoff);

        TurnOff.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String active = EditText_active.getText().toString();
                String SpinnerText = mSpinner.getSelectedItem().toString();

                if(SpinnerText.equals("SYNC_BOTH")){
                    robotAPI.wheelLights.turnOff(WheelLights.Lights.SYNC_BOTH, 0xff);

                }
                else if(SpinnerText.equals("ASYNC_LEFT")){
                    robotAPI.wheelLights.turnOff(WheelLights.Lights.ASYNC_LEFT, 0xff);

                }
                else if(SpinnerText.equals("ASYNC_RIGHT")){
                    robotAPI.wheelLights.turnOff(WheelLights.Lights.ASYNC_RIGHT, 0xff);

                }

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



    public WheelLightsTurnOff() {
        super(robotCallback, robotListenCallback);
    }


}
