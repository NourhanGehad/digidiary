package com.hayllieapps.digidiary.destinations;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.hayllieapps.digidiary.AppActivity;
import com.hayllieapps.digidiary.BaseBackPressedListener;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.adapters.MiniDiaryAdapter;
import com.hayllieapps.digidiary.models.Diary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.hayllieapps.digidiary.AppActivity.sqLiteHelper;

public class CalendarFragment extends Fragment  {

    CalendarView calendarView;
    ImageView addDiary;
    String currentStringDate = "";

    RecyclerView rv_miniDiary;
    MiniDiaryAdapter miniDiaryAdapter = null;
    List<Diary> miniDiaryList;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppActivity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.VISIBLE);

        rv_miniDiary = view.findViewById(R.id.rv_miniDiary);
        miniDiaryList = new ArrayList<>();

        calendarView = view.findViewById(R.id.calendarView);
        addDiary = view.findViewById(R.id.iv_add);

        goOn(getToday(), view);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView viewx, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
                currentStringDate = format.format(calendar.getTime());

                goOn(currentStringDate, view);
            }
        });

        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action =
                        CalendarFragmentDirections.actionToAddNewDiary().setSelectedDate(currentStringDate);
                Navigation.findNavController(view).navigate(action);
            }
        });

    }

    public String getToday (){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);//formating according to my need"EEE, MMM dd yyyy"
        String date = formatter.format(today);
        return  date;
    }

    private void goOn(String dateSelected, final View view) {

        miniDiaryAdapter = new MiniDiaryAdapter(miniDiaryList, new MiniDiaryAdapter.SelectionPropagator() {
            @Override
            public void propagateSelection(Diary diary) {
                NavDirections action =
                        CalendarFragmentDirections.actionCalendarToDetails().setDiaryToDisplay(diary);
                Navigation.findNavController(view).navigate(action);
            }

        });

        Cursor cursorData = sqLiteHelper.getData("SELECT Id, text, text2, diarydate, lastupdated, favourite FROM DIARIES WHERE diarydate = '" + dateSelected +"' AND deleted = 0");
        Cursor cursorImages = sqLiteHelper.getData("SELECT image FROM DIARIES WHERE diarydate = '" + dateSelected + "' AND deleted = 0");

        try{

            int id;
            String text1;
            String text2;
            String diarydate;
            String lastupdated;
            byte[] image;
            int favourite;

            miniDiaryList.clear();

            int cursorDataPosition =0;
            if(cursorData != null && cursorData.getCount() > 0){
                if(cursorData.moveToFirst()){
                    do{
                        id = cursorData.getInt(0);
                        text1 = cursorData.getString(1);
                        text2 = cursorData.getString(2);
                        diarydate = cursorData.getString(3);
                        lastupdated = cursorData.getString(4);
                        favourite = cursorData.getInt(5);


                        cursorDataPosition = cursorData.getPosition();

                        int colIndex = cursorImages.getColumnIndex("image");

                        cursorImages.moveToPosition(cursorDataPosition);
                        int currentcursorImagePosition = cursorImages.getPosition();
                        Log.i("cursorImagePosition",String.valueOf(currentcursorImagePosition));
                        image = cursorImages.getBlob(colIndex);
                        Log.i("cursorImagePosition2",String.valueOf(currentcursorImagePosition));


                        Diary diary = new Diary(id, text1, text2, diarydate, lastupdated, image, 0, favourite);

                        miniDiaryList.add(diary);


                    } while (cursorData.moveToNext());
                    cursorData.close();
                }
                miniDiaryAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "e", Toast.LENGTH_LONG).show();
        } finally {
            if(cursorData != null) { cursorData.close(); }
            if(cursorImages != null) { cursorImages.close(); }
            RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getActivity());
            rv_miniDiary.setLayoutManager(reLayoutManager);
            rv_miniDiary.setItemAnimator(new DefaultItemAnimator());
            rv_miniDiary.setAdapter(miniDiaryAdapter);
        }

    }

}
