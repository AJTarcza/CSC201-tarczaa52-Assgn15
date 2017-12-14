package edu.sunyulster.tarczamy.towersofhanoi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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

    Button button;

    Stack<ImageView> A;
    Stack<ImageView> B;
    Stack<ImageView> C;
    int heightOffset;
    int xOffset;

    ImageView pegA;
    ImageView pegB;
    ImageView pegC;

    boolean runLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        A = new Stack<ImageView>();
        B = new Stack<ImageView>();
        C = new Stack<ImageView>();

        final ConstraintLayout rl = (ConstraintLayout) findViewById(R.id.constraint01);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        final int RINGS = bundle.getInt("RingAmount");

        heightOffset = 130;
        xOffset = 170;

        pegA = (ImageView) findViewById(R.id.peg1);
        pegB = (ImageView) findViewById(R.id.peg2);
        pegC = (ImageView) findViewById(R.id.peg3);

        //Create the rings
        for (int i = RINGS; i > 0; i--) {
            // Initialize a new ImageView widget
            ImageView iv = new ImageView(getApplicationContext());

            // Set an image for ImageView
            iv.setImageResource(R.drawable.ring);

            // Create layout parameters for ImageView
            LayoutParams lp = new LayoutParams(100 , 40);

            // Add rule to layout parameters
            // Add the ImageView below to Button
            lp.addRule(RelativeLayout.BELOW);

            // Add layout parameters to ImageView
            iv.setLayoutParams(lp);

            iv.setX(140);
            if(i < RINGS) {
                iv.setY(heightOffset + (i * 50));
            }

            else
                iv.setY(heightOffset + (i * 20));

            heightOffset = iv.getHeight();

            // Finally, add the ImageView to layout
            rl.addView(iv);
            A.push(iv);
        }

        button = (Button) findViewById(R.id.animateButton);

        button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int n = A.size();
                    moveRings(n, A, B, C, pegAX, pegBX, pegCX);
                }
            });
    }

    @Override
     public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
         //Get the xy coordinates of each peg

        //Get peg locations
        final int pegOffset = 110;

        pegALoc = new int[2];
        pegA.getLocationOnScreen(pegALoc);

        pegAX = pegALoc[0] + pegOffset;

        pegBLoc = new int[2];
        pegB.getLocationOnScreen(pegBLoc);

        pegBX = pegBLoc[0] + pegOffset;

        pegCLoc = new int[2];
        pegC.getLocationOnScreen(pegCLoc);

        pegCX = pegCLoc[0] + pegOffset;
    }

    //Recursively move the rings to the appropriate stacks
    public void moveRings(int n, Stack pegA, Stack pegB, Stack pegC, int ax, int bx, int cx) {

        if (n > 0) {
            ImageView poppedValue = (ImageView) pegA.pop();

            //Boolean for individualized animation logic
            runLogic = false;

            //Create animations
            ObjectAnimator moveToB = ObjectAnimator.ofFloat(poppedValue, "translationX", bx);
            moveToB.setDuration(3000);//set duration

            ObjectAnimator moveToC = ObjectAnimator.ofFloat(poppedValue, "translationX", cx);
            moveToC.setDuration(3000);//set duration

            //Animation ended listeners
            moveToB.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    runLogic = true;
                }
            });

            moveToC.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    runLogic = true;
                }
            });

            //start the animation
            moveToB.start();

            //If the animation has finished
            if(runLogic) {
                //Move n-1 rings from A to B.
                moveRings(n - 1, pegA, pegC, pegB, ax, cx, bx);

                //Move disc n from A to C
                pegC.push(poppedValue);
            }   //End if

            runLogic = false;

            //Start the animation
            moveToC.start();


            if(runLogic) {
                //Move n-1 discs from B to C so they sit on disc n
                moveRings(n - 1, pegB, pegA, pegC, bx, ax, cx);
            }
        }	//End if

    }	//End moveRings

}