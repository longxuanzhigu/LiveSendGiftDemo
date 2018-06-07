package com.showly.livesendgiftdemo;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.showly.livesendgiftdemo.bean.ClientGiftInfos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * author：LY on 2017/8/11
 * description:文件说明
 * version:版本
 */
public class FragmentGiftDialog extends android.app.DialogFragment {
    private Dialog dialog;
    private ViewPager vp;
    private List<View> gridViews;
    private LayoutInflater layoutInflater;
    private List<ClientGiftInfos> giftHotInfos = new ArrayList<>();//热门礼物列表
    private List<ClientGiftInfos> welfareGiftInfos = new ArrayList<>();//福利礼物列表
    //private I8ShowIMSign.Data diamondData;
    private String pusherId;
    private RadioGroup radio_group;
    private RelativeLayout rlSentBtn;
    private RelativeLayout rlSentBigBtn;
    private TextView tvTime;
    private TextView tvGiftMoney;
    private TextView tvHotGift;
    private TextView tvWelfareGift;
    private int currentPosition;
    private OnGiftSendListener mOnSendGiftListener;
    private ImageView tvGiftMoneyImg;
    private GiftGridViewAdapter myGridViewAdapter1;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.common_gift_dialog, null, false);
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        initDialogStyle(rootView);
        initView(rootView);
        return dialog;
    }

    private void initView(View rootView) {
        Bundle args = getArguments();
        if (args == null)
            return;

        layoutInflater = getActivity().getLayoutInflater();
        rlSentBtn = (RelativeLayout) rootView.findViewById(R.id.rl_sent_btn);//发送按钮
        rlSentBigBtn = (RelativeLayout) rootView.findViewById(R.id.rl_sent_big_btn);//连发按钮
        tvTime = (TextView) rootView.findViewById(R.id.tv_time);//时间倒计时
        tvGiftMoney = (TextView) rootView.findViewById(R.id.tv_gift_money);//钻石数
        tvHotGift = (TextView) rootView.findViewById(R.id.tv_hot_gift);//热门礼物
        tvWelfareGift = (TextView) rootView.findViewById(R.id.tv_fuli_gift);//热门礼物
        //钻石数
        tvGiftMoneyImg = (ImageView) rootView.findViewById(R.id.iv_gift_money_img);
        vp = (ViewPager) rootView.findViewById(R.id.view_pager);
        radio_group = (RadioGroup) rootView.findViewById(R.id.radio_group);
        RadioButton radioButton = (RadioButton) radio_group.getChildAt(0);
        radioButton.setChecked(true);//设置第一个点为选中状态

        initViewPager(giftHotInfos);//初始化礼物列表
        tvHotGift.setTextColor(Color.parseColor("#D954E5"));

        tvHotGift.setOnClickListener(new View.OnClickListener() {//点击热门
            @Override
            public void onClick(View v) {
                initViewPager(giftHotInfos);
                tvHotGift.setTextColor(Color.parseColor("#D954E5"));
                tvWelfareGift.setTextColor(Color.parseColor("#FFFFFF"));
                tvGiftMoneyImg.setBackgroundResource(R.drawable.i8live_zuanshi);//钻石图标
                rlSentBigBtn.setVisibility(View.GONE);
                rlSentBtn.setVisibility(View.VISIBLE);
                rlSentBtn.setBackgroundResource(R.drawable.i8show_sent_bg);
            }
        });

        tvWelfareGift.setOnClickListener(new View.OnClickListener() {//点击福利
            @Override
            public void onClick(View v) {
                initViewPager(welfareGiftInfos);
                tvHotGift.setTextColor(Color.parseColor("#FFFFFF"));
                tvWelfareGift.setTextColor(Color.parseColor("#D954E5"));
                tvGiftMoneyImg.setBackgroundResource(R.drawable.i8live_zuanshi);//家币图标
                rlSentBigBtn.setVisibility(View.GONE);
                rlSentBtn.setVisibility(View.VISIBLE);
                rlSentBtn.setBackgroundResource(R.drawable.i8show_sent_bg);
            }
        });

    }

    public void initViewPager(final List<ClientGiftInfos> giftInfos) {
        gridViews = new ArrayList<View>();

        ///定义第一个GridView
        GridView gridView1 = (GridView) layoutInflater.inflate(R.layout.gift_fragment_home_new, null);
        myGridViewAdapter1 = new GiftGridViewAdapter(getActivity(), 0, 10);
        myGridViewAdapter1.setGifts(giftInfos);
        gridView1.setAdapter(myGridViewAdapter1);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (giftInfos.get(position).getType() == 2) {
                    I8SP.setGiftType(getActivity(), 2);
                } else if (giftInfos.get(position).getType() == 1) {
                    I8SP.setGiftType(getActivity(), 1);
                } else if (giftInfos.get(position).getType() == 0) {
                    I8SP.setGiftType(getActivity(), 0);
                }

                currentPosition = position;//获取当前点击的位置
                if (I8SP.getPosition(getActivity()) == position) {
                    if (!I8SP.getIsChecked(getActivity())) {
                        rlSentBtn.setBackgroundResource(R.drawable.i8show_send_btn);
                        I8SP.setIsChecked(getActivity(), true);
                        I8SP.setIsBg(getActivity(), false);
                        I8SP.setIsClick(getActivity(), true);
                    } else {
                        rlSentBtn.setBackgroundResource(R.drawable.i8show_sent_bg);
                        I8SP.setIsChecked(getActivity(), false);
                        I8SP.setIsBg(getActivity(), true);
                        I8SP.setIsClick(getActivity(), false);
                    }

                } else {
                    rlSentBtn.setBackgroundResource(R.drawable.i8show_send_btn);
                    rlSentBigBtn.setVisibility(View.GONE);
                    rlSentBtn.setVisibility(View.VISIBLE);
                    rlSentBtn.setClickable(true);
                    I8SP.setIsBg(getActivity(), false);
                    I8SP.setIsChecked(getActivity(), true);
                    I8SP.setIsClick(getActivity(), true);
                }

                myGridViewAdapter1.setSeclection(position);
                myGridViewAdapter1.notifyDataSetChanged();
            }
        });

       /* ///定义第二个GridView
        GridView gridView2 = (GridView)
                layoutInflater.inflate(R.layout.gift_fragment_home_new, null);
        GiftGridViewAdapter myGridViewAdapter2 = new GiftGridViewAdapter(getActivity(), 0, 10);
        myGridViewAdapter2.setGifts(giftInfos);
        gridView2.setAdapter(myGridViewAdapter2);
        myGridViewAdapter2.setOnGridViewClickListener(new GiftGridViewAdapter.OnGridViewClickListener() {
            @Override
            public void click(ClientGiftInfos gift, int position) {
                if (onGridViewClickListener != null) {
                    onGridViewClickListener.click(gift, position);
                }
            }
        });
        ///定义第三个GridView
        GridView gridView3 = (GridView)
                layoutInflater.inflate(R.layout.gift_fragment_home_new, null);
        GiftGridViewAdapter myGridViewAdapter3 = new GiftGridViewAdapter(getActivity(), 0, 10);
        myGridViewAdapter3.setGifts(giftInfos);
        gridView3.setAdapter(myGridViewAdapter3);
        myGridViewAdapter3.setOnGridViewClickListener(new GiftGridViewAdapter.OnGridViewClickListener() {
            @Override
            public void click(ClientGiftInfos gift, int position) {
                if (onGridViewClickListener != null) {
                    onGridViewClickListener.click(gift, position);
                }
            }
        });*/
        gridViews.add(gridView1);
      /*  gridViews.add(gridView2);
        gridViews.add(gridView3);*/

        ///定义viewpager的PagerAdapter
        vp.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return gridViews.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(gridViews.get(position));
                //super.destroyItem(container, position, object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(gridViews.get(position));
                return gridViews.get(position);
            }
        });
        ///注册viewPager页选择变化时的响应事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton)
                        radio_group.getChildAt(position);
                radioButton.setChecked(true);
            }
        });


        rlSentBtn.setOnClickListener(new View.OnClickListener() {//发送按钮
            @Override
            public void onClick(View v) {
                if (mOnSendGiftListener != null) {
                    onGridViewClickListener.onceClick(giftInfos.get(currentPosition), currentPosition);
                }
            }
        });

        rlSentBigBtn.setOnClickListener(new View.OnClickListener() {//连击按钮
            @Override
            public void onClick(View v) {
                if (mOnSendGiftListener != null) {
                    mOnSendGiftListener.onGiftSend(giftInfos.get(currentPosition), currentPosition);
                }
            }
        });

        //钻石余额处理
        if (I8SP.getGiftType(getActivity()) == 0) {//0是热门 , 1是福利
            tvGiftMoney.setText(I8SP.getDiamonds(getActivity()) + "");
        } else if (I8SP.getGiftType(getActivity()) == 1) {
            tvGiftMoney.setText(I8SP.getHomeCoin(getActivity()) + "");
        }

        tvGiftMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onGridViewClickListener.GetPayWebView();
            }
        });

        tvGiftMoneyImg.setOnClickListener(new View.OnClickListener() {//钻石图标按钮
            @Override
            public void onClick(View v) {
                onGridViewClickListener.GetPayWebView();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void isRepateOrFullScream(List<ClientGiftInfos> giftInfos) {

        if (giftInfos.get(currentPosition).getState() == 2) {//连发
            if (onGridViewClickListener != null) {
                if (I8SP.getIsClick(getActivity())) {
                    onGridViewClickListener.onceClick(giftInfos.get(currentPosition), currentPosition);
                    rlSentBtn.setVisibility(View.GONE);
                    rlSentBtn.setClickable(false);
                    rlSentBigBtn.setVisibility(View.VISIBLE);
                    timer.start();
                }
            }

            if (I8SP.getIsLive(getActivity())) {
                if (mOnSendGiftListener != null) {
                    mOnSendGiftListener.onGiftSend(giftInfos.get(currentPosition), currentPosition);
                }
            }

        } else {//全屏
            if (onGridViewClickListener != null) {
                if (I8SP.getIsClick(getContext())) {
                    onGridViewClickListener.onceClick(giftInfos.get(currentPosition), currentPosition);
                }
            }

            if (I8SP.getIsLive(getActivity())) {
                if (mOnSendGiftListener != null) {
                    mOnSendGiftListener.onGiftSend(giftInfos.get(currentPosition), currentPosition);
                }
            }
        }
    }

    private CountDownTimer timer = new CountDownTimer(7000, 1000) {//倒计时处理

        @Override
        public void onTick(long millisUntilFinished) {
            if ((millisUntilFinished / 1000) > 0) {
                tvTime.setText(((millisUntilFinished / 1000) - 1) + "");
            }
        }

        @Override
        public void onFinish() {
            rlSentBtn.setVisibility(View.VISIBLE);
            rlSentBtn.setClickable(true);
            rlSentBigBtn.setVisibility(View.GONE);
            timer.cancel();
        }
    };

    public static final FragmentGiftDialog newInstance() {
        FragmentGiftDialog fragment = new FragmentGiftDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public OnGridViewClickListener onGridViewClickListener;

    public FragmentGiftDialog setOnGridViewClickListener(OnGridViewClickListener onGridViewClickListener) {
        this.onGridViewClickListener = onGridViewClickListener;
        return this;
    }

    public void setmOnGiftSendListener(OnGiftSendListener onTextSendListener) {
        this.mOnSendGiftListener = onTextSendListener;
    }

    public interface OnGridViewClickListener {
        void click(ClientGiftInfos gift, int position);

        void onceClick(ClientGiftInfos gift, int position);

        void GetPayWebView();//获取充值界面
    }

    public interface OnGiftSendListener {
        void onGiftSend(ClientGiftInfos giftInfos, int position);
    }

    private void initDialogStyle(View view) {
        dialog = new Dialog(getActivity(), R.style.CustomGiftDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        I8SP.setIsClick(getActivity(), false);//点击取消之后 , 发送按钮设置为不能点击
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
    }

    public void setGiftData(List<ClientGiftInfos> giftData) {//接收礼物数据

        if (giftData != null) {
            for (int i = 0; i < giftData.size(); i++) {
                if (giftData.get(i).getType() == 0) {//0为热门礼物列表
                    giftHotInfos.add(giftData.get(i));
                } else if (giftData.get(i).getType() == 1) {//1为福利礼物列表
                    welfareGiftInfos.add(giftData.get(i));
                } else if (giftData.get(i).getType() == 2) {//2为阳光值礼物
                    giftHotInfos.add(0, giftData.get(i));
                }
            }
        }
    }

  /*  public void setDiamondData(I8ShowIMSign.Data data) {//接收钻石数据
        this.diamondData = data;
    }*/

    public void setPusherId(String id) {//接收主播id
        this.pusherId = id;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (I8SP.getGiftType(getActivity()) == 0) {
            tvGiftMoney.setText(I8SP.getDiamonds(getActivity()) + "");
        } else if (I8SP.getGiftType(getActivity()) == 1) {
            tvGiftMoney.setText(I8SP.getHomeCoin(getActivity()) + "");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public String sign(Map<String, String> params, String appkey) {
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        List<String> list = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
            //字段值不为空串时才加入签名
            if (entry != null) {
                list.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        sb.append(appkey);
        String sign = MD5Util.encrypt(sb.toString());
        return sign;
    }
}
