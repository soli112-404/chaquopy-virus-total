package com.example.imen_yar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton menu;
    ImageButton b_main;
    Button b_link;
    TextView text;
    DrawerLayout mDrawer;
    Display displaySize;
    Point size;
    int width;
    int height;
    int iii = 0;
    int activity = 0;
    DrawerLayout.LayoutParams params_b;
    DrawerLayout.LayoutParams params_t;
    DrawerLayout.LayoutParams params_b_l;
    String path;
    String interpreterOutput;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.mct);
        b_main = findViewById(R.id.b_main);
        b_link = findViewById(R.id.b_link);
        text = findViewById(R.id.textView);
        mDrawer = findViewById(R.id.drawer_layout);

        displaySize = getWindowManager().getDefaultDisplay();
        size = new Point();
        displaySize.getSize(size);
        width = size.x;
        height = size.y;

        params_b = (DrawerLayout.LayoutParams) b_main.getLayoutParams();
        params_t = (DrawerLayout.LayoutParams) text.getLayoutParams();
        params_b_l = (DrawerLayout.LayoutParams) b_link.getLayoutParams();

        menu.setOnClickListener(v -> mDrawer.openDrawer(GravityCompat.START));
        b_link.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, check_link.class);
            startActivity(intent);
        });
        NavigationView nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        if(width == 1080 && height == 1920){
            //Button Size
            b_main.setLayoutParams(new DrawerLayout.LayoutParams(750,750));
            params_b.setMargins(165, 549,165,549);
            b_main.setLayoutParams(params_b);
            //Text Size
            text.setLayoutParams(new DrawerLayout.LayoutParams(686,135));
            params_t.setMargins(0, 1278,394,435);
            text.setLayoutParams(params_t);
            //Link Button Size
            b_link.setLayoutParams(new DrawerLayout.LayoutParams(750,750));
            params_b_l.setMargins(686, 1278,118,435);
            b_link.setLayoutParams(params_b_l);
        }
        //else if(width == 720 && height == 1280){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(500, 500));
            //params_b.setMargins(110, 364,110,364);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(510,90));
            //params_t.setMargins(0, 900,210,290);
            //text.setLayoutParams(params_t);
        //}else if(width == 1440 && height == 2560){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(1000, 1000));
            //params_b.setMargins(220, 780,220,780);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1440,180));
            //params_t.setMargins(0, 1800,420,580);
            //text.setLayoutParams(params_t);
        //}else if(width == 540 && height == 960){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(375, 375));
            //params_b.setMargins(83, 293,82,292);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(540,68));
            //params_t.setMargins(0, 675,158,217);
            //text.setLayoutParams(params_t);
        //}else if(width == 1080 && height == 2400){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(750, 750));
            //params_b.setMargins(165, 825,165,825);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1080,135));
            //params_t.setMargins(0, 1688,315,577);
            //text.setLayoutParams(params_t);
        //}else if(width == 1080 && height == 2310){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(750, 750));
            //params_b.setMargins(165, 780,165,780);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1080,135));
            //params_t.setMargins(0, 1624,315,551);
            //text.setLayoutParams(params_t);
        //}else if(width == 1080 && height == 2340){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(750, 750));
            //params_b.setMargins(165, 795,165,795);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1080,135));
            //params_t.setMargins(0, 1645,315,560);
            //text.setLayoutParams(params_t);
        //}else if(width == 1440 && height == 3200){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(1000, 1000));
            //params_b.setMargins(220, 1100,220,1100);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1440,180));
            //params_t.setMargins(0, 2250,0,725);
            //text.setLayoutParams(params_t);
        //}else if(width == 1440 && height == 3088){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(1000, 1000));
            //params_b.setMargins(220, 1044,220,1044);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1440,180));
            //params_t.setMargins(0, 2171,0,737);
            //text.setLayoutParams(params_t);
        //}else if(width == 1440 && height == 3040){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(1000, 1000));
            //params_b.setMargins(220, 1020,220,1020);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1440,180));
            //params_t.setMargins(0, 2138,0,722);
            //text.setLayoutParams(params_t);
        //}else if(width == 1440 && height == 2280){
            //Button size
            //b_main.setLayoutParams(new DrawerLayout.LayoutParams(1000, 1000));
            //params_b.setMargins(220, 640,220,640);
            //b_main.setLayoutParams(params_b);
            //Text size
            //text.setLayoutParams(new DrawerLayout.LayoutParams(1440,180));
            //params_t.setMargins(0, 1603,0,497);
            //text.setLayoutParams(params_t);
        //}else{
        //    menu.setBackground(getDrawable(R.drawable.white));
        //    b_main.setBackground(getDrawable(R.drawable.size_screen_error));
        //    text.setText("");
        //}
        b_main.setOnClickListener(view ->{
            if(activity == 0){
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                activity = 1;
            }else if(activity == 1){
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                activity = 0;
            }
        });
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            menuItem -> {
                selectDrawerItem(menuItem);
                return true;
            }
        );
    }
    @SuppressLint("NonConstantResourceId")
    public void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_switch:
            case R.id.nav_switch2:
            case R.id.nav_switch3:
            case R.id.nav_switch4:
            case R.id.nav_switch5:
                Intent intent = new Intent(MainActivity.this, about_us_activity.class);
                startActivity(intent);
                break;
            case R.id.nav_switch6:
                intent = new Intent(MainActivity.this, check_link.class);
                startActivity(intent);
            break;
            default:
                super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("SetTextI18n")
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = this;

            path = for_getting_path.getRealPath(context,uri);

            if(path != null){
                class code_file extends Thread{
                    public void run(){
                        Python py = Python.getInstance();

                        String code = "import vt\n" +
                                "import hashlib\n" +
                                "import time\n" +
                                "\n" +
                                "client = vt.Client(\"e9481497c3198559e299e966ec79be2ece56bf903cd0308474f8e3059aad6ee5\")\n" +
                                "\n" +
                                "def check():\n" +
                                "    try:\n" +
                                "        with open(r\"" + path + "\",\"rb\") as f:\n" +
                                "            bytes = f.read() # read the entire file as bytes\n" +
                                "            readable_hash = hashlib.sha256(bytes).hexdigest()\n" +
                                "        file = client.get_object(\"/files/\" + readable_hash)\n" +
                                "        x = str(file.last_analysis_stats)\n" +
                                "        n = 109\n" +
                                "        if x[n] + x[n+1] + x[n+2] + x[n+3] + x[n+4] + x[n+5] + x[n+6] + x[n+7] + x[n+8] == \"malicious\":\n" +
                                "            t = x[n + 12]\n" +
                                "        elif x[n+1] + x[n+2] + x[n+3] + x[n+4] + x[n+5] + x[n+6] + x[n+7] + x[n+8] + x[n+9] == \"malicious\":\n" +
                                "            t = x[n + 13]\n" +
                                "        elif x[n+2] + x[n+3] + x[n+4] + x[n+5] + x[n+6] + x[n+7] + x[n+8] + x[n+9] + x[n+10] == \"malicious\":\n" +
                                "            t = x[n + 14]\n" +
                                "        elif x[n+3] + x[n+4] + x[n+5] + x[n+6] + x[n+7] + x[n+8] + x[n+9] + x[n+10] + x[n+11] == \"malicious\":\n" +
                                "            t = x[n + 15]\n" +
                                "        elif x[n+4] + x[n+5] + x[n+6] + x[n+7] + x[n+8] + x[n+9] + x[n+10] + x[n+11] + x[n+12] == \"malicious\":\n" +
                                "            t = x[n + 16]\n" +
                                "        elif x[n+5] + x[n+6] + x[n+7] + x[n+8] + x[n+9] + x[n+10] + x[n+11] + x[n+12] + x[n+13] == \"malicious\":\n" +
                                "            t = x[n + 17]\n" +
                                "        elif x[n+6] + x[n+7] + x[n+8] + x[n+9] + x[n+10] + x[n+11] + x[n+12] + x[n+13] + x[n+14] == \"malicious\":\n" +
                                "            t = x[n + 18]\n" +
                                "        if t == \"0\":\n" +
                                "            print(\"file is safe\")\n" +
                                "        else:\n" +
                                "            print(\"file isn't safe\")\n" +
                                "    except:\n" +
                                "        print(\"oh. it's a new file!!\")\n" +
                                "        check2()\n" +
                                "\n" +
                                "def check2(): \n" +
                                "    with open(r\"" + path + "\", \"rb\") as f:\n" +
                                "        analysis = client.scan_file(f, wait_for_completion=True)\n" +
                                "    while True:\n" +
                                "        analysis = client.get_object(\"/analyses/{}\", analysis.id)\n" +
                                "        if analysis.status == \"completed\":\n" +
                                "            check()\n" +
                                "            break\n" +
                                "        time.sleep(10)\n" +
                                "\n" +
                                "check()\n";

                        PyObject sys = py.getModule("sys");
                        PyObject io = py.getModule("io");
                        PyObject console = py.getModule("interpreter");
                        PyObject textOutputStream = io.callAttr("StringIO");

                        try {
                            console.callAttrThrows("mainTextCode", code);
                            interpreterOutput = String.valueOf(textOutputStream.callAttr("getvalue"));
                        }catch (PyException e){
                            interpreterOutput = e.getMessage();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }

                        sys.put("stdout", textOutputStream);
                    }
                }
                code_file mycode_file = new code_file();
                mycode_file.start();
                class while_code extends Thread{
                    public void run(){
                        if(iii == 1){
                            code_file mycode_file = new code_file();
                            mycode_file.start();
                            String output_one = "safe\n";
                            output_one += "Unclosed client session\n";
                            output_one += "client_session: <aiohttp.client.ClientSession object at 0x00000252B05A5F40>\n";
                            output_one += "Unclosed connector\n";
                            output_one += "connections: ['[(<aiohttp.client_proto.ResponseHandler object at 0x00000252B05D3640>, 243493.796)]']\n";
                            output_one += "connector: <aiohttp.connector.TCPConnector object at 0x00000252ADC3CA90>";

                            String output_two = "isn't safe\n";
                            output_two += "Unclosed client session\n";
                            output_two += "client_session: <aiohttp.client.ClientSession object at 0x00000252B05A5F40>\n";
                            output_two += "Unclosed connector\n";
                            output_two += "connections: ['[(<aiohttp.client_proto.ResponseHandler object at 0x00000252B05D3640>, 243493.796)]']\n";
                            output_two += "connector: <aiohttp.connector.TCPConnector object at 0x00000252ADC3CA90>";

                            if (interpreterOutput != null){
                                System.out.println(interpreterOutput);
                                if(interpreterOutput.equals(output_one)){
                                    System.out.println("file is safe");
                                    System.out.println(interpreterOutput);
                                }else if(interpreterOutput.equals(output_two)){
                                    System.out.println("file isn't safe!");
                                    System.out.println(interpreterOutput);
                                }else{
                                    System.out.println("fuck");
                                }
                            }
                            iii = 0;
                        }
                    }
                }
                while_code mywhile_code = new while_code();
                mywhile_code.start();
            }
        }
    }
}
//salam
