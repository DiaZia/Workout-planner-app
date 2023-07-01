package com.example.semestralna_praca

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * The activity to set exercises sets and weights.
 */
class Activity4weights : AppCompatActivity() {

    private var scroll : ScrollView? = null;
    private var scrollLayout : LinearLayout? = null;
    private var exercises = arrayListOf<ImageButton>();
    private var weights = arrayListOf<EditText>();
    private var sets = arrayListOf<EditText>();
    private var ids = arrayListOf<Int>();
    private var difficulty = 8;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity4weights)
        supportActionBar?.hide()

        scroll = findViewById(R.id.scrollView2);
        scrollLayout = findViewById(R.id.sclayout);

        val size = intent.extras?.getInt("size");

        val layout : LinearLayout = findViewById(R.id.layout2);
        val layout2 : LinearLayout = findViewById(R.id.layout3);
        for  (i in 0..size!!) {

            val inflater = LayoutInflater.from(this)
            val layoutActivity3 = inflater.inflate(R.layout.activity_activity3exercises, null)

            if (intent.extras?.getInt(i.toString()) != null) {
                val c = intent.extras?.getInt(i.toString()) as Int;
                ids.add(c);

                val b : ImageButton = layoutActivity3.findViewById(c);
                val newIm : ImageButton = ImageButton(this);
                newIm.background = b.background;

                layout.addView(newIm, layout.indexOfChild(b));
                val layoutParams = newIm.layoutParams
                layoutParams.width = Math.round(resources.displayMetrics.density * 140);
                layoutParams.height = Math.round(resources.displayMetrics.density * 90);
                newIm.layoutParams = layoutParams
                exercises.add(newIm);

                val weight : EditText = EditText(this);
                layout2.addView(weight);
                weight.hint = "Enter weight";
                val layoutParams2 = weight.layoutParams
                layoutParams2.width = Math.round(resources.displayMetrics.density * 210);
                layoutParams2.height = Math.round(resources.displayMetrics.density * 45);
                weight.layoutParams = layoutParams2;
                weight.inputType = InputType.TYPE_CLASS_NUMBER;
                weights.add(weight);

                val set : EditText = EditText(this);
                layout2.addView(set);
                set.hint = "Enter number of sets";
                val layoutParams3 = set.layoutParams
                layoutParams3.width = Math.round(resources.displayMetrics.density * 210);
                layoutParams3.height = Math.round(resources.displayMetrics.density * 45);
                set.layoutParams = layoutParams3;
                set.inputType = InputType.TYPE_CLASS_NUMBER;
                sets.add(set);
            }
        }
        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            val newConfig = Configuration(resources.configuration)
            newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
            onConfigurationChanged(newConfig)
        }

        val done : Button = findViewById(R.id.done3);
        done.setOnClickListener {
            val inte = Intent(this, Activity5plan::class.java);
            var index = 0;
            var i = 0;
            while (i < exercises.size) {
                inte.putExtra(index.toString(), ids[i]);
                inte.putExtra((index + 1).toString(), weights.get(i).text.toString());
                inte.putExtra((index + 2).toString(), sets.get(i).text.toString());
                index += 3;
                ++i;
            }
            inte.putExtra("size", i - 1);
            inte.putExtra("difficulty", difficulty)
            startActivity(inte);
        }

        val myRadioGroup = findViewById<RadioGroup>(R.id.myRadioGroup)
        myRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option1 -> {
                    difficulty = 8;
                }
                R.id.option2 -> {
                    difficulty = 10;
                }
                R.id.option3 -> {
                    difficulty = 12;
                }
            }
        }
    }

    /**
     * Called whenever the configuration of the screen changes.
     * @param newConfig The new device configuration.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (scroll?.visibility == View.VISIBLE) {
            scroll!!.visibility = View.GONE;
            scrollLayout!!.visibility = View.GONE;

            val lay2 : LinearLayout = findViewById(R.id.layout2);
            val lay3 : LinearLayout = findViewById(R.id.layout3);
            lay2.visibility = View.GONE;
            lay3.visibility = View.GONE;

            val horizontalScroll : HorizontalScrollView = findViewById(R.id.horizontalScroll2);
            val horizontalScrollLayout : LinearLayout = findViewById(R.id.horizontalScrollLayout);
            horizontalScroll.visibility = View.VISIBLE;
            horizontalScrollLayout.visibility = View.VISIBLE;

            val hSize = resources.displayMetrics.heightPixels;
            val wSize = resources.displayMetrics.widthPixels;
            val layP = horizontalScroll.layoutParams as ViewGroup.MarginLayoutParams
            layP.setMargins(0, Math.round(hSize * 0.3).toInt(), 0, Math.round(hSize * 0.2).toInt());
            horizontalScroll.layoutParams = layP;


            val size = intent.extras?.getInt("size");
            val layout : LinearLayout = findViewById(R.id.scrollLayout2);
            val lay2_1 :  LinearLayout = findViewById(R.id.scrollLayout3);
            val lay2_2 :  LinearLayout = findViewById(R.id.scrollLayout3_1);
            layout.visibility = View.VISIBLE;
            lay2_1.visibility = View.VISIBLE;
            lay2_2.visibility = View.VISIBLE;

            for  (i in 0..exercises.size - 1) {
                lay2.removeView(exercises[i]);
                lay3.removeView(weights[i]);
                lay3.removeView(sets[i]);

                val e = exercises[i].layoutParams;
                e.width = Math.round(resources.displayMetrics.density * 210);
                e.height = Math.round(resources.displayMetrics.density * 130);
                exercises[i].layoutParams = e;

                layout.addView(exercises[i]);
                lay2_1.addView(weights[i]);
                lay2_2.addView(sets[i]);
            }

            val myRadioGroup = findViewById<RadioGroup>(R.id.myRadioGroup)
            val layoutRadioGroup = myRadioGroup.layoutParams as ViewGroup.MarginLayoutParams
            layoutRadioGroup.setMargins(Math.round(resources.displayMetrics.density * 450), 0,  Math.round(resources.displayMetrics.density * 10), Math.round(resources.displayMetrics.density * 100));
            myRadioGroup.layoutParams = layoutRadioGroup;

        } else {
            scroll!!.visibility = View.VISIBLE;
            scrollLayout!!.visibility = View.VISIBLE;

            val lay2 : LinearLayout = findViewById(R.id.layout2);
            val lay3 : LinearLayout = findViewById(R.id.layout3);
            lay2.visibility = View.VISIBLE;
            lay3.visibility = View.VISIBLE;



            val horizontalScroll : HorizontalScrollView = findViewById(R.id.horizontalScroll2);
            val horizontalScrollLayout : LinearLayout = findViewById(R.id.horizontalScrollLayout);
            horizontalScroll.visibility = View.GONE;
            horizontalScrollLayout.visibility = View.GONE;


            val layout : LinearLayout = findViewById(R.id.scrollLayout2);
            val lay2_1 :  LinearLayout = findViewById(R.id.scrollLayout3);
            val lay2_2 :  LinearLayout = findViewById(R.id.scrollLayout3_1);

            layout.visibility = View.GONE;
            lay2_1.visibility = View.GONE;
            lay2_2.visibility = View.GONE;

            val size = intent.extras?.getInt("size");
            for  (i in 0..exercises.size - 1) {
                layout.removeView(exercises[i]);
                lay2_1.removeView(weights[i]);
                lay2_2.removeView(sets[i]);

                val e = exercises[i].layoutParams;
                e.width = Math.round(resources.displayMetrics.density * 140);
                e.height = Math.round(resources.displayMetrics.density * 90);
                exercises[i].layoutParams = e;

                lay2.addView(exercises[i]);
                lay3.addView(weights[i]);
                lay3.addView(sets[i]);
            }

            val myRadioGroup = findViewById<RadioGroup>(R.id.myRadioGroup)
            val layoutRadioGroup = myRadioGroup.layoutParams as ViewGroup.MarginLayoutParams
            layoutRadioGroup.setMargins( 0, Math.round(resources.displayMetrics.density * 20), Math.round(resources.displayMetrics.density * 150),  Math.round(resources.displayMetrics.density * 20));
            myRadioGroup.layoutParams = layoutRadioGroup;
        }
    }
}