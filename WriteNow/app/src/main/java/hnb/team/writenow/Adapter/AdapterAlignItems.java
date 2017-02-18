package hnb.team.writenow.Adapter;

import butterknife.Bind;
import hnb.team.writenow.Model.AlignItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import hnb.team.writenow.R;


public class AdapterAlignItems extends RecyclerView.Adapter<AdapterAlignItems.ViewHolder> {


    private AlignChangeListener alignChangeListener;

    public AdapterAlignItems(Context context, AlignChangeListener alignChangeListener){
        this.context = context;
        this.alignChangeListener = alignChangeListener;
    }

    List<AlignItem> datas = new ArrayList<AlignItem>();

    private Context context;


    public void setSource(List<AlignItem> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AlignItem data = datas.get(position);

        holder.imageIconAlign.setImageResource(getIconPath(data.getAlign()));

        holder.textAlign.setText(data.getAlignText());

        holder.textAlign.setOnClickListener(v -> {
            alignChangeListener.onChangeAlign(data);
        });
    }

    private int getIconPath(int gravity){
        switch (gravity){
            case Gravity.LEFT :
                return R.drawable.ic_align_left_v;
            case Gravity.CENTER_HORIZONTAL :
                return R.drawable.ic_align_center;
            case Gravity.RIGHT :
                return R.drawable.ic_align_right;
            case Gravity.TOP :
                return R.drawable.ic_align_top;
            case Gravity.CENTER_VERTICAL :
                return R.drawable.ic_align_middle;
            case Gravity.BOTTOM :
                return R.drawable.ic_align_bottom;
            default:
                return R.drawable.ic_align_center;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_align, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageIconAlign) ImageView imageIconAlign;
        @Bind(R.id.textAlign) TextView textAlign;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
