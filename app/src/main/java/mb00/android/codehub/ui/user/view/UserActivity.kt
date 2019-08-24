package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.ActivityUserBinding
import mb00.android.codehub.ui.base.view.BaseDrawerActivity
import mb00.android.codehub.ui.user.adapter.UserAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserViewModel

/**
 * Launched from [UserAdapter] if user in RecyclerView is clicked
 * Immediately launches [UserFragmentPagerAdapter]
 */

class UserActivity : BaseDrawerActivity<ActivityUserBinding, UserViewModel>() {

    private var userBundle: Bundle? = null
    private var authHeader: String? = ""
    private var userName: String? = ""

    override fun layout(): Int {
        return R.layout.activity_user
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getExtras()
        initToolbar()
        initViewPager()
    }

    private fun getExtras() {
        userBundle = intent.extras
        userName = userBundle?.getString(BundleKeys.USER_NAME)

        val preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
                .getString(PreferenceKeys.USER_NAME, "")
    }

    private fun initToolbar() {
        binding.userToolbarTextView.text = userName
    }

    private fun initViewPager() {
        val userPagerAdapter = UserFragmentPagerAdapter(supportFragmentManager, this, userBundle)

        binding.userViewPager.adapter = userPagerAdapter
        binding.userViewPager.offscreenPageLimit = 7
        binding.userViewPager.pageMargin = 4
        binding.userViewPager.setPageMarginDrawable(R.color.grey)
        binding.userTabLayout.setupWithViewPager(binding.userViewPager)

        userBundle?.let { binding.userViewPager.currentItem = userBundle?.getInt(BundleKeys.VIEW_PAGER_POSITION) ?: 0 }
    }

}
