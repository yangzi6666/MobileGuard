package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class LostFindActivity extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lost_find);
        startSetUp1Activity();
    }

    private void startSetUp1Activity() {

        Intent intent  = new Intent(LostFindActivity.this,Setup1Activity.class);
        startActivity(intent);
        finish();
    }


}
