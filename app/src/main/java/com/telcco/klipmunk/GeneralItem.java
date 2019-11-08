package com.telcco.klipmunk;

/**
 * Created by PHD on 2/7/2019.
 */

public  class GeneralItem extends ListItem {
    private ScreensModel screensModel;

    public ScreensModel getScreensModel(){
        return  screensModel;
    }
    public void setScreensModel(ScreensModel screensModel){
        this.screensModel = screensModel;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
