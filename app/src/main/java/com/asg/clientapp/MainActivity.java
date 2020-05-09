package com.asg.clientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
   static Toolbar toolbar;
    DrawerLayout drawer;
    static String currentfrag="";
    static String preFrag = "";
    DrawerLayout dreaw;
    LinearLayout numberpin,maindata;
    EditText edtnumber;
    Button sure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberpin = findViewById(R.id.numberpin);
        edtnumber = findViewById(R.id.number);
        sure=findViewById(R.id.sure);
        maindata=findViewById(R.id.maindata);


        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtnumber.getText().toString().trim().equals(getString(R.string.number_pin)))
                {
                    setFragment(new HomeFragment());
                    numberpin.setVisibility(View.GONE);

                    maindata.setVisibility(View.VISIBLE);
                }
                else {
                    Toasty.error(getApplicationContext(),"နံပါတ်မှားနေပါသည်။ကျေးဇူးပြုပြီး ပြန်ရိုက်ပေးပါ။",Toasty.LENGTH_LONG).show();
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);
        drawer =findViewById(R.id.drewer);

        setSupportActionBar(toolbar);
        setTitle("Home");
        currentfrag =getString(R.string.Home_frag);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,
                toolbar,
                (R.string.openDrawer),
                 (R.string.closeDrawer));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =findViewById(R.id.navView);

        View headerView = navigationView.getHeaderView(0);
        TextView txtVersion = headerView.findViewById(R.id.versionName);
        txtVersion.setText("Version"+BuildConfig.VERSION_NAME);

        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_menu)
                {
                    setFragment(new HomeFragment());
                    setTitle(getString(R.string.Home_frag));
                    currentfrag=getString(R.string.Home_frag);
                }
                if (item.getItemId() == R.id.movie_menu)
                {
                    setFragment(new MoviesFragment());
                    setTitle(getString(R.string.Movie_frag));
                    currentfrag=getString(R.string.Movie_frag);
                }
                 if (item.getItemId() == R.id.series_menu)
                {
                    setFragment(new SeriesFragment());
                    setTitle(getString(R.string.Series_frag));
                    currentfrag=getString(R.string.Series_frag);
                }

                 else if (item.getItemId() == R.id.share_menu)
                 {
                     Intent shareIntent = new Intent();
                     currentfrag =getString(R.string.Share_frag);
                     shareIntent.setAction(Intent.ACTION_SEND);

                     shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);

                   /* shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.plarium.solitaire");*/



                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);


                 }
                 else if (item.getItemId() == R.id.search_menu)
                 {
                     currentfrag= getString(R.string.search_frag);
                     setFragment(new SearchFragment());
                     setTitle(currentfrag);

                 }
                 else if (item.getItemId() == R.id.request_menu)
                 {
                     currentfrag = getString(R.string.Request_str);
                     setFragment(new RequestFragment());
                     setTitle(getString(R.string.Request_str));
                 }

                 else if (item.getItemId() == R.id.about_menu)
                 {
                     currentfrag=(getString(R.string.About_frag));
                     setFragment(new AboutFragment());
                     setTitle(getString(R.string.About_frag));


                 }
                 if (VideoDetialFragment.player!=null)
                 {
                     VideoDetialFragment.player.stop();
                 }

               drawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }
    public void setFragment(Fragment f)
    {
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.navContent,f);
        ft.commit();
    }

    public static void setHeader(String tiele)
    {

    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onBackPressed() {
       if (currentfrag.equals(getString(R.string.Home_frag)))
       {
           if (drawer.isDrawerOpen(Gravity.LEFT))
           {
               drawer.closeDrawer(Gravity.LEFT);
           }
           else
           {
               AlertDialog.Builder alert = new AlertDialog.Builder(this);
               alert.setTitle("Confirmation");
               alert.setMessage("Are You Sure To Exit?");
               alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       finish();;
                       finishAffinity();;
                   }
               });
               alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               alert.show();
           }
       }

       else  if  (currentfrag.equals(getString(R.string.Movie_frag)) ||
                 currentfrag.equals(getString(R.string.Request_str))
               || currentfrag.equals(getString(R.string.Series_frag))
               || currentfrag.equals(getString(R.string.About_frag))
               || currentfrag.equals(getString(R.string.search_frag))
       )
       {
           setFragment(new HomeFragment());
           currentfrag = getString(R.string.Home_frag);
           setTitle(currentfrag);
       }
       else if (currentfrag.equals(getString(R.string.series_det_frag)))
       {
           if (preFrag.equals(getString(R.string.Series_frag)))
           {
               setFragment(new HomeFragment());
               currentfrag=getString(R.string.Series_frag);
               setTitle(currentfrag);
           }
           else if (preFrag.equals(R.string.Series_frag))
           {

               setFragment(new SeriesFragment());
               currentfrag = getString(R.string.Series_frag);
               setTitle(currentfrag);
           }
           else if (preFrag.equals(getString(R.string.movie_det_frag)))

           {
               setFragment(new SeriesFragment());
               currentfrag=getString(R.string.Series_frag);
               setTitle(currentfrag);
               preFrag=getString(R.string.series_det_frag);
               VideoDetialFragment.player.stop();

           }
           else if (preFrag.equals(getString(R.string.search_frag)))
           {
               setFragment(new SearchFragment());
               currentfrag = getString(R.string.search_frag);
               setTitle(currentfrag);
           }
       }



       else if (currentfrag.equals(getString(R.string.movie_det_frag)))
       {
            if (preFrag.equals(getString(R.string.Home_frag)))
            {
                setFragment(new HomeFragment());
                currentfrag = getString(R.string.Home_frag);
                setTitle(currentfrag);
            }
            else if (preFrag.equals(getString(R.string.Movie_frag)))
            {
                setFragment(new MoviesFragment());
                currentfrag = getString(R.string.Movie_frag);
                setTitle(currentfrag);
            }
            else if (preFrag.equals(getString(R.string.series_det_frag)))
            {
                SeriesDetialFragment detfrag = new SeriesDetialFragment();
                setFragment(detfrag);
                currentfrag=getString(R.string.series_det_frag);
                preFrag=getString(R.string.movie_det_frag);
                setTitle(SeriesDetialFragment.model.seriesName);
            }


            else if (preFrag.equals(getString(R.string.search_frag)))
            {
                setFragment(new SearchFragment());
                currentfrag = getString(R.string.search_frag);
                setTitle(currentfrag);
                preFrag = getString(R.string.movie_det_frag);
            }
            else
            {
                setFragment(new SeriesFragment());
                currentfrag=getString(R.string.Series_frag);
                setTitle(currentfrag);

            }
            VideoDetialFragment.player.stop();
           setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           MainActivity.toolbar.setVisibility(View.VISIBLE);
       }
       else
       {
           super.onBackPressed();;
       }
       if (VideoDetialFragment.player!=null)
       {
           VideoDetialFragment.player.stop();
       }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(VideoDetialFragment.player!=null)
        {
            VideoDetialFragment.player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (VideoDetialFragment.player!=null)
        {
            VideoDetialFragment.player.setPlayWhenReady(false);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (VideoDetialFragment.player!=null)
        {
            VideoDetialFragment.player.setPlayWhenReady(true);
        }
    }
}
