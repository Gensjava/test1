package com.example.smartshop.smartshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gens on 10.02.2015.
 */
public class CategoryAdapter extends ArrayAdapter<CategoryProduct> implements View.OnClickListener  {

    private final LayoutInflater mLayoutInflater;
    Context ctx;
    onSomeEventListener someEventListener ;
    
    CategoryAdapter(Context context, final int resource, ArrayList<CategoryProduct> objects) {
        super(context, resource, objects);
        mLayoutInflater = LayoutInflater.from(context);
        ctx = context;
    }
    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       
        final ViewHolder viewHolder;
        final CategoryProduct item = getItem(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.category, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.categoryNameTextView = (TextView) convertView.findViewById(R.id.category_all_text);
            viewHolder.categoryImageView = (ImageView) convertView.findViewById(R.id.category__all_imageView);
            viewHolder.categoryNameTextView.setOnClickListener(this);
            
            viewHolder.categoryNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                   // someEventListener = (onSomeEventListener) ctx;
                    //someEventListener.someEvent("OnClickAdapterCategory",item.getId());
                    Сonstants.mCategory.clear();
                    //new UtilAsyncTask(item.getId(),(android.app.Activity) ctx).execute();
                }
            });


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
         }
       
        if(item != null){
            viewHolder.categoryNameTextView.setText(item.getName());
            viewHolder.categoryImageView.setImageResource(item.getImageView());
        }
                                
        return convertView;
    }



    public interface onSomeEventListener {

        public void someEvent(String id,String idItem );
    }
    @Override
    public void onClick(View v) {

    }

    private static class ViewHolder {
        public TextView categoryNameTextView;
        public ImageView categoryImageView;
    }
}
