package com.example.semestralna_praca

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import kotlin.math.roundToInt

/**
 * The activity to choose body parts.
 */
class Activity2 : AppCompatActivity() {

    private var leftColumn : LinearLayout? = null;
    private var rightColumn : LinearLayout? = null;

    private var buttons =  arrayListOf<Button>();

    private var layout1 : LinearLayout? = null;
    private var layout2 : LinearLayout? = null;
    private var layout3 : LinearLayout? = null;
    private var layout4 : LinearLayout? = null;
    private var layout5 : LinearLayout? = null;

    /**
     * Called when the activity is being created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        supportActionBar?.hide()

        val fullBody : Button = findViewById(R.id.fullbody);
        var allClick = false;

        buttons = arrayListOf<Button>(findViewById(R.id.biceps), findViewById(R.id.chest), findViewById(R.id.shoulders), findViewById(R.id.triceps), findViewById(R.id.abs), findViewById(R.id.back), findViewById(R.id.glutes), findViewById(R.id.quads), findViewById(R.id.calves), findViewById(R.id.hamstrings));
        var clicked = arrayListOf<Boolean>(false, false, false, false, false, false, false, false, false, false);

        leftColumn = findViewById(R.id.leftColumn)
        val lay1 = leftColumn!!.layoutParams;
        lay1.width = resources.displayMetrics.widthPixels / 2;
        leftColumn!!.layoutParams = lay1;

        rightColumn = findViewById(R.id.rightColumn)

        layout1 = LinearLayout(this);
        layout2 = LinearLayout(this);
        layout3 = LinearLayout(this);
        layout4 = LinearLayout(this);
        layout5 = LinearLayout(this);

        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            val newConfig = Configuration(resources.configuration)
            newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
            onConfigurationChanged(newConfig)
        }

        fullBody.setOnClickListener {
            if (allClick) {
                allClick = false;
            } else {
                allClick = true;
            }
            for (i in 0..buttons.size - 1) {
                if (allClick) {
                    buttons.get(i).setBackgroundColor(Color.rgb(10, 50, 150));
                    clicked = arrayListOf<Boolean>(true, true, true, true, true, true, true, true, true, true);
                } else {
                    buttons.get(i).setBackgroundColor(Color.rgb(32,112, 187));
                    clicked = arrayListOf<Boolean>(false, false, false, false, false, false, false, false, false, false);
                }
            }
        }

        for (i in 0..buttons.size - 1) {
            buttons.get(i).setOnClickListener {
                if (clicked.get(i)) {
                    buttons.get(i).setBackgroundColor(Color.rgb(32,112, 187));
                    clicked.removeAt(i);
                    clicked.add(i, false);
                } else {
                    buttons.get(i).setBackgroundColor(Color.rgb(10, 50, 150));
                    clicked.removeAt(i);
                    clicked.add(i, true);
                }
            }
        }


        val done : Button = findViewById(R.id.done);
        done.setOnClickListener {
            val inte = Intent(this, Activity3exercises::class.java);
            inte.putExtra("biceps", clicked.get(0));
            inte.putExtra("chest", clicked.get(1));
            inte.putExtra("shoulders", clicked.get(2));
            inte.putExtra("triceps", clicked.get(3));
            inte.putExtra("abs", clicked.get(4));
            inte.putExtra("back", clicked.get(5));
            inte.putExtra("glutes", clicked.get(6));
            inte.putExtra("quads", clicked.get(7));
            inte.putExtra("calves", clicked.get(8));
            inte.putExtra("hamstrings", clicked.get(9));
            startActivity(inte);
        }
    }

    /**
     * Called whenever the configuration of the screen changes.
     * @param newConfig The new device configuration.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            print("ok");
            leftColumn!!.removeView(buttons.get(0));
            leftColumn!!.removeView(buttons.get(1));
            leftColumn!!.removeView(buttons.get(2));
            leftColumn!!.removeView(buttons.get(3));
            leftColumn!!.removeView(buttons.get(4));
            rightColumn!!.removeView(buttons.get(5));
            rightColumn!!.removeView(buttons.get(6));
            rightColumn!!.removeView(buttons.get(7));
            rightColumn!!.removeView(buttons.get(8));
            rightColumn!!.removeView(buttons.get(9));
            val buttonsLayout: LinearLayout = findViewById(R.id.buttonsLayout);
            buttonsLayout.removeView(leftColumn);
            buttonsLayout.removeView(rightColumn);

            for (i in 0..9) {
                val lay = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                lay.topMargin = 10;
                lay.width = wSize / 5 - 10;
                buttons.get(i).layoutParams = lay
            }

            val blLay = buttonsLayout.layoutParams;
            if (wSize > 2 * hSize) {
                blLay.height = hSize / 4
            } else {
                blLay.height = hSize / 2;
            }
            buttonsLayout.layoutParams = blLay;

            layout1!!.orientation = LinearLayout.VERTICAL;
            layout1!!.gravity = Gravity.CENTER;
            buttonsLayout.addView(layout1);
            val la1 = layout1!!.layoutParams;
            la1.width = wSize / 5;
            layout1!!.layoutParams = la1;
            layout1!!.addView(buttons.get(0));
            layout1!!.addView(buttons.get(1));

            layout2!!.orientation = LinearLayout.VERTICAL;
            layout2!!.gravity = Gravity.CENTER;
            buttonsLayout.addView(layout2);
            val la2 = layout2!!.layoutParams;
            la2.width = wSize / 5;
            layout2!!.layoutParams = la2;
            layout2!!.addView(buttons.get(2));
            layout2!!.addView(buttons.get(3));

            layout3!!.orientation = LinearLayout.VERTICAL;
            layout3!!.gravity = Gravity.CENTER;
            buttonsLayout.addView(layout3);
            val la3 = layout3!!.layoutParams;
            la3.width = wSize / 5;
            layout3!!.layoutParams = la3;
            layout3!!.addView(buttons.get(4));
            layout3!!.addView(buttons.get(5));

            layout4!!.orientation = LinearLayout.VERTICAL;
            layout4!!.gravity = Gravity.CENTER;
            buttonsLayout.addView(layout4);
            val la4 = layout4!!.layoutParams;
            la4.width = wSize / 5;
            layout4!!.layoutParams = la4;
            layout4!!.addView(buttons.get(6));
            layout4!!.addView(buttons.get(7));

            layout5!!.orientation = LinearLayout.VERTICAL;
            layout5!!.gravity = Gravity.CENTER;
            buttonsLayout.addView(layout5);
            val la5 = layout5!!.layoutParams;
            la5.width = wSize / 5;
            layout5!!.layoutParams = la5;
            layout5!!.addView(buttons.get(8));
            layout5!!.addView(buttons.get(9));
        }
        else {
            layout1!!.removeView(buttons.get(0));
            layout1!!.removeView(buttons.get(1));
            layout2!!.removeView(buttons.get(2));
            layout2!!.removeView(buttons.get(3));
            layout3!!.removeView(buttons.get(4));
            layout3!!.removeView(buttons.get(5));
            layout4!!.removeView(buttons.get(6));
            layout4!!.removeView(buttons.get(7));
            layout5!!.removeView(buttons.get(8));
            layout5!!.removeView(buttons.get(9));


            val buttonsLayout: LinearLayout = findViewById(R.id.buttonsLayout);

            buttonsLayout.removeView(layout1);
            buttonsLayout.removeView(layout2);
            buttonsLayout.removeView(layout3);
            buttonsLayout.removeView(layout4);
            buttonsLayout.removeView(layout5);

            val blLay = buttonsLayout.layoutParams;
            blLay.height = (hSize / 1.5).roundToInt();
            buttonsLayout.layoutParams = blLay;

            buttonsLayout.addView(leftColumn);
            buttonsLayout.addView(rightColumn);

            val lay1 = leftColumn!!.layoutParams;
            lay1.width = resources.displayMetrics.widthPixels / 2;
            leftColumn!!.layoutParams = lay1;

            leftColumn!!.addView(buttons.get(0));

            leftColumn!!.addView(buttons.get(1));
            leftColumn!!.addView(buttons.get(2));
            leftColumn!!.addView(buttons.get(3));
            leftColumn!!.addView(buttons.get(4));
            rightColumn!!.addView(buttons.get(5));
            rightColumn!!.addView(buttons.get(6));
            rightColumn!!.addView(buttons.get(7));
            rightColumn!!.addView(buttons.get(8));
            rightColumn!!.addView(buttons.get(9));

        }
    }
}
