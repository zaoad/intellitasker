package info.androidhive.intellitasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zaoad on 10/27/17.
 */

public class All_taskname_view extends makeRecyclerView {
    All_task_adapter adapter;
    String name,groupname;
    List<String> members;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_group_name_view);
        Bundle b = getIntent().getExtras();
       // groupname = b.getString("group");
        name=b.getString("key");
        groupname=b.getString("group");
        members= (ArrayList<String>) getIntent().getSerializableExtra("memberlist");
        for(int i=0;i<members.size();i++)
        {
            Log.d("members from all task",String.valueOf(members.get(i)));
        }
        FloatingActionButton createnewgroupbutton= (FloatingActionButton) findViewById(R.id.FAB);
        createnewgroupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(All_taskname_view.this,assigntask.class);
                Bundle b = new Bundle();
                String titlename=name;
                b.putString("key",titlename); //Your id
                b.putString("groupname",groupname);
                intent.putExtra("memberlist",(ArrayList<String>)members);
                intent.putExtras(b);
                startActivityForResult(intent, selectedgroupconstrant.creategroup);
            }
        });
        ///initialize all_group_name_view/////
        Log.d("name ki asche",name+" "+groupname);
        adapter = new All_task_adapter(this,name,groupname,members);
        recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        setRecyclerLayout(recyclerView);
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("grouptasks").child(global.username);
        DatabaseReference ref2, ref3, ref4;
        ref2 = ref1.child("grouptask");
        ref3=ref2.child(groupname);
        ref4=ref3.child(name);
        ref4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Result will be holded Here
                int i=0;
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    task firebasetask=dsp.getValue(task.class);
                    String firebasetitle=firebasetask.getTitle();
                    String firebasedetails=firebasetask.getTime();
                    String firebaseid=firebasetask.getId();
                    boolean firebasedone=firebasetask.isDone();
                    //boolean firebasebutton=firebasetask.isDone();
                    adapter.updatePost(firebasetitle,firebaseid,firebasedetails,firebasedone);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //// after creating group name return to this fucntion and add new group to the view
    protected void onActivityResult(int requestcode, int resultcode, Intent data)
    {
        if(resultcode== selectedgroupconstrant.creategroup);
        {
            String id=data.getStringExtra("firebaseid");
            ////get  values of the new group to update from the createnewgroup class
            String titles= data.getStringExtra("tasktitle");
            Log.d("ShareResourceActivity", "here result 2" + titles+" ");
            String details=data.getStringExtra("endtime");

            ;
            Log.d("baji no ka aiyer ", "here result of all task view " + titles+" "+details+" "+id);
            //Catagory= "Anonymous has shared a resource on "+Catagory;
            //update the view of group task by adding a new group
            adapter.updatePost(titles,id,details,false);
        }
    }
}
