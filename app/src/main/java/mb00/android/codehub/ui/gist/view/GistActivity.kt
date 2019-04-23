package mb00.android.codehub.ui.gist.view

import android.os.Bundle

import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.databinding.ActivityGistBinding
import mb00.android.codehub.ui.base.view.BaseDrawerActivity
import mb00.android.codehub.ui.gist.adapter.GistAdapter
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter
import mb00.android.codehub.ui.gist.viewmodel.GistViewModel

/**
 * Launched from [GistAdapter] if gist in RecyclerView is clicked
 * Immediately launches [GistFragmentPagerAdapter]
 */

class GistActivity : BaseDrawerActivity<ActivityGistBinding, GistViewModel>() {

    private var gistBundle: Bundle? = null
    private var userName: String? = ""

    override fun layout(): Int {
        return R.layout.activity_gist
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getExtras()
        initToolbar()
        initViewPager()
    }

    private fun getExtras() {
        gistBundle = intent.extras
        userName = gistBundle?.getString(BundleKeys.USER_NAME)
}

    private fun initToolbar() {
        val gistToolbarText = "$userName/gist"
        binding.gistToolbarTextView.text = gistToolbarText
    }

    private fun initViewPager() {
        val gistPagerAdapter = GistFragmentPagerAdapter(supportFragmentManager, this, gistBundle)

        binding.gistViewPager.adapter = gistPagerAdapter
        binding.gistViewPager.offscreenPageLimit = 2
        binding.gistTabLayout.setupWithViewPager(binding.gistViewPager)
    }

}
