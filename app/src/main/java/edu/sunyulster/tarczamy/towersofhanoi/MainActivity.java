package edu.sunyulster.tarczamy.towersofhanoi;

import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    int[] pegALoc;
    int pegAX;
    int[] pegBLoc;
    int pegBX;
    int[] pegCLoc = new int[2];
    int pegCX;

    Stack<ImageView> A;
    Stack<ImageView> B;
    Stack<ImageView> C;

    ImageView pegA;
    ImageView pegB;
    ImageView pegC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        A = new Stack<ImageView>();
        B = new Stack<ImageView>();
        C = new Stack<ImageView>();

        final ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.constraint01);

        for (int i = 0; i < 3; i++) {
            // Initialize a new ImageView widget
            ImageView iv = new ImageView(getApplicationContext());

            // Set an image for ImageView
            iv.setImageResource(R.drawable.ring);

            // Create layout parameters for ImageView
            LayoutParams lp = new LayoutParams(i + 200, i + 100);

            // Add rule to layout parameters
            // Add the ImageView below to Button
            lp.addRule(RelativeLayout.BELOW);

            // Add layout parameters to ImageView
            iv.setLayoutParams(lp);

            iv.setX(i * 100);
            iv.setY(i * 100);

            // Finally, add the ImageView to layout
            rl.addView(iv);
            A.push(iv);
        }

        pegA = (ImageView) findViewById(R.id.peg1);
        pegB = (ImageView) findViewById(R.id.peg2);
        pegC = (ImageView) findViewById(R.id.peg3);

    }

    @Override
     public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
         //Get the xy coordinates of each peg
        pegALoc = new int[2];
        pegA.getLocationOnScreen(pegALoc);

        pegAX = pegALoc[0];

        pegBLoc = new int[2];
        pegB.getLocationOnScreen(pegBLoc);

        pegBX = pegBLoc[0];

        pegCLoc = new int[2];
        pegC.getLocationOnScreen(pegCLoc);

        pegCX = pegCLoc[0];

        int n = A.size();

        moveRings(n, A, B, C, pegAX, pegBX, pegCX);
    }

    public static void moveRings(int n, Stack pegA, Stack pegB, Stack pegC, int ax, int bx, int cx) {

        if (n > 0) {
            ImageView top = (ImageView) pegA.peek();
            //Move n-1 rings from A to B.
            Animation animation1 = new TranslateAnimation(0.0f, bx, 0.0f, 0.0f);
            animation1.setFillEnabled(true);
            animation1.setDuration(3000);
            top.startAnimation(animation1);

            moveRings(n-1, pegA, pegC, pegB, ax, cx, bx);

            //Move disc n from A to C
            ImageView poppedValue = (ImageView) pegA.pop();
            pegC.push(poppedValue);

            Animation animation2 = new TranslateAnimation(0.0f, cx, 0.0f, 0.0f);
            animation2.setFillAfter(true);
            animation2.setDuration(3000);
            poppedValue.startAnimation(animation2);

            //Move n-1 discs from B to C so they sit on disc n
            moveRings(n-1, pegB, pegA, pegC, bx, ax, cx);
        }	//End if

    }	//End moveRings

}