package com.example.netlogger;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "connection_logs")
public class ConnectionLog {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;       // عنوان
    public String status;      // CONNECTED / DISCONNECTED
    public String dateTime;    // تاریخ و ساعت قابل نمایش
    public long epochMillis;   // برای مرتب‌سازی دقیق

    public ConnectionLog(String title, String status, String dateTime, long epochMillis) {
        this.title = title;
        this.status = status;
        this.dateTime = dateTime;
        this.epochMillis = epochMillis;
    }
}
