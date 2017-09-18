package iamhere.ciapps.net.mytutorial;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer;
    private ImageView artistImage;
    private TextView leftTime;
    private TextView rightTime;
    private SeekBar seekBar;
    private Button prev;
    private Button play;
    private Button next;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        setUpUI();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

                int currPos = mediaPlayer.getCurrentPosition();
                int duration = mediaPlayer.getDuration();
                leftTime.setText(dateFormat.format(new Date(currPos)));
                rightTime.setText(dateFormat.format(new Date(duration-currPos)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //------------------------------------------------------------------------------
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                double duration = mp.getDuration();
                String mDuration = String.valueOf(duration/1000/60);
                Toast.makeText(getApplicationContext(), "duration " + mDuration, Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setUpUI() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sample);
        artistImage = (ImageView) findViewById(R.id.ovalID);
        leftTime = (TextView) findViewById(R.id.leftTimeID);
        rightTime = (TextView) findViewById(R.id.rightTimeID);
        seekBar = (SeekBar) findViewById(R.id.seekBarID);
        prev = (Button) findViewById(R.id.prevButtonID);
        play = (Button) findViewById(R.id.playButtonID);
        next = (Button) findViewById(R.id.fwButtonID);

        prev.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prevButtonID:
                backMusic();
                break;
            case R.id.playButtonID:
                if (mediaPlayer.isPlaying()) {
                    pauseMusic();
                } else {
                    startMusic();
                }
                break;
            case R.id.fwButtonID:
                nextMusic();
                break;
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            play.setBackgroundResource(android.R.drawable.ic_media_play);
        }

    }

    public void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            updateThread();
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void backMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
        }
    }

    public void nextMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mediaPlayer.getDuration()-1000);
        }
    }



    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        thread.interrupt();
        thread=null;
        super.onDestroy();
    }

    public void updateThread(){
        thread= new Thread(){
            @Override
            public void run() {
                try{
                    while(mediaPlayer!=null && mediaPlayer.isPlaying()){

                    Thread.sleep(50);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int newPos = mediaPlayer.getCurrentPosition();
                            int newMax= mediaPlayer.getDuration();
                            seekBar.setMax(newMax);
                            seekBar.setProgress(newPos);
                            leftTime.setText(String.valueOf(new java.text.SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getCurrentPosition()))));
                            rightTime.setText(String.valueOf(new java.text.SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition()))));
                        }
                    });
                    }
                }
                catch (InterruptedException e){
                        e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
