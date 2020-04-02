package com.ufc.com.telas_de_introduo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
EditText et_endereco;
Button btn_descobrir;
TextView tv_lat_e_lont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_endereco = findViewById(R.id.et_endereco);
        btn_descobrir = findViewById(R.id.btn_descobrir);
        tv_lat_e_lont = findViewById(R.id.tv_lat_lont);


        btn_descobrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endereco = et_endereco.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(endereco,getApplicationContext(),new GeoHandler());
            }
        });

    }

    public class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String endereco;
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    endereco = bundle.getString("end");
                    break;
                    default:
                        endereco = null;
                        Log.i("erro", "endereco não encontrado");
            }
            tv_lat_e_lont.setText(endereco);
        }
    }
}
