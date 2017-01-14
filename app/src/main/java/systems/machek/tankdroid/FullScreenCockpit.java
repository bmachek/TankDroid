package systems.machek.tankdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;

public class FullScreenCockpit extends AppCompatActivity {

    private MjpegView turretMjpegView, frontMjpegView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_cockpit);

        frontMjpegView = (MjpegView)findViewById(R.id.frontView);
        turretMjpegView = (MjpegView)findViewById(R.id.turretView);


        Mjpeg.newInstance(Mjpeg.Type.DEFAULT)
                .open(Constants.FRONT_MJPEG_CAM_URL)
                .subscribe(inputStream -> {
                    frontMjpegView.setSource(inputStream);
                    frontMjpegView.setDisplayMode(DisplayMode.BEST_FIT);
                    frontMjpegView.showFps(true);
                });



        Mjpeg.newInstance(Mjpeg.Type.DEFAULT)
                .open(Constants.FRONT_MJPEG_CAM_URL)
                .subscribe(inputStreamTurret -> {
                    turretMjpegView.setSource(inputStreamTurret);
                    turretMjpegView.setDisplayMode(DisplayMode.BEST_FIT);
                    turretMjpegView.showFps(true);
                });


    }
}