package com.example.semestralna_praca

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

/**
 * The activity to choose exercises.
 */
class Activity3exercises : AppCompatActivity() {

    private var scroll : ScrollView? = null;
    private var scrollLayout : LinearLayout? = null;

    private var bicepsButtons = arrayListOf<ImageButton>();
    private var chestButtons = arrayListOf<ImageButton>();
    private var shouldersButtons = arrayListOf<ImageButton>();
    private var tricepsButtons = arrayListOf<ImageButton>();
    private var absButtons = arrayListOf<ImageButton>();
    private var backButtons = arrayListOf<ImageButton>();
    private var glutesButtons = arrayListOf<ImageButton>();
    private var quadsButtons = arrayListOf<ImageButton>();
    private var calvesButtons = arrayListOf<ImageButton>();
    private var hamstringsButtons = arrayListOf<ImageButton>();

    private var bicepsButtons2 = arrayListOf<ImageButton>();
    private var chestButtons2 = arrayListOf<ImageButton>();
    private var shouldersButtons2 = arrayListOf<ImageButton>();
    private var tricepsButtons2 = arrayListOf<ImageButton>();
    private var absButtons2 = arrayListOf<ImageButton>();
    private var backButtons2 = arrayListOf<ImageButton>();
    private var glutesButtons2 = arrayListOf<ImageButton>();
    private var quadsButtons2 = arrayListOf<ImageButton>();
    private var calvesButtons2 = arrayListOf<ImageButton>();
    private var hamstringsButtons2 = arrayListOf<ImageButton>();

    // Arrays to track the clicked state of each ImageButton
    private var clickedBiceps = arrayListOf<Boolean>(false, false, false, false, false, false);
    private var clickedChest = arrayListOf<Boolean>(false, false, false, false, false, false, false);
    private var clickedShoulders = arrayListOf<Boolean>(false, false, false, false, false, false, false);
    private var clickedTriceps = arrayListOf<Boolean>(false, false, false, false);
    private var clickedAbs = arrayListOf<Boolean>(false, false, false);
    private var clickedBack = arrayListOf<Boolean>(false, false, false, false);
    private var clickedGlutes = arrayListOf<Boolean>(false, false, false, false);
    private var clickedQuads = arrayListOf<Boolean>(false, false, false, false);
    private var clickedCalves = arrayListOf<Boolean>(false, false);
    private var clickedHamstrings = arrayListOf<Boolean>(false, false, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity3exercises)
        supportActionBar?.hide()

        // Scroll View and Layout variables
        scroll = findViewById(R.id.scrollView);
        scrollLayout = findViewById(R.id.scrollLayout);

        // Arrays of ImageButtons for each muscle group
        bicepsButtons = arrayListOf<ImageButton>(findViewById(R.id.biceps_curls), findViewById(R.id.cable_curls), findViewById(R.id.chin_ups), findViewById(R.id.preacher_curls), findViewById(R.id.concentration_curls), findViewById(R.id.dumbbell_curls));
        chestButtons = arrayListOf<ImageButton>(findViewById(R.id.chest_dips), findViewById(R.id.flat_bench_press), findViewById(R.id.incline_bench_press), findViewById(R.id.decline_press), findViewById(R.id.dumbbell_press), findViewById(R.id.incline_dumbbell_presss), findViewById(R.id.push_ups));
        shouldersButtons = arrayListOf<ImageButton>(findViewById(R.id.machine_shoulder_press), findViewById(R.id.cable_front_raise), findViewById(R.id.dumbbell_shoulder_press), findViewById(R.id.upright_rows), findViewById(R.id.dumbbell_lateral_raise), findViewById(R.id.military_press));
        tricepsButtons = arrayListOf<ImageButton>(findViewById(R.id.dumbbell_extension), findViewById(R.id.barbell_extension),  findViewById(R.id.close_grip_bench_press),  findViewById(R.id.tricep_extension));
        absButtons = arrayListOf<ImageButton>(findViewById(R.id.sit_ups), findViewById(R.id.leg_raises), findViewById(R.id.elbow_plank));
        backButtons = arrayListOf<ImageButton>(findViewById(R.id.wide_grip_lat_pulldown), findViewById(R.id.seated_back_rows), findViewById(R.id.body_back_extension), findViewById(R.id.barbell_bent_over_row));
        glutesButtons = arrayListOf<ImageButton>(findViewById(R.id.kettlebell_swings), findViewById(R.id.hip_thrust), findViewById(R.id.dumbbell_deadlift), findViewById(R.id.adductor_machine));
        quadsButtons = arrayListOf<ImageButton>(findViewById(R.id.leg_press), findViewById(R.id.seated_machine_leg_extension), findViewById(R.id.bulgarian_split_squat), findViewById(R.id.barbell_back_squat));
        calvesButtons = arrayListOf<ImageButton>(findViewById(R.id.standing_calf_raise), findViewById(R.id.seated_calf_raise));
        hamstringsButtons = arrayListOf<ImageButton>(findViewById(R.id.barbell_romanian_deadlift), findViewById(R.id.lying_leg_curls), findViewById(R.id.barbell_good_morning));

        bicepsButtons2 = arrayListOf<ImageButton>(findViewById(R.id.biceps_curls2), findViewById(R.id.cable_curls2), findViewById(R.id.chin_ups2), findViewById(R.id.preacher_curls2), findViewById(R.id.concentration_curls2), findViewById(R.id.dumbbell_curls2));
        chestButtons2 = arrayListOf<ImageButton>(findViewById(R.id.chest_dips2), findViewById(R.id.flat_bench_press2), findViewById(R.id.incline_bench_press2), findViewById(R.id.decline_press2), findViewById(R.id.dumbbell_press2), findViewById(R.id.incline_dumbbell_press2), findViewById(R.id.push_ups2));
        shouldersButtons2 = arrayListOf<ImageButton>(findViewById(R.id.machine_shoulder_press2), findViewById(R.id.cable_front_raise2), findViewById(R.id.dumbbell_shoulder_press2), findViewById(R.id.upright_rows2), findViewById(R.id.dumbbell_lateral_raise2), findViewById(R.id.military_press2));
        tricepsButtons2 = arrayListOf<ImageButton>(findViewById(R.id.dumbbell_extension2), findViewById(R.id.barbell_extension2),  findViewById(R.id.close_grip_bench_press2),  findViewById(R.id.tricep_extension2));
        absButtons2 = arrayListOf<ImageButton>(findViewById(R.id.sit_ups2), findViewById(R.id.leg_raises2), findViewById(R.id.elbow_plank2));
        backButtons2 = arrayListOf<ImageButton>(findViewById(R.id.wide_grip_lat_pulldown2), findViewById(R.id.seated_back_rows2), findViewById(R.id.body_back_extension2), findViewById(R.id.barbell_bent_over_row2));
        glutesButtons2 = arrayListOf<ImageButton>(findViewById(R.id.kettlebell_swings2), findViewById(R.id.hip_thrust2), findViewById(R.id.dumbbell_deadlift2), findViewById(R.id.adductor_machine2));
        quadsButtons2 = arrayListOf<ImageButton>(findViewById(R.id.leg_press2), findViewById(R.id.seated_machine_leg_extension2), findViewById(R.id.bulgarian_split_squat2), findViewById(R.id.barbell_back_squat2));
        calvesButtons2 = arrayListOf<ImageButton>(findViewById(R.id.standing_calf_raise2), findViewById(R.id.seated_calf_raise2));
        hamstringsButtons2 = arrayListOf<ImageButton>(findViewById(R.id.barbell_romanian_deadlift2), findViewById(R.id.lying_leg_curls2), findViewById(R.id.barbell_good_morning2));

        setImages("biceps", bicepsButtons);
        setImages("chest", chestButtons);
        setImages("shoulders", shouldersButtons);
        setImages("triceps", tricepsButtons);
        setImages("abs", absButtons);
        setImages("back", backButtons);
        setImages("glutes", glutesButtons);
        setImages("quads", quadsButtons);
        setImages("calves", calvesButtons);
        setImages("hamstrings", hamstringsButtons);

        setAction(bicepsButtons, bicepsButtons2, clickedBiceps);
        setAction(chestButtons, chestButtons2, clickedChest);
        setAction(shouldersButtons, shouldersButtons2, clickedShoulders);
        setAction(tricepsButtons, tricepsButtons2, clickedTriceps);
        setAction(absButtons, absButtons2, clickedAbs);
        setAction(backButtons, backButtons2, clickedBack);
        setAction(glutesButtons, glutesButtons2, clickedGlutes);
        setAction(quadsButtons, quadsButtons2, clickedQuads);
        setAction(calvesButtons, calvesButtons2, clickedCalves);
        setAction(hamstringsButtons, hamstringsButtons2, clickedHamstrings);

        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            val newConfig = Configuration(resources.configuration)
            newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
            onConfigurationChanged(newConfig)
        }

        val searchView : SearchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener (object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (intent.extras?.getBoolean("biceps") == true) {
                    findText(query, bicepsButtons, bicepsButtons2, clickedBiceps);
                }
                if (intent.extras?.getBoolean("chest") == true) {
                    findText(query, chestButtons, chestButtons2, clickedChest);
                }
                if (intent.extras?.getBoolean("shoulders") == true) {
                    findText(query, shouldersButtons, shouldersButtons2, clickedShoulders);
                }
                if (intent.extras?.getBoolean("triceps") == true) {
                    findText(query, tricepsButtons, tricepsButtons2, clickedTriceps);
                }
                if (intent.extras?.getBoolean("abs") == true) {
                    findText(query, absButtons, absButtons2, clickedAbs);
                }
                if (intent.extras?.getBoolean("back") == true) {
                    findText(query, backButtons, backButtons2, clickedBack);
                }
                if (intent.extras?.getBoolean("glutes") == true) {
                    findText(query, glutesButtons, glutesButtons2, clickedGlutes);
                }
                if (intent.extras?.getBoolean("quads") == true) {
                    findText(query, quadsButtons, quadsButtons2, clickedQuads);
                }
                if (intent.extras?.getBoolean("calves") == true) {
                    findText(query, calvesButtons, calvesButtons2, clickedCalves);
                }
                if (intent.extras?.getBoolean("hamstrings") == true) {
                    findText(query, hamstringsButtons, hamstringsButtons2, clickedHamstrings);
                }
                return true;
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (intent.extras?.getBoolean("biceps") == true) {
                    findText(newText, bicepsButtons, bicepsButtons2, clickedBiceps);
                }
                if (intent.extras?.getBoolean("chest") == true) {
                    findText(newText, chestButtons, chestButtons2, clickedChest);
                }
                if (intent.extras?.getBoolean("shoulders") == true) {
                    findText(newText, shouldersButtons, shouldersButtons2, clickedShoulders);
                }
                if (intent.extras?.getBoolean("triceps") == true) {
                    findText(newText, tricepsButtons, tricepsButtons2, clickedTriceps);
                }
                if (intent.extras?.getBoolean("abs") == true) {
                    findText(newText, absButtons, absButtons2, clickedAbs);
                }
                if (intent.extras?.getBoolean("back") == true) {
                    findText(newText, backButtons, backButtons2, clickedBack);
                }
                if (intent.extras?.getBoolean("glutes") == true) {
                    findText(newText, glutesButtons, glutesButtons2, clickedGlutes);
                }
                if (intent.extras?.getBoolean("quads") == true) {
                    findText(newText, quadsButtons, quadsButtons2, clickedQuads);
                }
                if (intent.extras?.getBoolean("calves") == true) {
                    findText(newText, calvesButtons, calvesButtons2, clickedCalves);
                }
                if (intent.extras?.getBoolean("hamstrings") == true) {
                    findText(newText, hamstringsButtons, hamstringsButtons2, clickedHamstrings);
                }
                return true;
            }
        })


        val done : Button = findViewById(R.id.done2);
        done.setOnClickListener {
            val inte = Intent(this, Activity4weights::class.java);
            var name = 0
            for (i in 0..clickedBiceps.size - 1) {
                if (clickedBiceps.get(i)) {
                    inte.putExtra(name.toString(), bicepsButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedChest.size - 1) {
                if (clickedChest.get(i)) {
                    inte.putExtra(name.toString(), chestButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedShoulders.size - 1) {
                if (clickedShoulders.get(i)) {
                    inte.putExtra(name.toString(), shouldersButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedTriceps.size - 1) {
                if (clickedTriceps.get(i)) {
                    inte.putExtra(name.toString(), tricepsButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedAbs.size - 1) {
                if (clickedAbs.get(i)) {
                    inte.putExtra(name.toString(), absButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedBack.size - 1) {
                if (clickedBack.get(i)) {
                    inte.putExtra(name.toString(), backButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedGlutes.size - 1) {
                if (clickedGlutes.get(i)) {
                    inte.putExtra(name.toString(), glutesButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedQuads.size - 1) {
                if (clickedQuads.get(i)) {
                    inte.putExtra(name.toString(), quadsButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedCalves.size - 1) {
                if (clickedCalves.get(i)) {
                    inte.putExtra(name.toString(), calvesButtons.get(i).id);
                    name++;
                }
            }
            for (i in 0..clickedHamstrings.size - 1) {
                if (clickedHamstrings.get(i)) {
                    inte.putExtra(name.toString(), hamstringsButtons.get(i).id);
                    name++;
                }
            }
            inte.putExtra("size", name - 1);
            startActivity(inte);
        }
    }

    private fun horizontalImages(delete : LinearLayout?, addL : LinearLayout, buttons : ArrayList<ImageButton>, buttons2 : ArrayList<ImageButton>, clicked: ArrayList<Boolean>) {
        for (i in 0..buttons.size - 1) {
            if (delete != null) {
                delete.removeView(buttons.get(i))
            };
            if (delete != null) {
                delete.removeView(buttons2.get(i))
            };
            addL.addView(buttons.get(i));
            addL.addView(buttons2.get(i));
            if (clicked.get(i)) {
                buttons.get(i).visibility = View.GONE;
                buttons2.get(i).visibility = View.VISIBLE;
            }
        }
    }

    private fun verticalImages(delete : LinearLayout, addL : LinearLayout?, buttons : ArrayList<ImageButton>, buttons2 : ArrayList<ImageButton>, clicked: ArrayList<Boolean>) {
        for (i in 0..buttons.size - 1) {
            delete.removeView(buttons.get(i))
            delete.removeView(buttons2.get(i))
            addL!!.addView(buttons.get(i));
            addL.addView(buttons2.get(i));
            if (clicked.get(i)) {
                buttons.get(i).visibility = View.GONE;
                buttons2.get(i).visibility = View.VISIBLE;
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
            val lin : LinearLayout = findViewById(R.id.linearLayout);
            val l = lin.layoutParams;
            l.height = resources.displayMetrics.heightPixels/4;
            lin.layoutParams = l;

            scroll!!.visibility = View.GONE;
            scrollLayout!!.visibility = View.GONE;

            val horizontalScroll : HorizontalScrollView = findViewById(R.id.horizontalScroll);
            val horizontalScrollLayout : LinearLayout = findViewById(R.id.horizontalScrollLayout);
            horizontalScroll.visibility = View.VISIBLE;
            horizontalScrollLayout.visibility = View.VISIBLE;

            val s = horizontalScroll.layoutParams
            s.width = resources.displayMetrics.widthPixels
            s.height = Math.round(resources.displayMetrics.density * 180);
            horizontalScroll.layoutParams = s;

            horizontalImages(scrollLayout, horizontalScrollLayout, bicepsButtons, bicepsButtons2, clickedBiceps);
            horizontalImages(scrollLayout, horizontalScrollLayout, chestButtons, chestButtons2, clickedChest);
            horizontalImages(scrollLayout, horizontalScrollLayout, shouldersButtons, shouldersButtons2, clickedShoulders);
            horizontalImages(scrollLayout, horizontalScrollLayout, tricepsButtons, tricepsButtons2, clickedTriceps);
            horizontalImages(scrollLayout, horizontalScrollLayout, absButtons, absButtons2, clickedAbs);
            horizontalImages(scrollLayout, horizontalScrollLayout, backButtons, backButtons2, clickedBack);
            horizontalImages(scrollLayout, horizontalScrollLayout, glutesButtons, glutesButtons2, clickedGlutes);
            horizontalImages(scrollLayout, horizontalScrollLayout, quadsButtons, quadsButtons2, clickedQuads);
            horizontalImages(scrollLayout, horizontalScrollLayout, calvesButtons, calvesButtons2, clickedCalves);
            horizontalImages(scrollLayout, horizontalScrollLayout, hamstringsButtons, hamstringsButtons2, clickedHamstrings);
        }
        else if (findViewById<View?>(R.id.horizontalScroll).visibility == View.VISIBLE) {

            val horizontalScroll : HorizontalScrollView = findViewById(R.id.horizontalScroll);
            val horizontalScrollLayout : LinearLayout = findViewById(R.id.horizontalScrollLayout);
            horizontalScroll.visibility = View.GONE;
            horizontalScrollLayout.visibility = View.GONE;

            scroll!!.visibility = View.VISIBLE;
            scrollLayout!!.visibility = View.VISIBLE;

            verticalImages(horizontalScrollLayout, scrollLayout, bicepsButtons, bicepsButtons2, clickedBiceps);
            verticalImages(horizontalScrollLayout, scrollLayout, chestButtons, chestButtons2, clickedChest);
            verticalImages(horizontalScrollLayout, scrollLayout, shouldersButtons, shouldersButtons2, clickedShoulders);
            verticalImages(horizontalScrollLayout, scrollLayout, tricepsButtons, tricepsButtons2, clickedTriceps);
            verticalImages(horizontalScrollLayout, scrollLayout, absButtons, absButtons2, clickedAbs);
            verticalImages(horizontalScrollLayout, scrollLayout, backButtons, backButtons2, clickedBack);
            verticalImages(horizontalScrollLayout, scrollLayout, glutesButtons, glutesButtons2, clickedGlutes);
            verticalImages(horizontalScrollLayout, scrollLayout, quadsButtons, quadsButtons2, clickedQuads);
            verticalImages(horizontalScrollLayout, scrollLayout, calvesButtons, calvesButtons2, clickedCalves);
            verticalImages(horizontalScrollLayout, scrollLayout, hamstringsButtons, hamstringsButtons2, clickedHamstrings);
        }

    }

    private fun setImages(bodyPart: String, arrayList: ArrayList<ImageButton>) {
        if (intent.extras?.getBoolean(bodyPart) == false) {
            for (i in 0..arrayList.size - 1) {
                arrayList.get(i).visibility = View.GONE
            }
        } else {
            for (i in 0..arrayList.size - 1) {
                arrayList.get(i).visibility = View.VISIBLE
            }
        }
    }

    private fun findText(text : String, arrayList: ArrayList<ImageButton>, arrayList2: ArrayList<ImageButton>, clickedList: ArrayList<Boolean>) : Boolean {
        for (i in 0..arrayList.size - 1) {
            if (arrayList.get(i).contentDescription.contains(text)) {
                if (clickedList.get(i)) {
                    arrayList2.get(i).visibility = View.VISIBLE
                } else {
                    arrayList.get(i).visibility = View.VISIBLE
                }
            } else {
                if (clickedList.get(i)) {
                    arrayList2.get(i).visibility = View.GONE
                } else {
                    arrayList.get(i).visibility = View.GONE
                }
            }
        }
        return true;
    }

    private fun setAction(arrayList: ArrayList<ImageButton>, arrayList2: ArrayList<ImageButton>, clicked: ArrayList<Boolean>) {
        for (i in 0..arrayList.size - 1) {
            arrayList.get(i).setOnClickListener {
                if (clicked.get(i)) {
                    arrayList2.get(i).visibility = View.GONE
                    arrayList.get(i).visibility = View.VISIBLE
                    clicked.removeAt(i)
                    clicked.add(i, false)
                } else {
                    arrayList.get(i).visibility = View.GONE
                    arrayList2.get(i).visibility = View.VISIBLE
                    clicked.removeAt(i)
                    clicked.add(i, true)
                }
            }
            arrayList2.get(i).setOnClickListener {
                if (clicked.get(i)) {
                    arrayList2.get(i).visibility = View.GONE
                    arrayList.get(i).visibility = View.VISIBLE
                    clicked.removeAt(i)
                    clicked.add(i, false)
                } else {
                    arrayList.get(i).visibility = View.GONE
                    arrayList2.get(i).visibility = View.VISIBLE
                    clicked.removeAt(i)
                    clicked.add(i, true)
                }
            }
        }
    }

}

