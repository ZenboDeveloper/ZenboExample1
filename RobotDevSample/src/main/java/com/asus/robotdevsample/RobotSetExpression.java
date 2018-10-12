package com.asus.robotdevsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotCommand;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.asus.robotframework.API.results.RoomInfo;
import com.robot.asus.robotactivity.RobotActivity;


import org.json.JSONObject;

public class RobotSetExpression extends RobotActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private Spinner mSpinner;
    private ArrayAdapter<String> SpinnerList;
    private String[] FaceCandidateArray = {"INTERESTED", "DOUBTING", "PROUD", "DEFAULT", "HAPPY", "EXPECTING", "SHOCKED", "QUESTIONING", "IMPATIENT", "ACTIVE",
                                            "PLEASED", "HELPLESS", "SERIOUS", "WORRIED", "PRETENDING", "LAZY", "AWARE_RIGHT", "TIRED", "SHY", "INNOCENT",
                                            "SINGING", "AWARE_LEFT", "DEFAULT_STILL", "HIDEFACE"};

    private Button btn_start;
    private Button btn_doGoTo;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_robot_setexpression);

        //title
        TextView mTextViewTitle = (TextView)findViewById(R.id.textview_title);
        mTextViewTitle.setText(getString(R.string.robot_setExpression_full));


        //hide ime
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //spinner of expression selections
        mSpinner = (Spinner)findViewById(R.id.spinner_face_candidate);
        SpinnerList = new ArrayAdapter<String>(RobotSetExpression.this, android.R.layout.simple_spinner_item, FaceCandidateArray);
        mSpinner.setAdapter(SpinnerList);


        mCountDownTimer  = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                robotAPI.robot.setExpression(RobotFace.HIDEFACE);
            }

        };

        requestReadContactsPermission();

        btn_doGoTo = (Button) findViewById(R.id.btn_setGoTo);
        btn_doGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast toast;
                int intCmdID;
                try{
                    java.util.ArrayList<RoomInfo> dataRooms = robotAPI.contacts.room.getAllRoomInfo();
                    if(dataRooms.size()>0){
                        for(int i=0;i<dataRooms.size();i++){
                            toast = Toast.makeText(RobotSetExpression.this,
                                    "地點數量"+dataRooms.size(), Toast.LENGTH_LONG);
                            //顯示Toast
                            toast.show();

                            intCmdID=robotAPI.motion.goTo("房間");
                            toast = Toast.makeText(RobotSetExpression.this,
                                    "Goto button " + intCmdID, Toast.LENGTH_LONG);
                            //顯示Toast
                            toast.show();
                        }
                    }else{
                        toast = Toast.makeText(RobotSetExpression.this,
                                "尚未建置地圖，請先建置地圖", Toast.LENGTH_LONG);
                        //顯示Toast
                        toast.show();
                    }

                }catch (Exception e){

                    toast = Toast.makeText(RobotSetExpression.this,
                            "Error" + e.toString(), Toast.LENGTH_LONG);
                    //顯示Toast
                    toast.show();
                }
            }
        });

        btn_start = (Button) findViewById(R.id.btn_setExpression);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mCountDownTimer.cancel();


                String SpinnerText = mSpinner.getSelectedItem().toString();

                if(SpinnerText.equals("INTERESTED")){
                    robotAPI.robot.setExpression(RobotFace.INTERESTED);
                }
                else if(SpinnerText.equals("DOUBTING")){
                    robotAPI.robot.setExpression(RobotFace.DOUBTING);
                }
                else if(SpinnerText.equals("PROUD")){
                    robotAPI.robot.setExpression(RobotFace.PROUD);
                }
                else if(SpinnerText.equals("DEFAULT")){
                    robotAPI.robot.setExpression(RobotFace.DEFAULT);
                }
                else if(SpinnerText.equals("HAPPY")){
                    robotAPI.robot.setExpression(RobotFace.HAPPY);
                }
                else if(SpinnerText.equals("EXPECTING")){
                    robotAPI.robot.setExpression(RobotFace.EXPECTING);
                }
                else if(SpinnerText.equals("SHOCKED")){
                    robotAPI.robot.setExpression(RobotFace.SHOCKED);
                }
                else if(SpinnerText.equals("QUESTIONING")){
                    robotAPI.robot.setExpression(RobotFace.QUESTIONING);
                }
                else if(SpinnerText.equals("IMPATIENT")){
                    robotAPI.robot.setExpression(RobotFace.IMPATIENT);
                }
                else if(SpinnerText.equals("ACTIVE")){
                    robotAPI.robot.setExpression(RobotFace.ACTIVE);
                }
                else if(SpinnerText.equals("PLEASED")){
                    robotAPI.robot.setExpression(RobotFace.PLEASED);
                }
                else if(SpinnerText.equals("HELPLESS")){
                    robotAPI.robot.setExpression(RobotFace.HELPLESS);
                }
                else if(SpinnerText.equals("SERIOUS")){
                    robotAPI.robot.setExpression(RobotFace.SERIOUS);
                }
                else if(SpinnerText.equals("WORRIED")){
                    robotAPI.robot.setExpression(RobotFace.WORRIED);
                }
                else if(SpinnerText.equals("PRETENDING")){
                    robotAPI.robot.setExpression(RobotFace.PRETENDING);
                }
                else if(SpinnerText.equals("LAZY")){
                    robotAPI.robot.setExpression(RobotFace.LAZY);
                }
                else if(SpinnerText.equals("AWARE_RIGHT")){
                    robotAPI.robot.setExpression(RobotFace.AWARE_RIGHT);
                }
                else if(SpinnerText.equals("TIRED")){
                    robotAPI.robot.setExpression(RobotFace.TIRED);
                }
                else if(SpinnerText.equals("SHY")){
                    robotAPI.robot.setExpression(RobotFace.SHY);
                }
                else if(SpinnerText.equals("INNOCENT")){
                    robotAPI.robot.setExpression(RobotFace.INNOCENT);
                }
                else if(SpinnerText.equals("SINGING")){
                    robotAPI.robot.setExpression(RobotFace.SINGING);
                }
                else if(SpinnerText.equals("AWARE_LEFT")){
                    robotAPI.robot.setExpression(RobotFace.AWARE_LEFT);
                }
                else if(SpinnerText.equals("DEFAULT_STILL")){
                    robotAPI.robot.setExpression(RobotFace.DEFAULT_STILL);
                }
                else if(SpinnerText.equals("HIDEFACE")){
                    robotAPI.robot.setExpression(RobotFace.HIDEFACE);
                }



                //For demo, count down 5 sec to hide face
                mCountDownTimer.start();

            }
        });




    }

    @Override
    protected void onPause() {
        super.onPause();

        mCountDownTimer.cancel();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {

        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
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


    public RobotSetExpression() {
        super(robotCallback, robotListenCallback);
    }

    private void requestReadContactsPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            // new ConfirmationDialog().show(getChildFragmentManager(), FRAGMENT_DIALOG);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                /*
                ErrorDialog.newInstance(getString(R.string.request_permission))
                        .show(getChildFragmentManager(), FRAGMENT_DIALOG);
                                */
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
