package com.aliyun.vodplayerview.listener;

import com.aliyun.player.source.VidAuth;

/**
 * Auth刷新回调
 */
public interface RefreshAuthCallback {

    VidAuth refreshAuth(String vid, String quality, String format, String title, boolean encript);
}
