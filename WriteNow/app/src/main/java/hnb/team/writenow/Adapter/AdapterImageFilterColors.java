package hnb.team.writenow.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Interface.colorChangeListener;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 25..
 */

public class AdapterImageFilterColors extends RecyclerView.Adapter<AdapterImageFilterColors.ViewHolder> {


    public AdapterImageFilterColors(Context context, colorChangeListener colorChangeListener){
        this.context = context;
        this.colorChangeListener = colorChangeListener;
    }

    private colorChangeListener colorChangeListener;

    private List<Integer> datas = new ArrayList<Integer>();

    private Context context;

    public void setSource(List<Integer> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Integer data = datas.get(position);

        holder.colorView.setBackgroundColor(data);

        holder.colorView.setOnClickListener(v -> {
            colorChangeListener.onTextColorChange(data);
        });

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_filter, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.colorView) View colorView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
