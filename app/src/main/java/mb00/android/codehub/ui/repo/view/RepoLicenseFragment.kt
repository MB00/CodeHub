package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoLicenseBinding
import mb00.android.codehub.logic.utils.Base64Decoder
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoLicenseViewModel
import timber.log.Timber

/**
 * Fragment containing repository license; launched from [RepoFragmentPagerAdapter]
 */

class RepoLicenseFragment : BaseBindingFragment<FragmentRepoLicenseBinding, RepoLicenseViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_license
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

        repoLicenseCall(authHeader, userName, repoName)
    }

    private fun repoLicenseCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoLicense(header, user, repo)
                .subscribe({ repoLicense ->
                    if (repoLicense != null) {
                        val license = repoLicense.content?.let { Base64Decoder.decodeBase64(it) }
                        binding.repoLicenseTextView.text = license
                    } else {
                        binding.noRepoLicenseTextView.visibility = View.VISIBLE
                    }
                }, { throwable ->
                    Timber.e(throwable.toString())
                    binding.noRepoLicenseTextView.visibility = View.VISIBLE
                })
        )
    }

}
