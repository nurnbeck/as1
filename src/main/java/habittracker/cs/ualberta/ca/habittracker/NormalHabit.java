package habittracker.cs.ualberta.ca.habittracker;

/**
 * Created by watts1 on 9/13/16.
 */
public class NormalHabit extends Habit implements Habitable {

    public NormalHabit(String message){
        super(message);
    }

    @Override
    public Boolean isComplete() {
        return Boolean.FALSE;
    }
}
