package com.enlife.app.screens.main.fragments.stoicism;

import com.enlife.app.di.modules.QuotesModule;
import com.enlife.app.di.scopes.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {QuotesModule.class})
public interface QuotesComponent {

    void inject(QuotesFragment quotesFragment);

    @Subcomponent.Factory
    interface Factory {
        QuotesComponent create(@BindsInstance QuotesFragment fragment);
    }
}
