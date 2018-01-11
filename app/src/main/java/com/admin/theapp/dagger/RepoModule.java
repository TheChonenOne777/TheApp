package com.admin.theapp.dagger;

import com.admin.theapp.firebase.FirebaseDatabase;
import com.theapp.repository.Firebase;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
abstract class RepoModule {
    @Binds
    @Singleton
    abstract Firebase provideFirebase(FirebaseDatabase firebaseDatabase);
}
