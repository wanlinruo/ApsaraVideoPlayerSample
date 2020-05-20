package com.aliyun.vodplayerview.playlist;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2020/05/20/17:22
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CusAlivcVideoInfo {

    private String videoId;
    private String title;
    private String coverUrl;
    private int duration;
    private String sourceSize;
    private String remark;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSourceSize() {
        return sourceSize;
    }

    public void setSourceSize(String sourceSize) {
        this.sourceSize = sourceSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CusAlivcVideoInfo{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", duration=" + duration +
                ", sourceSize='" + sourceSize + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
