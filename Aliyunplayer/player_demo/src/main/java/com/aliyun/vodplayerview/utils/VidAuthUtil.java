package com.aliyun.vodplayerview.utils;

import android.os.AsyncTask;

import com.aliyun.player.source.VidAuth;
import com.aliyun.player.source.VidSts;
import com.aliyun.utils.VcPlayerLog;
import com.aliyun.vodplayerview.playlist.vod.core.AliyunVodHttpCommon;

import org.json.JSONObject;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2020/05/20/18:18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class VidAuthUtil {

    private static final String TAG = VidAuthUtil.class.getSimpleName();

    public interface OnAuthResultListener {

        void onSuccess(String vid, String playAuth, String title, String coverPath);

        void onFail();
    }

    public static void getVidAuth(final String vid, final VidAuthUtil.OnAuthResultListener onAuthResultListener) {
        AsyncTask<Void, Void, VidAuth> asyncTask = new AsyncTask<Void, Void, VidAuth>() {

            @Override
            protected VidAuth doInBackground(Void... params) {
                return getVidAuth(vid);
            }

            @Override
            protected void onPostExecute(VidAuth vidAuth) {
                if (vidAuth == null) {
                    onAuthResultListener.onFail();
                } else {
                    onAuthResultListener.onSuccess(vidAuth.getVid(), vidAuth.getPlayAuth(), vidAuth.getTitle(), vidAuth.getCoverPath());
                }
            }
        };
        asyncTask.execute();
    }


    public static VidAuth getVidAuth(String videoId) {
        try {
            String stsUrl = AliyunVodHttpCommon.getInstance().getVodAuthApi() + "?videoId=" + videoId;
            String response = HttpClientUtil.doGet(stsUrl);
            JSONObject jsonObject = new JSONObject(response);

            JSONObject securityTokenInfo = jsonObject.getJSONObject("data");
            if (securityTokenInfo == null) {

                VcPlayerLog.e(TAG, "SecurityTokenInfo == null ");
                return null;
            }

            String playAuth = securityTokenInfo.getString("playAuth");
            String coverUrl = securityTokenInfo.getString("coverUrl");
            String title = securityTokenInfo.getString("title");
            String duration = securityTokenInfo.getString("duration");

            VcPlayerLog.e("radish", "playAuth = " + playAuth + " , coverUrl = " + coverUrl +
                    " , title = " + title + " ,duration = " + duration);

            VidAuth vidAuth = new VidAuth();
            vidAuth.setVid(videoId);
            vidAuth.setPlayAuth(playAuth);
            vidAuth.setTitle(title);
            vidAuth.setCoverPath(coverUrl);
            return vidAuth;

        } catch (Exception e) {
            VcPlayerLog.e(TAG, "e = " + e.getMessage());
            return null;
        }
    }
}
