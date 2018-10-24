package com.asus.robotdevsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.WheelLights;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

import java.util.Random;

public class WheelLightsActivity extends RobotActivity {

    private ListView listView;
    private String[] listViewitems;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_menu);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.toolbar_title_subclass_wheel_title));

        listViewitems = getResources().getStringArray(R.array.subclasses_wheel);
        listView = (ListView)findViewById(R.id.list_view);
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listViewitems);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent subExample;
                switch(position) {
                    case 0:
                        //.setBrightness
                        subExample = new Intent(WheelLightsActivity.this, WheelLightsSetBrightness.class);
                        startActivity(subExample);
                        break;
                    case 1:
                        //.setColor
                        subExample = new Intent(WheelLightsActivity.this, WheelLightsSetColor.class);
                        startActivity(subExample);
                        break;
                    case 2:
                        //.startBlinking
                        robotAPI.wheelLights.turnOff(WheelLights.Lights.SYNC_BOTH, 0xff);
                        robotAPI.wheelLights.setColor(WheelLights.Lights.SYNC_BOTH, 0xff, 0x007F7F);
                        robotAPI.wheelLights.setBrightness(WheelLights.Lights.SYNC_BOTH, 0xff, 10);
                        robotAPI.wheelLights.startBlinking(WheelLights.Lights.SYNC_BOTH, 0xff, 30, 10, 5);
                        break;

                    case 3:
                        //.startBreathing
                        robotAPI.wheelLights.turnOff(WheelLights.Lights.SYNC_BOTH, 0xff);
                        robotAPI.wheelLights.setColor(WheelLights.Lights.SYNC_BOTH, 0xff, 0x00D031);
                        robotAPI.wheelLights.setBrightness(WheelLights.Lights.SYNC_BOTH, 0xff, 10);
                        robotAPI.wheelLights.startBreathing(WheelLights.Lights.SYNC_BOTH, 0xff, 20, 10, 0);
                        break;

                    case 4:
                        //.startCharging
                        robotAPI.wheelLights.turnOff(WheelLights.Lights.SYNC_BOTH, 0xff);
                        robotAPI.wheelLights.setColor(WheelLights.Lights.SYNC_BOTH, 0xff, 0xFF9000);
                        robotAPI.wheelLights.setBrightness(WheelLights.Lights.SYNC_BOTH, 0xff, 10);
                        robotAPI.wheelLights.startCharging(WheelLights.Lights.SYNC_BOTH, 0, 1, WheelLights.Direction.DIRECTION_FORWARD, 20);
                        break;
                    case 5:
                        //.startMarquee
                        robotAPI.wheelLights.turnOff(WheelLights.Lights.SYNC_BOTH, 0xff);
                        robotAPI.wheelLights.setBrightness(WheelLights.Lights.SYNC_BOTH, 0xff, 20);
                        robotAPI.wheelLights.startMarquee(WheelLights.Lights.SYNC_BOTH, WheelLights.Direction.DIRECTION_FORWARD, 40, 20, 3);
                        break;

                    case 6:
                        //.turnOff
                        subExample = new Intent(WheelLightsActivity.this, WheelLightsTurnOff.class);
                        startActivity(subExample);
                        break;
                }


            }
        });

    }



    int color = 0;

    private int getRandomColor() {
        Random random = new Random();

        return random.nextInt() % 0xA0 + random.nextInt() % 0xA0 * 0x100 +
                random.nextInt() % 0xA0 * 0x10000;
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



    public WheelLightsActivity() {
        super(robotCallback, robotListenCallback);
    }
}
