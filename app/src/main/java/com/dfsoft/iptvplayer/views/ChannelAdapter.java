package com.dfsoft.iptvplayer.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dfsoft.iptvplayer.R;
import com.dfsoft.iptvplayer.manager.IPTVCategory;
import com.dfsoft.iptvplayer.manager.IPTVChannel;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {
    protected IPTVCategory mCategory = null;
    protected Context mContext;
//    private Integer cateIndex = 0;

    private int currentItem = -1;

    public IPTVCategory getCategory() {
        return mCategory;
    }

    public ChannelAdapter(Context context, @NonNull IPTVCategory category) {
        this.mContext = context;
        this.mCategory = category;
        if (category.data.size() == 0)
            currentItem = -1;
        else
            currentItem = 0;

//        this.cateIndex = cateIndex;
    }

    private boolean listViewIsFocused = false;

    public void setListViewIsFocused(boolean listViewIsFocused) {
        this.listViewIsFocused = listViewIsFocused;
    }


    public IPTVChannel getChannel() {
        if (mCategory.data.size() == 0 || currentItem == -1)
            return null;

        return this.mCategory.data.get(currentItem);
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    @Override
    public int getCount() {
        return mCategory.data.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategory.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChannelAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_channel_item, null);
            holder = new ChannelAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChannelAdapter.ViewHolder) convertView.getTag();
        }
        //设置文字 内容

        IPTVChannel channel = mCategory.data.get(position);
        String chname = String.valueOf(channel.num) + "  "+channel.name;
        holder.mTextView.setText(chname);

        if (currentItem == position) {

            //如果被点击，设置当前TextView被选中
//            holder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.colorBlue));
//            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.lightblue));

//            if (listViewIsFocused)
//                holder.mTextView.setBackground(mContext.getResources().getDrawable(R.drawable.item_left_bg));
//            else
//                holder.mTextView.setBackground(null);
            holder.mTextView.setSelected(true);
            holder.mTextView.setPressed(true);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        } else {
            //如果没有被点击，设置当前TextView未被选中
            holder.mTextView.setSelected(false);
            holder.mTextView.setPressed(false);
//            holder.mTextView.setBackground(null);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.white));
//            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.white));
//            holder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.none));
        }

        return convertView;
    }

    class ViewHolder {
        TextView mTextView;

        public ViewHolder(View convertView) {
            mTextView = (TextView) convertView.findViewById(R.id.cate_channel_id);
        }
    }

}

