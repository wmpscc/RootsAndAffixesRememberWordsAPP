package info.heolis.rootsandaffixesrememberwords.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * 单例的MediaPlayer操作,适用于播放网络歌曲的时候使用；播放本地歌曲请使用
 * * 参考博客链接 http://blog.csdn.net/u011516685/article/details/50510902
 * 这里注意使用stop和prepareAsync是耗时操作需要在线程里面执行，同时由于stop多次出错之后之前的MediaPlayer不能够被正常使用,所以这里才会采取每次新建一个MediaPlayer实例
 * 这里PlayMusicCompleteListener没有使用弱引用,因为在应用程序里面会存在被GC掉,所以这里使用Handler来避免内存泄露的方式实现
 * Created by Tangxb on 2016/9/1.
 */
public class LMediaPlayerManger {
    private static LMediaPlayerManger instance;
    private MediaPlayer mediaPlayer;
    private HandlerThread playHandlerThread;
    private Handler playHandler;
    /**
     * 播放
     */
    private static final int PLAY = 101;
    /**
     * 停止
     */
    private static final int STOP = 102;
    /**
     * 释放
     */
    private static final int RELEASE = 103;
    /**
     * 界面不可见
     */
    private static final int ONSTOP = 104;
    private Handler handler;
    private int position;
    private String url;
    private PlayMusicCompleteListener listener;

    /**
     * 播放一首完成的回调
     */
    public interface PlayMusicCompleteListener {
        void playMusicComplete(int position);
    }

    private void createHandlerThreadIfNeed() {
        if (playHandlerThread == null) {
            playHandlerThread = new HandlerThread("playHandlerThread");
            playHandlerThread.start();
        }
    }

    private void createHandlerIfNeed() {
        if (playHandler == null) {
            playHandler = new Handler(playHandlerThread.getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case PLAY:
                            playMusic01();
                            break;
                        case STOP:
                            stopMediaPlayer02();
                            break;
                        case RELEASE:
                            releaseMediaPlayer02();
                            break;
                        case ONSTOP:
                            stopMediaPlayer03();
                            break;
                    }
                }
            };
        }
    }

    private void createPlayerIfNeed() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
    }

    private LMediaPlayerManger() {
        handler = new Handler(Looper.getMainLooper());
        createHandlerThreadIfNeed();
        createHandlerIfNeed();
    }

    public static LMediaPlayerManger getInstance() {
        if (instance == null) {
            instance = new LMediaPlayerManger();
        }
        return instance;
    }

    public void playMusic(int position, String url, PlayMusicCompleteListener listener) {
        this.position = position;
        this.url = url;
        this.listener = listener;
        playHandler.sendEmptyMessageDelayed(PLAY, 0L);
    }

    public void playMusic01() {
        createPlayerIfNeed();
        playMusic02(url);
    }

    public void playMusic02(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.setLooping(false);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer player) {
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    playMusicComplete();
                    stopMediaPlayer();
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer player, int what, int extra) {
                    stopMediaPlayer();
                    return false;
                }
            });
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMediaPlayer() {
        playHandler.sendEmptyMessage(STOP);
    }

    public void onStopForMediaPlayer() {
        playHandler.sendEmptyMessage(ONSTOP);
    }

    /**
     * 这里需要注意设置setOnPreparedListener和setOnCompletionListener为null,因为不设置它会调用上一个已经设置好的回调(经过测试,请注意)
     */
    public void stopMediaPlayer02() {
        if (mediaPlayer != null) {
            mediaPlayer.setOnPreparedListener(null);
            mediaPlayer.setOnCompletionListener(null);
            try {
                mediaPlayer.stop();
            } catch (IllegalStateException e) {

            }
        }
        mediaPlayer = null;
    }

    /**
     * 这里需要注意设置setOnPreparedListener和setOnCompletionListener为null,因为不设置它会调用上一个已经设置好的回调(经过测试,请注意)
     */
    public void stopMediaPlayer03() {
        stopMediaPlayer02();
        playMusicComplete();
    }

    /**
     * 播放完成,需要在主线程里面更新UI
     */
    public void playMusicComplete() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener.playMusicComplete(position);
                }
            }
        });
    }

    public void releaseMediaPlayer() {
        playHandler.sendEmptyMessage(RELEASE);
    }

    public void releaseMediaPlayer02() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (IllegalStateException e) {

            }
        }
        mediaPlayer = null;
        // 避免内存泄露
        listener = null;
        handler.removeCallbacksAndMessages(null);
    }
}