package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class Setup2Activity extends BaseSetUpActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_setup_2);
        //设置第一个小圆点的颜色
        ((RadioButton)findViewById(R.id.rd_second)).setChecked(true);
    }
    @Override
    public void showNext() {
        startActivityAndFinishSelf(Setup3Activity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(Setup1Activity.class);
    }
}