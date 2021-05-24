# Message

```
private Handler handler = new Handler(){
    public void handleMessage(android.os.Message msg){
        int arg1 =msg.arg1;
        int arg2 = msg.arg2;
        int what = msg.what;
        Obeject result = msg.obj;

    }
};

new Thread(new MyThread()).start();

public class MyThread implements Runable{
    @override
    pulic void run(){//写发送的消息对象，并对消息携带的信息进行定义
        Message message = Message.obtain();
        message.what =1;
        handler.sendMessage(message);

    }
}
```