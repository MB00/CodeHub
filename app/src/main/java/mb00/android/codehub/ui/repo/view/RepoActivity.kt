package mb00.android.codehub.ui.repo.view

import android.os.Bundle

import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.databinding.ActivityRepoBinding
import mb00.android.codehub.ui.base.view.BaseDrawerActivity
import mb00.android.codehub.ui.repo.adapter.RepoAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoViewModel

/**
 * Launched from [RepoAdapter] if repository in RecyclerView is clicked
 * Immediately launches [RepoFragmentPagerAdapter]
 */

class RepoActivity : BaseDrawerActivity<ActivityRepoBinding, RepoViewModel>() {

    private var repoBundle: Bundle? = null
    private var repoFullName: String? = ""

    override fun layout(): Int {
        return R.layout.activity_repo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getExtras()
        initToolbar()
        initViewPager()
    }

    private fun getExtras() {
        repoBundle = intent.extras
        repoFullName = repoBundle?.getString(BundleKeys.REPO_FULL_NAME)
    }

    private fun initToolbar() {
        binding.repoToolbarTextView.text = repoFullName
    }

    private fun initViewPager() {
        val repoPagerAdapter = RepoFragmentPagerAdapter(supportFragmentManager, this, repoBundle)
        binding.repoViewPager.adapter = repoPagerAdapter
        binding.repoViewPager.offscreenPageLimit = 9
        binding.repoViewPager.pageMargin = 4
        binding.repoViewPager.setPageMarginDrawable(R.color.grey)
        binding.repoTabLayout.setupWithViewPager(binding.repoViewPager)
    }

}
