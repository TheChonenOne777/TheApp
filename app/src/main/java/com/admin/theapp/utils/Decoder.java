package com.admin.theapp.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class Decoder {

    @NonNull
    private final Context context;

    @Inject
    public Decoder(@NonNull Context context) {
        this.context = context;
    }

    public BitmapDrawable decode(final byte[] data) {
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(data, 0, data.length));
    }
}
