package mb00.android.codehub.inject;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import mb00.android.codehub.ui.repo.inject.RepoCommitsFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoContributorsFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoIssuesFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoLicenseFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoPullRequestsFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoPulseFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoReadmeFragmentComponent;
import mb00.android.codehub.ui.repo.inject.RepoReleasesFragmentComponent;
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment;
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment;
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment;
import mb00.android.codehub.ui.repo.view.RepoLicenseFragment;
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment;
import mb00.android.codehub.ui.repo.view.RepoPulseFragment;
import mb00.android.codehub.ui.repo.view.RepoReadmeFragment;
import mb00.android.codehub.ui.repo.view.RepoReleasesFragment;
import mb00.android.codehub.ui.user.inject.UserFollowersFragmentComponent;
import mb00.android.codehub.ui.user.inject.UserFollowingFragmentComponent;
import mb00.android.codehub.ui.user.inject.UserGistsFragmentComponent;
import mb00.android.codehub.ui.user.inject.UserOverviewFragmentComponent;
import mb00.android.codehub.ui.user.inject.UserPulseFragmentComponent;
import mb00.android.codehub.ui.user.inject.UserReposFragmentComponent;
import mb00.android.codehub.ui.user.inject.UserStarredFragmentComponent;
import mb00.android.codehub.ui.user.view.UserFollowersFragment;
import mb00.android.codehub.ui.user.view.UserFollowingFragment;
import mb00.android.codehub.ui.user.view.UserGistsFragment;
import mb00.android.codehub.ui.user.view.UserOverviewFragment;
import mb00.android.codehub.ui.user.view.UserPulseFragment;
import mb00.android.codehub.ui.user.view.UserReposFragment;
import mb00.android.codehub.ui.user.view.UserStarredFragment;

//@Module
public abstract class FragmentBuilderModule {

    /*@Binds
    @IntoMap
    @FragmentKey(RepoCommitsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoCommitsFragment(RepoCommitsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoContributorsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoContributorsFragment(RepoContributorsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoIssuesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoIssuesFragment(RepoIssuesFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoLicenseFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoLicenseFragment(RepoLicenseFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoPullRequestsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoPullRequestsFragment(RepoPullRequestsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoPulseFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoPulseFragment(RepoPulseFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoReadmeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoReadmeFragment(RepoReadmeFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RepoReleasesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoReleasesFragment(RepoReleasesFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserFollowersFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserFollowersFragment(UserFollowersFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserFollowingFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserFollowingFragment(UserFollowingFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserGistsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserGistsFragment(UserGistsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserOverviewFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserOverviewFragment(UserOverviewFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserPulseFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserPulseFragment(UserPulseFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserReposFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserReposFragment(UserReposFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserStarredFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserStarredFragment(UserStarredFragmentComponent.Builder builder);*/


}
