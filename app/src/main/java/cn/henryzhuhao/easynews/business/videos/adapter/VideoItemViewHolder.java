package cn.henryzhuhao.easynews.business.videos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;

import cn.henryzhuhao.easynews.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by HenryZhuhao on 2017/5/17.
 */

public class VideoItemViewHolder extends RecyclerView.ViewHolder{
    public JCVideoPlayerStandard videoPlayer;
    public Context context;
//    public ImageView fullscreen;
    public VideoItemViewHolder(View itemView,Context context) {
        super(itemView);
        this.context=context;
        videoPlayer= (JCVideoPlayerStandard) itemView.findViewById(R.id.item_videoView);
        videoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
        Glide.with(context).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
                .into(videoPlayer.thumbImageView);
//        fullscreen= (ImageView) itemView.findViewById(R.id.item_videofullscreen);
//        fullscreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                videoPlayer.startWindowFullscreen();
//            }
//        });
    }
}
