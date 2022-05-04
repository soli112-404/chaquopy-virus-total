package com.example.imen_yar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import java.util.Objects;

public class check_link extends AppCompatActivity {
    String address = "";
    int i = 0;
    ImageButton back;
    TextView text;
    EditText Ed_text;
    Button check_button;
    Display displaySize;
    Point size;
    int width;
    int height;
    DrawerLayout.LayoutParams params_bb;
    DrawerLayout.LayoutParams params_t;
    DrawerLayout.LayoutParams params_ed;
    DrawerLayout.LayoutParams params_b;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_check);

        back = findViewById(R.id.back);
        text = findViewById(R.id.textii);
        Ed_text = findViewById(R.id.Ed_text);
        check_button = findViewById(R.id.check_button);
        displaySize = getWindowManager().getDefaultDisplay();
        size = new Point();
        displaySize.getSize(size);
        width = size.x;
        height = size.y;

        params_bb = (DrawerLayout.LayoutParams) back.getLayoutParams();
        params_t = (DrawerLayout.LayoutParams) text.getLayoutParams();
        params_ed = (DrawerLayout.LayoutParams) Ed_text.getLayoutParams();
        params_b = (DrawerLayout.LayoutParams) check_button.getLayoutParams();

        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }
        if(width == 1080 && height == 1920){
            // Back Button Size
            back.setLayoutParams(new DrawerLayout.LayoutParams(38,60));
            params_bb.setMargins(48, 48,994,1740);
            back.setLayoutParams(params_bb);
            // Text Size
            params_t.setMargins(0, 428,0,1270);
            text.setLayoutParams(params_t);
            // Edit Text Size
            params_ed.setMargins(0, 778,0,920);
            Ed_text.setLayoutParams(params_ed);
            // Button Size
            params_b.setMargins(380, 1028,380,670);
            check_button.setLayoutParams(params_b);
        }
        //
        // this section is for checking the entered link
        // this is how it work:
        //         ____________________
        //        |                    |
        //        |                    |                                ____________________
        //        |                    |                               |                    |
        //        |                    |                               |  check link is an  |
        //        |        link        | ----------------------------> |       https        |
        //        |                    |                               |      or http       |
        //        |                    |                               |                    |
        //        |                    |                               \____________________/
        //        |                    |
        //        \____________________/
        //
        //
        //
        // if link is an http, system says "link isn't safe"
        // else:
        //         _________________________
        //        |                         |
        //        |                         |
        //        |   https://example.com   | __________________________________________
        //        |                         |                                           |
        //        |                         |                                           |
        //        \_________________________/                                           |
        //                                                                              |
        //                                                                              |
        //                                                                              |
        //         _________________________                                            |
        //        |                         |                                           |
        //        |                         |                               _________________________
        //        |                         |                              |                         |
        //        |   Removes the letters   |           Successful         |                         |           Unsuccessful
        //        |   https from the link   | <--------------------------- |     Sends a request     | -------------------------------> "Connection error"
        //        |                         |                              |       to the link       |
        //        |                         |                              |                         |
        //        |                         |                              \_________________________/
        //        |                         |
        //        \_________________________/
        //                    |
        //                    |
        //                    |
        //                    |________________________________
        //                                                     |
        //                                                     |
        //                                                     |
        //                                              _________________________
        //                                             |                         |
        //                            Get version      |  Receives ssl and tls   |     error in operation *
        //  says "link is safe" <--------------------- |  versions from example  | --------------------------> says "link isn't safe"
        //                                             |  using a python script  |
        //                                             |                         |
        //                                             \_________________________/
        //
        //
        //
        // error in operation *:   This means that link entered by Google is not approved
        //     and does not have an ssl certificate, although in some cases this error
        //     is given because of Internet, but despite send request to link in the previous
        //     step, error is only because link does not have a certificate ssl
        //
        //
        //
        //errors:
        //    request failed-c11:  This means that no link is typed in the input field
        //    request failed-c12:  The link is short
        //    request failed-c13:  write with https or http
        //    request failed-c14:  Connection error
        //
        //
        //
        check_button.setOnClickListener(view ->{
            class code_link extends Thread{
                public void run(){
                    address+=Ed_text.getText();
                    if(i == 0){
                        check_button.setText("Check");
                        if(!(address.equals(""))){
                            String local_address = "";

                            char[] address_char = address.toCharArray();
                            if(address_char.length > 5){
                                local_address = local_address + address_char[0] + address_char[1] + address_char[2] + address_char[3] + address_char[4];
                                if(local_address.equals("http:")){
                                    text.setText("link isn't safe!");
                                    text.setBackgroundColor(Color.parseColor("#FF0000"));
                                    text.setLayoutParams(new DrawerLayout.LayoutParams(650,150));
                                    params_t.setMargins(215, 500,215,1198);
                                    text.setLayoutParams(params_t);
                                }else if(local_address.equals("https")){
                                    try{
                                        Python py = Python.getInstance();
                                        PyObject re = py.getModule("requests");
                                        PyObject re_get = re.callAttr("get", address);
                                        int eem = Objects.requireNonNull(re_get.get("status_code")).toInt();
                                        if(eem == 200){
                                            try{

                                                //https://www.google.com
                                                String address_test = "";
                                                address_test = address_test + address_char[8] + address_char[9] + address_char[10] + address_char[11];

                                                if(address_test.equals("www.")){
                                                    int dddd = 8;
                                                    address = "";
                                                    for(int b = address_char.length - 8; b > 0; b-=1){
                                                        address = address + address_char[dddd];
                                                        dddd +=1;
                                                    }
                                                }else if(address_test.equals("ww1.")){
                                                    int dddd = 8;
                                                    address = "ww1.";
                                                    for(int b = address_char.length - 8; b > 0; b-=1){
                                                        address = address + address_char[dddd];
                                                        dddd+=1;
                                                    }
                                                }else{
                                                    int dddd = 8;
                                                    address = "www.";
                                                    for(int b = address_char.length - 8; b > 0; b-=1){
                                                        address = address + address_char[dddd];
                                                        dddd+=1;
                                                    }
                                                }
                                                String code = "import socket as so\n";
                                                code += "import ssl\n";
                                                code += "address2 = \"" + address + "\"\n";
                                                code += "try:\n";
                                                code += "    context = ssl.create_default_context()\n";
                                                code += "    sock = so.create_connection((address2, 443))\n";
                                                code += "    ssock = context.wrap_socket(sock, server_hostname=address2)\n";
                                                code += "    print(\"T\")\n";
                                                code += "except Exception as e:\n";
                                                code += "    print(\"F\")";
                                                System.out.println(code);

                                                PyObject sys = py.getModule("sys");
                                                PyObject io = py.getModule("io");

                                                PyObject console = py.getModule("interpreter");

                                                PyObject textOutputStream = io.callAttr("StringIO");
                                                sys.put("stdout", textOutputStream);

                                                String interpreterOutput = "";

                                                try {
                                                    console.callAttrThrows("mainTextCode", code);

                                                    interpreterOutput = String.valueOf(textOutputStream.callAttr("getvalue"));
                                                }catch (PyException e){

                                                    interpreterOutput = e.getMessage();
                                                } catch (Throwable throwable) {
                                                    throwable.printStackTrace();
                                                }

                                                System.out.println("mission was successful" + interpreterOutput);
                                                if (interpreterOutput != null) {
                                                    if(interpreterOutput.equals("F\n")){
                                                        text.setText("link isn't safe!");
                                                        text.setBackgroundColor(Color.parseColor("#FF0000"));
                                                        text.setLayoutParams(new DrawerLayout.LayoutParams(650,150));
                                                        params_t.setMargins(215, 500,215,1198);
                                                        text.setLayoutParams(params_t);
                                                        System.out.println(code);
                                                        i += 1;
                                                        check_button.setText("Re-start");
                                                    }else if(interpreterOutput.equals("T\n")){
                                                        text.setText("link is safe");
                                                        text.setBackgroundColor(Color.parseColor("#00FF0A"));
                                                        text.setLayoutParams(new DrawerLayout.LayoutParams(500,150));
                                                        params_t.setMargins(290, 500,290,1198);
                                                        text.setLayoutParams(params_t);
                                                        System.out.println(code);
                                                        i += 1;
                                                        check_button.setText("Re-start");
                                                    }
                                                }
                                            }catch (Exception e){
                                                System.out.println(e.toString());
                                            }
                                        }
                                    }catch (Exception e){
                                        text.setText("request failed-c14");
                                    }
                                }else{
                                    text.setText("request failed-c13");
                                }
                            }else{
                                text.setText("request failed-c12");
                            }
                        }else{
                            text.setText("request failed-c11");
                        }

                    }else{
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        i -= 1;
                    }
                }
            }
            code_link mycode_link = new code_link();
            mycode_link.start();
        });
        back.setOnClickListener(v -> {
            Intent intent = new Intent(check_link.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
