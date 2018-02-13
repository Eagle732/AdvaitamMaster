package Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import ac.nita.advaitam4.R;
import ac.nita.advaitam4.TabbedActivity;

/**
 * Created by HRITIK on 2/12/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {


    private Context mContext;
    private List<String> uriList = new ArrayList<>();

    public GalleryAdapter(Context mContext, List<String> uri){
        this.mContext = mContext;
        this.uriList = uri;
    }
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v = inflator.inflate(R.layout.gallery_list_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .override(100,100)
                .placeholder(R.drawable.advaitam_4_logo)
                .error(R.drawable.advaitam_4_logo);
        Glide.with(mContext).asBitmap().load(uriList.get(position)).apply(options).into(holder.images);

    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        ImageView images;
        ProgressBar progressBar;
    public ViewHolder(View itemView) {
        super(itemView);
        v = itemView;
        images = itemView.findViewById(R.id.GalleryElement);
        mContext = itemView.getContext();

        }
    }
}
