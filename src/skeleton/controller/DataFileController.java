package skeleton.controller;

import skeleton.model.DailyLog;
import skeleton.model.Data;
import skeleton.model.FoodCollection;

public class DataFileController {
    private FoodCollection fc;
    private DailyLog dl;
    private Data dataFileType;

    public DataFileController(FoodCollection fc, Data dataFileType) {
        this.fc = fc;
        this.dataFileType = dataFileType;
    }

    public void saveFoods() {
        fc.saveToFile(dataFileType);
    }

    public DataFileController(DailyLog dl, Data dataFileType) {
        this.dl = dl;
        this.dataFileType = dataFileType;
    }

    public void saveDailyLog() {
        dl.writeTo();
    }
}
