package com.admin.theapp.utils.mappers;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<From, To> {
    @NonNull
    public abstract To map(@NonNull From from);

    @NonNull
    public List<To> map(@NonNull List<From> from) {
        final List<To> newList = new ArrayList<>();
        for (From f : from) {
            newList.add(map(f));
        }
        return newList;
    }
}
