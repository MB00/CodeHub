package mb00.android.codehub.ui.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import mb00.android.codehub.BR;
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;


public abstract class BaseBindingFragment<B extends ViewDataBinding, V extends BaseViewModel> extends BaseFragment {

    private B binding;

    @Inject
    V viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setBinding(inflater, container);

        return binding.getRoot();
    }

    private void setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false);
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