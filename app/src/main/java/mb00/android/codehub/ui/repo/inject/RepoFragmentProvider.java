package mb00.android.codehub.ui.repo.inject;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import mb00.android.codehub.ui.repo.view.RepoCodeFragment;
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment;
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment;
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment;
import mb00.android.codehub.ui.repo.view.RepoLicenseFragment;
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment;
import mb00.android.codehub.ui.repo.view.RepoPulseFragment;
import mb00.android.codehub.ui.repo.view.RepoReadmeFragment;
import mb00.android.codehub.ui.repo.view.RepoReleasesFragment;


@Module
public abstract class RepoFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(RepoCodeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRepoCodeFragment(RepoCodeFragmentComponent.Builder builder);

    @Binds
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

}
