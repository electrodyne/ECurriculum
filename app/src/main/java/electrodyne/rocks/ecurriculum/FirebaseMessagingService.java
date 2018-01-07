package electrodyne.rocks.ecurriculum;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by Fauni on 1/6/2018.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {
        Intent i = new Intent(this,MainActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sound = Uri.parse("android.resource://electrodyne.rocks.ecurriculum/" + R.raw.notif);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "0")
                .setAutoCancel(true)
                .setContentTitle("ANNOUNCEMENT")
                .setContentText(message)
                .setSound(sound)
                .setSmallIcon(R.drawable.favicon)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());

        //PLAY WITH MANAGER ID AND CHANNEL ID LATER.

    }
}

