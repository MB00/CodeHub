package mb00.android.codehub.ui.search.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import mb00.android.codehub.R
import mb00.android.codehub.api.builder.RetrofitBuilder
import mb00.android.codehub.api.service.GitHubService
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.ui.user.adapter.UserAdapter
import retrofit2.Retrofit


// not currently utilized

class SearchCodeFragment : Fragment() {

    /*private var code: String? = null
    private var searchUsersRecyclerView: RecyclerView? = null
    private val searchUsersAdapter: UserAdapter? = null
    private var noUserResultsTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val searchUsersView = inflater.inflate(R.layout.fragment_search_code, container, false)
        searchUsersRecyclerView = searchUsersView.findViewById(R.id.search_codes_recycler_view)
        searchUsersRecyclerView.layoutManager = LinearLayoutManager(activity)
        noUserResultsTextView = searchUsersView.findViewById(R.id.no_code_results_text_view)

        code = arguments.getString(BundleKeys.SEARCH_QUERY_KEY)

        if (code == null) {
            noUserResultsTextView.visibility = View.VISIBLE
        } else {
            codeCall(code)
        }

        return searchUsersView
    }

    private fun codeCall(code: String) {
        val retrofit = RetrofitBuilder.getInstance()
        val service = retrofit.create(GitHubService::class.java)
    }*/

}
