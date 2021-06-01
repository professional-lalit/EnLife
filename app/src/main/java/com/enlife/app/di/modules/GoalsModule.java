package com.enlife.app.di.modules;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.MilestoneDataOperator;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragmentPresenter;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneDialogPresenter;
import com.enlife.app.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;

@Module
public class GoalsModule {

    @PerScreen
    @Provides
    AddGoalFragmentPresenter provideAddGoalFragmentPresenter(DateFormatter dateFormatter,
                                                             GoalDataOperator goalDatabaseOperator,
                                                             MilestoneDataOperator milestoneDatabaseOperator,
                                                             EventDataOperator eventDatabaseOperator
    ) {
        return new AddGoalFragmentPresenter(
                dateFormatter,
                goalDatabaseOperator,
                milestoneDatabaseOperator,
                eventDatabaseOperator
        );
    }

    @PerScreen
    @Provides
    AddMilestoneDialogPresenter provideAddMilestoneDialogPresenter(DateFormatter dateFormatter,
                                                                   MilestoneDataOperator milestoneDatabaseOperator
    ) {
        return new AddMilestoneDialogPresenter(
                dateFormatter,
                milestoneDatabaseOperator
        );
    }


}
