package habittracker.cs.ualberta.ca.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.EditText;

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
import java.util.Collection;
import java.util.List;

/**
 * Created by Nolan on 9/30/2016.
 */

public class AddHabitActivity  extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    //private ListView myHabitsList;

    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    //private HabitList habitList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view to add Habit(item) view and body text field
        setContentView(R.layout.add_item);
        bodyText = (EditText) findViewById(R.id.body);
        //Activity transition buttons
        Button backButton = (Button) findViewById(R.id.back);
        Button AddButton = (Button) findViewById(R.id.AddHabit);
        //check boxes of weekdays
        final CheckBox checkboxMon = (CheckBox) findViewById(R.id.checkBoxMonday);
        final CheckBox checkboxTues = (CheckBox) findViewById(R.id.checkBoxTuesday);
        final CheckBox checkboxWed = (CheckBox) findViewById(R.id.checkBoxWednesday);
        final CheckBox checkboxThurs = (CheckBox) findViewById(R.id.checkBoxThursday);
        final CheckBox checkboxFri = (CheckBox) findViewById(R.id.checkBoxFriday);
        final CheckBox checkboxSat = (CheckBox) findViewById(R.id.checkBoxSaturday);
        final CheckBox checkboxSun = (CheckBox) findViewById(R.id.checkBoxSunday);

        // adapter setup
        //ListView listView = (ListView) findViewById(R.id.studentListView);
        //Collection<Habit> Habits = HabitList;
        //final ArrayList<Habit> list = new ArrayList<Habit>(Habits);
        final ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1,
                habitList);
        //private ArrayList<Habit> habitList = new ArrayList<Habit>();
        //private ArrayAdapter<Habit> adapter;

        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                saveInFile();
                Intent intent = new Intent(AddHabitActivity.this, HabitTrackerActivity.class);
                startActivity(intent);
                finish();
                //POSSIBLE ERROR

                //Habit newHabit = new NormalHabit(text);

                //habitList.add(newHabit);
                //adapter.notifyDataSetChanged();


            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                saveInFile();
                Intent intent = new Intent(AddHabitActivity.this, HabitTrackerActivity.class);
                startActivity(intent);
                finish();
                //adapter.notifyDataSetChanged();
            }
        });
        AddButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String text = bodyText.getText().toString();
                //testing
                Habit newHabit = new NormalHabit(text);
                //evaluate check boxes
                /*
                newHabit.resDay(0);
                newHabit.resDay(1);
                newHabit.resDay(2);
                newHabit.resDay(3);
                newHabit.resDay(4);
                newHabit.resDay(5);
                newHabit.resDay(6);
                */
                if (( checkboxMon ).isChecked()){
                    newHabit.setDay(0);}
                if (( checkboxTues ).isChecked()){
                    newHabit.setDay(1);}
                if (( checkboxWed ).isChecked()){
                    newHabit.setDay(2);}
                if (( checkboxThurs ).isChecked()){
                    newHabit.setDay(3);}
                if (( checkboxFri ).isChecked()){
                    newHabit.setDay(4);}
                if (( checkboxSat ).isChecked()){
                    newHabit.setDay(5);}
                if (( checkboxSun ).isChecked()){
                    newHabit.setDay(6);}
                habitList.add(newHabit);
                bodyText.getText().clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                Intent intent = new Intent(AddHabitActivity.this, HabitTrackerActivity.class);
                startActivity(intent);
                finish();
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