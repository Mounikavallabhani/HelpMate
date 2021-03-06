package com.arkainfoteck.helpmate.Adapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.helpmate.Model.Person;
import com.arkainfoteck.helpmate.R;

import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Person> mPersonList;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView personNameTextView, personAddTextView, personDsgTextView;
        LinearLayout list_row;
        public ViewHolder(View v) {
            super(v);
            personNameTextView = (TextView) v.findViewById(R.id.timeslot);
            list_row = (LinearLayout) v.findViewById(R.id.list_row);

        }
    }
    public void add(int position, Person item) {
        mPersonList.add(position, item);
        notifyItemInserted(position);
    }
    public void remove(String item) {
        int position = mPersonList.indexOf(item);
        mPersonList.remove(position);
        notifyItemRemoved(position);
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapter(ArrayList<Person> personList, Context context) {
        mPersonList = personList;
        mPref = context.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();
        this. context=context;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.personNameTextView.setText(mPersonList.get(position).getPersonName());
        Log.e("selection", "" + mPersonList.get(position).isSelected());
        if (mPersonList.get(position).isSelected()) {
            holder.list_row.setBackgroundResource(R.drawable.selectitemshape);

            sharedPreferences=context.getSharedPreferences("part_time_details",Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("time_slots",mPersonList.get(position).getPersonName());
            editor.apply();
            editor.commit();





        } else {

            holder.list_row.setBackground(context.getResources().getDrawable(R.drawable.timeshape));

       //     Toast.makeText(context,"not_ok "+mPersonList.get(position).getPersonName(),Toast.LENGTH_LONG).show();
        }
    }
    public void setSelected(int pos) {
        try {
            if (mPersonList.size() > 1) {
                mPersonList.get(mPref.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            mPersonList.get(pos).setSelected(true);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
}
