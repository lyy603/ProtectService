package android.yiniu.com.protectservice;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i("jiajia","onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("jiajia","onStartCommand");
        Log.i("jiajia",""+isServiceAlive(MainActivity.packageName));
        if(!isServiceAlive(MainActivity.packageName)){
            Intent start = getPackageManager().getLaunchIntentForPackage(MainActivity.packageName);
            startActivity(start);
            Observable.just("com.tencent.mm")
            .delay(10, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    Intent start = getPackageManager().getLaunchIntentForPackage(s);
                    startActivity(start);
                }
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("jiajia","onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("jiajia","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public boolean isServiceAlive(String threadName){
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> procList = null;
        procList = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info:procList){
            if(info.process.equals(threadName)){
                return true;
            }
        }
        return false;
    }
}
