package songmofan.baway.com.recyclerviewdemol;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 类的作用：
 * <p>
 * 作者： 宋莫凡
 * <p>
 * 思路：
 * <p>
 * on 2017/8/15 14
 */

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;
    private int totalItemCount;
    private int visibleItemCount;
    private int lastVisibleItemPosition;
    private boolean isLoading  = false;//控制不要重复加载更多
    private int previousTotal;

    public LoadMoreScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLinearLayoutManager.getItemCount();
        visibleItemCount = mLinearLayoutManager.getChildCount();
        lastVisibleItemPosition
                = mLinearLayoutManager.findLastVisibleItemPosition();

        if (isLoading) {
            if (totalItemCount > previousTotal) {//说明数据已经加载结束
                isLoading = false;
                previousTotal = totalItemCount;
            }
        }

        if (visibleItemCount > 0  && !isLoading
                && lastVisibleItemPosition >= totalItemCount - 1//最后一个item可见
                && totalItemCount > visibleItemCount) {//数据不足一屏幕不触发加载更多
            onLoadMore();
            isLoading = true;
        }
    }
    public abstract void onLoadMore();
}
