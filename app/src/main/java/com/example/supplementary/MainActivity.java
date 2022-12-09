package com.example.supplementary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.example.supplementary.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtitem, edtprice,edtdesccription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtitem = findViewById(R.id.editTextTextPersonName2);
        edtprice = findViewById(R.id.editTextTextPassword);
        edtdesccription = findViewById(R.id.editTextTextMultiLine);
    }

    public void saveUser(View view){
        String item = edtitem.getText().toString();
        String price = edtprice.getText().toString();
        String description = edtdescription.getText().toString();

        DBHelper dbHelper = new DBHelper(this);

        if(item.isEmpty()||price.isEmpty()||description.isEmpty()){
            Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dbHelper.addInfo(item,price,description);

            if(inserted>0){
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Something went wrong ;(", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);

        List info = dbHelper.readAllInfo();
        List items = new ArrayList<Integer>();

        String[] data = (String[]) info.toArray(new String[0]);

        //Toast.makeText(this, info.toString(), Toast.LENGTH_SHORT).show();

//        Snackbar snackbar = Snackbar.make(view, info.toString(),Snackbar.LENGTH_LONG);
//        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
//        snackbar.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Items");
        builder.setItems(data, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String userName = data[i].split(":")[0];
                edtitem.setText(item);
                edtprice.setText(price);
                edtdescription.setText(description);

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void deleteUser(View view){
        DBHelper dbHelper = new DBHelper(this);

        String userName = edtName.getText().toString();

        if(userName.isEmpty()){
            Toast.makeText(this, "Please select user to Delete", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.deleteInfo(item);
            Toast.makeText(this, "User deleted!", Toast.LENGTH_SHORT).show();

            edtitem.setText("");
            edtprice.setText("");
            edtdescription.setText("");


    }

    public void updateUser(View view){
        DBHelper dbHelper = new DBHelper(this);
        String item = edtitem.getText().toString();
        String price = edtprice.getText().toString();
        String description = edtdescription.getText().toString();


            if(item.isEmpty()||price.isEmpty()||description.isEmpty()){

        }else{
            dbHelper.updateInfo(view,item,price,description);
        }

    }



}