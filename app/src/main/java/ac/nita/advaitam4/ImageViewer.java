package ac.nita.advaitam4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import sourav.springpebbles.zoomableimageview.SenseView;

public class ImageViewer extends AppCompatActivity {


    RequestOptions options = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = null;
        try {
            Intent intent = getIntent();
            url = intent.getExtras().getString("url");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SenseView senseView = (SenseView)findViewById(R.id.image_view);

        Glide.with(this).load(url).apply(options).into(senseView);

    }

}
