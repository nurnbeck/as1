package habittracker.cs.ualberta.ca.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Nolan on 9/30/2016.
 */

public class DeleteOldHabitActivity extends Activity {


    private static final String FILENAME = "file.sav";
    private EditText bodytext;
    private ListView myHabitsList;

    private ArrayList<Habit> habitList = new ArrayList<Habit>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.del_old_habit);
        Button backButton = (Button) findViewById(R.id.back);
        Button delButton = (Button) findViewById(R.id.del);
        Button clearButton = (Button) findViewById(R.id.clear);
        myHabitsList = (ListView) findViewById(R.id.oldHabits);

        final ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, habitList);

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                //Habit newHabit = new NormalHabit(text);
                //habitList.add(newHabit);
                //adapter.notifyDataSetChanged();
                //saveInFile();
                Intent intent = new Intent(DeleteOldHabitActivity.this, oldHabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                habitList.clear();


                //clean up and setup of new Activity
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(DeleteOldHabitActivity.this, oldHabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                habitList.clear();

                //our clean up after clearing
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(DeleteOldHabitActivity.this, oldHabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(DeleteOldHabitActivity.this, oldHabitTrackerActivity.class);
                startActivity(intent);
                //habitList.clear();
                //adapter.notifyDataSetChanged();

                saveInFile();
            }
        });

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        //adapter = new ArrayAdapter<Habit>(this,R.layout.list_item, habitList);
        //myHabitsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<NormalHabit>>() {
            }.getType();

            habitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}