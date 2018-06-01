package com.kai.ling.studyrxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.step).setOnClickListener(this);
        findViewById(R.id.chain).setOnClickListener(this);
        findViewById(R.id.create).setOnClickListener(this);
        findViewById(R.id.transform).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.step:
                startActivity(new Intent(this, StepUseActivity.class));
                break;
            case R.id.chain:
                startActivity(new Intent(this, ChainUseActivity.class));
                break;
            case R.id.create:
                startActivity(new Intent(this, CreateOperatorActivity.class));
                break;
            case R.id.transform:
                startActivity(new Intent(this, TransformOperatorActivity.class));
                break;
        }
    }
}
