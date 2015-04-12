package com.example.smartshop.smartshop;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gens on 02.03.2015.
 */

public class MainFragment extends android.support.v4.app.Fragment {

    private int itemNumber = 1;
    private ArrayList<ProductDual> mPoducts = new ArrayList<ProductDual>();
    private MainAdapter mMainAdapter;
    private ListView lvMain;
    private Product ProductOne;
    private Product ProductTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        //
        mMainAdapter = new  MainAdapter(getActivity(), mPoducts);
        // настраиваем список
        lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        //
        lvMain.setAdapter(mMainAdapter);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount ) {
                    itemNumber  ++;
                    //
                    String mUrl = Сonstants.url_all_products;
                    HashMap<String, String> mParamsUrl = Product.getParamsUrlNumber(itemNumber);
                    //
                    UtilAsyncHttpClient utilAsyncTask = new UtilAsyncHttpClient( mParamsUrl, mUrl, getTag(), TypeRequest.GET ,getActivity()) ;
                    utilAsyncTask.getAsyncArrayValues();
                }
            }
        });
        return rootView;
    }

    void executeArrayValues(final JSONArray mPJSONArray)  {

        try {
            // проходим в цикле через все товары
            for (int i = 0; i < mPJSONArray.length(); i++) {
                JSONObject c = mPJSONArray.getJSONObject(i);

                String id = c.getString(Сonstants.TAG_PID);
                String wayImage = c.getString(Сonstants.TAG_WAY_IMAGE);
                String fullImage = Сonstants.url_main_way_image + wayImage;

                double price = 0.00;

                price = Double.parseDouble(c.getString(Сonstants.TAG_PRICE));
                switch (i) {
                    case 0:
                        ProductOne = new Product(price, R.drawable.flatscreen, id,fullImage);
                        break;
                    case 1:
                        ProductTwo = new Product(price, R.drawable.flatscreen, id, fullImage);
                        break;
                    default:
                        break;
                }
            }
            mPoducts.add(new ProductDual(ProductOne, ProductTwo));
            mMainAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
