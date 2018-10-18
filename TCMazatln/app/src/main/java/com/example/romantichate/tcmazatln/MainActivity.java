package com.example.romantichate.tcmazatln;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.android.gms.maps.MapView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private CardView tecnicoCard,mapViewCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define cards
        //mapViewCard = (CardView) findViewById(R.id.mapView_Card);
        tecnicoCard = (CardView) findViewById(R.id.tecnico_card);

        //Onclick (Liseners)
        //mapViewCard.setOnClickListener(this);
        tecnicoCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){

        Intent i;
        switch (v.getId()){
            //case R.id.mapView_Card : i = new  Intent(this,MapView.class);startActivity(i);break;
            case R.id.tecnico_card : i = new  Intent(this,Tecnico.class);startActivity(i);break;
            default:break;
        }
    }
}
