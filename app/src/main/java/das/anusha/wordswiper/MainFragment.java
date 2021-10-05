package das.anusha.wordswiper;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment {
    ViewPager2 parentScrn;
    Chord scrnChord;
    Chord[] allChords;
    //static method can create itself
    public static Fragment newInstance(ViewPager2 parentScrn, Chord pos, Chord[] allChords){
        MainFragment fragment = new MainFragment();//uses super constructor calls overrided methods
        fragment.parentScrn = parentScrn;
        fragment.scrnChord = pos;
        fragment.allChords = allChords;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState); inflate layout ourselves
        return inflater.inflate(R.layout.frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //access text, and buttons
        TextView extTxt = view.findViewById(R.id.words);
        TextView baseTxt = view.findViewById(R.id.baseChordName);
        int randExtIndx = scrnChord.getRandomExt();
        String txtViewTxt = scrnChord.getBase() + "\n" + scrnChord.getNotes();
        extTxt.setText(scrnChord.getExtString(randExtIndx));
        baseTxt.setText(txtViewTxt);
        boolean correctness = scrnChord.isAvailable(randExtIndx);

        Animation correct = AnimationUtils.loadAnimation(view.getContext(), R.anim.button_correct);
        Animation wrong = AnimationUtils.loadAnimation(view.getContext(), R.anim.button_wrong);
        AppCompatButton yesBtn = view.findViewById(R.id.myBtn1);
        AppCompatButton noBtn = view.findViewById(R.id.myBtn2);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctness) yesBtn.startAnimation(correct);
                else yesBtn.startAnimation(wrong);
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctness) noBtn.startAnimation(wrong);
                else noBtn.startAnimation(correct);
            }
        });

        //can access parent activity layout (no need for activity bundles)
        TabLayout fragBar = getActivity().findViewById(R.id.fragPicker);
        //ADD ALL TABS as frags being created from adapter, creates variables tab and position
        new TabLayoutMediator(fragBar, parentScrn, (tab, position) -> tab.setText(allChords[position].getBase())).attach();
    }

    ////https://evgenii.com/blog/spring-button-animation-on-android/
        // make a set .startAnimation on it
        //setInterpolater for realistic bounce or falling effect

    //developer.android.com/training/animation/overview - bitmap
    //define  original shape: GROUP of  PATH values (vector)-drawable folder
        //search up vector attribute possibilites
    //target those paths or whole group and set their animation (animated-vector)-drawable folder
    //specify time location etc(objectAnimator or set-objectAnimator)
        //call in java using ObjectAnimator or AnimatorSet
}

