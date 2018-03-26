package app.cddic.com.smarter.fragment.contact;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.NewFriendItemsAdapter;
import app.cddic.com.smarter.entity.NewFriendMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


public class NewFriendsFragment extends BaseFragment {

    private FragmentManager fm;
    private Fragment fragment;
    private TopView mTopView;
    private ListView mNewFriendListView;
    private  List<NewFriendMSG> mNewFriendMSGList = new ArrayList<>();
    @Override
    protected void initViews() {
        mTopView = findView(R.id.newFriend_topView);
        mTopView.setText("返回","新朋友",null);
        mNewFriendListView = (ListView)mView.findViewById(R.id.newFriend_listView);
    }

    @Override
    protected void setupAdapters() {
        for(int i=0;i<5;i++) {
            NewFriendMSG item = null;
            switch (i) {
                case 0:
                    item = new NewFriendMSG("张师傅", "留言：我们那里一起工作", "2016/8/1 12:01", 1);
                    break;
                case 1:
                    item = new NewFriendMSG("李妍", "留言：我们那里一起工作", "2016/8/2 12:02", 0);
                    break;
                case 2:
                    item = new NewFriendMSG("妍蝉", "留言：我们那里一起工作", "2016/8/3 12:03", 1);
                    break;
                case 3:
                    item = new NewFriendMSG("民人", "留言：我们那里一起工作", "2016/8/4 12:04", 0);
                    break;
                case 4:
                    item = new NewFriendMSG("胡适", "留言：我们那里一起工作", "2016/8/5 12:05", 0);
                    break;

            }
            mNewFriendMSGList.add(item);
        }
        NewFriendItemsAdapter newFriendItemsAdapter = new NewFriendItemsAdapter(getActivity(), mNewFriendMSGList);
        mNewFriendListView.setAdapter(newFriendItemsAdapter);

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
        return  R.layout.fragment_new_friend;
    }
}
