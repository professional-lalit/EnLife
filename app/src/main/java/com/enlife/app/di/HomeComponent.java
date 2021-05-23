package com.enlife.app.di;

import com.enlife.app.screens.main.dialog.EventsBottomDialog;
import com.enlife.app.screens.main.dialog.EventsBottomDialogPresenter;
import com.enlife.app.screens.main.fragments.home.HomeFragment;
import com.enlife.app.screens.main.fragments.home.HomeFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent
public interface HomeComponent {

    @Subcomponent.Factory
    public interface Factory {
        HomeComponent create();
    }

    void inject(HomeFragment homeFragment);

    void inject(HomeFragmentPresenter presenter);

    void inject(EventsBottomDialogPresenter presenter);

    void inject(EventsBottomDialog dialogFragment);
}
