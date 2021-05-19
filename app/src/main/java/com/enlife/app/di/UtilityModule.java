package com.enlife.app.di;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.utils.DateFormatter;
import com.enlife.app.utils.Utils;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilityModule {

    @Provides
    public Utils utils() {
        return new Utils();
    }

    @Provides
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    @Provides
    public CustomApplication application() {
        return CustomApplication.getInstance();
    }

}
