package com.asus.robotsensorsample;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotFace;
import com.asus.robotframework.API.Utility;

public class MainActivity extends Activity {
    public static final int TYPE_CAPACITY_TOUCH = Utility.SensorType.CAPACITY_TOUCH;
    public static final int TYPE_DROP_LASER = Utility.SensorType.DROP_LASER;
    public static final int TYPE_SONAR = Utility.SensorType.SONAR;
    public static final int TYPE_ODOMETRY = Utility.SensorType.ODOMETRY;
    public static final int TYPE_NECK_ENCODER = Utility.SensorType.NECK_ENCODER;
    public static final int TYPE_WHEEL_ENCODER = Utility.SensorType.WHEEL_ENCODER;
    public static final int TYPE_ROBOT_BODY_ACCELEROMETER_RAW = Utility.SensorType.ROBOT_BODY_ACCELEROMETER_RAW;
    public static final int TYPE_ROBOT_BODY_GYROSCOPE_RAW = Utility.SensorType.ROBOT_BODY_GYROSCOPE_RAW;
    public static final int TYPE_ROBOT_MOTOR = Utility.SensorType.ROBOT_MOTOR;
    public static final int TYPE_ROBOT_DOCK_IR = Utility.SensorType.ROBOT_DOCK_IR;
    public static final int TYPE_ROBOT_NECK_TRAJECTORY = Utility.SensorType.ROBOT_NECK_TRAJECTORY;
    public static final int TYPE_ROBOT_WHEEL_TRAJECTORY = Utility.SensorType.ROBOT_WHEEL_TRAJECTORY;

    // sensor manager
    private SensorManager mSensorManager;

    // sensor
    private Sensor mSensorCapacityTouch;
    private Sensor mSensorDropLaser;
    private Sensor mSensorSonar;
    private Sensor mSensorOdometry;
    private Sensor mSensorNeckEncoder;
    private Sensor mSensorWheelEncoder;
    private Sensor mSensorRobotBodyAccelerometerRaw;
    private Sensor mSensorRobotBodyGyroscopeRaw;
    private Sensor mSensorRobotMotor;
    private Sensor mSensorRobotDockIr;
    private Sensor mSensorRobotNeckTrajectory;
    private Sensor mSensorRobotWheelTrajectory;


    // ui
    //CAPACITY_TOUCH
    private TextView mTextView_capacity_touch_value0;
    private TextView mTextView_capacity_touch_value1;

    //DROP_LASER
    private TextView mTextView_drop_laser_value0;
    private TextView mTextView_drop_laser_value1;
    private TextView mTextView_drop_laser_value2;
    private TextView mTextView_drop_laser_value3;
    private TextView mTextView_drop_laser_value4;
    private TextView mTextView_drop_laser_value5;
    private TextView mTextView_drop_laser_value6;
    private TextView mTextView_drop_laser_value7;
    private TextView mTextView_drop_laser_value8;
    private TextView mTextView_drop_laser_value9;

    //SONAR
    private TextView mTextView_sonar_value0;
    private TextView mTextView_sonar_value1;
    private TextView mTextView_sonar_value2;
    private TextView mTextView_sonar_value3;
    private TextView mTextView_sonar_value4;
    private TextView mTextView_sonar_value5;

    //ODOMETRY
    private TextView mTextView_odometry_value0;
    private TextView mTextView_odometry_value1;
    private TextView mTextView_odometry_value2;

    //NECK_ENCODER
    private TextView mTextView_neck_encoder_value0;
    private TextView mTextView_neck_encoder_value1;
    private TextView mTextView_neck_encoder_value2;

    //WHEEL_ENCODER
    private TextView mTextView_wheel_encoder_value0;
    private TextView mTextView_wheel_encoder_value1;

    //ROBOT_BODY_ACCELEROMETER_RAW
    private TextView mTextView_robot_body_accelerometer_raw_value0;
    private TextView mTextView_robot_body_accelerometer_raw_value1;
    private TextView mTextView_robot_body_accelerometer_raw_value2;

    //ROBOT_BODY_GYROSCOPE_RAW
    private TextView mTextView_robot_body_gyroscope_raw_value0;
    private TextView mTextView_robot_body_gyroscope_raw_value1;
    private TextView mTextView_robot_body_gyroscope_raw_value2;

    //ROBOT_MOTOR
    private TextView mTextView_robot_motor_value0;
    private TextView mTextView_robot_motor_value1;
    private TextView mTextView_robot_motor_value2;
    private TextView mTextView_robot_motor_value3;
    private TextView mTextView_robot_motor_value4;
    private TextView mTextView_robot_motor_value5;
    private TextView mTextView_robot_motor_value6;
    private TextView mTextView_robot_motor_value7;

    //ROBOT_DOCK_IR
    private TextView mTextView_robot_dock_ir_value0;
    private TextView mTextView_robot_dock_ir_value1;
    private TextView mTextView_robot_dock_ir_value2;

    //ROBOT_NECK_TRAJECTORY
    private TextView mTextView_robot_neck_trajectory_value0;
    private TextView mTextView_robot_neck_trajectory_value1;

    //ROBOT_WHEEL_TRAJECTORY
    private TextView mTextView_robot_wheel_trajectory_value0;
    private TextView mTextView_robot_wheel_trajectory_value1;


    // robot api
    public RobotAPI robotAPI;
    RobotCallback robotCallback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // robot api
        this.robotAPI = new RobotAPI(getApplicationContext(), robotCallback);

        // ui
        //CAPACITY_TOUCH
        mTextView_capacity_touch_value0 = (TextView)findViewById(R.id.id_sensor_type_capacity_touch_value0_value);
        mTextView_capacity_touch_value1 = (TextView)findViewById(R.id.id_sensor_type_capacity_touch_value1_value);


        // DROP_LASER
        mTextView_drop_laser_value0 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value0_value);
        mTextView_drop_laser_value1 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value1_value);
        mTextView_drop_laser_value2 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value2_value);
        mTextView_drop_laser_value3 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value3_value);
        mTextView_drop_laser_value4 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value4_value);
        mTextView_drop_laser_value5 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value5_value);
        mTextView_drop_laser_value6 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value6_value);
        mTextView_drop_laser_value7 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value7_value);
        mTextView_drop_laser_value8 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value8_value);
        mTextView_drop_laser_value9 = (TextView)findViewById(R.id.id_sensor_type_drop_laser_value9_value);

        //SONAR
        mTextView_sonar_value0 = (TextView)findViewById(R.id.id_sensor_type_sonar_value0_value);
        mTextView_sonar_value1 = (TextView)findViewById(R.id.id_sensor_type_sonar_value1_value);
        mTextView_sonar_value2 = (TextView)findViewById(R.id.id_sensor_type_sonar_value2_value);
        mTextView_sonar_value3 = (TextView)findViewById(R.id.id_sensor_type_sonar_value3_value);
        mTextView_sonar_value4 = (TextView)findViewById(R.id.id_sensor_type_sonar_value4_value);
        mTextView_sonar_value5 = (TextView)findViewById(R.id.id_sensor_type_sonar_value5_value);

        //ODOMETRY
        mTextView_odometry_value0 = (TextView)findViewById(R.id.id_sensor_type_odometry_value0_value);
        mTextView_odometry_value1 = (TextView)findViewById(R.id.id_sensor_type_odometry_value1_value);
        mTextView_odometry_value2 = (TextView)findViewById(R.id.id_sensor_type_odometry_value2_value);

        //NECK_ENCODER
        mTextView_neck_encoder_value0 = (TextView)findViewById(R.id.id_sensor_type_neck_encoder_value0_value);
        mTextView_neck_encoder_value1 = (TextView)findViewById(R.id.id_sensor_type_neck_encoder_value1_value);
        mTextView_neck_encoder_value2 = (TextView)findViewById(R.id.id_sensor_type_neck_encoder_value2_value);

        //WHEEL_ENCODER
        mTextView_wheel_encoder_value0 = (TextView)findViewById(R.id.id_sensor_type_wheel_encoder_value0_value);
        mTextView_wheel_encoder_value1 = (TextView)findViewById(R.id.id_sensor_type_wheel_encoder_value1_value);

        //ROBOT_BODY_ACCELEROMETER_RAW
        mTextView_robot_body_accelerometer_raw_value0 = (TextView)findViewById(R.id.id_sensor_type_robot_body_accelerometer_raw_value0_value);
        mTextView_robot_body_accelerometer_raw_value1 = (TextView)findViewById(R.id.id_sensor_type_robot_body_accelerometer_raw_value1_value);
        mTextView_robot_body_accelerometer_raw_value2 = (TextView)findViewById(R.id.id_sensor_type_robot_body_accelerometer_raw_value2_value);

        //ROBOT_BODY_GYROSCOPE_RAW
        mTextView_robot_body_gyroscope_raw_value0 = (TextView)findViewById(R.id.id_sensor_type_robot_body_gyroscope_raw_value0_value);
        mTextView_robot_body_gyroscope_raw_value1 = (TextView)findViewById(R.id.id_sensor_type_robot_body_gyroscope_raw_value1_value);
        mTextView_robot_body_gyroscope_raw_value2 = (TextView)findViewById(R.id.id_sensor_type_robot_body_gyroscope_raw_value2_value);

        //ROBOT_MOTOR
        mTextView_robot_motor_value0 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value0_value);
        mTextView_robot_motor_value1 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value1_value);
        mTextView_robot_motor_value2 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value2_value);
        mTextView_robot_motor_value3 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value3_value);
        mTextView_robot_motor_value4 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value4_value);
        mTextView_robot_motor_value5 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value5_value);
        mTextView_robot_motor_value6 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value6_value);
        mTextView_robot_motor_value7 =  (TextView)findViewById(R.id.id_sensor_type_robot_motor_value7_value);

        //ROBOT_DOCK_IR
        mTextView_robot_dock_ir_value0 = (TextView)findViewById(R.id.id_sensor_type_robot_dock_ir_value0_value);
        mTextView_robot_dock_ir_value1 = (TextView)findViewById(R.id.id_sensor_type_robot_dock_ir_value1_value);
        mTextView_robot_dock_ir_value2 = (TextView)findViewById(R.id.id_sensor_type_robot_dock_ir_value2_value);

        //NECK_TRAJECTORY
        mTextView_robot_neck_trajectory_value0 = (TextView)findViewById(R.id.id_sensor_type_robot_neck_trajectory_value0_value);
        mTextView_robot_neck_trajectory_value1 = (TextView)findViewById(R.id.id_sensor_type_robot_neck_trajectory_value1_value);

        //ROBOT_WHEEL_TRAJECTORY
        mTextView_robot_wheel_trajectory_value0 = (TextView)findViewById(R.id.id_sensor_type_robot_wheel_trajectory_value0_value);
        mTextView_robot_wheel_trajectory_value1 = (TextView)findViewById(R.id.id_sensor_type_robot_wheel_trajectory_value1_value);



        // sensor manager
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        // sensors
        mSensorCapacityTouch = mSensorManager.getDefaultSensor(TYPE_CAPACITY_TOUCH);
        mSensorDropLaser = mSensorManager.getDefaultSensor(TYPE_DROP_LASER);
        mSensorSonar = mSensorManager.getDefaultSensor(TYPE_SONAR);
        mSensorOdometry = mSensorManager.getDefaultSensor(TYPE_ODOMETRY);
        mSensorNeckEncoder =  mSensorManager.getDefaultSensor(TYPE_NECK_ENCODER);
        mSensorWheelEncoder = mSensorManager.getDefaultSensor(TYPE_WHEEL_ENCODER);
        mSensorRobotBodyAccelerometerRaw = mSensorManager.getDefaultSensor(TYPE_ROBOT_BODY_ACCELEROMETER_RAW);
        mSensorRobotBodyGyroscopeRaw = mSensorManager.getDefaultSensor(TYPE_ROBOT_BODY_GYROSCOPE_RAW);
        mSensorRobotMotor = mSensorManager.getDefaultSensor(TYPE_ROBOT_MOTOR);
        mSensorRobotDockIr = mSensorManager.getDefaultSensor(TYPE_ROBOT_DOCK_IR);
        mSensorRobotNeckTrajectory = mSensorManager.getDefaultSensor(TYPE_ROBOT_NECK_TRAJECTORY);
        mSensorRobotWheelTrajectory = mSensorManager.getDefaultSensor(TYPE_ROBOT_WHEEL_TRAJECTORY);

    }


    @Override
    protected void onResume() {
        super.onResume();

        //hide expression
        robotAPI.robot.setExpression(RobotFace.HIDEFACE);

        //register sensor listeners
        mSensorManager.registerListener(listenerCapacityTouch, mSensorCapacityTouch, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerDropLaser, mSensorDropLaser, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerSonar, mSensorSonar, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerOdometry, mSensorOdometry, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerNeckEncoder, mSensorNeckEncoder, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerWheelEncoder, mSensorWheelEncoder, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerRobotBodyAccelerometerRaw, mSensorRobotBodyAccelerometerRaw, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerRobotBodyGyroscpoeRaw, mSensorRobotBodyGyroscopeRaw, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerRobotMotor, mSensorRobotMotor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerRobotDockIr, mSensorRobotDockIr, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerRobotNeckTrajectory, mSensorRobotNeckTrajectory, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(listenerRobotWheelTrajectory, mSensorRobotWheelTrajectory, SensorManager.SENSOR_DELAY_UI);
    }



    @Override
    protected void onPause() {
        super.onPause();

        robotAPI.robot.unregisterListenCallback();robotAPI.robot.unregisterListenCallback();

        //register sensor listeners
        mSensorManager.unregisterListener(listenerCapacityTouch);
        mSensorManager.unregisterListener(listenerDropLaser);
        mSensorManager.unregisterListener(listenerSonar);
        mSensorManager.unregisterListener(listenerOdometry);
        mSensorManager.unregisterListener(listenerNeckEncoder);
        mSensorManager.unregisterListener(listenerWheelEncoder);
        mSensorManager.unregisterListener(listenerRobotBodyAccelerometerRaw);
        mSensorManager.unregisterListener(listenerRobotBodyGyroscpoeRaw);
        mSensorManager.unregisterListener(listenerRobotMotor);
        mSensorManager.unregisterListener(listenerRobotDockIr);
        mSensorManager.unregisterListener(listenerRobotNeckTrajectory);
        mSensorManager.unregisterListener(listenerRobotWheelTrajectory);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        robotAPI.release();
    }



    //listener - TYPE_CAPACITY_TOUCH
    SensorEventListener listenerCapacityTouch = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_capacity_touch_value0.setText(String.valueOf(event.values[0]));
            mTextView_capacity_touch_value1.setText(String.valueOf(event.values[1]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //listener - TYPE_DROP_LASER
    SensorEventListener listenerDropLaser = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_drop_laser_value0.setText(String.valueOf(event.values[0]));
            mTextView_drop_laser_value1.setText(String.valueOf(event.values[1]));
            mTextView_drop_laser_value2.setText(String.valueOf(event.values[2]));
            mTextView_drop_laser_value3.setText(String.valueOf(event.values[3]));
            mTextView_drop_laser_value4.setText(String.valueOf(event.values[4]));
            mTextView_drop_laser_value5.setText(String.valueOf(event.values[5]));
            mTextView_drop_laser_value6.setText(String.valueOf(event.values[6]));
            mTextView_drop_laser_value7.setText(String.valueOf(event.values[7]));
            mTextView_drop_laser_value8.setText(String.valueOf(event.values[8]));
            mTextView_drop_laser_value9.setText(String.valueOf(event.values[9]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //listener - TYPE_SONAR
    SensorEventListener listenerSonar = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_sonar_value0.setText(String.valueOf(event.values[0]));
            mTextView_sonar_value1.setText(String.valueOf(event.values[1]));
            mTextView_sonar_value2.setText(String.valueOf(event.values[2]));
            mTextView_sonar_value3.setText(String.valueOf(event.values[3]));
            mTextView_sonar_value4.setText(String.valueOf(event.values[4]));
            mTextView_sonar_value5.setText(String.valueOf(event.values[5]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //listener - TYPE_ODOMETRY
    SensorEventListener listenerOdometry = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_odometry_value0.setText(String.valueOf(event.values[0]));
            mTextView_odometry_value1.setText(String.valueOf(event.values[1]));
            mTextView_odometry_value2.setText(String.valueOf(event.values[2]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //listener - TYPE_NECK_ENCODER
    SensorEventListener listenerNeckEncoder = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_neck_encoder_value0.setText(String.valueOf(event.values[0]));
            mTextView_neck_encoder_value1.setText(String.valueOf(event.values[1]));
            mTextView_neck_encoder_value2.setText(String.valueOf(event.values[2]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //listener - TYPE_WHEEL_ENCODER
    SensorEventListener listenerWheelEncoder = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_wheel_encoder_value0.setText(String.valueOf(event.values[0]));
            mTextView_wheel_encoder_value1.setText(String.valueOf(event.values[1]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //listener - TYPE_ROBOT_BODY_ACCELEROMETER_RAW
    SensorEventListener listenerRobotBodyAccelerometerRaw = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_robot_body_accelerometer_raw_value0.setText(String.valueOf(event.values[0]));
            mTextView_robot_body_accelerometer_raw_value1.setText(String.valueOf(event.values[1]));
            mTextView_robot_body_accelerometer_raw_value2.setText(String.valueOf(event.values[2]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };



    //listener - TYPE_ROBOT_BODY_GYROSCOPE_RAW
    SensorEventListener listenerRobotBodyGyroscpoeRaw = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_robot_body_gyroscope_raw_value0.setText(String.valueOf(event.values[0]));
            mTextView_robot_body_gyroscope_raw_value1.setText(String.valueOf(event.values[1]));
            mTextView_robot_body_gyroscope_raw_value2.setText(String.valueOf(event.values[2]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //listener - TYPE_ROBOT_MOTOR
    SensorEventListener listenerRobotMotor = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_robot_motor_value0.setText(String.valueOf(event.values[0]));
            mTextView_robot_motor_value1.setText(String.valueOf(event.values[1]));
            mTextView_robot_motor_value2.setText(String.valueOf(event.values[2]));
            mTextView_robot_motor_value3.setText(String.valueOf(event.values[3]));
            mTextView_robot_motor_value4.setText(String.valueOf(event.values[4]));
            mTextView_robot_motor_value5.setText(String.valueOf(event.values[5]));
            mTextView_robot_motor_value6.setText(String.valueOf(event.values[6]));
            mTextView_robot_motor_value7.setText(String.valueOf(event.values[7]));
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //listener - TYPE_ROBOT_DOCK_IR
    SensorEventListener listenerRobotDockIr = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_robot_dock_ir_value0.setText(String.valueOf(event.values[0]));
            mTextView_robot_dock_ir_value1.setText(String.valueOf(event.values[1]));
            mTextView_robot_dock_ir_value2.setText(String.valueOf(event.values[2]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //listener - TYPE_ROBOT_NECK_TRAJECTORY
    SensorEventListener listenerRobotNeckTrajectory = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_robot_neck_trajectory_value0.setText(String.valueOf(event.values[0]));
            mTextView_robot_neck_trajectory_value1.setText(String.valueOf(event.values[1]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //listener - TYPE_ROBOT_WHEEL_TRAJECTORY
    SensorEventListener listenerRobotWheelTrajectory = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            mTextView_robot_wheel_trajectory_value0.setText(String.valueOf(event.values[0]));
            mTextView_robot_wheel_trajectory_value1.setText(String.valueOf(event.values[1]));

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}



