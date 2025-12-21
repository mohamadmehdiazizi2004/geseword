package com.example.question;
import android.os.CountDownTimer;

class MyCountDownTimer extends CountDownTimer {

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // عملیات انجام دهید در هر ثانیه
    }

    @Override
    public void onFinish() {
        // عملیات انجام دهید وقتی که زمان تمام شد
    }
}

