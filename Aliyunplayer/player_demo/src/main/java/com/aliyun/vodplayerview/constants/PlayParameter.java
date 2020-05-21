package com.aliyun.vodplayerview.constants;

/**
 * 播放参数, 包含:
 * vid, vidSts, akId, akSecre, scuToken
 */
public class PlayParameter {


    /**
     * type, 用于区分播放类型, 默认为vidsts播放
     * vidsts: vid类型
     * localSource: url类型
     */
    public static String PLAY_PARAM_TYPE = "vidAuth";

    /**
     * vid初始值
     */
    public static final String PLAY_PARAM_VID_DEFAULT = "9caa942ee6fe489ca8ac4ff01ae73dd3";

    /**
     * vid
     */
    public static String PLAY_PARAM_VID = "";

    public static String PLAY_PARAM_REGION = "cn-shanghai";

    /**
     * url类型的播放地址, 初始为:http://player.alicdn.com/video/aliyunmedia.mp4
     */
    public static String PLAY_PARAM_URL = "http://player.alicdn.com/video/aliyunmedia.mp4";


    /**
     * 播放凭证VidAuth
     */
    public static String PLAY_PARAM_TYPE_AUTH_PLAY_AUTH = "playAuth";

    /**
     * 标题
     */
    public static String PLAY_PARAM_TYPE_AUTH_TITLE = "title";

    /**
     * 封面
     */
    public static String PLAY_PARAM_TYPE_AUTH_COVER_PATH = "coverPath";

}
