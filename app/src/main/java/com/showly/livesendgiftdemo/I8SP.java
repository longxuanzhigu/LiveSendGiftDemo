package com.showly.livesendgiftdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/8/14.
 */

public class I8SP {
    private static final String I8_SHOW = "i8_show";
    private static final String RECOMMEND_FILE = "recommend_file";
    private static final String ISCHECKED = "isChecked";
    private static final String POSITION = "position";
    private static final String DIAMONDS = "diamonds";
    private static final String HOMECOIN = "homeCoin";
    private static final String TAG = "tag";
    private static final String TAGPLAYER = "tagPlayer";
    private static final String TAGPUBLISHER = "tagPublisher";
    private static final String ISBG = "isBg";
    private static final String ISCLICK = "isClick";
    private static final String BEAN = "bean";
    private static String DOWNLOADBAG = "downloadbag";
    private static String GIFTTYPE = "giftType";
    private static String ATTENTIONTYPE = "attentionType";
    private static String ISATTENTION = "isAttention";
    private static String ISLIKE = "isLike";
    private static String LIKECOUNT = "likeCount";
    private static String COMMENTCOUNT = "commentCount";
    private static String VIDEOADDRESSNEW = "videoAddressNew";
    private static String ONLINEGIFT = "onlineGift";
    private static String ISNULLCOMMENTLIST = "isNullCommentList";
    private static String LASTCOMMENTTIME = "lastCommentTime";
    private static String ISGETGIFTOVER = "isGetGiftOver";
    private static String MFILEID = "fileId";
    private static String ISLIVE = "isLive";
    private static String RECOMMEND_DATAS = "recommendDatas";
    private static String ISOPENPRESSION = "isOpenPression";
    private static String LUCKMONEY = "luckMoney";
    private static String ROOMID = "roomId";
    private static String LOADMORETYPE = "loadmoreType";

    public static void setIsChecked(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISCHECKED, service);
        editor.commit();
    }

    public static boolean getIsChecked(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISCHECKED, false);
    }

    public static void setPosition(Context context, int isClose) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(POSITION, isClose);
        editor.commit();
    }

    public static int getPosition(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(POSITION, -1);
        return data;
    }

    public static void setIsBg(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISBG, service);
        editor.commit();
    }

    public static boolean getIsBg(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISBG, false);
    }

    public static void setIsClick(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISCLICK, service);
        editor.commit();
    }

    public static boolean getIsClick(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISCLICK, false);
    }

    public static void setDiamonds(Context context, int isClose) {//获取钻石的余额
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(DIAMONDS, isClose);
        editor.commit();
    }

    public static int getDiamonds(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(DIAMONDS, 0);
        return data;
    }


    public static void setHomeCoin(Context context, int count) {//获取加币的余额
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(HOMECOIN, count);
        editor.commit();
    }

    public static int getHomeCoin(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(HOMECOIN, 0);
        return data;
    }

    public static void setSelectGiftTag(Context context, String tag) {//设置选中的礼物,观众端
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(TAG, tag);
        editor.commit();
    }

    public static String getSelectGiftTag(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        String data = uiState.getString(TAG, "");
        return data;
    }

    public static void setSelectGiftTagPlayer(Context context, String tag) {//设置选中的礼物,观众接收端
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(TAGPLAYER, tag);
        editor.commit();
    }

    public static String getSelectGiftTagPlayer(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        String data = uiState.getString(TAGPLAYER, "");
        return data;
    }


    public static void setSelectGiftTagPublisher(Context context, String tagPublisher) {//设置选中的礼物,主播端
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(TAGPUBLISHER, tagPublisher);
        editor.commit();
    }

    public static String getSelectGiftTagPublisher(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        String data = uiState.getString(TAGPUBLISHER, "");
        return data;
    }

    //保存上次下载的包名
    public static void setDownloadBag(Context context, String name) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(DOWNLOADBAG, name);
        editor.commit();
    }

    public static String getDownloadBag(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getString(DOWNLOADBAG, "i8show_hahaha_text.apk");
    }

    public static void setGiftType(Context context, int type) {//获取礼物的类型
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(GIFTTYPE, type);
        editor.commit();
    }

    public static int getGiftType(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(GIFTTYPE, 0);
        return data;
    }

    public static void setAttentionType(Context context, int type) {//获取关注、粉丝、好友类型
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(ATTENTIONTYPE, type);
        editor.commit();
    }

    public static int getAttentionType(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(ATTENTIONTYPE, 0);
        return data;
    }

    //记录是否已关注
    public static void setIsAttention(Context context, int isClose) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(ISATTENTION, isClose);
        editor.commit();
    }

    public static int getIsAttention(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(ISATTENTION, -1);
        return data;
    }

    //记录是否已点赞
    public static void setIsLike(Context context, int isClose) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(ISLIKE, isClose);
        editor.commit();
    }

    public static int getIsLike(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(ISLIKE, -1);
        return data;
    }

    //记录点赞数
    public static void setLikeCount(Context context, int isClose) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(LIKECOUNT, isClose);
        editor.commit();
    }

    public static int getLikeCount(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(LIKECOUNT, -1);
        return data;
    }

    //记录评论数
    public static void setCommentCount(Context context, int isClose) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(COMMENTCOUNT, isClose);
        editor.commit();
    }

    public static int getCommentCount(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(COMMENTCOUNT, -1);
        return data;
    }

    //保存小视频地址
    public static void setVideoAddress(Context context, String tagPublisher) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(VIDEOADDRESSNEW, tagPublisher);
        editor.commit();
    }

    public static String getVideoAddress(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        String data = uiState.getString(VIDEOADDRESSNEW, "");
        return data;
    }

    public static void setOnlineGiftInfo(Context context, int type) {//获取在线礼物配置信息
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(ONLINEGIFT, type);
        editor.commit();
    }

    public static int getOnlineGiftInfo(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(ONLINEGIFT, 0);
        return data;
    }

    public static void setIsNullCommentList(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISNULLCOMMENTLIST, service);
        editor.commit();
    }

    public static boolean getIsNullCommentList(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISNULLCOMMENTLIST, false);
    }

    //记录是否已关注
    public static void setLastCommentTime(Context context, long isClose) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putLong(LASTCOMMENTTIME, isClose);
        editor.commit();
    }

    public static long getLastCommentTime(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        long data = uiState.getLong(LASTCOMMENTTIME, -1);
        return data;
    }

    //记录是否领取礼物已满
    public static void setIsGetGiftOver(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISGETGIFTOVER, service);
        editor.commit();
    }

    public static boolean getIsGetGiftOver(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISGETGIFTOVER, false);
    }

    //保存fileid
    public static void setFileId(Context context, String name) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(MFILEID, name);
        editor.commit();
    }

    public static String getFileId(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getString(MFILEID, "");
    }

    //记录是直播还是小视频与回放
    public static void setIsLive(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISLIVE, service);
        editor.commit();
    }

    public static boolean getIsLive(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISLIVE, false);
    }

    //记录用户是否开启悬浮球权限
    public static void setIsOpenPression(Context context, boolean service) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putBoolean(ISOPENPRESSION, service);
        editor.commit();
    }

    public static boolean getIsOpenPression(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        return uiState.getBoolean(ISOPENPRESSION, true);
    }

    //获取红包令的数据
    public static void setLuckMoney(Context context, int luckmoney) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(LUCKMONEY, luckmoney);
        editor.commit();
    }

    public static int getLuckMoney(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(LUCKMONEY, 0);
        return data;
    }

    //保存开启红包活动成功后的房间id
    public static void setRoomId(Context context, String tagPublisher) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putString(ROOMID, tagPublisher);
        editor.commit();
    }

    public static String getRoomId(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        String data = uiState.getString(ROOMID, "");
        return data;
    }

    //加载更多类型，0表示直播，1表示小视频，2表示回放
    public static void setLoadmoreType(Context context, int type) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();
        editor.putInt(LOADMORETYPE, type);
        editor.commit();
    }

    public static int getLoadmoreType(Context context) {
        SharedPreferences uiState = context.getSharedPreferences(I8_SHOW, Context.MODE_PRIVATE);
        int data = uiState.getInt(LOADMORETYPE, 0);
        return data;
    }
}
