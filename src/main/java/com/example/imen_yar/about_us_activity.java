package com.example.imen_yar;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class about_us_activity extends AppCompatActivity {
    ImageButton back;
    ImageView image;
    TextView t1;
    TextView t2;
    TextView t3;
    Display displaySize;
    Point size;
    int width;
    int height;
    DrawerLayout.LayoutParams params_back;
    DrawerLayout.LayoutParams params_i;
    DrawerLayout.LayoutParams params_t1;
    DrawerLayout.LayoutParams params_t2;
    DrawerLayout.LayoutParams params_t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_activity);
        back = findViewById(R.id.back2);
        image = findViewById(R.id.error);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);

        displaySize = getWindowManager().getDefaultDisplay();
        size = new Point();
        displaySize.getSize(size);
        width = size.x;
        height = size.y;

        params_back = (DrawerLayout.LayoutParams) back.getLayoutParams();
        params_i = (DrawerLayout.LayoutParams) image.getLayoutParams();
        params_t1 = (DrawerLayout.LayoutParams) t1.getLayoutParams();
        params_t2 = (DrawerLayout.LayoutParams) t2.getLayoutParams();
        params_t3 = (DrawerLayout.LayoutParams) t3.getLayoutParams();

        if(width == 1080 && height == 1920){
            //Back Button Size
            back.setLayoutParams(new DrawerLayout.LayoutParams(38,60));
            params_back.setMargins(48,48,994,1740);
            back.setLayoutParams(params_back);
            //Image Size
            image.setLayoutParams(new DrawerLayout.LayoutParams(615,459));
            params_i.setMargins(232, 498,233,891);
            image.setLayoutParams(params_i);
            //Text1 Size
            t1.setLayoutParams(new DrawerLayout.LayoutParams(1080,240));
            params_t1.setMargins(0, 928,0,680);
            t1.setLayoutParams(params_t1);
            //Text2 Size
            t2.setLayoutParams(new DrawerLayout.LayoutParams(1080,240));
            params_t2.setMargins(0, 998,0,610);
            t2.setLayoutParams(params_t2);
            //Text3 Size
            t3.setLayoutParams(new DrawerLayout.LayoutParams(1080,240));
            params_t3.setMargins(0, 1068,0,540);
            t3.setLayoutParams(params_t3);
        }
        back.setOnClickListener(v -> {
            Intent intent = new Intent(about_us_activity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
