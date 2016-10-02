package habittracker.cs.ualberta.ca.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class oldHabitTrackerActivity extends Activity {

    private static final String FILENAME = "file.sav";
    // private EditText bodyText;
    private ListView myHabitsList;
    private ArrayList<Habit> oldhabitList = new ArrayList<Habit>();
    //private HabitList habitList;
    private ArrayAdapter<Habit> adapter;
    /* Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.old_habit);
            final ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,
                    oldhabitList);
            Button BackButton = (Button) findViewById(R.id.Back);
            Button clearButton = (Button) findViewById(R.id.clear);
            Button deleteButton = (Button) findViewById(R.id.delete);
            myHabitsList = (ListView)  findViewById(R.id.Habits_old);

        BackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

                //POSSIBLE ERROR
                //Habit newHabit = new NormalHabit(text);
                //habitList.add(newHabit);
                //adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(oldHabitTrackerActivity.this, HabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                oldhabitList.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                oldhabitList.clear();
                adapter.notifyDataSetChanged();

                saveInFile();
                Intent intent = new Intent(oldHabitTrackerActivity.this, DeleteOldHabitActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
/////////////////////////////////////////////////////////////
    //on startup
        @Override
        protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, oldhabitList);
        myHabitsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<NormalHabit>>() {
            }.getType();

            oldhabitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            oldhabitList = new ArrayList<Habit>();
            //habitList = new HabitList();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(oldhabitList, out);
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
}
