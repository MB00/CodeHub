package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import mb00.android.codehub.R
import mb00.android.codehub.api.model.User
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserOverviewBinding
import mb00.android.codehub.logic.utils.DateParser
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserOverviewViewModel
import timber.log.Timber

/**
 * Fragment containing user overview; launched from [UserFragmentPagerAdapter]
 */

class UserOverviewFragment : BaseBindingFragment<FragmentUserOverviewBinding, UserOverviewViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userLogin: String

    override fun layout(): Int {
        return R.layout.fragment_user_overview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
        userLogin = arguments?.getString(BundleKeys.USER_NAME) ?: ""
    }

    override fun onStart() {
        super.onStart()

        userOverViewCall(authHeader, userLogin)
    }

    private fun userOverViewCall(header: String, user: String) {
        disposables.add(viewModel.loadUserOverview(header, user)
                .subscribe({ user ->
                    setUserInfo(user)
                }, { error -> Timber.e(error.message) })
        )
    }

    private fun setUserInfo(user: User) {
        // set in layout
        val toolbarTextView = activity?.findViewById<TextView>(R.id.user_toolbar_text_view)
        val creationDate = DateParser.parseEnglish(user.creationDate)

        toolbarTextView?.text = user.login
        Glide.with(activity!!).load(user.avatarUrl).into(binding.userOverviewAvatarImageView)

        binding.userOverviewNameTextView.text = user.name
        binding.userOverviewLoginTextView.text = user.login
        binding.userOverviewCompanyTextView.text = user.company
        binding.userOverviewLocationTextView.text = user.location
        binding.userOverviewEmailTextView.text = user.email
        binding.userOverviewWebsiteTextView.text = user.website
        binding.userOverviewCreationDateTextView.text = creationDate

        if (user.name.isNullOrEmpty()) {
            binding.userOverviewNameTextView.visibility = View.GONE
        }
        if (user.company.isNullOrEmpty()) {
            binding.userOverviewCompanyTextView.visibility = View.GONE
        }
        if (user.location.isNullOrEmpty()) {
            binding.userOverviewLocationTextView.visibility = View.GONE
        }
        if (user.email.isNullOrEmpty()) {
            binding.userOverviewEmailTextView.visibility = View.GONE
        }
        if (user.website.isNullOrEmpty()) {
            binding.userOverviewWebsiteTextView.visibility = View.GONE
        }
    }

}
