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
import hnb.team.writenow.Interface.colorChangeListener;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 18..
 */

public class AdapterTextColors extends RecyclerView.Adapter<AdapterTextColors.ViewHolder> {


    public AdapterTextColors(Context context, colorChangeListener colorChangeListener, String textType){
        this.context = context;
        this.colorChangeListener = colorChangeListener;
        this.textType = textType;
    }

    private colorChangeListener colorChangeListener;

    private List<Integer> datas = new ArrayList<Integer>();

    private Context context;

    private String textType;

    public final static String TEXT_TYPE_TITLE = "title";
    public final static String TEXT_TYPE_DESC = "desc";


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

        holder.textFont.setTextColor(data);

        if(textType.equals(TEXT_TYPE_TITLE))
            holder.textFont.setText(context.getResources().getString(R.string.str_title));
        else
            holder.textFont.setText(context.getResources().getString(R.string.str_desc));

        holder.textFont.setOnClickListener(v -> {
            colorChangeListener.onTextColorChange(data);
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
