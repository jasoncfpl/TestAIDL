package example.admin.com.testaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin
 * date 16/10/11.
 */

public class MyService extends Service {

    public MyService() {
        Log.i("TAG", "MyService: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TAG", "onBind: " + intent);
        return mBinder;
    }

    public final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void hello(String str) throws RemoteException {
            Log.i("TAG", "hello: " + str);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
}
