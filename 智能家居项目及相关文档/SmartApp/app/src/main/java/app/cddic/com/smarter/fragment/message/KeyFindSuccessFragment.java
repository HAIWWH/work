package app.cddic.com.smarter.fragment.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;


/**
 * Created by asus on 2017/3/28.
 */

public class KeyFindSuccessFragment extends BaseFragment {
    private Button mButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "KeyFindSuccess create", Toast.LENGTH_SHORT).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {
        mButton = (Button)mView.findViewById(R.id.returnLogin_btn);

    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_key_find_success;
    }
}
