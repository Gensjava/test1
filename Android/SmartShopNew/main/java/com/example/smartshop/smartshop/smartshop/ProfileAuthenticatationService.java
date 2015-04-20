package ua.smartshop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ua.smartshop.Utils.ProfileAccountAuthenticator;

/**
 * Created by Gens on 25.03.2015.
 */
public class ProfileAuthenticatationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub

        return new ProfileAccountAuthenticator(this).getIBinder();

    }
}
