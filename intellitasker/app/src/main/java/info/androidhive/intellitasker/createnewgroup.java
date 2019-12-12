package info.androidhive.intellitasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.String.valueOf;

/**
 * Created by zaoad on 10/12/17.
 */

public class createnewgroup extends AppCompatActivity {
    int i=0;
    EditText groupnametext;
    Button groupnamebutton;
    String groupname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creategruopevent);
        groupnametext=(EditText)findViewById(R.id.groupnametext);
        groupnamebutton=(Button) findViewById(R.id.groupnamebutton);


        groupnamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupname= valueOf(groupnametext.getText());
                groupnametext.setText("");
                ////send group tame and details to All_Grouptask_View
                Intent intent= new Intent();
                intent.putExtra(selectedgroupconstrant.titles,groupname);
                intent.putExtra(selectedgroupconstrant.details,"edit your group");
                //intent.putExtra(FeedIntentConstraint.Datecode,curDate);
                setResult(12,intent);
                finish();

            }
        });

    }
}
