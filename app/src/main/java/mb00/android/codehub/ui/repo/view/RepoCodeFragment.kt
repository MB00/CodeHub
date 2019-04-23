package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoCodeBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.CodeAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoCodeViewModel
import timber.log.Timber
import java.util.*

/**
 * Fragment containing repository contents; launched from [RepoFragmentPagerAdapter]
 */

class RepoCodeFragment : BaseBindingFragment<FragmentRepoCodeBinding, RepoCodeViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences!!.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = if (arguments != null) arguments.getString(BundleKeys.USER_NAME) else ""
        repoName = if (arguments != null) arguments.getString(BundleKeys.REPO_NAME) else ""
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        repoCodeCall(binding.repoCodeRecyclerView, authHeader, userName, repoName, "")

        binding.pathHomeButton.setOnClickListener {
            repoCodeCall(binding.repoCodeRecyclerView, authHeader, userName, repoName, "")
            //displayPathAsViewObjects("", view.getContext(), (ViewGroup) view.getParent());
        }
    }

    private fun initRecyclerView() {
        binding.repoCodeRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoCodeRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoCodeSwipeRefreshLayout.setOnRefreshListener {
            repoCodeCall(binding.repoCodeRecyclerView, authHeader, userName, repoName, "")
            binding.repoCodeSwipeRefreshLayout.isRefreshing = false
        }
    }

    //var codeAdapter: CodeAdapter
    //private val layoutInflater: LayoutInflater? = null

    private fun repoCodeCall(codeRecyclerView: RecyclerView, header: String?, user: String?, repo: String?, path: String) {
        disposables.add(viewModel.loadRepoCode(header!!, user!!, repo!!, path)
                .subscribe({ repoCodeList ->
                    if (repoCodeList.isNotEmpty()) {
                        val sortedCodeList = viewModel.sortCodeList(repoCodeList)
                        codeRecyclerView.adapter = CodeAdapter(viewModel, sortedCodeList, codeRecyclerView, header, user, repo)
                    } else {
                        val noRepoCodeTextView = (codeRecyclerView.parent as View).findViewById<TextView>(R.id.no_repo_code_text_view)
                        noRepoCodeTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

    // Parses the path and turns each directory into a clickable TextView
    fun displayPathAsViewObjects(path: String, context: Context, viewParent: ViewGroup) {
        val repoCodeView = layoutInflater!!.inflate(R.layout.fragment_repo_code, viewParent, false)
        val dynamicPath = repoCodeView.findViewById<LinearLayout>(R.id.repo_dynamic_path)
        val pathList = ArrayList<String>()

        var parsePosition = 0

        for (i in 0 until path.length) {
            if (path[i] == '/') {
                pathList.add(path.substring(parsePosition, i))
                if (i + 1 < path.length) {
                    parsePosition = i + 1
                }
            } else {
                pathList.add(path.substring(parsePosition, path.length))
                break
            }
        }

        for (j in pathList.indices) {
            val pathSeparator = ImageView(context)
            val pathTextView = TextView(context)

            pathSeparator.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            pathTextView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            pathSeparator.setImageResource(R.drawable.ic_chevron_right)
            pathTextView.text = pathList[j]

            dynamicPath.addView(pathSeparator)
            dynamicPath.addView(pathTextView)
        }

        //layoutInflater.inflate(R.layout.fragment_repo_code, viewParent, false);
    }

    /*public static Observable<Void> displayPathAsViewObjectsObservable(String path, Context context, ViewGroup viewParent) {
        return Observable.just(displayPathAsViewObjects(path, context, viewParent));
    }*/


}
