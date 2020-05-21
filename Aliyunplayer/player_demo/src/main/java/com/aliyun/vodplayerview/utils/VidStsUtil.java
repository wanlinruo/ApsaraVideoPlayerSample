package com.aliyun.vodplayerview.utils;

import android.os.AsyncTask;

import com.aliyun.player.source.VidSts;
import com.aliyun.utils.VcPlayerLog;
import com.aliyun.vodplayerview.playlist.vod.core.AliyunVodHttpCommon;

import org.json.JSONObject;

/**
 * Created by pengshuang on 31/08/2017.
 */
public class VidStsUtil {


    private static final String TAG = VidStsUtil.class.getSimpleName();

    public static VidSts getVidSts(String videoId) {

        try {
            //以前的连接地址"https://demo-vod.cn-shanghai.aliyuncs.com/voddemo/CreateSecurityToken?BusinessType=vodai&TerminalType=pc&DeviceModel=iPhone9,2&UUID=59ECA-4193-4695-94DD-7E1247288&AppVersion=1.0.0&VideoId=" + videoId"
            String stsUrl = AliyunVodHttpCommon.getInstance().getVodStsDomain() + "getSts";
            String response = HttpClientUtil.doGet(stsUrl);
            JSONObject jsonObject = new JSONObject(response);

            JSONObject securityTokenInfo = jsonObject.getJSONObject("data");
            if (securityTokenInfo == null) {

                VcPlayerLog.e(TAG, "SecurityTokenInfo == null ");
                return null;
            }

            String accessKeyId = securityTokenInfo.getString("accessKeyId");
            String accessKeySecret = securityTokenInfo.getString("accessKeySecret");
            String securityToken = securityTokenInfo.getString("securityToken");
            String expiration = securityTokenInfo.getString("expiration");

            VcPlayerLog.e("radish", "accessKeyId = " + accessKeyId + " , accessKeySecret = " + accessKeySecret +
                    " , securityToken = " + securityToken + " ,expiration = " + expiration);

            VidSts vidSts = new VidSts();
            vidSts.setVid(videoId);
            vidSts.setAccessKeyId(accessKeyId);
            vidSts.setAccessKeySecret(accessKeySecret);
            vidSts.setSecurityToken(securityToken);
            return vidSts;

        } catch (Exception e) {
            VcPlayerLog.e(TAG, "e = " + e.getMessage());
            return null;
        }
    }

    public interface OnStsResultListener {
        void onSuccess(String vid, String akid, String akSecret, String token);

        void onFail();
    }

    public static void getVidSts(final String vid, final OnStsResultListener onStsResultListener) {
        AsyncTask<Void, Void, VidSts> asyncTask = new AsyncTask<Void, Void, VidSts>() {

            @Override
            protected VidSts doInBackground(Void... params) {
                return getVidSts(vid);
            }

            @Override
            protected void onPostExecute(VidSts s) {
                if (s == null) {
                    onStsResultListener.onFail();
                } else {
                    onStsResultListener.onSuccess(s.getVid(), s.getAccessKeyId(), s.getAccessKeySecret(), s.getSecurityToken());
                }
            }
        };
        asyncTask.execute();
    }
}
