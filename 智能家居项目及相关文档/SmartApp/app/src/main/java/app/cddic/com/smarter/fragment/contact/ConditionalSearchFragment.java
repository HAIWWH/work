package app.cddic.com.smarter.fragment.contact;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.ContactActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.fragment.contact.AddContactFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by VileWind on 2017/5/9 0009.
 */

public class ConditionalSearchFragment extends BaseFragment {

    private TopView mTopView;
    private SearchView mSearchView;
    private Button PSbtn;
    private Button Tenementbtn;
    private Button Unlimitbtn;
    private ImageButton Professionbtn;
    private ImageButton Locationbtn;
    private ImageButton Agebtn;

    @Override
    protected void initViews() {
        mTopView=(TopView)mView.findViewById(R.id.conditional_search_TopView);
        mTopView.setText("返回","按条件查找",null);

        mSearchView=(SearchView)mView.findViewById(R.id.add_contact_searchView);
        PSbtn=(Button) mView.findViewById(R.id.public_security_btn);
        Tenementbtn=(Button)mView.findViewById(R.id.tenement_btn);
        Unlimitbtn=(Button)mView.findViewById(R.id.unlimited_btn);
        Professionbtn= (ImageButton) mView.findViewById(R.id.profession_btn);
        Locationbtn=(ImageButton) mView.findViewById(R.id.location_btn);
        Agebtn=(ImageButton)mView.findViewById(R.id.age_btn);

    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContactActivity) mActivity).replaceCurrentFragment(
                        new AddContactFragment());
            }
        },null);

        PSbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保安
            }
        });
        Tenementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //物业
            }
        });
        Unlimitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不限
            }
        });

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_conditional_search;
    }
}
