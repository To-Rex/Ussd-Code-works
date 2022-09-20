package app.app.ussdcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import app.app.ussdcode.Fragments.FragmentBeeline;
import app.app.ussdcode.Fragments.FragmentMobiUz;
import app.app.ussdcode.Fragments.FragmentUsell;
import app.app.ussdcode.Fragments.FragmentUzMobile;
import app.app.ussdcode.adapters.CompanyAdapter;

public class Sample extends AppCompatActivity {

    private TabLayout tabLayout;

    FragmentBeeline fragmentBeeline = new FragmentBeeline();
    FragmentUsell fragmentUsell = new FragmentUsell();
    FragmentUzMobile fragmentUzMobile = new FragmentUzMobile();
    FragmentMobiUz fragmentMobiUz = new FragmentMobiUz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //appbar hide
        getSupportActionBar().hide();
        setContentView(R.layout.sample);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences1 = this.getSharedPreferences("datfragment.iso", Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences1.edit();

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.red));
                    new FragmentBeeline().onPause();
                    new FragmentUsell().onPause();
                    new FragmentUzMobile().onResume();
                    new FragmentMobiUz().onPause();
                }
                if (tabLayout.getSelectedTabPosition() == 1) {
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_light));
                    new FragmentBeeline().onPause();
                    new FragmentUsell().onPause();
                    new FragmentUzMobile().onResume();
                    new FragmentMobiUz().onPause();
                }
                if (tabLayout.getSelectedTabPosition() == 2) {
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.orange1));
                    new FragmentUsell().onPause();
                    new FragmentBeeline().onResume();
                    new FragmentUzMobile().onPause();
                    new FragmentMobiUz().onPause();
                }
                if (tabLayout.getSelectedTabPosition() == 3) {
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_purple));
                    new FragmentBeeline().onPause();
                    new FragmentUsell().onResume();
                    new FragmentUzMobile().onPause();
                    new FragmentMobiUz().onPause();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        CompanyAdapter adapter = new CompanyAdapter(getSupportFragmentManager());
        adapter.addFrag(fragmentBeeline, fragmentMobiUz, fragmentUsell, fragmentUzMobile);
        viewPager.setAdapter(adapter);
    }
}