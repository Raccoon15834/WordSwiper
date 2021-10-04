package das.anusha.wordswiper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class CoverActivity extends Activity {
    AppCompatButton startBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        getWindow().setExitTransition(new StartTransition());

        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainScrnChange = new Intent(getApplicationContext(), MainActivity.class);
                ImageView titleLogo = findViewById(R.id.title);
                startActivity(mainScrnChange);
            }
        });

    }

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.??, R.anim.??);
//    }
// https://developer.android.com/guide/topics/resources/animation-resource#Tween
}
