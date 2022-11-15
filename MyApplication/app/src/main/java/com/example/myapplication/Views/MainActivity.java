package com.example.myapplication.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.KnowledgeBase.BaseOfRules;
import com.example.myapplication.R;
import com.github.cschen1205.ess.engine.Clause;
import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    ListView listView, listViewResult;
    Toast t;
    Button enterButtonAddRules, enterButtonGetStart;
    List<String> arrayList, newResult;
    List<List<String>> arrayListForKnowledgeBase;
    ArrayAdapter<String> adapterOfArrayOfItems, adapterOfArrayOfResult;
    TextInputEditText addValueOfRule;
    AutoCompleteTextView addVariableOfRule, addQuestions;
    TextView liableResultForwardChaining, liableResultBackwardChaining;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        listViewResult = findViewById(R.id.list_of_result_by_backward_chaining);
        enterButtonAddRules = findViewById(R.id.right_button_add_fact);
        enterButtonGetStart = findViewById(R.id.left_button_get_start);
        makeIntractable(enterButtonGetStart, false);
        addValueOfRule = findViewById(R.id.label_for_input_value);
        addVariableOfRule = findViewById(R.id.label_for_input_variable);
        addQuestions = findViewById(R.id.input_question);
        liableResultForwardChaining = findViewById(R.id.text_result_vehicle);
        liableResultBackwardChaining = findViewById(R.id.text_result_conditions);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        liableResultForwardChaining.setVisibility(View.INVISIBLE);
        liableResultBackwardChaining.setVisibility(View.INVISIBLE);

        String[] getVariable = new String[]{"Your ukrainian language score", "Was maths exam?", "Your maths score", "Was foreign language exam?", "Your foreign language score", "University"};
        ArrayAdapter<String> adapterOfDropDownMenuVariable = new ArrayAdapter<>(this, R.layout.dropdown_item, getVariable);
        addVariableOfRule.setAdapter(adapterOfDropDownMenuVariable);

        String[] getQuestions = new String[]{"Infer more facts using forward chaining","Search for answer to a question using backward chaining"};
        ArrayAdapter<String> adapterOfDropDownMenuQuestions = new ArrayAdapter<>(this, R.layout.dropdown_item, getQuestions);
        addQuestions.setAdapter(adapterOfDropDownMenuQuestions);

        arrayList = new ArrayList<>();
        newResult = new ArrayList<>();
        arrayListForKnowledgeBase = new ArrayList<>();

        swipeRefreshLayout.setOnRefreshListener(() -> { //There is method for cleaning list of items at present rolling
            listView.setAdapter(null);
            arrayList.clear();
            newResult.clear();
            arrayListForKnowledgeBase.clear();
            swipeRefreshLayout.setRefreshing(false);
            liableResultForwardChaining.setVisibility(View.INVISIBLE);
            liableResultBackwardChaining.setVisibility(View.INVISIBLE);
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> { //This method shows concrete values of string from item`s list of 'Conditions'
            String name = arrayList.get(i);
            makeToast(name);
        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            makeToast("Long press: " + arrayList.get(i));
            return false;
        });

        adapterOfArrayOfItems = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapterOfArrayOfItems);


        enterButtonAddRules.setOnClickListener(view -> { //This method adds new items to item`s list
            String getValueOfRule = Objects.requireNonNull(addValueOfRule.getText()).toString();
            String getVariableOfRule = addVariableOfRule.getText().toString();
            if(getVariableOfRule.length() == 0 || getValueOfRule.length() == 0){
                makeToast("Please, enter a not empty values");
            }
            else {
                liableResultBackwardChaining.setVisibility(View.VISIBLE);
                addItem(getVariableOfRule + "\t" + getValueOfRule);
                arrayListForKnowledgeBase.add(new ArrayList<>(Arrays.asList(getVariableOfRule, getValueOfRule)));
                addValueOfRule.setText("");
                makeIntractable(enterButtonGetStart, true);
            }
        });

        enterButtonGetStart.setOnClickListener(view -> {
            String getQuestionToString = addQuestions.getText().toString();
            RuleInferenceEngine rie = new BaseOfRules().getInferenceEngine();
            if(getQuestionToString.equals("Infer more facts using forward chaining")) {
                newResult.clear();
                if(arrayListForKnowledgeBase.size() != 0){
                    liableResultForwardChaining.setVisibility(View.VISIBLE);
                    for (int i = 0; i < arrayListForKnowledgeBase.size(); i++){
                        for (int j = 0; j < 1; j++){
                            rie.addFact(new EqualsClause(arrayListForKnowledgeBase.get(i).get(j), arrayListForKnowledgeBase.get(i).get(j+1)));
                        }
                    }
                    rie.infer();
                    if(arrayListForKnowledgeBase.size() == rie.getFacts().size()){
                        DialogWindow("Message","We don't have respond for your question. Check your correctness of the data or added more data.");
                        listView.setAdapter(null);
                        arrayList.clear();
                    }
                    else {
                        for(int i = 0; i <rie.getFacts().size(); i++){
                            newResult.add(String.valueOf(rie.getFacts().get(i)));
                        }
                    }
                }
                else {
                    DialogWindow("Message","We don't have respond for your question. Check your correctness of the data or added more data.");
                }
            }
            else if(getQuestionToString.equals("Search for answer to a question using backward chaining")){
                Vector<Clause> unproved_conditions= new Vector<>();
                for (int i = 0; i < arrayListForKnowledgeBase.size(); i++){
                    for (int j = 0; j < 1; j++){
                        rie.addFact(new EqualsClause(arrayListForKnowledgeBase.get(i).get(j), arrayListForKnowledgeBase.get(i).get(j+1)));
                    }
                }
                Clause conclusion = rie.infer("Your chances:", unproved_conditions);
                if(conclusion != null){
                    newResult.clear();
                    newResult.add("Conclusion: "+ conclusion);
                    DialogWindow("Congratulation", "We searched respond for your question.");
                    liableResultForwardChaining.setVisibility(View.VISIBLE);
                }
                else {
                    DialogWindow("Message","We don't have respond for your question. Check your correctness of the data or added more data.");
                    listView.setAdapter(null);
                    arrayList.clear();
                }
            }
            else{
                makeToast("Please, specify your question");
            }
            adapterOfArrayOfResult = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newResult);
            listViewResult.setAdapter(adapterOfArrayOfResult);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        addVariableOfRule.setText(null);
        addQuestions.setText(null);
        newResult.clear();
        arrayListForKnowledgeBase.clear();
        listView.setAdapter(null);
        listViewResult.setAdapter(null);
        arrayListForKnowledgeBase.clear();
        addValueOfRule.setText(null);
        liableResultForwardChaining.setVisibility(View.INVISIBLE);
        liableResultBackwardChaining.setVisibility(View.INVISIBLE);
    }

    public void addItem(String item) {
        arrayList.add(item);
        listView.setAdapter(adapterOfArrayOfItems);
    }

    private void makeToast(String s) { //There is method for reference of message about correct input for user
        if(t != null) t.cancel();
        t= Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        t.show();
    }

    private void makeIntractable(View v, boolean intractable){ //There is turn on/turn off of button for start work app besides add facts
        listView.setEnabled(intractable);
    }

    private void DialogWindow(String message, String subMessage){  //This method for message user about your errors and getting result
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        if(message.equals("Message")){
            builder.setIcon(R.drawable.logo_error);
            liableResultBackwardChaining.setVisibility(View.INVISIBLE);
            liableResultForwardChaining.setVisibility(View.INVISIBLE);
        }
        else if(message.equals("Congratulation")){
            builder.setIcon(R.drawable.logo_cogratulation);
        }
        builder.setTitle(message);
        builder.setMessage(subMessage);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }
}


