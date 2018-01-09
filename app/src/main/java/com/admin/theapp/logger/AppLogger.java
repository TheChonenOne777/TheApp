package com.admin.theapp.logger;

import android.support.annotation.NonNull;
import android.util.Log;

import com.theapp.tools.Logger;

public class AppLogger implements Logger {

    @Override
    public void d(@NonNull String tag, @NonNull String msg) {
        Log.d(tag, msg);
    }

    @Override
    public void e(@NonNull String tag, @NonNull String msg) {
        Log.e(tag, msg);
    }

    @Override
    public void e(@NonNull String tag, @NonNull String msg, @NonNull Throwable tr) {
        Log.e(tag, msg, tr);
    }

    @Override
    public void i(@NonNull String tag, @NonNull String msg) {
        Log.i(tag, msg);
    }

    @Override
    public void v(@NonNull String tag, @NonNull String msg) {
        Log.v(tag, msg);
    }

    @Override
    public void w(@NonNull String tag, @NonNull String msg) {
        Log.w(tag, msg);
    }
}
