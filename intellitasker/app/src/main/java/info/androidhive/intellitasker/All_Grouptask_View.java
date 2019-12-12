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

public class All_Grouptask_View extends makeRecyclerView {
    All_Grouptask_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_group_name_view);
        FloatingActionButton  createnewgroupbutton= (FloatingActionButton) findViewById(R.id.FAB);
        createnewgroupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(All_Grouptask_View.this, createnewgroup.class);
                startActivityForResult(intent, selectedgroupconstrant.creategroup);
            }
        });
        ///initialize all_group_name_view/////
        String username=global.getUserName();
        Log.d("username group ",username);
        adapter = new All_Grouptask_Adapter(this);
        recyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        setRecyclerLayout(recyclerView);
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("grouptasks").child(username);
        final DatabaseReference ref2, ref3, ref4;
        ref2 = ref1.child("grouptask");
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Result will be holded Here
                int i=0;
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        String firebasegroupname=dsp.getKey();
                        Log.d("hey there ",firebasegroupname);
                        adapter.updatePost(firebasegroupname,"details");
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
            ////get  values of the new group to update from the createnewgroup class
            String titles= data.getStringExtra(selectedgroupconstrant.titles);
            Log.d("ShareResourceActivity", "here result 2" + titles+" ");
            String details=data.getStringExtra(selectedgroupconstrant.details);
            ;
            Log.d("ShareResourceActivity", "here result 2" + titles+" "+details+" ");
            //Catagory= "Anonymous has shared a resource on "+Catagory;
            //update the view of group task by adding a new group
            adapter.updatePost(titles,details);
        }
    }
}
