package com.example.smartshop.smartshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Gens on 27.03.2015.
 */
public class OrderFinishFragment  extends android.support.v4.app.Fragment  {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.order, container,
                false);
        ListView lvCart = (ListView) rootView.findViewById(R.id.order_listView);



        return rootView;
    }
}
