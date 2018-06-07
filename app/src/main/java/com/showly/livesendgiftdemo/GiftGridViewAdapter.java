package com.showly.livesendgiftdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.showly.livesendgiftdemo.bean.ClientGiftInfos;

import java.util.List;

/**
 * author：LY on 2017/8/10
 * description:文件说明
 * version:版本
 */
///定影GridView的Adapter
public class GiftGridViewAdapter extends BaseAdapter {
    private int page;
    private int count;
    private List<ClientGiftInfos> gifts;
    private Context context;
    private int clickTemp = -1;

    public void setGifts(List<ClientGiftInfos> gifts) {//传递礼物数据集合
        this.gifts = gifts;
        notifyDataSetChanged();
    }

    public void setSeclection(int position) {//传递选中的礼物角标
        clickTemp = position;
        //I8SP.setPosition(context, position);
    }

    public GiftGridViewAdapter(Context context, int page, int count) {
        this.page = page;
        this.count = count;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (gifts != null) {
            return gifts.size();
        } else {
            return 0;
        }
    }

    @Override
    public ClientGiftInfos getItem(int position) {
        // TODO Auto-generated method stub
        return gifts.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        final ClientGiftInfos catogary = gifts.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gift_view, null);
            viewHolder.grid_fragment_home_item_img =
                    (ImageView) convertView.findViewById(R.id.grid_fragment_home_item_img);
            viewHolder.grid_fragment_home_item_txt =
                    (TextView) convertView.findViewById(R.id.grid_fragment_home_item_txt);
            viewHolder.grid_fragment_home_item_bg =
                    (ImageView) convertView.findViewById(R.id.grid_fragment_home_item_bg);
            convertView.setTag(viewHolder);
            viewHolder.grid_fragment_home_item_money =
                    (TextView) convertView.findViewById(R.id.grid_fragment_home_item_money);
            viewHolder.ivLianjiImg =
                    (ImageView) convertView.findViewById(R.id.iv_lianji);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.grid_fragment_home_item_txt.setText(catogary.getName());
        if (catogary.getType() == 0) {//钻石
            viewHolder.grid_fragment_home_item_money.setText(catogary.getPrice() + "钻");
        } else if (catogary.getType() == 1) {//家币
            viewHolder.grid_fragment_home_item_money.setText(catogary.getPrice() + "币");
        } else if (catogary.getType() == 2) {//阳光值
            viewHolder.grid_fragment_home_item_money.setText("111");
        }

        if (catogary.getState() == 2) {//连击显示
            viewHolder.ivLianjiImg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivLianjiImg.setVisibility(View.GONE);
        }

        Glide.with(context)
                .load("file:///android_asset/" + catogary.getPic())
                .into(viewHolder.grid_fragment_home_item_img);
        final ViewHolder finalViewHolder = viewHolder;


        // 点击改变选中listItem的背景框
        if (clickTemp == position) {
            if (I8SP.getIsBg(context)) {
                finalViewHolder.grid_fragment_home_item_bg.setVisibility(View.GONE);//设置选中框
                finalViewHolder.grid_fragment_home_item_txt.setTextColor(Color.parseColor("#ffffff"));
                finalViewHolder.grid_fragment_home_item_money.setTextColor(Color.parseColor("#968e9c"));
            } else {
                finalViewHolder.grid_fragment_home_item_bg.setVisibility(View.VISIBLE);//设置选中框;
                finalViewHolder.grid_fragment_home_item_txt.setTextColor(Color.parseColor("#A945AE"));
                finalViewHolder.grid_fragment_home_item_money.setTextColor(Color.parseColor("#A945AE"));

                Animation clickAnimation = AnimationUtils.loadAnimation(context, R.anim.gift_btn_crile);
                finalViewHolder.grid_fragment_home_item_img.startAnimation(clickAnimation);
            }
        } else {
            finalViewHolder.grid_fragment_home_item_bg.setVisibility(View.GONE);//设置选中框
            finalViewHolder.grid_fragment_home_item_txt.setTextColor(Color.parseColor("#ffffff"));
            finalViewHolder.grid_fragment_home_item_money.setTextColor(Color.parseColor("#968e9c"));
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView grid_fragment_home_item_img;
        public TextView grid_fragment_home_item_txt;
        public ImageView grid_fragment_home_item_bg;
        public ImageView ivLianjiImg;
        public TextView grid_fragment_home_item_money;
    }

    public OnGridViewClickListener onGridViewClickListener;

    public void setOnGridViewClickListener(OnGridViewClickListener onGridViewClickListener) {
        this.onGridViewClickListener = onGridViewClickListener;
    }

    public interface OnGridViewClickListener {
        void click(ClientGiftInfos gift, int position);
    }
}
