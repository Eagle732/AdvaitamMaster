package ac.nita.advaitam4;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

 class SponsorAdapter extends ArrayAdapter<SponsorItemsSetter> {

     Context mContext;
     int mLayoutResourceId;
     ArrayList<SponsorItemsSetter> mList = new ArrayList<>();



    public SponsorAdapter(Context context, int resource, ArrayList<SponsorItemsSetter>myPlacesArray) {
        super(context, resource, myPlacesArray);
        this.mContext = context;
        this.mLayoutResourceId = R.layout.sponser_item;
        this.mList = myPlacesArray;
//        Toast.makeText(mContext,myPlacesArray.get(0).getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public SponsorItemsSetter getItem(int position)
    {
        return super.getItem(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        SponsorAdapter.PlaceHolder holder = null;
        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);
            holder = new PlaceHolder();
            holder.cd = row.findViewById(R.id.cardViewForSponsor);
            holder.Name = row.findViewById(R.id.company_name);
            holder.iv = row.findViewById(R.id.company_pic);
            row.setTag(holder);
        }else {
            holder = (PlaceHolder) row.getTag();
        }

        SponsorItemsSetter eventsClass = mList.get(position);
        holder.Name.setText(eventsClass.getName());
        holder.Name.setTextColor(Color.parseColor("#ffc61e"));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .placeholder(R.drawable.advaitam_4_logo)
                .error(R.drawable.advaitam_4_logo);
//        Toast.makeText(getContext(),eventsClass.getImage(),Toast.LENGTH_SHORT).show();
        String im = eventsClass.getImage();
        Glide.with(mContext).load(im).apply(options).into(holder.iv);
        return row;
    }
    private static class PlaceHolder {
        TextView Name;
        ImageView iv;
        CardView cd;
    }



}
