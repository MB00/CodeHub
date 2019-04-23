package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.ActivityRepoFileBinding
import mb00.android.codehub.logic.utils.Base64Decoder
import mb00.android.codehub.ui.base.view.BaseBindingActivity
import mb00.android.codehub.ui.repo.adapter.CodeAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoFileViewModel
import timber.log.Timber

/**
 * Launched from [CodeAdapter] if repository file in RecyclerView is clicked
 */

class RepoFileActivity : BaseBindingActivity<ActivityRepoFileBinding, RepoFileViewModel>() {

    override fun layout(): Int {
        return R.layout.activity_repo_file
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        val authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "")
        val userName = intent.extras!!.getString(BundleKeys.USER_NAME)
        val repoName = intent.extras!!.getString(BundleKeys.REPO_NAME)
        val fileName = intent.extras!!.getString(BundleKeys.FILE_NAME)
        val filePath = intent.extras!!.getString(BundleKeys.FILE_PATH)

        binding.repoFileBackButton.setOnClickListener { finish() }
        binding.repoFileTitleTextView.text = fileName

        codeFileCall(authHeader, userName, repoName, filePath)
    }

    private fun codeFileCall(header: String, user: String, repo: String, filePath: String) {
        disposables.add(viewModel.loadRepoFile(header, user, repo, filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repoFile ->
                    val file = repoFile.content?.let { Base64Decoder.decodeBase64(it) }
                    binding.repoFileTextView.text = file
                }, { error -> Timber.e(error.message) }))
    }

}
