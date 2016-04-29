package com.androidbelieve.drawerwithswipetabs.domain.sharedusecase;

import android.content.Context;
import android.provider.Settings;

import java.security.MessageDigest;

/**
 * Created by teiyuueki on 2016/04/29.
 */
public class SendBirdHelper {
    public static String generateDeviceUUID(Context context) {
        String serial = android.os.Build.SERIAL;
        String androidID = Settings.Secure.ANDROID_ID;
        String deviceUUID = serial + androidID;

        /*
         * SHA-1
         */
        MessageDigest digest;
        byte[] result;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            result = digest.digest(deviceUUID.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }

    public static String setUserName() {
        String sb;
        sb = "oki doki!";
        return sb;
    }
}
