package com.enlife.app.di.modules;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.MilestoneDataOperator;
import com.enlife.app.di.scopes.FragmentScope;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragment;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragmentPresenter;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneContract;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneDialogPresenter;
import com.enlife.app.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;

@Module
public class GoalsModule {

    @FragmentScope
    @Provides
    AddGoalFragmentPresenter addGoalFragmentPresenter(DateFormatter dateFormatter,
                                                      GoalDataOperator goalDatabaseOperator,
                                                      MilestoneDataOperator milestoneDatabaseOperator,
                                                      EventDataOperator eventDatabaseOperator,
                                                      AddGoalFragment addGoalFragment
    ) {
        return new AddGoalFragmentPresenter(
                dateFormatter,
                goalDatabaseOperator,
                milestoneDatabaseOperator,
                eventDatabaseOperator,
                addGoalFragment
        );
    }

    @FragmentScope
    @Provides
    AddMilestoneDialogPresenter addMilestoneDialogPresenter(DateFormatter dateFormatter,
                                                            MilestoneDataOperator milestoneDatabaseOperator,
                                                            AddMilestoneBottomDialog dialog
    ) {
        return new AddMilestoneDialogPresenter(dateFormatter, milestoneDatabaseOperator, dialog);
    }


}
