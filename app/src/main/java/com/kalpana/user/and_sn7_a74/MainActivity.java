package com.kalpana.user.and_sn7_a74;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kalpana.user.and_sn7_a74.R;

import static android.R.attr.name;

public class MainActivity extends Activity {
    SQLiteDatabase db;
    TextView tv;
    EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize all view objects
        tv=(TextView)findViewById(R.id.textView1);
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText2);
        //create database if not already exist
        db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
        //create new table if not already exist
        db.execSQL("create table if not exists employee(Id varchar,first_name varchar, last_name varchar)");
    }
    //This method will call on when we click on insert button
    public void insert(View v)
    {
        String emp_id=et1.getText().toString();
        String fname=et2.getText().toString();
        String l_name=et3.getText().toString();
        et1.setText("");
        et2.setText("");
        //insert data into able
        db.execSQL("insert into employee values('"+emp_id+"','"+fname+"','"+l_name+"')");
        //display Toast
        Toast.makeText(this, "values inserted successfully.", Toast.LENGTH_LONG).show();
    }
    //This method will call when we click on display button
    public void display(View v)
    {
        //use cursor to keep all data
        //cursor can keep data of any data type
        Cursor c=db.rawQuery("select * from employee", null);
        tv.setText("");
        //move cursor to first position
        c.moveToFirst();
        //fetch all data one by one
        do
        {
            //we can use c.getString(0) here
            //or we can get data using column index
            String id =c.getString(0);
            String firstname=c.getString(1);
            String lastname=c.getString(2);

            //display on text view
            tv.append("ID:"+id+"First_Name:"+firstname+" and last name:"+lastname+"\n");
            //move next position until end of the data
        }while(c.moveToNext());
    }
}