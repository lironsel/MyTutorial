package iamhere.ciapps.net.mytutorial;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class batActivity extends AppCompatActivity {
    private AnimationDrawable batAnimation;
    private ImageView batImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat);

        batImage = (ImageView)findViewById(R.id.batID);
//        batImage.setBackgroundResource(R.drawable.bat_anim);
//        batAnimation= (AnimationDrawable)batImage.getBackground();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        batAnimation.start();

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fedain_animation);
                batImage.startAnimation(startAnimation);
//                batAnimation.stop();
            }
        },50);
        return super.onTouchEvent(event);
    }
}
