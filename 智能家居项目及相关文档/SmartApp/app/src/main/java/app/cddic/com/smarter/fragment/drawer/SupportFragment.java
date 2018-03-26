package app.cddic.com.smarter.fragment.drawer;

import android.view.View;
import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by 小帆哥 on 2017/4/25.
 */

public class SupportFragment extends BaseFragment {
    private TopView mTopView;

    @Override
    protected void initViews() {
        mTopView = findView(R.id.topView);
        mTopView.setText("设置", "关于与帮助", null);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActivity().finish();
            }
        },null);
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_support;
    }
}