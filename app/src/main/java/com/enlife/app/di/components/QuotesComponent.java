package com.enlife.app.di.components;

import com.enlife.app.di.modules.QuotesModule;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.fragments.stoicism.QuotesFragment;

import dagger.Subcomponent;

@PerScreen
@Subcomponent(modules = {QuotesModule.class})
public interface QuotesComponent {

    void inject(QuotesFragment quotesFragment);

    @Subcomponent.Builder
    interface Builder {
        QuotesComponent build();
    }
}
