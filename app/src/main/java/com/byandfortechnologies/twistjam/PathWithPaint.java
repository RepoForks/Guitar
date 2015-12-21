package com.byandfortechnologies.twistjam;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by William on 12/21/2015.
 */
public class PathWithPaint {
    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    private Paint mPaint;

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
