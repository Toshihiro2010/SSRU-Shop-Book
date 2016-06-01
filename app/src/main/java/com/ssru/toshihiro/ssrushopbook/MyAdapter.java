package com.ssru.toshihiro.ssrushopbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Admin on 1/6/2559.
 */
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] nameStrings , priceStrings , coverStrings;


    public MyAdapter(Context context,
                     String[] nameStrings,
                     String[] priceStrings,
                     String[] coverStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.priceStrings = priceStrings;
        this.coverStrings = coverStrings;



    }//Constructor


    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_listview , viewGroup , false);

        TextView nameTextView = (TextView) view1.findViewById(R.id.textView10);
        nameTextView.setText(nameStrings[i]);

        TextView priceTextView = (TextView) view1.findViewById(R.id.textView11);
        priceTextView.setText(priceStrings[i] + " THB. ");




        return view1;
    }
}//Main Class
