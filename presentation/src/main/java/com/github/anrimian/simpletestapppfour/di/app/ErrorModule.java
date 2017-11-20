package com.github.anrimian.simpletestapppfour.di.app;

import android.content.Context;

import com.github.anrimian.simpletestapppfour.utils.error.parser.DefaultErrorParser;
import com.github.anrimian.simpletestapppfour.utils.error.parser.ErrorParser;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 29.10.2017.
 */

@Module
public class ErrorModule {

    @Provides
    @Nonnull
    @Singleton
    ErrorParser provideErrorParser(Context context) {
        return new DefaultErrorParser(context);
    }
}
