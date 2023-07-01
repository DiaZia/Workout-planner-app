package com.example.semestralna_praca

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.room.Room
import java.text.SimpleDateFormat

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * The activity to display finished plan.
 */
class Activity5plan : AppCompatActivity() {

    private var scroll : ScrollView? = null;
    private var scrollLayout : LinearLayout? = null;
    private var exercises = arrayListOf<ImageButton>();
    private var weightsSets = arrayListOf<TextView>();
    private var name = "";
    private var ids = arrayListOf<Int>();

    private lateinit var planDao: PlanDao
    private lateinit var exerciseDao: ExerciseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity5plan)
        supportActionBar?.hide()

        val database = Room.databaseBuilder(applicationContext, PlansDatabase::class.java, "myapp-db").fallbackToDestructiveMigration().build()

        planDao = database.planDao()
        exerciseDao = database.exerciseDao()

        scroll = findViewById(R.id.scrollView3);
        scrollLayout = findViewById(R.id.sclayout);

        val layout2 : LinearLayout = findViewById(R.id.layout2);
        val layout3 : LinearLayout = findViewById(R.id.layout3);

        val inflater = LayoutInflater.from(this)
        val layoutActivity3 = inflater.inflate(R.layout.activity_activity3exercises, null)
        var size = intent.extras?.getInt("size");
        var difficulty = intent.extras?.getInt("difficulty");
        var i = 0;
        while (i <= (size!! * 3)) {
            val id = intent.extras?.getInt(i.toString()) as Int;

            ids.add(id);
            val b : ImageButton = layoutActivity3.findViewById(id);
            val newIm : ImageButton = ImageButton(this);
            newIm.background = b.background;
            layout2.addView(newIm, layout2.indexOfChild(b));
            val layoutParams = newIm.layoutParams
            layoutParams.width = Math.round(resources.displayMetrics.density * 160);
            layoutParams.height = Math.round(resources.displayMetrics.density * 100);
            newIm.layoutParams = layoutParams
            exercises.add(newIm);

            val weight = intent.extras?.getString((i + 1).toString());
            val sets = intent.extras?.getString((i + 2).toString());

            val weightText : TextView = TextView(this)
            weightText.text = sets + "x " + difficulty + ":   " +  weight + " kg";
            weightText.textSize = 20f;
            layout3.addView(weightText);
            val layoutParams2 = weightText.layoutParams
            layoutParams2.width = Math.round(resources.displayMetrics.density * 180);
            layoutParams2.height = Math.round(resources.displayMetrics.density * 100);
            weightText.layoutParams = layoutParams2;
            weightsSets.add(weightText);

            i += 3;
        }

        val save : Button = findViewById(R.id.save);
        save.setOnClickListener {
            scroll!!.visibility = View.GONE;
            scrollLayout!!.visibility = View.GONE

            val saveWindow : LinearLayout = findViewById(R.id.saveName);
            saveWindow.visibility = View.VISIBLE;
        }

        val ok : Button = findViewById(R.id.ok);
        ok.setOnClickListener {
            val planName : EditText = findViewById(R.id.enterText);
            name = planName.text.toString();
            GlobalScope.launch(Dispatchers.IO) {
                name = setName(name, name, 1);
                for (i in 0 .. exercises.size - 1) {
                    val tag = ids[i];
                    exerciseDao.insertExercise(Exercise(name + ":" + i, tag, weightsSets[i].text.toString()))
                    val currentDate = Calendar.getInstance().time;
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                    val formattedDate = dateFormat.format(currentDate)
                    planDao.insertPlan(Plan(name = name, exercise = name + ":" + i, date = formattedDate));
                }
            }
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }

        val back : Button = findViewById(R.id.back);
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }

        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            val newConfig = Configuration(resources.configuration)
            newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
            onConfigurationChanged(newConfig)
        } else {
            val newConfig = Configuration(resources.configuration)
            newConfig.orientation = Configuration.ORIENTATION_PORTRAIT
            onConfigurationChanged(newConfig)
        }
    }

    private fun setName(name : String, newName : String, num : Int) : String{
        var name1 = name;
        var newName1 = newName;
        val database = Room.databaseBuilder(applicationContext, PlansDatabase::class.java, "myapp-db").fallbackToDestructiveMigration().build();
        if (database.planDao().getAllUniquePlans().contains(newName)) {
            name1 = name + "(" + num + ")";
            newName1 = setName(name, name1, num + 1);
        }
        return newName1;
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
            val layoutParams = scroll?.layoutParams
            layoutParams?.width = Math.round(resources.displayMetrics.density * 580);
            layoutParams?.height = (hSize * 0.8).toInt();
            scroll?.layoutParams = layoutParams


            val layout2 : LinearLayout = findViewById(R.id.layout2);
            val layoutParams2 = layout2.layoutParams
            layoutParams2?.width = Math.round(resources.displayMetrics.density * 350);
            layout2.layoutParams = layoutParams2

            for (i in 0..exercises.size - 1) {
                val layoutParams = exercises[i].layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.width = Math.round(resources.displayMetrics.density * 290);
                layoutParams.height = Math.round(resources.displayMetrics.density * 160);
                layoutParams.setMargins(Math.round(resources.displayMetrics.density * 70), 0, 0, 0)
                exercises[i].layoutParams = layoutParams
            }

            val layout3 : LinearLayout = findViewById(R.id.layout3);
            val layoutParams3 = layout3.layoutParams
            layoutParams3?.width = Math.round(resources.displayMetrics.density * 230);
            layout3.layoutParams = layoutParams3

            for (i in 0..weightsSets.size - 1) {
                val layoutParams = weightsSets[i].layoutParams  as ViewGroup.MarginLayoutParams
                layoutParams.width = Math.round(resources.displayMetrics.density * 180);
                layoutParams.height = Math.round(resources.displayMetrics.density * 160);
                layoutParams.setMargins(Math.round(resources.displayMetrics.density * 40), 0, 0, 0)
                weightsSets[i].layoutParams = layoutParams
            }
        } else {
            val layoutParams = scroll?.layoutParams
            layoutParams?.width = Math.round(resources.displayMetrics.density * 300);
            layoutParams?.height = (hSize * 0.8).toInt();
            scroll?.layoutParams = layoutParams


            val layout2 : LinearLayout = findViewById(R.id.layout2);
            val layoutParams2 = layout2.layoutParams;
            layoutParams2?.width = Math.round(resources.displayMetrics.density * 160);
            layout2.layoutParams = layoutParams2

            for (i in 0..exercises.size - 1) {
                val layoutParams = exercises[i].layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.width = Math.round(resources.displayMetrics.density * 160);
                layoutParams.height = Math.round(resources.displayMetrics.density * 100);
                layoutParams.setMargins(0, 0, 0, 0)
                exercises[i].layoutParams = layoutParams
            }

            val layout3 : LinearLayout = findViewById(R.id.layout3);
            val layoutParams3 = layout3.layoutParams
            layoutParams3?.width = Math.round(resources.displayMetrics.density * 140);
            layout3.layoutParams = layoutParams3

            for (i in 0..weightsSets.size - 1) {
                val layoutParams = weightsSets[i].layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.width = Math.round(resources.displayMetrics.density * 140);
                layoutParams.height = Math.round(resources.displayMetrics.density * 100);
                layoutParams.setMargins(0, 0, 0, 0)
                weightsSets[i].layoutParams = layoutParams
            }
        }
    }
}

