package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class Setup2Activity extends BaseSetUpActivity implements View.OnClickListener{
    private TelephonyManager mTelephonyManager;
    private Button mBindsSIMBtn;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_setup_2);
        //设置第一个小圆点的颜色
        ((RadioButton)findViewById(R.id.rd_second)).setChecked(true);
        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mBindsSIMBtn = (Button) findViewById(R.id.btn_bind_sim);
        mBindsSIMBtn.setOnClickListener(this);
        if (isBind()) {
            mBindsSIMBtn.setEnabled(false);
        }else {
            mBindsSIMBtn.setEnabled(true);;
        }
    }
    private boolean isBind(){
        String simString = sp.getString("sim",null);
        if (TextUtils.isEmpty(simString)){
            return false;
        }
        return true;
    }
    @Override
    public void showNext() {
        if (!isBind()){
            Toast.makeText(this,"您还没有绑定SIM卡！",Toast.LENGTH_LONG).show();
            return;
        }
        startActivityAndFinishSelf(Setup3Activity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(Setup1Activity.class);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bind_sim:
                bindSIM();
                break;
        }
    }
    private void bindSIM(){
        if (!isBind()){
            String simSerialNumber = mTelephonyManager.getSimSerialNumber();
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("sim",simSerialNumber);
            edit.commit();
            Toast.makeText(this,"SIM卡绑定成功！",Toast.LENGTH_LONG).show();
            mBindsSIMBtn.setEnabled(false);
        }else {
            Toast.makeText(this,"SIM卡已经绑定！",Toast.LENGTH_LONG).show();
            mBindsSIMBtn.setEnabled(false);
        }
    }
}