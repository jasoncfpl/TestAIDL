package example.admin.com.testaidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Button mButton;

    IMyAidlInterface mIMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView(){
        mTextView = (TextView) findViewById(R.id.textview);
        mButton = (Button) findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setAction("example.admin.com.testaidl.MyService");//你定义的service的action
                    intent.setPackage("example.admin.com.testaidl");//这里你需要设置你应用的包名
                    bindService(intent,mConnection, Context.BIND_AUTO_CREATE);

            }
        });

    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.i("TAG", "onServiceConnected: " + mIMyAidlInterface);
            try {
                mIMyAidlInterface.hello("World!!!");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TAG", "onServiceDisconnected: " + name);
        }
    };
}
