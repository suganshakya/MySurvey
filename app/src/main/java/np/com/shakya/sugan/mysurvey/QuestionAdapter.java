package np.com.shakya.sugan.mysurvey;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sugan on 24/08/16.
 */
public class QuestionAdapter extends BaseAdapter {
    private final int TYPE_COUNT = 3;
    List<Question> questionList = new ArrayList<>();

    public QuestionAdapter(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String csvLine;
            bufferedReader.readLine();
            while((csvLine=bufferedReader.readLine()) != null){
                String [] row = csvLine.split(",");
                int qType = Integer.parseInt(row[0]);
                //  qId skipped for now
                Question q = new Question(qType, row[2], row[3], null);
                if(qType == 1){
                    q.setOptions(Arrays.copyOfRange(row, 4, row.length-1));
                }
                questionList.add(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater;
        int type = getItemViewType(position);
        TextView textView;

        Question q = questionList.get(position);

        if (view == null) {
            layoutInflater = (LayoutInflater) parent.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            switch (type) {
                case Question.TEXT_QUESTION:
                    view = layoutInflater.inflate(R.layout.text_layout, parent, false);
                    textView = (TextView) view.findViewById(R.id.text_text_view);
                    textView.setText(q.getQuestionText());
                    EditText editText = (EditText) view.findViewById(R.id.edit_text);
                    break;

                case Question.RADIO_BUTTON_QUESTION:
                    view = layoutInflater.inflate(R.layout.true_false_layout, parent, false);
                    textView = (TextView) view.findViewById(R.id.true_false_text_view);
                    textView.setText(q.getQuestionText());
                    RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.true_false_group);
                    String [] options = new String[]{"True", "False"};
                    RadioButton rb[] = new RadioButton[options.length];
                    rb[0] = (RadioButton) view.findViewById(R.id.true_false_button0);
                    rb[1] = (RadioButton) view.findViewById(R.id.true_false_button1);

                    for (int i = 0; i < options.length; ++i) {
                        rb[i].setText(options[i]);
                    }
                    break;

                case Question.SPINNER_QUESTION:
                    view = layoutInflater.inflate(R.layout.spinner_layout, parent, false);
                    textView = (TextView) view.findViewById(R.id.spinner_text_view);
                    textView.setText(q.getQuestionText());

                    Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
                    String[] options1 = q.getOptions();

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parent.getContext(),
                            android.R.layout.simple_spinner_item, options1);
                    // set the view for the Drop down list
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // set the ArrayAdapter to the spinner
                    spinner.setAdapter(dataAdapter);
                    spinner.setSelection(0);
                    break;

           }
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        Question q = questionList.get(position);
        return q.getQuestionType();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }
}

