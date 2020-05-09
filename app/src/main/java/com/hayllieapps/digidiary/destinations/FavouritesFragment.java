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
import android.widget.Toast;

import com.hayllieapps.digidiary.AppActivity;
import com.hayllieapps.digidiary.BaseBackPressedListener;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.adapters.DiaryAdapter;
import com.hayllieapps.digidiary.models.Diary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.hayllieapps.digidiary.AppActivity.sqLiteHelper;


public class FavouritesFragment extends Fragment{

    RecyclerView rv_diaries;
    DiaryAdapter diaryAdapter;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppActivity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.VISIBLE);

        rv_diaries = view.findViewById(R.id.rv_fav_diary);
        AppActivity.favouriteDiariesList = new ArrayList<>();
        getFavouriteDiaries(view);
    }

    private void getFavouriteDiaries(final View view) {

        diaryAdapter = new DiaryAdapter(AppActivity.favouriteDiariesList, new DiaryAdapter.SelectionPropagator() {
            @Override
            public void propagateSelection(Diary diary) {
                NavDirections action =
                        FavouritesFragmentDirections.actionFavouritesToDetails().setDiaryToDisplay(diary);
                Navigation.findNavController(view).navigate(action);
            }

            @Override
            public void addToFavourites(Diary diary) {
            }

            @Override
            public void removeFromFavourites(Diary diary) {
                sqLiteHelper.removeDiaryFromFavourites(diary.getLocaldiary_id());
                AppActivity.favouriteDiariesList.remove(diary);
                diaryAdapter.notifyDataSetChanged();
            }

        });

        Cursor cursorData = sqLiteHelper.getData("SELECT Id, text, text2, diarydate, lastupdated, favourite FROM DIARIES WHERE deleted = 0 AND favourite = 1");
        Cursor cursorImages = sqLiteHelper.getData("SELECT image FROM DIARIES WHERE deleted = 0 AND favourite = 1");
        try{
            int id;
            String text1;
            String text2;
            String diarydate;
            String lastupdated;
            byte[] image;
            int favourite;

            AppActivity.favouriteDiariesList.clear();

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
                        int currentCursorImagePosition = cursorImages.getPosition();
                        Log.i("cursorImagePosition",String.valueOf(currentCursorImagePosition));
                        image = cursorImages.getBlob(colIndex);
                        Log.i("cursorImagePosition2",String.valueOf(currentCursorImagePosition));

                        String dh = text1;
                        Diary diary = new Diary(id, dh, text2, diarydate, lastupdated, image, 0, favourite);

                        AppActivity.favouriteDiariesList.add(diary);

                    } while (cursorData.moveToNext());
                    cursorData.close();
                    cursorImages.close();
                }

                diaryAdapter.notifyDataSetChanged();
                AppActivity.favouriteDiariesListSize = AppActivity.favouriteDiariesList.size();
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "e", Toast.LENGTH_LONG).show();
        }finally {
            if(cursorData != null) { cursorData.close(); }
            if(cursorImages != null) { cursorImages.close(); }
            RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getActivity());
            rv_diaries.setLayoutManager(reLayoutManager);
            rv_diaries.setItemAnimator(new DefaultItemAnimator());
            rv_diaries.setAdapter(diaryAdapter);
        }

    }

}
