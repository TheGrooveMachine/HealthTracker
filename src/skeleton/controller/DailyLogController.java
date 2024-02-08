package skeleton.controller;

import java.time.LocalDate;
import java.util.List;

import skeleton.model.DailyLog;
import skeleton.model.FoodCollection;
import skeleton.model.Log;

public class DailyLogController {

    private DailyLog dl;

    public DailyLogController(FoodCollection fc, DailyLog dl) {
        this.dl = dl;
    }

    public boolean removeDailyLogByDate(LocalDate date) {
        return dl.removeDailyLogDate(date);
    }

    public List<Log> searchDailyByDate(LocalDate date) {
        return dl.searchDailyDate(date);
    }

    public Log searchDailyByLog(Log log) {
        return dl.searchDailyLog(log);
    }

    public void addDailyLogToLog(Log log) {
        dl.addDailyLog(log);
    }

    public boolean writeFile() {
        return dl.writeTo();
    }
}
