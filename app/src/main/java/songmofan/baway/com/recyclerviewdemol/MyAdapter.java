package songmofan.baway.com.recyclerviewdemol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用：
 * <p>
 * 作者： 宋莫凡
 * <p>
 * 思路：
 * <p>
 * on 2017/8/14 19
 */

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyHolder>{

    private  Context mcontext;
    private  List<String> data=new ArrayList<>();
    private  OnItemClickLisener monItemClickLisener;


    public MyAdapter(Context context, List<String> list){
        this.mcontext=context;
        this.data=list;
    }
    //定义接口

    public interface OnItemClickLisener{
        void onItemClick(View view, int position);
    }
    //创建方法接口作为参数
    public void  setOnItemClickLisener(OnItemClickLisener onItemClickLisener){
        this.monItemClickLisener=onItemClickLisener;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mcontext, R.layout.layout,null);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv.setText(data.get(position));
        if (monItemClickLisener != null) {
       holder.lin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               monItemClickLisener.onItemClick(v,position);
           }
       });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final RelativeLayout lin;

        public MyHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv);
            lin = (RelativeLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}
