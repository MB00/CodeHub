package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoPulseBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoPulseViewModel
import mb00.android.codehub.ui.universaladapter.PulseAdapter
import timber.log.Timber

/**
 * Fragment containing repository pulse; launched from [RepoFragmentPagerAdapter]
 */

class RepoPulseFragment : BaseBindingFragment<FragmentRepoPulseBinding, RepoPulseViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_pulse
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

        initRecyclerView()
        initSwipeRefreshLayout()

        repoPulseCall(authHeader, userName, repoName)
    }

    private fun initRecyclerView() {
        binding.repoPulseRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoPulseRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoPulseSwipeRefreshLayout.setOnRefreshListener {
            repoPulseCall(authHeader, userName, repoName)
            binding.repoPulseSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoPulseCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoPulse(header, user, repo)
                .subscribe({ repoPulseList ->
                    if (repoPulseList.isNotEmpty()) {
                        val pulseAdapter = PulseAdapter(repoPulseList, activity!!)
                        binding.repoPulseRecyclerView.adapter = pulseAdapter
                    } else {
                        binding.noRepoPulseTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
