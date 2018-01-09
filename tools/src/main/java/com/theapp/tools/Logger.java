package com.theapp.tools;

import android.support.annotation.NonNull;

public interface Logger {

    void d(@NonNull String tag, @NonNull String msg);

    void e(@NonNull String tag, @NonNull String msg);

    void e(@NonNull String tag, @NonNull String msg, @NonNull Throwable tr);

    void i(@NonNull String tag, @NonNull String msg);

    void v(@NonNull String tag, @NonNull String msg);

    void w(@NonNull String tag, @NonNull String msg);

}
