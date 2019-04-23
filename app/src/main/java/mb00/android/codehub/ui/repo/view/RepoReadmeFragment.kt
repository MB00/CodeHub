package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.text.Html
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoReadmeBinding
import mb00.android.codehub.logic.utils.Base64Decoder
import mb00.android.codehub.logic.utils.MarkdownParser
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoReadmeViewModel
import timber.log.Timber

/**
 * Fragment containing repository readme; launched from [RepoFragmentPagerAdapter]
 */

class RepoReadmeFragment : BaseBindingFragment<FragmentRepoReadmeBinding, RepoReadmeViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_readme
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences!!.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = if (arguments != null) arguments.getString(BundleKeys.USER_NAME) else ""
        repoName = if (arguments != null) arguments.getString(BundleKeys.REPO_NAME) else ""
    }

    override fun onStart() {
        super.onStart()

        repoReadmeCall(authHeader, userName, repoName)
    }

    private fun repoReadmeCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoReadme(header, user, repo)
                .subscribe({ repoReadme ->
                    val readmeMarkdown = repoReadme.content?.let { Base64Decoder.decodeBase64(it) }
                    val readmeParsed = MarkdownParser.parseMarkdown(readmeMarkdown)
                    binding.repoReadmeTextView.text = Html.fromHtml(readmeParsed)
                }, { error -> Timber.e(error.message) })
        )
    }

}
