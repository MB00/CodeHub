package mb00.android.codehub.ui.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import javax.inject.Inject;

import mb00.android.codehub.BR;
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;


public abstract class BaseBindingActivity<B extends ViewDataBinding, V extends BaseViewModel> extends BaseActivity {

    private B binding;

    @Inject
    V viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();
    }

    private void setBinding() {
        binding = DataBindingUtil.setContentView(this, layout());
        binding.setVariable(BR.viewModel, viewModel);
    }

    @LayoutRes
    protected abstract int layout();

    protected B getBinding() {
        return binding;
    }

    protected V getViewModel() {
        return viewModel;
    }

}
