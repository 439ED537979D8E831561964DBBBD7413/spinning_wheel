package ch.android.mynhien.spinningwheel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ch.android.mynhien.spinningwheel.R; //import resources

public class OptionsActivity extends AppCompatActivity {
    private FloatingActionButton addOptionButton;
    private TextInputLayout optionInputField;
    private Button doneButton;
    private ListView optionsListView;

    private ArrayList<String> optionsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        initializeControls();
        addEventHandlers();
    }

    private void initializeControls() {
        addOptionButton = (FloatingActionButton) findViewById(R.id.addOptionBtn);
        optionInputField = (TextInputLayout) findViewById(R.id.optionInputField);
        doneButton = (Button) findViewById(R.id.doneBtn);

        optionsListView = (ListView) findViewById(R.id.optionList);
        optionsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, optionsList); //get Adapter-Instance
        optionsListView.setAdapter(adapter); //set Adapter

    }

    private void addEventHandlers() {
        addOptionButton.setOnTouchListener((v, e) -> {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setPressed(true);
                    String option = optionInputField.getEditText().getText().toString(); //extract Input from TextField
                    optionsList.add(option); //add Input to list
                case MotionEvent.ACTION_UP:
                    v.setPressed(false);
                    optionInputField.getEditText().setText("Option"); //reset TextField
            }
            return true;//?
        });

        doneButton.setOnTouchListener((v,e) -> {
            switch(e.getAction()){
                case MotionEvent.ACTION_DOWN:
                    v.setPressed(true);
                    Bundle bundle = new Bundle(); //create Bundle-Object
                    bundle.putStringArrayList("options", optionsList); //add OptionList to bundle
                    Intent intent = new Intent(OptionsActivity.this, SpinningWheelActivity.class); //create new Intent(Connection between activities)
                    intent.putExtras(bundle); //add bundle to intent
                    startActivity(intent); //execute intent

                case MotionEvent.ACTION_UP:
                    v.setPressed(false);
            }
            return true; //?
        });

    }

}
