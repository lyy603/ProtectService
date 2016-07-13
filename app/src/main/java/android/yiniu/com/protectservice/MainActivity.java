package android.yiniu.com.protectservice;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.yiniu.com.protectservice.util.RepeatUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String packageName = "com.yiniu.wxhelper";
    Button btn_start;
    Button btn_stop;
    Button btn_show_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RepeatUtil.startRepeatService(MainActivity.this,15,MyService.class,"0");
        btn_start = (Button)findViewById(R.id.btn_start_check);
        btn_stop= (Button)findViewById(R.id.btn_stop_check);
        btn_show_service= (Button)findViewById(R.id.btn_show_service);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("jiajia","btn_start");
                RepeatUtil.startRepeatService(MainActivity.this,15,MyService.class,"0");
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("jiajia","btn_stop");
                RepeatUtil.stopRepeatService(MainActivity.this,MyService.class,"0");
            }
        });
        btn_show_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                List<ActivityManager.RunningServiceInfo> list = activityManager.getRunningServices(Integer.MAX_VALUE);
                for(ActivityManager.RunningServiceInfo info:list){
                    Log.d("jiajia",info.process);
                }
            }
        });
    }
}
