package com.hayllieapps.digidiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.models.Diary;

import java.util.List;

public class MiniDiaryAdapter extends RecyclerView.Adapter<MiniDiaryAdapter.Myholder> {

    private List<Diary> diariesList;
    private SelectionPropagator obsever;

    public MiniDiaryAdapter(List<Diary> diariesList, SelectionPropagator obsever) {
        this.diariesList = diariesList;
        this.obsever = obsever;
    }

    @Override
    public MiniDiaryAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mini_diary, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        final Diary currentDiary = diariesList.get(position);
        String text = currentDiary.getText1();
        String text2 = currentDiary.getText2();

        holder.tv_today_is_value.setText(text);
        holder.tv_today_desc.setText(text2);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obsever.propagateSelection(currentDiary);
            }
        });

    }


    class Myholder extends RecyclerView.ViewHolder {
        private ConstraintLayout container;
        private TextView tv_today_is_value;
        private TextView tv_today_desc;

        private Myholder(View itemView) {
            super(itemView);
            tv_today_is_value = itemView.findViewById(R.id.today_title);
            tv_today_desc = itemView.findViewById(R.id.today_desc);
            container = itemView.findViewById(R.id.mini_diary_item);
        }

    }

    @Override
    public int getItemCount() {
        return diariesList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface SelectionPropagator {
        void propagateSelection(Diary diary);
    }

}
