package com.hayllieapps.digidiary.destinations;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hayllieapps.digidiary.AppActivity;
import com.hayllieapps.digidiary.BaseBackPressedListener;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.adapters.DiaryAdapter;
import com.hayllieapps.digidiary.models.Diary;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.hayllieapps.digidiary.AppActivity.sqLiteHelper;

public class HomeFragment extends Fragment{

    RecyclerView rv_diaries;
    DiaryAdapter diaryAdapter;
    
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppActivity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimpleDraweeView draweeView = view.findViewById(R.id.my_image_view);
        Uri myUri = Uri.parse("https://i.ibb.co/vx8dggj/congratsfresco.png");
        draweeView.setImageURI(myUri);


        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.VISIBLE);


        rv_diaries = view.findViewById(R.id.rv_diary);
        AppActivity.diaryList = new ArrayList<>();
        getDiaries(view);
    }

    private void getDiaries(final View view) {
        diaryAdapter = new DiaryAdapter(AppActivity.diaryList,new DiaryAdapter.SelectionPropagator() {
            @Override
            public void propagateSelection(Diary diary) {
                NavDirections action =
                        HomeFragmentDirections.actionHomeToDetails().setDiaryToDisplay(diary);
                Navigation.findNavController(view).navigate(action);
            }

            @Override
            public void addToFavourites(Diary diary) {
                sqLiteHelper.addDiaryToFavourites(diary.getLocaldiary_id());
            }

            @Override
            public void removeFromFavourites(Diary diary) {
                sqLiteHelper.removeDiaryFromFavourites(diary.getLocaldiary_id());

            }

        });

        Cursor cursorData = sqLiteHelper.getData("SELECT Id, text, text2, diarydate, lastupdated, favourite FROM DIARIES WHERE deleted = 0");
        Cursor cursorImages = sqLiteHelper.getData("SELECT image FROM DIARIES WHERE deleted = 0");

        try{
            int id;
            String text1;
            String text2;
            String diarydate;
            String lastupdated;
            int favourite;
            byte[] image;

            AppActivity.diaryList.clear();

            int cursorDataPosition = 1;
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
                        image = cursorImages.getBlob(colIndex);


                        Diary diary = new Diary(id, text1, text2, diarydate, lastupdated, image, 0, favourite);
                        AppActivity.diaryList.add(diary);
                    } while (cursorData.moveToNext());
                }

                diaryAdapter.notifyDataSetChanged();
                AppActivity.diaryListSize = AppActivity.diaryList.size();
            }

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "e", Toast.LENGTH_LONG).show();
        }finally {
            if(cursorData != null) {
                cursorData.close();
            }
            if(cursorImages != null) {
                cursorImages.close();
            }
            RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getActivity());
            rv_diaries.setLayoutManager(reLayoutManager);
            rv_diaries.setAdapter(diaryAdapter);
        }

    }
}
