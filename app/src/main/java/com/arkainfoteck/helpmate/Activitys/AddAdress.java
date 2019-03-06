package com.arkainfoteck.helpmate.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arkainfoteck.helpmate.R;

public class AddAdress extends AppCompatActivity {
    LinearLayout submit;
    EditText clandmark,house,locality,nearbyplace;
    DatabaseHelper databaseHelper;
    String clandmark1,house1,locality1,nearbyplace1;

    RadioGroup radioGroup;
    RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);
        submit=(LinearLayout)findViewById(R.id.submit);

        clandmark=(EditText)findViewById(R.id.clandmark);
        house=(EditText)findViewById(R.id.house);
        locality=(EditText)findViewById(R.id.locality);
        nearbyplace=(EditText)findViewById(R.id.nearbyplace);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb ) {
                    Toast.makeText(AddAdress.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });



        databaseHelper = new DatabaseHelper(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rb.getText().toString().isEmpty() || house.getText().toString().isEmpty() || locality.getText().toString().isEmpty() ){

                    Toast.makeText(AddAdress.this, "please fill details", Toast.LENGTH_SHORT).show();
                }else {

                    clandmark1= rb.getText().toString();
                    house1=house.getText().toString();
                    locality1=locality.getText().toString();
                    nearbyplace1=nearbyplace.getText().toString();
                    System.out.println("sendddata"+nearbyplace1);
                    databaseHelper.insertdata(clandmark1,house1,locality1,nearbyplace1);

                    Intent intent = new Intent(AddAdress.this,ChangeArea.class);
                    startActivity(intent);

                    house.setText("");
                    locality.setText("");
                    nearbyplace.setText("");

                }

            }
        });
    }

}
