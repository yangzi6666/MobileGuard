package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.os.Bundle;
import android.widget.RadioButton;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class Setup4Activity extends BaseSetUpActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_setup_4);
        //设置第一个小圆点的颜色
        ((RadioButton)findViewById(R.id.rd_four)).setChecked(true);
    }
    @Override
    public void showNext() {
        startActivityAndFinishSelf(LostFindActivity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(Setup3Activity.class);
    }
}