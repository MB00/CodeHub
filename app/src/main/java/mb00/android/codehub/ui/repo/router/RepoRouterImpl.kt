package mb00.android.codehub.ui.repo.router

import androidx.appcompat.app.AppCompatActivity


class RepoRouterImpl(private val activity: AppCompatActivity) : RepoRouter {

    override fun finishRepoActivity() {
        activity.finish()
    }

}
