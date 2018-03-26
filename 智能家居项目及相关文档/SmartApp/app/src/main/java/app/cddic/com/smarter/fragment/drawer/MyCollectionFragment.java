package app.cddic.com.smarter.fragment.drawer;


import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.MyCollectionItemsAdapter;
import app.cddic.com.smarter.entity.MyCollectionMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by 小帆哥 on 2017/5/9.
 */

public class MyCollectionFragment extends BaseFragment {
    private ListView mListView;
    private TopView mTopView;
    private  List<MyCollectionMSG> myCollectionMSGList = new ArrayList<>();


    @Override
    protected void initViews() {
        mListView = (ListView)mView.findViewById(R.id.myCollection_listView);
        mTopView =findView(R.id.topView);
        mTopView.setText("设置", "我的收藏", null);
    }

    @Override
    protected void setupAdapters() {
        for(int i=0;i<3;i++) {
            MyCollectionMSG item = null;
            switch (i) {
                case 0:
                    item = new MyCollectionMSG("张 三001", "账号密码:165442sdsdsdjhxcuds 车牌号5464 验证码DSFKHS-DSFJBDS-DSFBS-DS", "2016/8/2 12:02");
                    break;
                case 1:
                    item = new MyCollectionMSG("李 四002", "账号密码:165442sdsdsdjhxcuds 车牌号5464 验证码DSFKHS-DSFJBDS-DSFBS-DS", "2016/8/2 12:02");
                    break;
                case 2:
                    item = new MyCollectionMSG("王 五003", "账号密码:165442sdsdsdjhxcuds 车牌号5464 验证码DSFKHS-DSFJBDS-DSFBS-DS", "2016/8/2 12:02");
                    break;
            }
           myCollectionMSGList.add(item);
        }


        MyCollectionItemsAdapter myCollectionItemsAdapter = new MyCollectionItemsAdapter(getActivity(), myCollectionMSGList);
        mListView.setAdapter(myCollectionItemsAdapter);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        }, null);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_my_collection;
    }
}