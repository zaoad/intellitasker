package info.androidhive.intellitasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.String.valueOf;

/**
 * Created by zaoad on 10/26/17.
 */

public class createnewmember extends AppCompatActivity {
    int i=0;
    EditText membernametext;
    Button memberamebutton;
    String groupname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creategruopevent);
        membernametext=(EditText)findViewById(R.id.groupnametext);
        memberamebutton =(Button) findViewById(R.id.groupnamebutton);
        memberamebutton.setText("addmember");
        memberamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupname= valueOf(membernametext.getText());
                membernametext.setText("");
                ////send group tame and details to All_Grouptask_View
                Intent intent= new Intent();
                intent.putExtra(selectedgroupconstrant.titles,groupname);
                intent.putExtra(selectedgroupconstrant.details,"edit your task");
                //intent.putExtra(FeedIntentConstraint.Datecode,curDate);
                setResult(13,intent);
                finish();

            }
        });

    }
}
