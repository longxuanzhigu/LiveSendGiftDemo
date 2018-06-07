package com.showly.livesendgiftdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.showly.livesendgiftdemo.bean.ClientGiftInfos;
import com.showly.livesendgiftdemo.util.MagicTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FragmentGiftDialog.OnGiftSendListener {

    private Button sendBtn;
    private List<ClientGiftInfos> giftDataBeen = new ArrayList<>();
    private String[] stringArrayb;
    private String[] stringArraym;
    private String[] stringArrays;
    public int currentPosition;
    private LinearLayout llgiftcontent;
    private TranslateAnimation inAnim;
    private TranslateAnimation outAnim;
    private NumAnim giftNumAnim;
    private List<View> giftViewCollection = new ArrayList<View>();
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if (y1 - y2 > 50) {
                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if (y2 - y1 > 50) {
                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if (x1 - x2 > 50) {
                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if (x2 - x1 > 50) {
                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onTouchEvent(event);
    }

    private void initData() {
        stringArrayb = getResources().getStringArray(R.array.gift_b);
        stringArraym = getResources().getStringArray(R.array.gift_m);
        stringArrays = getResources().getStringArray(R.array.gift_s);

        for (int i = 0; i < 8; i++) {
            ClientGiftInfos infosData = new ClientGiftInfos();
            infosData.setEffect_type(1);
            infosData.setGift_id(i);
            infosData.setGiver_exp(50);
            infosData.setIn_use(1);
            infosData.setMiddle_pic(stringArraym[i]);
            infosData.setName("sdfd");
            infosData.setPic(stringArrayb[i]);
            infosData.setPrice(20);
            infosData.setPrice_type(0);
            infosData.setRecipient_exp(100);
            infosData.setSmall_pic(stringArrays[i]);
            infosData.setState(1);
            infosData.setType(0);
            infosData.setWeight(i);
            giftDataBeen.add(infosData);
        }
    }

    private void initListener() {
        sendBtn.setOnClickListener(this);
    }

    private void initView() {
        sendBtn = (Button) findViewById(R.id.btn_send_gift);
        //礼物列表
        llgiftcontent = (LinearLayout) findViewById(R.id.llgiftcontent);
        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gift_in);
        outAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gift_out);
        giftNumAnim = new NumAnim();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_gift:
                showGift();
                break;
        }
    }

    private void showGift() {//显示礼物界面
        FragmentGiftDialog fragmentGiftDialog = FragmentGiftDialog.newInstance();
        fragmentGiftDialog.setGiftData(giftDataBeen);//传递礼物数据
        fragmentGiftDialog.setOnGridViewClickListener(new FragmentGiftDialog.OnGridViewClickListener() {

            @Override
            public void click(ClientGiftInfos gift, int position) {//连击
                currentPosition = position;
                if (gift.getEffect_type() == 1) {
                    showGiftList(gift, position);
                }
            }

            @Override
            public void onceClick(ClientGiftInfos gift, int position) {//点击一次
                if (gift.getEffect_type() == 1) {
                    showOnceGiftList(gift, position + "");
                } else if (gift.getEffect_type() == 2) {
                    //showAllAnimation(gift);
                }
            }

            @Override
            public void GetPayWebView() {//跳转到支付页面
                Toast.makeText(getApplicationContext(), "功能暂未开放", Toast.LENGTH_SHORT).show();

            }
        }).show(getFragmentManager(), "dialog");

        fragmentGiftDialog.setmOnGiftSendListener(this);
    }


    private void showOnceGiftList(ClientGiftInfos gift, String tag) {
        View giftView11 = llgiftcontent.findViewWithTag(tag);
        if (giftView11 == null) {
            I8SP.setSelectGiftTag(getApplicationContext(), tag);
        } else if (tag.equals(I8SP.getSelectGiftTag(getApplicationContext()))) {
            I8SP.setSelectGiftTag(getApplicationContext(), tag + 00);
        } else {
            I8SP.setSelectGiftTag(getApplicationContext(), tag + 11);
        }

        View giftView = llgiftcontent.findViewWithTag(I8SP.getSelectGiftTag(getApplicationContext()));

        if (llgiftcontent.getChildCount() > 2) {//如果正在显示的礼物的个数超过两个，那么就移除最后一次更新时间比较长的
            View giftView1 = llgiftcontent.getChildAt(0);
            final TextView picTv1 = (TextView) giftView1.findViewById(R.id.gift_usernickname_tv);
            long lastTime1 = (Long) picTv1.getTag();
            View giftView2 = llgiftcontent.getChildAt(1);
            final TextView picTv2 = (TextView) giftView2.findViewById(R.id.gift_usernickname_tv);
            long lastTime2 = (Long) picTv2.getTag();
            View giftView3 = llgiftcontent.getChildAt(2);
            final TextView picTv3 = (TextView) giftView3.findViewById(R.id.gift_usernickname_tv);
            long lastTime3 = (Long) picTv3.getTag();

            if (lastTime1 > lastTime2 && lastTime1 > lastTime3) {
                removeGiftView(2);
            } else if (lastTime3 > lastTime1 && lastTime3 > lastTime2) {
                removeGiftView(0);
            } else{
                removeGiftView(1);
            }
        }

        giftView = addGiftView();/*获取礼物的View的布局*/
        giftView.setTag(tag);/*设置view标识*/
        giftView.setTag(I8SP.getSelectGiftTag(getApplicationContext()));

        final ImageView animationGift = (ImageView) giftView.findViewById(R.id.animation_gift);
        final TextView tvUserName = (TextView) giftView.findViewById(R.id.gift_usernickname_tv);
        final TextView tvGiftName = (TextView) giftView.findViewById(R.id.gift_usersign_tv);
        final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
        giftNum.setText("x1");/*设置礼物数量*/
        tvUserName.setTag(System.currentTimeMillis());
        giftNum.setTag(1);/*给数量控件设置标记*/
        tvUserName.setText(gift.getName());
        tvGiftName.setText(gift.getName());
        Glide.with(getApplicationContext())
                .load("file:///android_asset/" + gift.getMiddle_pic())
                .into(animationGift);

        llgiftcontent.addView(giftView);/*将礼物的View添加到礼物的ViewGroup中*/
        llgiftcontent.invalidate();/*刷新该view*/
        giftView.startAnimation(inAnim);/*开始执行显示礼物的动画*/

        inAnim.setAnimationListener(new Animation.AnimationListener() {/*显示动画的监听*/
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                giftNumAnim.start(giftNum);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    /**
     * 删除礼物view
     */
    private void removeGiftView(final int index) {
        final View removeView = llgiftcontent.getChildAt(index);
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llgiftcontent.removeViewAt(index);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeView.startAnimation(outAnim);
            }
        });
    }

    /**
     * 添加礼物view,(考虑垃圾回收)
     */
    private View addGiftView() {
        View view = null;
        if (giftViewCollection.size() <= 0) {
            /*如果垃圾回收中没有view,则生成一个*/
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_gift, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = 10;
            view.setLayoutParams(lp);
            llgiftcontent.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View view) {
                }

                @Override
                public void onViewDetachedFromWindow(View view) {
                    giftViewCollection.add(view);
                }
            });
        } else {
            view = giftViewCollection.get(0);
            giftViewCollection.remove(view);
        }
        return view;
    }


    /**
     * 数字放大动画
     */
    public class NumAnim {
        private Animator lastAnimator = null;

        public void start(View view) {
            if (lastAnimator != null) {
                lastAnimator.removeAllListeners();
                lastAnimator.end();
                lastAnimator.cancel();
            }
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1.3f, 1.0f);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1.3f, 1.0f);
            AnimatorSet animSet = new AnimatorSet();
            lastAnimator = animSet;
            animSet.setDuration(200);
            animSet.setInterpolator(new OvershootInterpolator());
            animSet.playTogether(anim1, anim2);
            animSet.start();
        }
    }

    /**
     * 显示礼物的方法
     */
    private void showGiftList(ClientGiftInfos gift, int tag) {

        View giftView = llgiftcontent.findViewWithTag(I8SP.getSelectGiftTag(getApplicationContext()));
        if (giftView != null) {
            TextView tvUserName = (TextView) giftView.findViewById(R.id.gift_usernickname_tv);
            MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);
            int showNum = (Integer) giftNum.getTag() + 1;
            giftNum.setText("x" + showNum);
            giftNum.setTag(showNum);
            tvUserName.setTag(System.currentTimeMillis());
            giftNumAnim.start(giftNum);
        }
    }

    @Override
    public void onGiftSend(ClientGiftInfos giftInfos, int position) {

    }
}
