package songmofan.baway.com.recyclerviewdemol;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwip;
    private LinearLayoutManager                                                                                                                                                                                                                                                                                                                                             layoutManager;
    private MyAdapter mAdapter;
    private List<String> mDatas;
    private int loadMoreNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();


    }

    private void initView() {
        //初始化view ，设置适配器
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSwip = (SwipeRefreshLayout) findViewById(R.id.swip);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new MyAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLisener(new MyAdapter.OnItemClickLisener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点到了 ："+mDatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        //上拉加载更多

        mRecyclerView.addOnScrollListener(new LoadMoreScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                mSwip.setRefreshing(true);
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add("哼哼哼!我是上拉加载更多出来的数据"+loadMoreNum);
                        mAdapter.notifyDataSetChanged();
                        mSwip.setRefreshing(false);
                        loadMoreNum++;
                    }
                },1000);
            }
        });

     //下拉刷新
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //实际开发中，我们在这里发送请求获取数据
                mDatas.add(0,"嘿嘿!我是下拉刷新的数据!");
                mAdapter.notifyDataSetChanged();
                mSwip.setRefreshing(false);
            }
        });

    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}
