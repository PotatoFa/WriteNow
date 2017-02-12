package hnb.team.writenow.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class AdapterMyContents extends RecyclerView.Adapter<AdapterMyContents.ViewHolder> {

    public AdapterMyContents(Context context){
        this.context = context;
    }

    private AdapterOnClickListener adapterOnClickListener;

    public void setAdapterOnClickListener(AdapterOnClickListener adapterOnClickListener){
        this.adapterOnClickListener = adapterOnClickListener;
    };

    List<Contents> datas = new ArrayList<Contents>();

    private Context context;


    public void setSource(List<Contents> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contents contents = datas.get(position);

        Glide.with(context).load(contents.getTitleImage()).override(600,400).into(holder.myCardImage);

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_contents, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.myCardImage) ImageView myCardImage;
        @Bind(R.id.myCardSNSButton) LinearLayout myCardSNSButton;
        @Bind(R.id.myCardEditButton) LinearLayout myCardEditButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
