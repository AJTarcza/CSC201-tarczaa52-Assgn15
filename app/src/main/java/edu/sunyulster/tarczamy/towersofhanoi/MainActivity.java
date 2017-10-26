package edu.sunyulster.tarczamy.towersofhanoi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));

    }

}

class DrawView extends View {

    private Rect rectangle;
    private Paint paint;
    private ShapeDrawable mDrawable;

    public DrawView(Context context) {
        super(context);

        int x = 10;
        int y = 10;
        int width = 100;
        int height = 50;

        mDrawable = new ShapeDrawable(new RectShape());

        mDrawable.getPaint().setColor(Color.BLACK);

        mDrawable.setBounds(x, y, width, height);

        /*int x = 50;
        int y = 50;
        int sideLength = 200;

        // create a rectangle that we'll draw later
        rectangle = new Rect(x, y, sideLength, sideLength);

        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.BLACK);*/

        Animation animation1 = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        animation1.setDuration(2000);
        //mDrawable.startAnimation(animation1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.WHITE);
        //canvas.drawRect(rectangle, paint);
        mDrawable.draw(canvas);
    }

}
