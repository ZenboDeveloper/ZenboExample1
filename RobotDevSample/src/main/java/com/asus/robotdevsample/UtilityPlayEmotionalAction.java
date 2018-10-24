package com.asus.robotdevsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.asus.robotframework.API.RobotUtil;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UtilityPlayEmotionalAction extends RobotActivity {
    private Spinner mSpinner;
    private ArrayAdapter<String> SpinnerList;
    private String[] EmotionalActionCandidateArray = {"Example 1", "Example 2"};

    private Button btn_start;

    private static RobotAPI mRobotAPI;
    private static int iCurrentCommandSerial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_utility_playemotionalaction);

        mRobotAPI = robotAPI;

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.utility_playEmotionalAction_full));

        //hide ime
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //spinner of expression selections
        mSpinner = (Spinner)findViewById(R.id.spinner_emotional_action_example_candidate);
        SpinnerList = new ArrayAdapter<String>(UtilityPlayEmotionalAction.this, android.R.layout.simple_spinner_item, EmotionalActionCandidateArray);
        mSpinner.setAdapter(SpinnerList);

        btn_start = (Button) findViewById(R.id.btn_setEmotionalAction);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                String SpinnerText = mSpinner.getSelectedItem().toString();

                if(SpinnerText.equals("Example 1")){
                    List<RobotUtil.faceItem> faceItemList = new ArrayList<>();

                    faceItemList.add(new RobotUtil.faceItem(RobotFace.DEFAULT, 10));
                    faceItemList.add(new RobotUtil.faceItem(RobotFace.HAPPY, 10));
                    faceItemList.add(new RobotUtil.faceItem(RobotFace.EXPECTING, 10));
                    faceItemList.add(new RobotUtil.faceItem(RobotFace.SHOCKED, 10));
                    faceItemList.add(new RobotUtil.faceItem(RobotFace.LAZY, 10));

                    iCurrentCommandSerial = robotAPI.utility.playEmotionalAction(faceItemList, 5);
                }
                else if(SpinnerText.equals("Example 2")){
                    List<RobotUtil.faceItem> faceItemList = new ArrayList<>();

                    faceItemList.add(new RobotUtil.faceItem(RobotFace.TIRED, 5));
                    faceItemList.add(new RobotUtil.faceItem(RobotFace.INNOCENT, 10));
                    faceItemList.add(new RobotUtil.faceItem(RobotFace.WORRIED, 15));

                    iCurrentCommandSerial = robotAPI.utility.playEmotionalAction(faceItemList, 22);
                }

                Log.d("RobotDevSample", "start playEmotionalAction with command serial = " + iCurrentCommandSerial );

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

            if (serial == iCurrentCommandSerial && state != RobotCmdState.ACTIVE){

                Log.d("RobotDevSample", "command: "+ iCurrentCommandSerial + " SUCCEED");
                mRobotAPI.robot.setExpression(RobotFace.HIDEFACE);
            }

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


    public UtilityPlayEmotionalAction() {
        super(robotCallback, robotListenCallback);
    }
}
