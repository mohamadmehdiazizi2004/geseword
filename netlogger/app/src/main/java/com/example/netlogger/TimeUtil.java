package com.example.netlogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public static String format(long millis) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
                .format(new Date(millis));
    }

    public static long toSeconds(long millisDiff) {
        return TimeUnit.MILLISECONDS.toSeconds(millisDiff);
    }

    public static String formatDuration(long seconds) {
        long h = seconds / 3600;
        long m = (seconds % 3600) / 60;
        long s = seconds % 60;
        if (h > 0) return h + "س " + m + "د " + s + "ث";
        if (m > 0) return m + "د " + s + "ث";
        return s + "ث";
    }
}
