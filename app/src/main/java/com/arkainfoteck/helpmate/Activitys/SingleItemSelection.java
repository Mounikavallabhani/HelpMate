package com.arkainfoteck.helpmate.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arkainfoteck.helpmate.Adapter.CustomAdapter;
import com.arkainfoteck.helpmate.Model.Person;
import com.arkainfoteck.helpmate.R;

import java.util.ArrayList;

public class SingleItemSelection extends AppCompatActivity {
    private ArrayList<Person> mPersonList;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_selection);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        try {
                            mAdapter.setSelected(position);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
        setupPersonList();
    }
    private void setupPersonList() {
        mPersonList = new ArrayList<Person>();
        mPersonList.clear();
        for (int i = 0; i < 25; i++) {
             person = new Person("Person ");
            mPersonList.add(person);
        }
        mAdapter = new CustomAdapter(mPersonList, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}















