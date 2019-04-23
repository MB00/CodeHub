package mb00.android.codehub.ui.home.view

import android.os.Bundle

import mb00.android.codehub.R
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.ActivityHomeBinding
import mb00.android.codehub.ui.base.view.BaseDrawerActivity
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel
import mb00.android.codehub.ui.main.view.MainActivity

/**
 * Launched from [MainActivity] if [.SIGNED_IN == true][PreferenceKeys]
 */

class HomeActivity : BaseDrawerActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun layout(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {
        val homePagerAdapter = HomeFragmentPagerAdapter(supportFragmentManager, this)

        binding.homeViewPager.adapter = homePagerAdapter
        binding.homeViewPager.offscreenPageLimit = 4
        binding.homeViewPager.pageMargin = 4
        binding.homeViewPager.setPageMarginDrawable(R.color.grey)
        binding.homeTabLayout.setupWithViewPager(binding.homeViewPager)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}
