package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class DetailsPage extends AppCompatActivity {
    Button signOutGoogle;

    private ImageButton femaleGenderBtn;
    private ImageButton maleGenderBtn;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextAge;
    private EditText editTextFatPercent;
    private DataBase dataBase;
    private ProgressBar progressBar;
    private static final String TAG = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_details_page);

        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
        editTextAge = findViewById(R.id.age);
        editTextFatPercent = findViewById(R.id.percent_body_fat);
        femaleGenderBtn = findViewById(R.id.femaleBtn);
        maleGenderBtn = findViewById(R.id.maleBtn);
        dataBase = DataBase.getInstance();
        progressBar = findViewById(R.id.progress_bar_details_page);
        final Button finishDetailsBtn = findViewById(R.id.finish_details_btn);
        //  signOutGoogle = findViewById(R.id.sign_out_google_btn);

        progressBar.setVisibility(View.GONE);

//        femaleGenderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setSelected(!v.isSelected());
//
//                if(v.isSelected()){
//
//                }
//                else
//                {
//
//                }
//            }
//        });



        finishDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                updateDetails();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(DetailsPage.this, DetailsPage.this.getString(R.string.details_save), duration);
                toast.show();
            }
        });


        femaleGenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonEffect(v);
            }
        });

        //Workout workout = new Workout("Workout 2", R.drawable.incline_bench_press);

        //    DataBase dataBase = DataBase.getInstance();

        //  dataBase.addWorkout(workout);

        //    Exercise exercise = new Exercise("Exercise 1", R.drawable.incline_bench_press);

        //    dataBase.addExercise(exercise, "Workout 1");


    }

    public void updateDetails() {

        String heightCheck =editTextHeight.getText().toString().trim();
        String weightCheck = editTextWeight.getText().toString().trim();
        String ageCheck = editTextAge.getText().toString().trim();
        String fatPercentCheck = editTextFatPercent.getText().toString().trim();

//        double heightCheck = Double.parseDouble(editTextHeight.getText().toString().trim());
//        double weightCheck = Double.parseDouble(editTextWeight.getText().toString().trim());
//        double ageCheck = Double.parseDouble(editTextAge.getText().toString().trim());
//        double fatPercentCheck = Double.parseDouble(editTextFatPercent.getText().toString().trim());

//        String heightCheck = Double.toString(height);
//        String weightCheck = Double.toString(weight);
//        String ageCheck = Double.toString(age);
//        String fatPercentCheck = Double.toString(fatPercent);

        Log.d(TAG,"Hight is:" + heightCheck);

        if(heightCheck.isEmpty()){
            editTextHeight.setError(DetailsPage.this.getString(R.string.height_rquiered));
            editTextHeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(heightCheck.length() > 25){
            editTextHeight.setError(DetailsPage.this.getString(R.string.string_length));
            editTextHeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(weightCheck.isEmpty()){
            editTextWeight.setError(DetailsPage.this.getString(R.string.weight_rquiered));
            editTextWeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(weightCheck.length() > 25){
            editTextWeight.setError(DetailsPage.this.getString(R.string.string_length));
            editTextWeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(ageCheck.isEmpty()){
            editTextAge.setError(DetailsPage.this.getString(R.string.age_requiered));
            editTextAge.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(ageCheck.length() > 25){
            editTextAge.setError(DetailsPage.this.getString(R.string.string_length));
            editTextAge.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(fatPercentCheck.isEmpty()){
            editTextFatPercent.setError(DetailsPage.this.getString(R.string.fat_percent_rquierd));
            editTextFatPercent.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(fatPercentCheck.length() > 25){
            editTextFatPercent.setError(DetailsPage.this.getString(R.string.string_length));
            editTextFatPercent.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        double height = Double.parseDouble(editTextHeight.getText().toString().trim());
        double weight = Double.parseDouble(editTextWeight.getText().toString().trim());
        double age = Double.parseDouble(editTextAge.getText().toString().trim());
        double fatPercent = Double.parseDouble(editTextFatPercent.getText().toString().trim());

        MyDetails myDetails = new MyDetails(height,weight,age,fatPercent);
        dataBase.updateMyDetails(myDetails,this);

    }

    public static void buttonEffect(View v){
        v.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        //  v.getBackground().clearColorFilter();
                        // v.invalidate();
                        v.getBackground();
                        //    v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

}



//        signOutGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mGoogleSignInClient.signOut()
//                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                            }
//                            //FirebaseAuth.getInstance().signOut();
//                            // Intent intent = new Intent(DetailsPage.this,MainActivity.class);
//                            //   startActivity(intent);
//                        });


//        private void revokeAccess() {
//            mGoogleSignInClient.revokeAccess()
//                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            // ...
//                        }
//                    });
//        }



//
//        });
//    }
//}




