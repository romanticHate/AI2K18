package com.example.romantichate.tcmandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView tecnicoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tecnicoCard = (CardView) findViewById(R.id.tecnico_card);
        tecnicoCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()) {
            //case R.id.mapView_Card : i = new  Intent(this,MapView.class);startActivity(i);break;
            case R.id.tecnico_card:
                 i = new Intent(this, MapsActivity.class);
                 startActivity(i);
                 break;

            default:
                break;
        }
    }

}
