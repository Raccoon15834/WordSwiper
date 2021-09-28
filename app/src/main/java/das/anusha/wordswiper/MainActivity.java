package das.anusha.wordswiper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout myBar;
    ViewPager2 swipeScrn;
    RecyclerView.Adapter myFragAdater;
    int NUMSCRNS = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBar = findViewById(R.id.fragPicker);
        swipeScrn = findViewById(R.id.swipeContainer);

        //pass yourself so adaptor attaches to thiss activity
        myFragAdater = new MyFragAdapter(this);
        swipeScrn.setAdapter(myFragAdater);
        //set up chord identities
        String[] chordBases = getResources().getStringArray(R.array.chordbases);
        Chord[] myChords=  new Chord[chordBases.length];
        for(int i=0; i<myChords.length; i++)
            myChords[i] = new Chord(chordBases[i]);
    }

    private class MyFragAdapter extends FragmentStateAdapter {
        public MyFragAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(swipeScrn, position);//sends in this frags pos
        }

        @Override
        public int getItemCount() {
            return NUMSCRNS;//how many screens
        }
    }
}

//view pagerAdapter or FragmentPagerAdapter??? How do you cycle through fragments??
//add color TODO
//Shift + F6 -- refactor all of that var name!!!
//Video 31 out of 34

//submit P3L06aDasAnushaViewPager2.apk, video, all java and xml
//embellish with sound, diff word swipe. P3L06bDasAnushaViewPager2.apk and video
