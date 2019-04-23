package mb00.android.codehub.ui.repo.router

import android.support.v7.app.AppCompatActivity


class RepoRouterImpl(private val activity: AppCompatActivity) : RepoRouter {

    override fun finishRepoActivity() {
        activity.finish()
    }

}
