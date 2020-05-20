package com.aliyun.vodplayerview.playlist;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2020/05/20/17:41
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class AlivcVideoList {
    private String requestId;
    private AlivcVideoInfo[] videoList;
    private int totall;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public AlivcVideoInfo[] getVideoList() {
        return videoList;
    }

    public void setVideoList(AlivcVideoInfo[] videoList) {
        this.videoList = videoList;
    }

    public int getTotall() {
        return totall;
    }

    public void setTotall(int totall) {
        this.totall = totall;
    }
}
