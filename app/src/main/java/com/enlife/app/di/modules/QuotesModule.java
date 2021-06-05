package com.enlife.app.di.modules;

import android.content.Context;

import com.enlife.app.common.QuotesManager;
import com.enlife.app.database.models.Quote;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.QuoteDataOperator;
import com.enlife.app.di.scopes.PerScreen;

import dagger.Module;
import dagger.Provides;

@Module
public class QuotesModule {

    @PerScreen
    @Provides
    QuotesManager provideQuotesManager(Context context, QuoteDataOperator databaseOperator) {
        return new QuotesManager(context, databaseOperator);
    }

}
