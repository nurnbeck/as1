package habittracker.cs.ualberta.ca.habittracker;

/**
 * Created by Nolan on 9/16/2016.
 */
public class CompleteHabit extends Habit {
    public CompleteHabit(String message){
        super(message);
    }

    @Override
    public Boolean isComplete(){
        return Boolean.TRUE;
    }
}
