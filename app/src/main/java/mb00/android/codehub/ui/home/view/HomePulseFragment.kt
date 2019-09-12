package mb00.android.codehub.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentHomePulseBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel
import mb00.android.codehub.ui.universaladapter.PulseAdapter
import timber.log.Timber

/**
 * Fragment containing user pulse; launched from [HomeFragmentPagerAdapter]
 */

class HomePulseFragment : BaseBindingFragment<FragmentHomePulseBinding, HomeViewModel>() {

    private var userName: String? = null
    private var authHeader: String? = null

    override fun layout(): Int {
        return R.layout.fragment_home_pulse
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userName = if (arguments != null) {
            arguments?.getString(BundleKeys.USER_NAME)
        } else {
            preferences?.getString(PreferenceKeys.USER_NAME, "")
        }
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()

        if (viewModel == null) {
            Toast.makeText(activity, "viewmodel null", Toast.LENGTH_SHORT).show()
        }

        binding.userPulseSwipeRefreshLayout.setOnRefreshListener {
            userPulseCall(authHeader, userName)
            binding.userPulseSwipeRefreshLayout.isRefreshing = false
        }
        userPulseCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userPulseRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userPulseRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun userPulseCall(header: String?, user: String?) {
        disposables.add(viewModel.loadUserPulse(header!!, user!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userPulse ->
                    if (userPulse.isNotEmpty()) {
                        val pulseAdapter = PulseAdapter(userPulse, activity!!)
                        binding.userPulseRecyclerView.adapter = pulseAdapter
                    } else {
                        binding.noUserPulseTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) }))
    }

}
