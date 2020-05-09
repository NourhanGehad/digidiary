package com.hayllieapps.digidiary.destinations;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hayllieapps.digidiary.AppActivity;
import com.hayllieapps.digidiary.R;
import com.hayllieapps.digidiary.models.Diary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class DiaryEditFragment extends Fragment {

    final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 2;

    Diary diary;

    TextView save;
    TextView date;
    ImageView diaryImage;
    ImageView calendarIcon;
    ImageView changePicture;
    EditText text1;
    EditText text2;
    Bitmap bitmap;


    private Calendar myCalendar = Calendar.getInstance();

    public DiaryEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);

        diary = DiaryEditFragmentArgs.fromBundle(getArguments()).getDiaryToEdit();

        save = view.findViewById(R.id.save);
        date = view.findViewById(R.id.tv_date);
        diaryImage = view.findViewById(R.id.iv_diary_image);
        text1 = view.findViewById(R.id.et_diary_title);
        text2 = view.findViewById(R.id.et_diary_desc);
        calendarIcon = view.findViewById(R.id.iv_calendar);
        changePicture = view.findViewById(R.id.iv_change_picture);

        checkPermissions();
        updateUI();
        handleUserInteractions(view);

    }

    private void updateUI() {
        byte[] image = diary.getImage();
        bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        diaryImage.setImageBitmap(bitmap);

        date.setText(diary.getDiarydate());
        text1.setText(diary.getText1());
        text2.setText(diary.getText2());
    }

    private void handleUserInteractions(final View view) {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1String = text1.getText().toString().trim();
                String text2String = text2.getText().toString().trim();

                if (text1.length() > 0 || text2.length() > 0) {
                    try {
                        AppActivity.sqLiteHelper.updateData(
                                text1String,
                                text2String,
                                date.getText().toString().trim(),
                                getNow(),
                                imageViewToByte(diaryImage),
                                diary.getLocaldiary_id()
                        );
                        Toast.makeText(getActivity(), "Diary updated successfully", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigateUp();
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(), "Opss! couldn't save it", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Opss! Looks like you forgot to fill your diary", Toast.LENGTH_SHORT).show();

                }

            }


        });

        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MMM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                date.setText(sdf.format(myCalendar.getTime()));
            }

        };

        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        changePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose a diary image"), REQUEST_CODE_GALLERY);
            }
        });
    }


    private void checkPermissions() {
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        // If do not grant write external storage permission.
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            // Request user to grant write external storage permission.
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION);
        }
    }

    private byte[] imageViewToByte(ImageView iv_diary) {
        Bitmap sbitmap = ((BitmapDrawable) iv_diary.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        sbitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream);
        byte[] byteToArray = stream.toByteArray();

        return byteToArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose a diary image"), REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getActivity(), "Coudn't access without permission", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION) {
            int grantResultsLength = grantResults.length;
            if (grantResultsLength > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "You grant read external storage permission. Please click original button again to continue.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "You denied read external storage permission.", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    public String getNow() {

        Date now = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd-hh:mm", Locale.ENGLISH);//formating according to my need EEE MMM dd hh:mm:ss yyyy
        String dateNow = formatter.format(now);
        Log.i("dateNow", dateNow);
        return dateNow;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                diaryImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
