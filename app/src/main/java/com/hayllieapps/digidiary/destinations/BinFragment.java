package com.hayllieapps.digidiary.destinations;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.adapters.BinAdapter;
import com.hayllieapps.digidiary.models.Diary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.hayllieapps.digidiary.AppActivity.sqLiteHelper;


public class BinFragment extends Fragment {

    RecyclerView rv_bin;
    BinAdapter binAdapter;
    TextView restore;
    TextView delete;

    public static List<Diary> binList;

    public BinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);


        rv_bin = view.findViewById(R.id.rv_bin);
        binList = new ArrayList<>();
        getDiariesinBin(view);

        restore = view.findViewById(R.id.restore);
        delete = view.findViewById(R.id.delete);

        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vx) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.alert);
                builder.setMessage(R.string.confirm_restore);
                builder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ArrayList<Integer> selectedIdsList = new ArrayList<>();
                        for (Diary diary : binAdapter.selectedDiaries){
                            selectedIdsList.add(diary.getLocaldiary_id());
                        }
                        sqLiteHelper.restoreDiaries(selectedIdsList);
                        binAdapter.selectedDiaries.clear();
                        getDiariesinBin(view);
                        binAdapter.notifyDataSetChanged();
                        restore.setVisibility(View.GONE);
                        delete.setVisibility(View.GONE);
                    }
                });
                builder.setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnPositive.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnPositive.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnNegative.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnNegative.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.alert);
                builder.setMessage(R.string.confirm_permanent_delete);
                builder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ArrayList<Integer> selectedIdsList = new ArrayList<>();
                        for (Diary diary : binAdapter.selectedDiaries){
                            selectedIdsList.add(diary.getLocaldiary_id());
                        }
                        sqLiteHelper.deleteDiaries(selectedIdsList);
                        binAdapter.selectedDiaries.clear();
                        getDiariesinBin(view);
                        binAdapter.notifyDataSetChanged();
                        restore.setVisibility(View.GONE);
                        delete.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnPositive.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnPositive.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnNegative.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnNegative.setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        });
    }

    private void getDiariesinBin(final View view) {
        binAdapter = new BinAdapter(binList, new BinAdapter.SelectionPropagator() {
            @Override
            public void propagateSelectionState(Boolean isSelectionDisabled) {
                if(isSelectionDisabled) {
                    restore.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                } else {
                    restore.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }
            }

        });

        Cursor cursorData = sqLiteHelper.getData("SELECT Id, text, text2, diarydate, lastupdated, favourite FROM DIARIES WHERE deleted = 1");
        Cursor cursorImages = sqLiteHelper.getData("SELECT image FROM DIARIES WHERE deleted = 1");
        try{

            int id;
            String text1;
            String text2;
            String diarydate;
            String lastupdated;
            byte[] image;
            int favourite;

            binList.clear();

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

                        binList.add(diary);


                    } while (cursorData.moveToNext());
                    cursorData.close();
                }
                binAdapter.notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "e", Toast.LENGTH_LONG).show();
        } finally {
            if(cursorData != null) { cursorData.close(); }
            if(cursorImages != null) { cursorImages.close(); }
            RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getActivity());
            rv_bin.setLayoutManager(reLayoutManager);
            rv_bin.setItemAnimator(new DefaultItemAnimator());
            rv_bin.setAdapter(binAdapter);
        }
    }
}
