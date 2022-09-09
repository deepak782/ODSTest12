package com.deepak.odstest12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class AllNotificationActivity extends AppCompatActivity {

    EditText title,body;
    String titleStr,bodyStr;
    private static final String TAG = "Firebase Messaging";
    String onePlusToken="dkv0lH6KT-W37r2wuzkUBn:APA91bH6d1Gt1gVdIKWg8p6mMCF8hZoJOiIbvB5t7xcOWk_n-1wskWrLPz16h7Be0Syer8AU9S3k417L4bYJgTmgscs6a1NTCfFe1WlUS_qGyjdtm_0PQxi1M1oLvyTkXNE_MTnfE4b5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notification);
        title=findViewById(R.id.edt_title);
        body=findViewById(R.id.edt_body);

    }

    public void sendAll(View view) {
        titleStr=title.getText().toString();
        bodyStr=body.getText().toString();
        //All user who sub to particular topic
      /*  FcmSenderNotification fcmSenderNotification=new FcmSenderNotification("/topics/all",titleStr,bodyStr,getApplicationContext(),AllNotificationActivity.this);
        fcmSenderNotification.SendNotifications();*/

        //msg to particlaur user
        FcmSenderNotification fcmSenderNotification=new FcmSenderNotification(onePlusToken,titleStr,bodyStr,getApplicationContext(),AllNotificationActivity.this);
        fcmSenderNotification.SendNotifications();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getSubscribe();
        getToken();
    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();

                // Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
                Log.d(TAG, msg);
                Toast.makeText(AllNotificationActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getSubscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic("all").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg;
                if(!task.isSuccessful())
                {
                    msg="Failed";
                }
                msg="Subscribed!";
                Toast.makeText(AllNotificationActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

            }
        });

    }
}