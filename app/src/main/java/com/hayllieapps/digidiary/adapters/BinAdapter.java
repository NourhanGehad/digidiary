package com.hayllieapps.digidiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.models.Diary;

import java.util.ArrayList;
import java.util.List;

public class BinAdapter extends RecyclerView.Adapter<BinAdapter.Myholder> {

    private List<Diary> diariesList;
    public ArrayList<Diary> selectedDiaries = new ArrayList<>();
    private SelectionPropagator observer;

    public BinAdapter(List<Diary> diariesList, SelectionPropagator observer) {
        this.diariesList = diariesList;
        this.observer = observer;
    }

    @Override
    public BinAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType){
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


        final int currentPosition = position;
        Boolean isSelected = currentDiary.getSelected();
        if(isSelected){
            holder.iv_selection_mark.setImageResource(R.drawable.selection_mark_selected);
        } else  {
            holder.iv_selection_mark.setImageResource(R.drawable.selection_mark_not_selected);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isSelected = currentDiary.getSelected();
                if(isSelected){
                    selectedDiaries.remove(currentDiary);
                    diariesList.get(currentPosition).setSelected(false);
                    holder.iv_selection_mark.setImageResource(R.drawable.selection_mark_not_selected);
                } else {
                    selectedDiaries.add(currentDiary);
                    diariesList.get(currentPosition).setSelected(true);
                    holder.iv_selection_mark.setImageResource(R.drawable.selection_mark_selected);
                }

                observer.propagateSelectionState(selectedDiaries.isEmpty());

                notifyDataSetChanged();
            }
        });
        if(selectedDiaries.isEmpty()){
            holder.iv_selection_mark.setVisibility(View.GONE);
        } else {
            holder.iv_selection_mark.setVisibility(View.VISIBLE);
        }
    }


    class Myholder extends RecyclerView.ViewHolder {
        private ConstraintLayout container;
        private TextView tv_today_is_value;
        private TextView tv_today_desc;
        private ImageView iv_selection_mark;

        private Myholder(View itemView) {
            super(itemView);
            tv_today_is_value = itemView.findViewById(R.id.today_title);
            tv_today_desc = itemView.findViewById(R.id.today_desc);
            container = itemView.findViewById(R.id.mini_diary_item);
            iv_selection_mark = itemView.findViewById(R.id.iv_selection_mark);
        }

    }


    public ArrayList<Diary> getSelectedDiaries(){
        return selectedDiaries;
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
        void propagateSelectionState(Boolean isSelectionDisabled);
    }

}
