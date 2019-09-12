package mb00.android.codehub.ui.gist.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentGistFilesBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.gist.adapter.GistFileAdapter
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter
import mb00.android.codehub.ui.gist.viewmodel.GistViewModel
import timber.log.Timber
import java.util.*

/**
 * Fragment containing gist files; launched from [GistFragmentPagerAdapter]
 */

class GistFilesFragment : BaseBindingFragment<FragmentGistFilesBinding, GistViewModel>() {

    private var authHeader: String? = null
    private lateinit var gistId: String

    override fun layout(): Int {
        return R.layout.fragment_gist_files
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
        gistId = arguments?.getString(BundleKeys.GIST_ID) ?: ""

        initRecyclerView()
        initSwipeRefreshLayout()

        gistFilesCall(authHeader, gistId)
    }

    private fun initRecyclerView() {
        binding.gistFilesRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.gistFilesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.gistFilesSwipeRefreshLayout.setOnRefreshListener {
            gistFilesCall(authHeader, gistId)
            binding.gistFilesSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun gistFilesCall(header: String?, gist: String?) {
        disposables.add(viewModel.loadGistFiles(header!!, gist!!)
                .subscribe({ gistFile ->
                    val gistFileList = ArrayList(gistFile.files?.values ?: Collections.emptyList())

                    if (gistFileList.isNotEmpty()) {
                        binding.gistFilesRecyclerView.adapter = GistFileAdapter(gistFileList, binding.gistFilesRecyclerView, authHeader!!, gistId)
                    } else {
                        binding.noGistFilesTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
