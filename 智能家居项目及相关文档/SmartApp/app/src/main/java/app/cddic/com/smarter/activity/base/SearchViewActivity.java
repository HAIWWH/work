package app.cddic.com.smarter.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.SearchViewChatAdapter;
import app.cddic.com.smarter.adapter.SearchViewDeviceAdapter;
import app.cddic.com.smarter.adapter.SearchViewcontentAdapter;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by VileWind on 2017/8/2 0002.
 */

public class SearchViewActivity extends BaseActivity {
    private TopView mTopView;
    private ListView mSearchViewDeviceLV;
    private ListView mSearchViewContentLV;
    private ListView mSearchViewChatLV;

    protected void initViews() {
        mTopView = (TopView) findViewById(R.id.search_view_topview);
        mTopView.setText("返回","搜索结果",null);
        mSearchViewDeviceLV = (ListView)findViewById(R.id.searchview_device_listview);
        mSearchViewContentLV = (ListView)findViewById(R.id.searchview_contact_list);
        mSearchViewChatLV =(ListView)findViewById(R.id.searchview_chat_listview);

    }
    protected void setupAdapters() {
        SearchViewDeviceAdapter searchViewDeviceAdapter = new SearchViewDeviceAdapter(this);
        mSearchViewDeviceLV.setAdapter(searchViewDeviceAdapter);

        SearchViewcontentAdapter searchViewcontentAdapter = new SearchViewcontentAdapter(this);
        mSearchViewContentLV.setAdapter(searchViewcontentAdapter);

        SearchViewChatAdapter searchViewChatAdapter = new SearchViewChatAdapter(this);
        mSearchViewChatLV.setAdapter(searchViewChatAdapter);
    }


    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        },null);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        initViews();
        setupAdapters();
        setupListeners();
    }


    @Override
    public void onHandleMsg(int MsgType) {

    }
}
