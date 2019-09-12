package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserPulseBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.universaladapter.PulseAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserPulseViewModel
import timber.log.Timber

/**
 * Fragment containing user pulse; launched from [UserFragmentPagerAdapter]
 */

class UserPulseFragment : BaseBindingFragment<FragmentUserPulseBinding, UserPulseViewModel>() {

    private lateinit var userName: String
    private lateinit var authHeader: String

    override fun layout(): Int {
        return R.layout.fragment_user_pulse
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity!!.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userName = arguments?.getString(BundleKeys.USER_NAME) ?: ""
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSwipeRefreshLayout()

        loadUserPulse()
    }

    private fun initSwipeRefreshLayout() {
        binding.userPulseSwipeRefreshLayout.setOnRefreshListener {
            loadUserPulse()
            binding.userPulseSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initRecyclerView() {
        binding.userPulseRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userPulseRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun loadUserPulse() {
        // set in layout
        disposables.add(viewModel.loadUserPulse(authHeader, userName).subscribe({ userPulse ->
            when (userPulse.isNotEmpty()) {
                true -> binding.userPulseRecyclerView.adapter = PulseAdapter(userPulse, activity!!)
                false -> binding.noUserPulseTextView.visibility = View.VISIBLE
            }
        }, { error -> Timber.e(error.message) })
        )
    }

}
