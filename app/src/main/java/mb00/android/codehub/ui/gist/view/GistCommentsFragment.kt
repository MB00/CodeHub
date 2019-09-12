package mb00.android.codehub.ui.gist.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentGistCommentsBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter
import mb00.android.codehub.ui.gist.viewmodel.GistViewModel
import mb00.android.codehub.ui.universaladapter.CommentAdapter
import timber.log.Timber

/**
 * Fragment containing gist comments; launched from [GistFragmentPagerAdapter]
 */

class GistCommentsFragment : BaseBindingFragment<FragmentGistCommentsBinding, GistViewModel>() {

    private lateinit var authHeader: String
    private lateinit var gistId: String

    override fun layout(): Int {
        return R.layout.fragment_gist_comments
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
        gistId = arguments?.getString(BundleKeys.GIST_ID) ?: ""

        initRecyclerView()
        initSwipeRefreshLayout()

        gistCommentsCall(authHeader, gistId)
    }

    private fun initRecyclerView() {
        binding.gistCommentsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.gistCommentsRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.gistCommentsSwipeRefreshLayout.setOnRefreshListener {
            gistCommentsCall(authHeader, gistId)
            binding.gistCommentsSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun gistCommentsCall(header: String, gist: String) {
        disposables.add(viewModel.loadGistComments(header, gist)
                .subscribe({ gistCommentList ->
                    if (gistCommentList.isNotEmpty()) {
                        binding.gistCommentsRecyclerView.adapter = CommentAdapter(gistCommentList, activity!!)
                    } else {
                        binding.noGistCommentsTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
