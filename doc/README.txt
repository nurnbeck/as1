Habit Tracker Documentation:

VIDEO DEMONSTRATION:

https://www.youtube.com/watch?v=4RNcYiACK0k


Habit Tracker is an app that is designed to accomplish the following tasks:

-view the habit completions
-add new habit
-complete a habit for the day
-delete habits
-delete past habit completions
-see per habit, how many times the task has been fulfilled

////////////////////////////////////////////////////////////////////////
Is implemented as 4 views:

main:

view current habits
complete current habits
choose to go to add new habit, old habits, or delete habits

add Habit:

add a new habit and confirm options with add button

del Habit:

click on habits to delete them

old Habit Main:

Click on habits to delete them

////////////////////////////////////////////////////////////////
Classes:

Habits com in completed(records) and incompleted(normal)

HabitList is a controller for Habits

The activitys are views for

HabitList methods 

Habits is ArrayList of Habits

addHabit(Habit)

hasHabit(Habit) checks if habit is in our list of habits

getHabit(int ) gets a habit from our list of habits

getHabits() returns list of Habits


remove(Habit) removes habit from habits

public int getCount() returns size of list

/////////////////////////////////////////////////////////////////
Habit Methods:

Habit's title and content:
private String message;

Date created/settable date:
private Date date;

These store which days the habit occurs on and the print message
String Monday
String Tuesday
Sring Wednesday
String Thursday
String Friday
String Saturday
String Sunday

Stores the number of time the habit was completed:
int Count

bool of if it is complete or not
Boolean isComplete();

//supply day index
public void resDay(int x) reset index x where 0 = monday, day habit to not be on that day

void setDay(int x) set day x where 0 = monday to occur that day

void setMessage(String message) set message (max length 140)
  

setDate(Date) set new date

void incCount() increment count

String getMessage() return message

int getCount() return Count

Date getDate() return date

String toString() convert Habit to usable string to be outputed
 
int compareTo(compareHabit)
