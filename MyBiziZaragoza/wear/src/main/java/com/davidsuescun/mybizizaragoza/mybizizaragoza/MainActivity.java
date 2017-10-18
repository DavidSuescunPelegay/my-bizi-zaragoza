package com.davidsuescun.mybizizaragoza.mybizizaragoza;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import com.davidsuescun.mybizizaragoza.mybizizaragoza.data.Data;
import com.davidsuescun.mybizizaragoza.mybizizaragoza.utils.APICommunicator;

public class MainActivity extends Activity {

    private TextView bicisPlazaMozart;
    private TextView huecosPlazaMozart;
    private TextView bicisPedroJSoler;
    private TextView huecosPedroJSoler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APICommunicator apiCommunicator = new APICommunicator();
        apiCommunicator.plazaMozart();
        apiCommunicator.pedroJSoler();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub watchViewStub) {
                bicisPlazaMozart = (TextView) stub.findViewById(R.id.bicis_plaza_mozart);
                bicisPlazaMozart.setText(Data.getBicisPlazaMozart());

                huecosPlazaMozart = (TextView) stub.findViewById(R.id.huecos_plaza_mozart);
                huecosPlazaMozart.setText(Data.getHuecosPlazaMozart());

                bicisPedroJSoler = (TextView) stub.findViewById(R.id.bicis_pedro_j_soler);
                bicisPedroJSoler.setText(Data.getBicisPedroJSoler());

                huecosPedroJSoler = (TextView) stub.findViewById(R.id.huecos_pedro_j_soler);
                huecosPedroJSoler.setText(Data.getHuecosPedroJSoler());
            }
        });
    }
}
