package com.hayllieapps.digidiary.adapters;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.models.Diary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.Myholder> {

    private List<Diary> diariesList;
    private SelectionPropagator obsever;

    public DiaryAdapter(List<Diary> diariesList, SelectionPropagator obsever) {
        this.diariesList = diariesList;
        this.obsever = obsever;
    }

    @Override
    public DiaryAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_diary_expanded, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        final Diary currentDiary = diariesList.get(position);

        String text = currentDiary.getText1();
        String text2 = currentDiary.getText2();
        String diarydate = currentDiary.getDiarydate();
        String lastUpdated = currentDiary.getLastupdated();
        int Local_id = currentDiary.getLocaldiary_id();
        byte[] diaryImage = currentDiary.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(diaryImage,0,diaryImage.length);

        Date date = extractDate(diarydate);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String month = new SimpleDateFormat("MMM", Locale.ENGLISH).format(calendar.getTime());
        int year = calendar.get(Calendar.YEAR);

        holder.tv_day.setText(String.valueOf(day));
        holder.tv_month.setText(month);
        holder.tv_year.setText(String.valueOf(year));

        holder.tv_today_is_value.setText(text);
        holder.tv_today_desc.setText(text2);

        holder.iv_diary_pic.setImageBitmap(bitmap);

        if (currentDiary.getFavourite() == 0){
            holder.iv_favourite.setBackgroundResource(R.drawable.ic_heart);
        } else if (currentDiary.getFavourite() == 1) {
            holder.iv_favourite.setBackgroundResource(R.drawable.ic_filled_heart);
        }
        holder.go_to_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obsever.propagateSelection(currentDiary);
            }
        });
        holder.iv_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentDiary.getFavourite() == 1)
                {
                    currentDiary.setFavourite(0);
                    v.setBackgroundResource(R.drawable.ic_heart);
                    obsever.removeFromFavourites(currentDiary);
                } else if(currentDiary.getFavourite() == 0){
                    currentDiary.setFavourite(1);
                    v.setBackgroundResource(R.drawable.ic_filled_heart);
                    obsever.addToFavourites(currentDiary);
                }

            }
        });

    }

    class Myholder extends RecyclerView.ViewHolder {
        private TextView tv_day;
        private TextView tv_month;
        private TextView tv_year;
        private TextView tv_today_is_value;
        private TextView tv_today_desc;
        private ImageView go_to_details;
        private ImageView iv_diary_pic;
        private ImageView iv_favourite;

        private Myholder(View itemView) {
            super(itemView);
            tv_day = itemView.findViewById(R.id.day);
            tv_month = itemView.findViewById(R.id.month);
            tv_year = itemView.findViewById(R.id.year);
            tv_today_is_value = itemView.findViewById(R.id.today_title);
            tv_today_desc = itemView.findViewById(R.id.today_desc);
            go_to_details = itemView.findViewById(R.id.go_to_details);
            iv_diary_pic = itemView.findViewById(R.id.iv_image);
            iv_favourite = itemView.findViewById(R.id.iv_favourite);
        }

    }

    private Date extractDate(String inputDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
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
        void addToFavourites(Diary diary);
        void removeFromFavourites(Diary diary);
    }

}
