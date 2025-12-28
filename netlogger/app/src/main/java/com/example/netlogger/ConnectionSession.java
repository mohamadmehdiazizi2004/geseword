package com.example.netlogger;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "connection_sessions")
public class ConnectionSession {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;       // "اتصال به اینترنت" یا "قطع اینترنت"
    public String status;      // CONNECTED / DISCONNECTED

    public long startMillis;   // شروع وضعیت
    public Long endMillis;     // پایان وضعیت (تا وقتی تغییر نکرده null می‌مونه)
    public Long durationSec;   // مدت زمان (ثانیه) وقتی end ثبت شد

    public ConnectionSession(String title, String status, long startMillis, Long endMillis, Long durationSec) {
        this.title = title;
        this.status = status;
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.durationSec = durationSec;
    }
}
