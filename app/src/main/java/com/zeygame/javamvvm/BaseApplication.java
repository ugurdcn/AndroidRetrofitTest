package com.zeygame.javamvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.Configuration;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication  extends Application implements Configuration.Provider {

    @Inject
    HiltWorkerFactory workerFactory;
    @NonNull
    @NotNull
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder().setWorkerFactory(workerFactory).build();
    }
}
