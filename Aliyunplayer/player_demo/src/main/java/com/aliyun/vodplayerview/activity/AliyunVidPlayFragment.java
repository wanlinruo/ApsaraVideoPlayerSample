package com.aliyun.vodplayerview.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aliyun.vodplayer.R;
import com.aliyun.vodplayerview.constants.PlayParameter;
import com.aliyun.vodplayerview.listener.OnNotifyActivityListener;
import com.aliyun.vodplayerview.utils.FixedToastUtils;
import com.aliyun.vodplayerview.utils.VidAuthUtil;

import java.lang.ref.WeakReference;

/**
 * vid设置界面
 * Created by Mulberry on 2018/4/4.
 */
public class AliyunVidPlayFragment extends Fragment {

    EditText etVid;
    EditText etPlayAuth;
    EditText etTitle;
    EditText etCoverPath;
    /**
     * get StsToken stats
     */
    private boolean inRequest;

    /**
     * 返回给上个activity的resultcode: 100为vid播放类型, 200为URL播放类型
     */
    private static final int CODE_RESULT_VID = 100;

    private OnNotifyActivityListener onNotifyActivityListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_vidplay_layout, container, false);

        initStsView(v);
        return v;
    }

    private void initStsView(View v) {
        etVid = (EditText) v.findViewById(R.id.et_vid);
        etPlayAuth = (EditText) v.findViewById(R.id.et_play_auth);
        etTitle = (EditText) v.findViewById(R.id.et_title);
        etCoverPath = (EditText) v.findViewById(R.id.et_cover_path);
    }

    public void startToPlayerByVid() {
        String mVid = etVid.getText().toString();
        String playAuth = etPlayAuth.getText().toString();
        String title = etTitle.getText().toString();
        String coverPath = etCoverPath.getText().toString();

        PlayParameter.PLAY_PARAM_TYPE = "vidAuth";

        if (TextUtils.isEmpty(mVid) || TextUtils.isEmpty(playAuth) || TextUtils.isEmpty(title) || TextUtils.isEmpty(coverPath)) {
            if (inRequest) {
                return;
            }

            inRequest = true;
            VidAuthUtil.getVidAuth(mVid, new MyAuthListener(this));
        } else {
            PlayParameter.PLAY_PARAM_VID = mVid;
            PlayParameter.PLAY_PARAM_TYPE_AUTH_PLAY_AUTH = playAuth;
            PlayParameter.PLAY_PARAM_TYPE_AUTH_TITLE = title;
            PlayParameter.PLAY_PARAM_TYPE_AUTH_COVER_PATH = coverPath;

            getActivity().setResult(CODE_RESULT_VID);
            getActivity().finish();
        }
    }

    private static class MyAuthListener implements VidAuthUtil.OnAuthResultListener {

        private WeakReference<AliyunVidPlayFragment> weakctivity;

        public MyAuthListener(AliyunVidPlayFragment view) {
            weakctivity = new WeakReference<AliyunVidPlayFragment>(view);
        }

        @Override
        public void onSuccess(String vid, String playAuth, String title, String coverPath) {
            AliyunVidPlayFragment fragment = weakctivity.get();
            if (fragment != null) {
                fragment.onAuthSuccess(vid, playAuth, title, coverPath);
            }
        }

        @Override
        public void onFail() {
            AliyunVidPlayFragment fragment = weakctivity.get();
            if (fragment != null) {
                fragment.onStsFail();
            }
        }
    }

    private void onStsFail() {
        if (getContext() != null) {
            FixedToastUtils.show(getContext().getApplicationContext(), R.string.request_vid_auth_fail);
        }
        inRequest = false;
    }

    private void onAuthSuccess(String vid, String playAuth, String title, String coverPath) {

        PlayParameter.PLAY_PARAM_VID = vid;
        PlayParameter.PLAY_PARAM_TYPE_AUTH_PLAY_AUTH = playAuth;
        PlayParameter.PLAY_PARAM_TYPE_AUTH_TITLE = title;
        PlayParameter.PLAY_PARAM_TYPE_AUTH_COVER_PATH = coverPath;

        if (onNotifyActivityListener != null) {
            onNotifyActivityListener.onNotifyActivity();
        }

        //getActivity().setResult(CODE_RESULT_VID);
        //getActivity().finish();
        //

        inRequest = false;
    }

    public void setOnNotifyActivityListener(OnNotifyActivityListener listener) {
        this.onNotifyActivityListener = listener;
    }
}
