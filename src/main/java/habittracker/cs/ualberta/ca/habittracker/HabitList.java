package habittracker.cs.ualberta.ca.habittracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nolan on 10/1/2016.
 */

public class HabitList {
    private List<Habit> Habits = new ArrayList<Habit>();

    public void addHabit(Habit habit) {
        Habits.add(habit);
    }

    public boolean hasHabit(Habit habit) {
        return Habits.contains(habit);
    }

    public Habit getHabit(int i) {
        return Habits.get(i);
    }

    public List<Habit> getHabits() {
        Collections.sort(Habits);
        return Habits;
    }

    public void remove(Habit habit) {
        Habits.remove(habit);
    }

    //returns size of list
    public int getCount() {
        return Habits.size();
    }

    public void doHabit(int i) {

        Habits.get(i).incCount();

        //return Habits.getHabit(habit.incCount());}

        return;
    }

}
