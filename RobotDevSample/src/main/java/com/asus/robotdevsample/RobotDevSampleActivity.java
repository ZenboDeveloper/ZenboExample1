package com.asus.robotdevsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;

import org.json.JSONObject;

public class RobotDevSampleActivity extends com.robot.asus.robotactivity.RobotActivity {
    private ListView listView;
    private String[] listViewitems;
    private ArrayAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_menu);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.toolbar_title_mainclass_robotapi_title));

        listView = (ListView)findViewById(R.id.list_view);
        listViewitems = getResources().getStringArray(R.array.subclasses_type);
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listViewitems);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent subExample;
                switch(position) {
                    case 0:
                        //.motion
                        subExample = new Intent(RobotDevSampleActivity.this, MotionActivity.class);
                        startActivity(subExample);
                        break;
                    case 1:
                        //.robot
                        subExample = new Intent(RobotDevSampleActivity.this, RobotActivity.class);
                        startActivity(subExample);
                        break;
                    case 2:
                        //.utility
                        subExample = new Intent(RobotDevSampleActivity.this, UtilityActivity.class);
                        startActivity(subExample);
                        break;
                    case 3:
                        //.vision
                        subExample = new Intent(RobotDevSampleActivity.this, VisionActivity.class);
                        startActivity(subExample);
                        break;
                    case 4:
                        //.wheellight
                        subExample = new Intent(RobotDevSampleActivity.this, WheelLightsActivity.class);
                        startActivity(subExample);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //hide expression
        robotAPI.robot.setExpression(RobotFace.HIDEFACE);
    }

    public RobotDevSampleActivity() {
        super(robotCallback, robotListenCallback);
    }

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
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
}
