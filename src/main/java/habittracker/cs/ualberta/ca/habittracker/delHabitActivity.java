package habittracker.cs.ualberta.ca.habittracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;

/**
 * Created by Nolan on 9/30/2016.
 */

public class delHabitActivity  extends Activity {

    private static final String FILENAME = "file.sav";
    //private EditText bodytext;
    private ListView myHabitsList;
    //private HabitList habitList;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ALWAYS do set content view first
        setContentView(R.layout.del_habit);
        //final ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,habitList);
        //Button delButton = (Button) findViewById(R.id.delete_habits);
        Button backButton = (Button) findViewById(R.id.back);
        myHabitsList = (ListView) findViewById(R.id.Habits);

        /*
        delButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(delHabitActivity.this, HabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        */
        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(delHabitActivity.this, HabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        myHabitsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                final int finalPosition = position;
                Habit habit = habitList.get(finalPosition);
                habitList.remove(habit);
                adapter.notifyDataSetChanged();
                saveInFile();
                return true;
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
        adapter = new ArrayAdapter<Habit>(this,R.layout.list_item, habitList);
        myHabitsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<List<NormalHabit>>() {
            }.getType();

            habitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<Habit>();
            //habitList = new HabitList();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
