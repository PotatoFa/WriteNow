package hnb.team.writenow.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hnb.team.writenow.Model.PixaBayImage;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 11..
 */

public class SquareImageAdapter extends RecyclerView.Adapter<SquareImageAdapter.ViewHolder> {

    private AdapterOnClickListener adapterOnClickListener;
    public void setAdapterOnClickListener(AdapterOnClickListener adapterOnClickListener){
        this.adapterOnClickListener = adapterOnClickListener;
    };

    List<PixaBayImage> datas = new ArrayList<PixaBayImage>();

    private Context context;

    public SquareImageAdapter(Context context){
        this.context = context;
    }

    public void setSource(List<PixaBayImage> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PixaBayImage data = datas.get(position);

        Glide.with(context).load(data.getTestImageUrl()).override(300,300).centerCrop().into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if(adapterOnClickListener!=null)
                adapterOnClickListener.onClickView(position, data);
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_square_image, parent, false);
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
