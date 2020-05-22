package com.aliyun.svideo.vodupload;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2020/05/22/11:58
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HttpUtils {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static void post(String url, String videoId, Callback responseCallback) {
        RequestBody formBody = new FormBody.Builder().add("videoId", videoId).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(responseCallback);
    }

    public static Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }
}
