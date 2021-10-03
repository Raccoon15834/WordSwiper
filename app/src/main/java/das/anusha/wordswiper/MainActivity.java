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
    Chord[] myChords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up chord identities
        String[] chordBases = getResources().getStringArray(R.array.chordbases);
        String[] extensions = getResources().getStringArray(R.array.extensions);
        myChords=  new Chord[chordBases.length];
        for(int i=0; i<myChords.length; i++)
            myChords[i] = new Chord(chordBases[i], extensions, i);
        setUpChords(myChords);

        myBar = findViewById(R.id.fragPicker);
        swipeScrn = findViewById(R.id.swipeContainer);

        //CREATE ADAPTER CLASS to create fragments
        //pass yourself so adaptor attaches to thiss activity
        myFragAdater = new MyFragAdapter(this);
        swipeScrn.setAdapter(myFragAdater);
    }

    private class MyFragAdapter extends FragmentStateAdapter {
        public MyFragAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        //MAKE OWN CLASS to instantiate fragment in viewpager
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(swipeScrn, myChords[position]);//sends in fragment
        }

        @Override
        public int getItemCount() {
            return myChords.length;//how many screens
        }
    }

    public void setUpChords(Chord[] chords){
        chords[0].setAvailable(new int[]{1, 3, 5});//CMaj7
        chords[1].setAvailable(new int[]{1, 2, 5});//Cm7
        chords[2].setAvailable(new int[]{1, 2, 4});//aug
        chords[3].setAvailable(new int[]{1, 2, 5});//CmMaj7
        chords[4].setAvailable(new int[]{1, 3});//CMaj+7
        chords[5].setAvailable(new int[]{1, 2, 4, 6});//dim
    }
}

//TODO color, polish activity main layout, icon,
// TODO cycle fragments, every time fragment reloaded diff question
//TODO fix tab names
//Shift + F6 -- refactor all of that var name!!!

//submit P3L06aDasAnushaViewPager2.apk, video, all java and xml
//embellish with sound, diff word swipe. P3L06bDasAnushaViewPager2.apk and video
