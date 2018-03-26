package app.cddic.com.smarter.fragment.message;

import android.view.View;
import android.widget.TabHost;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/6/15.
 */

public class ChatFileFragment extends BaseFragment {
    private TopView mTopView;
    private TabHost mTabHost;
    @Override
    protected void initViews() {
        mTopView = (TopView)findView(R.id.chatFile_topView);
        mTopView.setText("返回","聊天记录",null);

        mTabHost = (TabHost)findView(R.id.chatFile_tabHost);
        mTabHost.setup();

        mTabHost.addTab(mTabHost.newTabSpec("PicturesOrVideo").setIndicator("图片/视频").setContent(R.id.PicturesOrVideo));
        mTabHost.addTab(mTabHost.newTabSpec("join").setIndicator("衔接").setContent(R.id.join));
        mTabHost.addTab(mTabHost.newTabSpec("other").setIndicator("其他").setContent(R.id.other));
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);


    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_chat_file;
    }
}
