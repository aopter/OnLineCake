package com.example.onlinecake.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinecake.R;
import com.example.onlinecake.entity.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class UserLoginActivity extends AppCompatActivity {

    private EditText etPhone;
    private EditText etPwd;
    private Button login;
    private RadioButton customer;
    private RadioButton seller;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
//                    接收信息
//                    发送
                    String result = (String)msg.obj;
                    if("true".equals(result)){
//
                        Log.e("handleMessage: ", "成功");
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        findViews();

//        设置监听器
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_login:
//                        查看单选选中按钮
                        if(customer.isChecked()){//顾客
                           CustomerLogin customerLogin = new CustomerLogin();
                           customerLogin.setPhone(etPhone.getText().toString());
                           customerLogin.setPassWord(etPwd.getText().toString());

                            JSONObject jCustomer = new JSONObject();
                            try {
                                jCustomer.put("phone",customerLogin.getPhone());
                                jCustomer.put("pwd",customerLogin.getPassWord());
                                String loginJson = jCustomer.toString();
//                                Message msg = new Message();
//                                msg.what = 1;
//                                msg.obj = jCustomer;
//                                handler.sendMessage(msg);
                                sendLogin(loginJson);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        });

    }

    private void findViews() {
        etPhone = findViewById(R.id.et_phone);
        login = findViewById(R.id.btn_login);
        etPwd = findViewById(R.id.et_pwd);
        customer = findViewById(R.id.rb_buy);
        seller = findViewById(R.id.rb_seller);
    }


    private void sendLogin(final String loginJson) {
        new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://10.7.89.92:8080/CakeOnlineForAndroid/customerLogin");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置网络请求的方式为POST
                conn.setRequestMethod("POST");
                //获取网络输出流
                OutputStream out = conn.getOutputStream();

                    //获取待发送的字符串
                    out.write(loginJson.getBytes());
                    //必须要获取网络输入流，保证客户端和服务端建立连接
                    InputStream in = conn.getInputStream();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    //读取字符信息
//                  得到拼接好的字符串
                    String result = reader.readLine();

                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    handler.sendMessage(msg);





                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
