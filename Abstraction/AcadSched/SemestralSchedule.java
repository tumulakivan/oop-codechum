package AcadSched;
@SuppressWarnings("deprecation")

import java.util.Date;

public class SemestralSchedule extends ScheduleStructure {
    private Date[] pairs;
    private Date currentDate;

    public SemestralSchedule(Date currentDate, Date sem1Start, Date sem1End, Date sem2Start, Date sem2End, int currentPeriodNumber) {
        super(currentPeriodNumber);
        this.pairs = new Date[]{sem1Start, sem1End, sem2Start, sem2End};
        this.currentDate = currentDate;
    }

    public int dateDiff(Date date1, Date date2) {
        // INSERT UTILITY METHOD HERE
        if(this.getCurrentPeriodNumber() == 2) {
            
        }
}