package com.example.semestralna_praca

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.room.Room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * The opening activity of the Workout Planner App.
 */
class MainActivity : AppCompatActivity() {

    private var exercises = mapOf<String, ArrayList<Int>>();
    private var exercisesProperties = mapOf<String, ArrayList<String>>();
    private var plansTexts : ArrayList<TextView> = arrayListOf();
    private var imageIds : ArrayList<Int> = arrayListOf();
    private var textIds : ArrayList<Int> = arrayListOf();
    private var width = 0;
    private var deletedPlans : ArrayList<Int> = arrayListOf();

    private lateinit var planDao: PlanDao
    private lateinit var exerciseDao: ExerciseDao

    /**
     * Called when the activity is being created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Workout Planner App"

        // Determine the width based on screen orientation
        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            width = (resources.displayMetrics.heightPixels * 0.7).toInt();
        } else {
            width = (resources.displayMetrics.widthPixels * 0.7).toInt();
        }

        val database = Room.databaseBuilder(applicationContext, PlansDatabase::class.java, "myapp-db").fallbackToDestructiveMigration().build()

        planDao = database.planDao()
        exerciseDao = database.exerciseDao()

        GlobalScope.launch(Dispatchers.IO) {
           //database.clearAllTables();

            val plans = planDao.getAllPlans();
            val exercisesP = exerciseDao.getAllExecises();
            val plansNames = planDao.getAllUniquePlans().toMutableList();

            val scrollLayout : LinearLayout = findViewById(R.id.layoutPlans)
            for (i in 0..plansNames.size - 1) {
                val textView = TextView(this@MainActivity);
                for (j in 0..plans.size -1) {
                    if (plans[j].name.equals(plansNames[i])) {
                        textView.text = plansNames[i] + ", " + plans[j].date;
                        break;
                    }
                }
                textView.textSize = 20f;
                GlobalScope.launch(Dispatchers.Main) {
                    scrollLayout.addView(textView);
                    val layoutParams = textView.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.width = (width * 0.9).toInt();
                    layoutParams.setMargins((width * 0.1).toInt(), 0, 0, 0)
                    textView.layoutParams = layoutParams
                }

                plansTexts.add(textView);
                val deletedPlan = -1;
                for (i in 0..plansTexts.size - 1) {
                    if (!deletedPlans.contains(i)) {
                        plansTexts[i].isClickable = true
                        plansTexts[i].setEnabled(true);
                        var clicked = true;
                        plansTexts[i].setOnClickListener {
                            if (clicked) {
                                plansTexts[i].textSize = 23f;
                                val buttonOpen: Button = findViewById(R.id.open);
                                buttonOpen.visibility = View.VISIBLE;
                                val buttonDelete: Button = findViewById(R.id.delete);
                                buttonDelete.visibility = View.VISIBLE;
                                buttonOpen.setOnClickListener {
                                    showPlan(plansTexts[i].text.toString().split(", ")[0]);
                                    buttonOpen.visibility = View.GONE;
                                    buttonDelete.visibility = View.GONE;
                                    plansTexts[i].textSize = 20f;
                                }
                                buttonDelete.setOnClickListener {
                                    plansTexts[i].visibility = View.GONE;
                                    deletedPlans.add(i);
                                    buttonOpen.visibility = View.GONE;
                                    buttonDelete.visibility = View.GONE;
                                    GlobalScope.launch(Dispatchers.IO) {
                                        planDao.removePlan(plansNames[i])
                                        exerciseDao.deleteExercisesFromPlan(plansNames[i])
                                    }
                                }
                                clicked = false;
                            } else {
                                plansTexts[i].textSize = 20f;
                                val buttonOpen: Button = findViewById(R.id.open);
                                buttonOpen.visibility = View.GONE;
                                val buttonDelete: Button = findViewById(R.id.delete);
                                buttonDelete.visibility = View.GONE;
                                clicked = true;
                            }
                        }
                    }
                }
            }

            for (i in 0..exercisesP.size - 1) {
                val name  = exercisesP[i].name.split(":")[0];
                val id = exercisesP[i].imageId;
                if (exercises[name] == null) {
                    exercises = exercises + (name to arrayListOf<Int>());
                }
                exercises[name]!!.add(id);
                if (exercisesProperties[name] == null) {
                    exercisesProperties = exercisesProperties + (name to arrayListOf<String>());
                }
                exercisesProperties[name]!!.add(exercisesP[i].property);
            }
        }


        val choosePlan : Button = findViewById(R.id.button2);
        choosePlan.setOnClickListener {
            val title : TextView = findViewById(R.id.textView2);
            title.visibility = View.GONE;
            val button1 : Button = findViewById(R.id.button);
            button1.visibility = View.GONE;
            choosePlan.visibility = View.GONE;
            val scroll : ScrollView = findViewById(R.id.scrollViewPlans);
            scroll.visibility = View.VISIBLE;
            val back : Button = findViewById(R.id.buttonBack);
            back.visibility = View.VISIBLE;
            val image : ImageView = findViewById(R.id.myImageView);
            image.visibility = View.GONE;
            val image2 : ImageView = findViewById(R.id.myImageView2);
            image2.visibility = View.GONE;
            val image3 : ImageView = findViewById(R.id.myImageView3);
            image3.visibility = View.GONE;
            val image4 : ImageView = findViewById(R.id.myImageView4);
            image4.visibility = View.GONE;
            supportActionBar?.hide();
        }

        val back : Button = findViewById(R.id.buttonBack);
        back.setOnClickListener {
            val title : TextView = findViewById(R.id.textView2);
            title.visibility = View.VISIBLE;
            val button1 : Button = findViewById(R.id.button);
            button1.visibility = View.VISIBLE;
            val button2 : Button = findViewById(R.id.button2);
            button2.visibility = View.VISIBLE;
            val scroll : ScrollView = findViewById(R.id.scrollViewPlans);
            scroll.visibility = View.GONE;
            back.visibility = View.GONE;
            val image : ImageView = findViewById(R.id.myImageView);
            image.visibility = View.VISIBLE;
            val image2 : ImageView = findViewById(R.id.myImageView2);
            image2.visibility = View.VISIBLE;
            val image3 : ImageView = findViewById(R.id.myImageView3);
            image3.visibility = View.VISIBLE;
            val image4 : ImageView = findViewById(R.id.myImageView4);
            image4.visibility = View.VISIBLE;
            val buttonOpen : Button = findViewById(R.id.open);
            buttonOpen.visibility = View.GONE;
            val buttonDelete : Button = findViewById(R.id.delete);
            buttonDelete.visibility = View.GONE;
            supportActionBar?.show();
        }

        val buttonNext : Button = findViewById(R.id.button);
        buttonNext.setOnClickListener {
            chooseBodyScreen();
        }

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

    private fun chooseBodyScreen() {
        val intent = Intent(this, Activity2::class.java);
        startActivity(intent);
    }

    private fun showPlan(name : String) {
        val scroll : ScrollView = findViewById(R.id.scrollViewPlans);
        val scrollLayout : LinearLayout = findViewById(R.id.layoutPlans);
        val buttonBack : Button = findViewById(R.id.buttonBack);
        buttonBack.visibility = View.GONE;
        val buttonBackPlan : Button = findViewById(R.id.buttonBackPlan);
        buttonBackPlan.visibility = View.VISIBLE;
        for (i in 0..plansTexts.size - 1) {
            plansTexts[i].isClickable = false
            plansTexts[i].visibility = View.GONE;
        }

        val linearLayout : LinearLayout = LinearLayout(this);
        linearLayout.orientation = LinearLayout.HORIZONTAL;

        val planName : TextView = TextView(this);
        planName.text = name;
        planName.textSize = 18f;
        scrollLayout.addView(planName);
        val layoutParams = planName.layoutParams
        layoutParams.width = Math.round(resources.displayMetrics.density * 350);
        layoutParams.height = Math.round(resources.displayMetrics.density * 40);
        planName.layoutParams = layoutParams;
        planName.gravity = Gravity.CENTER;

        val layoutLeft : LinearLayout = LinearLayout(this);
        layoutLeft.orientation = LinearLayout.VERTICAL;
        val layoutRight : LinearLayout = LinearLayout(this);
        layoutRight.orientation = LinearLayout.VERTICAL;
        linearLayout.addView(layoutLeft);
        linearLayout.addView(layoutRight);

        for (i in 0..exercises.get(name)!!.size -1) {

            val inflater = LayoutInflater.from(this)
            val layoutActivity3 = inflater.inflate(R.layout.activity_activity3exercises, null)
            val b : ImageButton = layoutActivity3.findViewById(exercises.get(name)!!.get(i));
            val newIm : ImageButton = ImageButton(this);
            newIm.background = b.background;

            val text : TextView = TextView(this);
            text.text = exercisesProperties.get(name)!!.get(i);
            text.textSize = 18f;

            if (i%2 == 0) {
                layoutLeft.addView(newIm);
                layoutLeft.addView(text);
            } else {
                layoutRight.addView(newIm);
                layoutRight.addView(text);
            }
            val layoutParams = newIm.layoutParams
            val hSize = resources.displayMetrics.heightPixels;
            val wSize = resources.displayMetrics.widthPixels;
            layoutParams.width = (width /2).toInt();
            layoutParams.height = (layoutParams.width * 0.6).toInt();
            newIm.layoutParams = layoutParams
            imageIds.add(newIm.id);
            val layoutParams2 = text.layoutParams
            layoutParams2.width = (width /2).toInt();
            layoutParams2.height = (layoutParams2.width * 0.2).toInt();
            text.layoutParams = layoutParams2
            textIds.add(newIm.id);

        }
        scrollLayout.addView(linearLayout);
        val backButtonPlan : Button = findViewById(R.id.buttonBackPlan);
        backButtonPlan.setOnClickListener {
            linearLayout.visibility = View.GONE;
            for (i in 0..plansTexts.size - 1) {
                if (!deletedPlans.contains(i)) {
                    plansTexts[i].isClickable = true;
                    plansTexts[i].visibility = View.VISIBLE;
                    buttonBack.visibility = View.VISIBLE;
                    backButtonPlan.visibility = View.GONE;
                    planName.visibility = View.GONE
                    val buttonOpen: Button = findViewById(R.id.open);
                    buttonOpen.visibility = View.VISIBLE;
                    val buttonDelete: Button = findViewById(R.id.delete);
                    buttonDelete.visibility = View.VISIBLE;
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

        val hSize = resources.displayMetrics.heightPixels;
        val wSize = resources.displayMetrics.widthPixels;
        if (wSize > hSize) {
            val scroll : ScrollView = findViewById(R.id.scrollViewPlans);
            val layoutParams = scroll.layoutParams;
            layoutParams.width = width;
            layoutParams.height = Math.round(hSize * 0.8).toInt();
            scroll?.layoutParams = layoutParams;
        } else {
            val scroll : ScrollView = findViewById(R.id.scrollViewPlans);
            val layoutParams = scroll.layoutParams;
            layoutParams.width =  width;
            layoutParams.height = Math.round(hSize * 0.8).toInt();
            scroll?.layoutParams = layoutParams;
        }
    }

}

