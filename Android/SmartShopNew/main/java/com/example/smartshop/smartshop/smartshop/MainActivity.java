package ua.smartshop;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.appcompat.*;


import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import ua.smartshop.Activitys.CartActivity;
import ua.smartshop.Adapters.CategoryAdapter;
import ua.smartshop.Adapters.MainAdapter;
import ua.smartshop.Adapters.MainCategoryAdapter;
import ua.smartshop.Adapters.MainPagerAdapter;
import ua.smartshop.Adapters.ProductAdapter;
import ua.smartshop.Adapters.ProductItemAdapter;
import ua.smartshop.Enums.TypeRequest;
import ua.smartshop.Fragments.CartFragment;
import ua.smartshop.Fragments.CategoryProductFragment;
import ua.smartshop.Fragments.CategoryProductRootFragment;
import ua.smartshop.Fragments.MainContactFragment;
import ua.smartshop.Fragments.MainLogoFragment;
import ua.smartshop.Fragments.PreferenceFragment;
import ua.smartshop.Fragments.ProductDiscriptionFragment;
import ua.smartshop.Fragments.ProducttItemFragment;
import ua.smartshop.Models.Cart;
import ua.smartshop.Models.Profile;
import ua.smartshop.Utils.Сonstants;


public class MainActivity extends ActionBarActivity  implements
        MainAdapter.onSomeEventListener,
        ProductItemAdapter.onSomeEventListener,
        MainPagerAdapter.onSomeEventListener,
        View.OnClickListener,
        CategoryAdapter.onSomeEventListener,
        ProductAdapter.onSomeEventListener,
        AdapterView.OnItemSelectedListener,
        ProducttItemFragment.onUpDataCartListener,
        MainCategoryAdapter.onSomeEventListener

{

    public static final String KEY_ITEM = "KEY_ITEM";
    private static final String ACTION_SEARCH = "ACTION_SEARCH";
    public static final String URL_KEY = "URL_KEY";

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    //
    private Fragment fragment;
    private android.support.v4.app.FragmentTransaction ft;
    private boolean openMain;
    private TextView ui_hot = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        MainLogoFragment mainLogoFragment = new MainLogoFragment();
        onOpenFragment(mainLogoFragment);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //делаем поиск события
                openMain = true;
                onOpenFragment(new MainFragment());

            }
        }, 5000); //


        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
       //делаем фон
//        Drawable catdrawable = getResources().getDrawable( R.color.sub_main_orange );
//        getSupportActionBar().setBackgroundDrawable(catdrawable);
//        getSupportActionBar().show();

//        Window window =  getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(getResources().getColor(R.color.main_orange));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cart_make_order:
                someEvent( CartFragment.ACTION_GART_FRAGMENT,  null);
                break;
            case R.id.hotlist_bell:
                selectItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
        // mSelectText.setText("Выбранный элемент: " + mAdapter.GetItem(position));
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {
//        mSelectText.setText("Выбранный элемент: ничего");
    }

    @Override
    public void UpDataCart() {
        updateHotCount();
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {

        switch (position) {
            case 0:
                Intent ProfileIntent = new Intent(this,ProfileActivity.class);
                startActivity(ProfileIntent);
                break;
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                if (!Profile.mAuthorization){
                    Profile.startAuthorization(this);
                } else {
                    Intent CartIntent = new Intent(this,CartActivity.class);
                    startActivity(CartIntent);
                }
                break;
            case 3:
                fragment = new MainContactFragment();
                break;
            case 4:
                fragment = new PreferenceFragment();
                break;
            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            onOpenFragment(fragment);
            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        final MenuItem custom = menu.add(0, R.id.menu_search, 0,"");
        custom.setActionView(R.layout.main_action_bar_search);
        custom.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setIcon(R.drawable.ipad_b);
        final View actionView = menuItem.getActionView();

        final SearchView ButtonSearch = (SearchView) actionView.findViewById(R.id.searchView);
        //меняем значок поиска
        int searchIconId = ButtonSearch.getContext().getResources().
                getIdentifier("android:id/search_button", null, null);
        ImageView searchIcon = (ImageView) ButtonSearch.findViewById(searchIconId);
        searchIcon.setImageResource(R.drawable.abc_ic_search_api_mtrl_alpha);


        ButtonSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                someEvent(ACTION_SEARCH, s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(final String s) {
                return false;
            }
        });

        //////////////////////////////////////////////////////////////////////////////////

        final MenuItem customCart = menu.add(0, R.id.menu_hotlist, 0,"");
        customCart.setActionView(R.layout.main_action_bar_cart);
        customCart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        final View menu_hotlist = menu.findItem(R.id.menu_hotlist).getActionView();

        ui_hot = (TextView) menu_hotlist.findViewById(R.id.hotlist_hot);

        updateHotCount();

        final MenuItem menuItemCart = menu.findItem(R.id.menu_hotlist);
        final View actionViewCart = menuItemCart.getActionView();

        final View ButtonCart = actionViewCart.findViewById(R.id.hotlist_bell);
        ButtonCart.setOnClickListener(this);

        return super.onCreateOptionsMenu(menu);

    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }
    @Override
    public void someEvent(final String key, final String value) {

        Bundle bundleItem = new Bundle();
        bundleItem.putString(KEY_ITEM,  value);

        switch (key) {// обрабатывам клик на товар
            case MainAdapter.ACTION_ITEM:
            case ProductAdapter.ACTION_ITEM_PRODUCT:

                fragment = new ProducttItemFragment();
                break;
            case ProductItemAdapter.ACTION_DISRIPTION:// просмотр хар-к товара

                fragment = new ProductDiscriptionFragment();
                break;
            case MainAdapter.ACTION_CATEGORY_ALL:// открываем корень категорий

                fragment = new CategoryProductRootFragment();
                break;
            case MainPagerAdapter.ACTION_ONCLIK_ITEM_PEGER_ADAPTER://банер

                fragment = new ProductFragment();
                bundleItem.putString(URL_KEY, Сonstants.url_get_slider_main_page_category);
                break;
            case ACTION_SEARCH://поиск потовару

                fragment = new ProductFragment();
                bundleItem.putString(URL_KEY, Сonstants.url_get_search_products_two);
                break;
            case CategoryAdapter.ACTION_FROM_CATEGORY_PRODUCT:

                fragment = new ProductFragment();
                bundleItem.putString(URL_KEY, Сonstants.url_get_cproducts_from_category);

                break;
            case CategoryAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER://категория товаров
            case MainCategoryAdapter.ACTION_ONCLIK_ITEM_CATEGORY_ADAPTER_MAIN:

                 fragment = new CategoryProductFragment();
                break;
            default:
                break;
        }
        if (fragment!= null) {
            fragment.setArguments(bundleItem);
        }

        //открываем фрагмент
        onOpenFragment(fragment);
    }
    void onOpenFragment(Fragment fragment) {

        if (fragment!= null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            ft = fragmentManager.beginTransaction();
            if(fragment.getClass().equals(new MainLogoFragment().getClass()) || openMain){
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
                getSupportActionBar().show();
                openMain = false;

            } else {
                ft.addToBackStack(fragment.getClass().toString());
            }
            ft.replace(R.id.content_frame, fragment, fragment.getClass().toString());
            ft.commit();
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onSupportActionModeStarted(final ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        updateHotCount();
    }

    public void updateHotCount() {

        if (ui_hot == null) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Cart.mCart.size() == 0)
                    ui_hot.setVisibility(View.INVISIBLE);
                else {
                    ui_hot.setVisibility(View.VISIBLE);
                    ui_hot.setText(Integer.toString(Cart.mCart.size()));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateHotCount();
    }
}