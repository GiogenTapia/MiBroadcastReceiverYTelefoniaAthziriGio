package net.ivanvega.mibroadcastreceiverytelefonia.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import net.ivanvega.mibroadcastreceiverytelefonia.MainActivity;

public class MiReceiverPhone extends BroadcastReceiver {


    private static final String TAG = "Tell";
    MainActivity mainActivity;


    @Override
    public void onReceive(Context context, Intent intent) {
        mainActivity = new MainActivity();
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                if (state == TelephonyManager.CALL_STATE_RINGING){
                    Log.d(TAG, "onCallStateChanged: " + incomingNumber);
                    mainActivity.sendMsm(incomingNumber);
                }
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }
}
