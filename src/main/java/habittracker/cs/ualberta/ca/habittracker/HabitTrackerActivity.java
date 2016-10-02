package habittracker.cs.ualberta.ca.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.view.View;
import android.widget.AdapterView;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HabitTrackerActivity extends Activity {

    private static final String FILENAME = "file.sav";
    //private EditText bodyText;
    private ListView myHabitsList;
    private ArrayList<Habit> oldhabitList = new ArrayList<Habit>();
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    //private HabitList habitList;
    //private List<Habit> ThehabitList =  new ArrayList<Habit>(); // habitList.getHabits();
    //List<Habit> myList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;

    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //bodyText = (EditText) findViewById(R.id.body);
        Button historyButton = (Button) findViewById(R.id.history);
        Button d_transitionButton = (Button) findViewById(R.id.d_transition);
        Button a_transitionButton = (Button) findViewById(R.id.a_transition);
        myHabitsList = (ListView)  findViewById(R.id.Habits);

        historyButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                ////////
                //String text = bodyText.getText().toString();
                //Habit newHabit = new NormalHabit(text);
                //habitList.add(newHabit);
                //bodyText.getText().clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(HabitTrackerActivity.this, oldHabitTrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        d_transitionButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                //habitList.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(HabitTrackerActivity.this, delHabitActivity.class);
                startActivity(intent);
                finish();
            }
        });
        a_transitionButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                //habitList.clear();
                adapter.notifyDataSetChanged();

                saveInFile();
                Intent intent = new Intent(HabitTrackerActivity.this, AddHabitActivity.class);
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
                habit.incCount();
                oldhabitList.add(habit);
                adapter.notifyDataSetChanged();
                saveInFile();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitList);
        myHabitsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

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

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            //ThehabitList = habitList.getHabits();
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
}
