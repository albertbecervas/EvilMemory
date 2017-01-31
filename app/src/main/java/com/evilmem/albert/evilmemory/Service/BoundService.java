package com.evilmem.albert.evilmemory.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {
    public int codigo = 0;
    private IBinder binder = new MyBinder();

    public class MyBinder extends Binder{
        BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }

    @Override
    public void onDestroy() {
        //TODO código para liberar recursos
    }


}
