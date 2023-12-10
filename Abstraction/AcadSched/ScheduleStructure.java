package AcadSched;
// Hey there, start typing your Java code here...

abstract class ScheduleStructure {
    private int currentPeriodNumber; // 1 for first sem, 2 for 2nd

    public ScheduleStructure(int currentPeriodNumber) {
        this.currentPeriodNumber = currentPeriodNumber;
    }

    public int getCurrentPeriodNumber() {
        return this.currentPeriodNumber;
    }

    abstract int getDaysRemaining(); // return no. of days remaining in the current period
    abstract int getDaysUntilNextPeriod(); // return no. of days until next period
}