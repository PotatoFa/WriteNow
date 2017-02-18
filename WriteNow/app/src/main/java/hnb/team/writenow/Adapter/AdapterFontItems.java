package hnb.team.writenow.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Model.FontItem;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 18..
 */

public class AdapterFontItems extends RecyclerView.Adapter<AdapterFontItems.ViewHolder> {

    private FontChangeListener fontChangeListener;

    public AdapterFontItems(Context context, FontChangeListener fontChangeListener){
        this.context = context;
        this.fontChangeListener = fontChangeListener;
    }

    List<FontItem> datas = new ArrayList<FontItem>();

    private Context context;


    public void setSource(List<FontItem> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FontItem data = datas.get(position);

        holder.textFont.setTypeface(data.getFont());

        holder.textFont.setOnClickListener(v -> {
            fontChangeListener.onChangeFont(data);
        });

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_font, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textFont) TextView textFont;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
