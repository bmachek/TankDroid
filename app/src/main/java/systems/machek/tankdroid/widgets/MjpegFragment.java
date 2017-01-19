package systems.machek.tankdroid.widgets;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;

import systems.machek.tankdroid.R;


public class MjpegFragment extends Fragment {

    private String url;

    private MjpegView mjpegView;


    public MjpegFragment() { }

    public static MjpegFragment newInstance() {
        MjpegFragment fragment = new MjpegFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mjpeg, container, false);
        mjpegView = (MjpegView)v.findViewById(R.id.mjpegView);
        return v;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

        Mjpeg.newInstance(Mjpeg.Type.DEFAULT)
                .open(url)
                .subscribe(inputStream -> {
                    mjpegView.setSource(inputStream);
                    mjpegView.setDisplayMode(DisplayMode.BEST_FIT);
                    mjpegView.showFps(false);
                });
    }
}
