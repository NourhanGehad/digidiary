package com.hayllieapps.digidiary.destinations;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hayllieapps.digidiary.AppActivity;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.models.Diary;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DiaryDetailsFragment extends Fragment {

    Diary diary;

    TextView edit;
    TextView date;
    ImageView diaryImage;
    TextView text1;
    TextView text2;
    Bitmap bitmap;
    ImageView delete;

    public DiaryDetailsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);

        diary = DiaryDetailsFragmentArgs.fromBundle(getArguments()).getDiaryToDisplay();

        edit = view.findViewById(R.id.edit);
        date = view.findViewById(R.id.tv_date);
        diaryImage = view.findViewById(R.id.iv_image);
        text1 = view.findViewById(R.id.tv_diary_title);
        text2 = view.findViewById(R.id.tv_diary_desc);
        delete = view.findViewById(R.id.iv_delete);

        updateUI();
        handleUserInteractions(view);
    }

    private void updateUI(){
        byte[] image = diary.getImage();
        bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        diaryImage.setImageBitmap(bitmap);

        date.setText(diary.getDiarydate());

        text1.setText(diary.getText1());
        text2.setText(diary.getText2());
    }

    private void handleUserInteractions(final View view){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action =
                        DiaryDetailsFragmentDirections.actionDetailsToEdit().setDiaryToEdit(diary);
                Navigation.findNavController(view).navigate(action);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.alert);
                builder.setMessage(R.string.confirm_soft_delete);
                builder.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AppActivity.sqLiteHelper.softDeleteDiary(diary.getLocaldiary_id());
                        Navigation.findNavController(view).navigateUp();
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

}
