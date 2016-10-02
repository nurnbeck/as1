package habittracker.cs.ualberta.ca.habittracker;
import android.print.PrinterId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by watts1 on 9/13/16.
 */
public abstract class Habit implements Comparable<Habit> {
    private String message;
    private Date date;
    //private List<String> Weekdays = new ArrayList<String>(200);
    //private String[] Weekdays = new String[7];
    //public String[] Weekdays = {"X","X","X","X","X","X","X"};
   //private List<String> Weekdays = new ArrayList<String>{"a","b","c"};
    private String Monday = " M:X |";
    private String Tuesday = " T:X |";
    private String Wednesday = " W:X |";
    private String Thursday = " R:X |";
    private String Friday = " F:X |";
    private String Saturday = " S:X |";
    private String Sunday = " S:X";
    private int Count = 0;
    //private ArrayList<Habit> HabitList;
    //public void HabitAdd(Habit NewHabit){
    //    HabitList.add(NewHabit);
    //}
    public Habit(String message){
        this.message = message;
        this.date = new Date();
    }

    public Habit(String message, Date date){
        this.message = message;
        this.date = new Date();
    }


    public abstract Boolean isComplete();

//supply day index
    public void resDay(int x){

        if (x == 0){Monday = " Mon:X |";}
        if (x == 1){Tuesday = " Tue:X |";}
        if (x == 2){Wednesday = " Wed:X |";}
        if (x == 3){Thursday = " Thu:X |";}
        if (x == 4){Friday = " Fri:X |";}
        if (x == 5){Saturday = " Sat:X |";}
        if (x == 6){Sunday = " Sun:X";}
    }
    public void setDay(int x){
        if (x == 0){Monday = " Mon:Y |";}
        if (x == 1){Tuesday = " Tue:Y |";}
        if (x == 2){Wednesday = " Wed:Y |";}
        if (x == 3){Thursday = " Thu:Y |";}
        if (x == 4){Friday = " Fri:Y |";}
        if (x == 5){Saturday = " Sat:Y |";}
        if (x == 6){Sunday = " Sun:Y";}
    }

    public void setMessage(String message) throws HabitTooLongException {
        if (message.length() > 140) {
            //Do something
            throw new HabitTooLongException();
        }
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void incCount() {
        this.Count += 1;
    }

    public String getMessage() {
        return message;
    }

    public int getCount(){ return Count; }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString(){
        //System.out.print(Weekdays[0]);
        //String X = Weekdays[0];
        return  Count + " | " + date.toString() + " | " + message + "\n"+ Monday + Tuesday +
                Wednesday + Thursday + Friday + Saturday + Sunday
                /*+ this.Weekdays[0] + this.Weekdays[1]+ this.Weekdays[2]+ this.Weekdays[3]
                + this.Weekdays[4]+ this.Weekdays[5]+ this.Weekdays[6]
                */;
    }

    public int compareTo(Habit compareHabit) {

        Date compareDate = ((Habit) compareHabit).getDate();
        //ascending order
        if (this.getDate().after(compareDate)) {
            return 1;
        }

        if (this.getDate().equals(compareDate)) {
            return 0;
        }
        return 1;
    }
}
