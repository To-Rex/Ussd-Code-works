package app.app.ussdcode.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

import app.app.ussdcode.Fragments.FragmentBeeline;
import app.app.ussdcode.Fragments.FragmentMobiUz;
import app.app.ussdcode.Fragments.FragmentUsell;
import app.app.ussdcode.Fragments.FragmentUzMobile;

public class CompanyAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    public CompanyAdapter(FragmentManager manager) {
        super(manager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(FragmentBeeline fragmentBeeline, FragmentMobiUz fragmentMobiUz, FragmentUsell fragmentUsell, FragmentUzMobile fragmentUzMobile) {
        mFragmentList.add(fragmentMobiUz);
        mFragmentList.add(fragmentUzMobile);
        mFragmentList.add(fragmentBeeline);
        mFragmentList.add(fragmentUsell);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
