package hnb.team.writenow.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Interface.AdapterOnClickListener;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class AdapterMakeContents extends RecyclerView.Adapter<AdapterMakeContents.ViewHolder> {

    public AdapterMakeContents(Context context){
        this.context = context;
    }

    private AdapterOnClickListener adapterOnClickListener;

    public void setAdapterOnClickListener(AdapterOnClickListener adapterOnClickListener){
        this.adapterOnClickListener = adapterOnClickListener;
    };

    List<Contents> datas = new ArrayList<Contents>();

    public static final int ADD_BUTTON_ID = -999;

    private Context context;

    public Contents addButtonItem(){
        return new Contents(ADD_BUTTON_ID, R.drawable.ic_plus_oval_v);
    }

    public void setSource(List<Contents> datas){
        this.datas = datas;
        this.datas.add(addButtonItem());
        notifyDataSetChanged();
    }

    public void addContents(Contents data){
        this.datas.add(0, data);
    }

    public void refreshBindingView(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contents contents = datas.get(position);

        holder.imageView.setOnClickListener(v -> {
            if(adapterOnClickListener!=null)
                adapterOnClickListener.onClickView(position, contents);
        });


        if(contents.getContentsId() == ADD_BUTTON_ID){
            holder.imageView.setImageResource(contents.getTitleImage());
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
            return;
        }

/*
        File image = new File(contents.getFilePath());
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        bitmap = Bitmap.createBitmap(bitmap);*/

        //imageView.setImageBitmap(bitmap);


        //Glide.with(context).load(bitmap).override(320,320).into(holder.imageView);

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_make_contents, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageView) ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}