package mb00.android.codehub.ui.base.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mb00.android.codehub.BR
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject


abstract class BaseBindingFragment<B : ViewDataBinding, V : BaseViewModel> : BaseFragment() {

    protected lateinit var binding: B

    @Inject
    protected lateinit var viewModel: V

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setBinding(inflater, container)
        return binding.root
    }

    private fun setBinding(inflater: LayoutInflater?, container: ViewGroup?) {
        inflater?.let {
            binding = DataBindingUtil.inflate(it, layout(), container, false)
            binding.setVariable(BR.viewModel, viewModel)
        }
    }

    @LayoutRes
    protected abstract fun layout(): Int

}