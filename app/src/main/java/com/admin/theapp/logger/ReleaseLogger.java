package com.admin.theapp.logger;

import android.support.annotation.NonNull;

import com.theapp.tools.Logger;

public class ReleaseLogger implements Logger {
    @Override
    public void d(@NonNull String tag, @NonNull String msg) { // default implementation ignored
    }

    @Override
    public void e(@NonNull String tag, @NonNull String msg) { // default implementation ignored
    }

    @Override
    public void e(@NonNull String tag, @NonNull String msg, @NonNull Throwable tr) { // default implementation ignored
    }

    @Override
    public void i(@NonNull String tag, @NonNull String msg) { // default implementation ignored
    }

    @Override
    public void v(@NonNull String tag, @NonNull String msg) { // default implementation ignored
    }

    @Override
    public void w(@NonNull String tag, @NonNull String msg) { // default implementation ignored
    }
}
