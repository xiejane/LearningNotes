# 重启app自身

[转载](https://www.jianshu.com/p/6321c7bfb443)

## 方案
其实原来很简单，就是先开启一个Service，然后自己杀死APP的进程，最后在Service里通过指定服务入口来启动一个APP并销毁Service。


```java

/**
 * 该工具仅用于重启自身 
 */
public class RestartSelfTool {

    public static void restartApp(Context context, long delayed){
    	/* 开启新的服务来启动app */
        Intent intent = new Intent(context, KillSelfService.class);
        intent.putExtra("delayed", delayed);
        context.startService(intent);

        /*杀死整个进程*/
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void restartApp(Context context){
        restartApp(context, 2 * 1000L);
    }

}

```


```Java

/* 用来启动app，启动之后杀死自己。生命周期也仅仅是重启app， 该服务需要在清单文件内注册*/
public class KillSelfService extends Service {

	// 关闭应用后需要多久后重启
    private static long stopDelayed = 2 * 1000;
    private Handler mHandler;

    public KillSelfService(){
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stopDelayed = intent.getLongExtra("delayed", 2 * 1000L);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
            	// 唤起目标服务（这里是因为我的app是主要在后台服务）
                Intent launchIntent = new Intent(getApplicationContext(), DmsGroupService.class);
                startService(launchIntent);

                // 自我杀死
                KillSelfService.this.stopSelf();
            }
        }, stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
```


在清单文件内注册：

```xml
<service
    android:name="com.inhand.core.utils.KillSelfService"
    android:enabled="true"
    android:exported="true"/>
```