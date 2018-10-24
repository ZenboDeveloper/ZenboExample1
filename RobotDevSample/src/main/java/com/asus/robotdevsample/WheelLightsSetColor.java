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

public class WheelLightsSetColor extends RobotActivity {
    private EditText EditText_active;
    private EditText EditText_color;

    private Spinner mSpinner;
    private ArrayAdapter<String> SpinnerList;
    private String[] wheelLightsID = {"SYNC_BOTH", "ASYNC_LEFT", "ASYNC_RIGHT"};


    private Button SetColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wheellights_setcolor);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.wheelLights_setColor_full));

        //IME hide
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Spinner of wheel lights ID
        mSpinner = (Spinner)findViewById(R.id.wheellights_spinner_lights_id);
        SpinnerList = new ArrayAdapter<String>(WheelLightsSetColor.this, android.R.layout.simple_spinner_item, wheelLightsID);
        mSpinner.setAdapter(SpinnerList);

        //Edittext
        EditText_active = (EditText) findViewById(R.id.setcolor_edittext_active);
        EditText_active.setText("0xff");

        EditText_color= (EditText) findViewById(R.id.setcolor_edittext_bright);
        EditText_color.setText("0xFF0000");


        //Button
        SetColor = (Button) findViewById(R.id.wheellight_button_setcolor);

        SetColor.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String active = EditText_active.getText().toString();
                int color = Integer.decode(EditText_color.getText().toString());
                String SpinnerText = mSpinner.getSelectedItem().toString();

                if(SpinnerText.equals("SYNC_BOTH")){
                    robotAPI.wheelLights.setColor(WheelLights.Lights.SYNC_BOTH, 0xff, color);
                }
                else if(SpinnerText.equals("ASYNC_LEFT")){
                    robotAPI.wheelLights.setColor(WheelLights.Lights.ASYNC_LEFT, 0xff, color);
                }
                else if(SpinnerText.equals("ASYNC_RIGHT")){
                    robotAPI.wheelLights.setColor(WheelLights.Lights.ASYNC_RIGHT, 0xff, color);
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



    public WheelLightsSetColor() {
        super(robotCallback, robotListenCallback);
    }
}
