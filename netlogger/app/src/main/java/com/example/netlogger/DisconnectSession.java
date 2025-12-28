package com.example.netlogger;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "disconnect_sessions")
public class DisconnectSession {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;        // مثلا: "گزارش قطعی اینترنت"
    public long startMillis;    // زمان شروع قطعی
    public Long endMillis;      // زمان پایان قطعی (وقتی وصل شد)
    public Long durationSec;    // مدت قطعی (ثانیه)

    public DisconnectSession(String title, long startMillis, Long endMillis, Long durationSec) {
        this.title = title;
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.durationSec = durationSec;
    }
}
