package mb00.android.codehub.ui.base.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import mb00.android.codehub.BR
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject


abstract class BaseBindingActivity<B : ViewDataBinding, V : BaseViewModel> : BaseActivity() {

    protected lateinit var binding: B

    @Inject
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, layout())
        binding.setVariable(BR.viewModel, viewModel)
    }

    @LayoutRes
    protected abstract fun layout(): Int

}
