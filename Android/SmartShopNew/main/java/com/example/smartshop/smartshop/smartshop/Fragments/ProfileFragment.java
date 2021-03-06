package ua.smartshop.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import ua.smartshop.Utils.AsyncWorker;
import ua.smartshop.interfaces.AsyncWorkerInterface;
import ua.smartshop.Models.Profile;
import ua.smartshop.Adapters.ProfileAdapter;
import ua.smartshop.R;
import ua.smartshop.Enums.TypeRequest;

/**
 * Created by Gens on 10.03.2015.
 */
public class ProfileFragment extends android.support.v4.app.Fragment implements AsyncWorkerInterface {

    private ArrayList<Profile> mProfile = new ArrayList<Profile>();
    private ListView lvMain;
    private ProfileAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list, container,
                false);

        if (mProfile.size() == 0   ){
            doSomethingAsyncOperaion( Profile.getParamsuserNameUrl(Profile.mUserName) , getString(R.string.url_get_user),  TypeRequest.POST);
        }
        itemAdapter = new ProfileAdapter(getActivity(), mProfile);
        // настраиваем список
        lvMain = (ListView) rootView.findViewById(R.id.lvMain);
        lvMain.setAdapter(itemAdapter);

        return rootView;
    }

    private void doSomethingAsyncOperaion(HashMap paramsUrl,String url, TypeRequest typeRequest) {

        new AsyncWorker(this, paramsUrl, url, typeRequest, getActivity()) {
        }.execute();
    }

    @Override
    public void onBegin() {

    }

    @Override
    public void onSuccess(final JSONArray mPJSONArray) {
        try {

            Profile profile = null;
            for (int i = 0; i < mPJSONArray.length(); i++) {
                profile =  new Gson().fromJson(mPJSONArray.getJSONObject(i).toString(), Profile.class);
                mProfile.add(profile);
            }
            if (profile != null){
                for (int i = 0; i < 5; i++) {
                    mProfile.add(profile);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(final Throwable t) {

    }

    @Override
    public void onEnd() {

    }
}
