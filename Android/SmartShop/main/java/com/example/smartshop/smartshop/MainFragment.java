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
import java.util.List;

/**
 * Created by Gens on 02.03.2015.
 */

public class MainFragment extends android.support.v4.app.Fragment implements AdapterViewPager.onSomeEventListener {

    public MainFragment() {
    }

    private int itemNumber = 1;
    ArrayList<ProductDual> mPoducts = new ArrayList<ProductDual>();

    MainAdapter mainAdapter;

    ListView lvMain;
    private ProgressDialog pDialog;

    Product ProductOne;
    Product ProductTwo;
    // массив товаров JSONArray
    JSONArray products;
    // Создаем объект JSON Parser
    JSONParser jParser = new JSONParser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_main, container,
                false);

        new LoadAllProductsTask().execute();

        // создаем адаптер
        fillData();
        mainAdapter = new MainAdapter(getActivity(), mPoducts);

        // настраиваем список
        lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(mainAdapter);

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    itemNumber++;
                    new LoadAllProductsTask().execute();
                }
            }
        });
        return rootView;
    }
    // генерируем данные для адаптера
    void fillData() {

        for (int i = 1; i <= 3; i++) {
            mPoducts.add(new ProductDual(new Product(), new Product()));
        }
    }

    @Override
    public void someEvent(String view_id, String item_id) {
        Log.i("getView2", "getView2");
    }

    class LoadAllProductsTask extends AsyncTask<String, String, String> {
        // Сначала покажем диалоговое окно прогресса

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("itemnumber", "" + itemNumber));

            try {
                // получим строку JSON из URL
                JSONObject json = jParser.makeHttpRequest( Сonstants.url_all_products, "GET",
                        params);
                int success = json.getInt(Сonstants.TAG_SUCCESS);

                if (success == 1) {     // товар найден
                    // получаем массив товаров
                    products = json.getJSONArray(Сonstants.TAG_PRODUCTS);

                    // проходим в цикле через все товары
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        String id = c.getString(Сonstants.TAG_PID);
                        String wayImage = c.getString(Сonstants.TAG_WAY_IMAGE);
                        String fullImage = Сonstants.url_main_way_image + wayImage;

                        double price = 0.00;

                        if (!(c.getString(Сonstants.TAG_PRICE).equals("null"))){
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
                    }
                    mPoducts.add(new ProductDual(ProductOne, ProductTwo));
                } else {
                    // не нашли товар по pid
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
           // pDialog.dismiss();
            mainAdapter.notifyDataSetChanged();
        }
    }
    // Задача в другом потоке для загрузки всех товаров через HTTP Request
//    void LoadAllProductsTask ()  {
//
//        // Строим параметры
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        // получим строку JSON из URL
//
//        params.add(new BasicNameValuePair("itemnumber", "" + itemNumber));
//        String paramString = URLEncodedUtils.format(params, "utf-8");
//
//        String url = Сonstants.url_all_products+"/?"+paramString;
//
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            Product ProductOne ;
//            Product ProductTwo ;
//            @Override
//            public void onResponse(JSONObject response) {
//                // TODO Auto-generated method stub
//
//                JSONObject json = response;
//                try {
//                    int success = response.getInt(Сonstants.TAG_SUCCESS);
//
//                    if (success == 1) {     // товар найден
//                        // получаем массив товаров
//                        products = response.getJSONArray(Сonstants.TAG_PRODUCTS);
//                        //
//
//
//                        // проходим в цикле через все товары
//                        for (int i = 0; i < products.length(); i++) {
//                            JSONObject c = products.getJSONObject(i);
//
//                            String id = c.getString(Сonstants.TAG_PID);
//                            String wayImage = c.getString(Сonstants.TAG_WAY_IMAGE);
//                            String fullImage = Сonstants.url_main_way_image + wayImage;
//
//                            double price = 0.00;
//
//                            if (!(c.getString(Сonstants.TAG_PRICE).equals("null"))){
//                                price = Double.parseDouble(c.getString(Сonstants.TAG_PRICE));
//                                switch (i) {
//                                    case 0:
//                                        ProductOne = new Product(price, R.drawable.flatscreen, id,fullImage);
//                                        break;
//                                    case 1:
//                                        ProductTwo = new Product(price, R.drawable.flatscreen, id, fullImage);
//                                        break;
//                                    default:
//                                        break;
//                                }
//                            }
//                        }
//                        mPoducts.add(new ProductDual(ProductOne, ProductTwo));
//                        mainAdapter.notifyDataSetChanged();
//
//                    } else {
//                        // не нашли товар по pid
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//        queue.add(jsObjRequest);
//        //обновляем
//    }
}
