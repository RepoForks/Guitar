package com.byandfortechnologies.twistjam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    View mView;
    private Paint mPaint;
    private RelativeLayout mBoardLayout;
    int mRelativeLayoutWidth, mRelativeLayoutHeight;
    TranslateAnimation mBoardAnim;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBoardLayout = (RelativeLayout) findViewById(R.id.board_layout);
        context = MainActivity.this;


        ViewTreeObserver observer = mBoardLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                mRelativeLayoutWidth = mBoardLayout.getWidth();
                mRelativeLayoutHeight = mBoardLayout.getHeight();
                mBoardLayout.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                mBoardAnim = new TranslateAnimation(mRelativeLayoutWidth / 3-100, mRelativeLayoutWidth / 5-100, 20, mRelativeLayoutHeight - 100);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        TextView mChordsTextView = new TextView(context);
                        mChordsTextView.setText("AM");
                        mBoardLayout.addView(mChordsTextView, new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT
                        ));
                        mBoardAnim.setDuration(3000);
                        mBoardAnim.setFillAfter(true);
                        mChordsTextView.startAnimation(mBoardAnim);
                    }
                });

                Log.d("x+y: ", mRelativeLayoutWidth + "+" + mRelativeLayoutHeight);
            }
        });


        LinearLayout layout = (LinearLayout) findViewById(R.id.myDrawing);
        mView = new DrawingView(this);
        layout.addView(mView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        init();
    }


    //    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        // TODO Auto-generated method stub
//        super.onWindowFocusChanged(hasFocus);
//        updateSizeInfo();
//        mBoardAnim = new TranslateAnimation(mRelativeLayoutWidth / 3, mRelativeLayoutWidth / 5, 20, mRelativeLayoutHeight - 50);
//        mBoardAnim.setDuration(3000);
//        mBoardAnim.setFillAfter(true);
//        mChordsTextView.startAnimation(mBoardAnim);
//    }
//
    private void updateSizeInfo() {
        mBoardLayout = (RelativeLayout) findViewById(R.id.board_layout);
        mRelativeLayoutWidth = mBoardLayout.getWidth();
        mRelativeLayoutHeight = mBoardLayout.getHeight();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }

    class DrawingView extends View {
        private Path path;
        private Bitmap mBitmap;
        private Canvas mCanvas;

        public DrawingView(Context context) {
            super(context);
            path = new Path();
            mBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            this.setBackgroundColor(Color.BLACK);
        }

        private ArrayList<PathWithPaint> _graphics1 = new ArrayList<PathWithPaint>();

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            PathWithPaint pp = new PathWithPaint();
            mCanvas.drawPath(path, mPaint);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                path.moveTo(event.getX(), event.getY());
                path.lineTo(event.getX(), event.getY());
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                path.lineTo(event.getX(), event.getY());
                pp.setPath(path);
                pp.setmPaint(mPaint);
                _graphics1.add(pp);
            }
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (_graphics1.size() > 0) {
                canvas.drawPath(
                        _graphics1.get(_graphics1.size() - 1).getPath(),
                        _graphics1.get(_graphics1.size() - 1).getmPaint());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
