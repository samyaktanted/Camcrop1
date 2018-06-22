package com.example.d.camcrop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserActivity extends AppCompatActivity {

    EditText editText,editText2,editText4,editText5;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = editText.getText().toString();
                String contact = editText2.getText().toString();
                String address = editText4.getText().toString();
                String email = editText5.getText().toString();
                saveText(name,contact,address,email);
            }

        });

    }

    private void saveText(String name ,String contact ,String address ,String email ){
        String fileName = name + ".txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName);


        try {
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(contact.getBytes());
            fos.write(address.getBytes());
            fos.write(email.getBytes());
            fos.close();
            Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "FILE NOT FOUND", Toast.LENGTH_SHORT).show();
        } catch (IOException e){

            e.printStackTrace();
            Toast.makeText(this, "Error saving", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000 :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"PERMISSION GRANTED!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"PERMISSION NOT GRANTED!",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}
