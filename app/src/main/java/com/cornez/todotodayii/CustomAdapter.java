package com.cornez.todotodayii;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * Created by mukul on 12/14/17.
 */

public class CustomAdapter extends ArrayAdapter<ToDo_Item> implements View.OnClickListener{

    private ArrayList<ToDo_Item> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtMedName;
        TextView txtDose;
        TextView txtUnits;
        TextView txtFrequencyWeek;
        TextView txtFrequencyDay;


        ImageView info;
    }



    public CustomAdapter(ArrayList<ToDo_Item> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ToDo_Item dataModel=(ToDo_Item)object;




        switch (v.getId())
        {

            case R.id.item_info:

                Snackbar.make(v, "Times Per Day:  " +dataModel.getDay(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();

                break;


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ToDo_Item dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtMedName = (TextView) convertView.findViewById(R.id.tx_med_name);
            viewHolder.txtDose = (TextView) convertView.findViewById(R.id.tx_dose);
            viewHolder.txtUnits = (TextView) convertView.findViewById(R.id.tx_units);
            viewHolder.txtFrequencyWeek = (TextView) convertView.findViewById(R.id.tx_frequency_week);
            viewHolder.txtFrequencyDay = (TextView) convertView.findViewById(R.id.tx_frequency_day);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.txtMedName.setText(dataModel.get_med_id());
        viewHolder.txtDose.setText(dataModel.getDose());
        viewHolder.txtUnits.setText(dataModel.getUnits());
        viewHolder.txtFrequencyWeek.setText(dataModel.getWeek());
        viewHolder.txtFrequencyDay.setText(dataModel.getDay());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


}
