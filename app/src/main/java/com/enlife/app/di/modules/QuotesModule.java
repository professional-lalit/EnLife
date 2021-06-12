package com.enlife.app.di.modules;

import android.content.Context;

import com.enlife.app.common.QuotesManager;
import com.enlife.app.database.operators.QuoteDataOperator;
import com.enlife.app.di.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class QuotesModule {

    @FragmentScope
    @Provides
    QuotesManager provideQuotesManager(Context context, QuoteDataOperator databaseOperator) {
        return new QuotesManager(context, databaseOperator);
    }

}
