package cn.edu.gdmec.android.mobileguard.m1home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.provider.Settings;
//import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.R;
import cn.edu.gdmec.android.mobileguard.m1home.adapter.HomeAdapter;
import cn.edu.gdmec.android.mobileguard.m2theftguard.LostFindActivity;
import cn.edu.gdmec.android.mobileguard.m2theftguard.dialog.InterPasswordDialog;
import cn.edu.gdmec.android.mobileguard.m2theftguard.dialog.SetUpPasswordDialog;
import cn.edu.gdmec.android.mobileguard.m2theftguard.utils.MD5Utils;

public class HomeActivity extends AppCompatActivity {
    private GridView gv_home;
    private long mExitTime;
private SharedPreferences msharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        gv_home=(GridView)findViewById(R.id.gv_home);
        msharedPreferences=getSharedPreferences("config",MODE_PRIVATE);
        gv_home.setAdapter(new HomeAdapter(HomeActivity.this));
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view,int i,long l ){
                System.out.print(i);
                switch (i){
                    case 0:
                        //点击手机防盗
                        if (isSetUpPassword() ){
                            //弹出输入密码对话框
                            showInterPswdDialog();
                        }else{
                            //弹出设置密吗对话框
                            showSetUpPswdDialog();
                        }
                        break;
                }
            }
        });
    }



    public void startActivity(Class<?>cls){
        Intent intent = new Intent(HomeActivity.this,cls);
        startActivity(intent);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis()-mExitTime)<2000){
                System.exit(0);
            }else{
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_LONG).show();
                mExitTime= System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    /*弹出设置密吗对话框 本方法需要完成手机防盗模块之后才能启用*/
    private void showSetUpPswdDialog(){
        final SetUpPasswordDialog setUpPasswordDialog = new  SetUpPasswordDialog(HomeActivity.this);
        setUpPasswordDialog.setCallBack(new SetUpPasswordDialog.MyCallBack(){

            @Override
            public void ok() {
                String firstPwsd = setUpPasswordDialog.mFirstPWDET.getText().toString().trim();
                String affirmPwsd = setUpPasswordDialog.mAffirmET.getText().toString().trim();
                if(!TextUtils.isEmpty(firstPwsd)&&!TextUtils.isEmpty(affirmPwsd)){
                    if(firstPwsd.equals(affirmPwsd)){
                        //两次密码一致，存储密码
                        savePswd(affirmPwsd);
                        setUpPasswordDialog.dismiss();
                        //显示输入密码对话框
                        showInterPswdDialog();
                    }else{
                        Toast.makeText(HomeActivity.this,"两次密码不一致！",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(HomeActivity.this,"密码不能为空!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void cancel() {
                 setUpPasswordDialog.dismiss();
            }
        });
        setUpPasswordDialog.setCancelable(true);
        //让对话框显示
        setUpPasswordDialog.show();
    }
    /*弹出输入密码对话框 本方法需要完成手机防盗模块之后才能启用*/
           private void showInterPswdDialog(){
               final String password = getPassword();
               final InterPasswordDialog mInPswDialog = new InterPasswordDialog(HomeActivity.this);
               mInPswDialog.setCallBack(new InterPasswordDialog.MyCallBack(){

                   @Override
                   public void confirm() {
                       if (TextUtils.isEmpty(mInPswDialog.getPassword())){
                           Toast.makeText(HomeActivity.this,"密码不为空！",0).show();
                       }else if (password.equals(MD5Utils.encode(mInPswDialog.getPassword()))){
                           //进入防盗主界面
                           mInPswDialog.dismiss();
                           startActivity(LostFindActivity.class);
                           Toast.makeText(HomeActivity.this,"可以进入手机防盗模块",Toast.LENGTH_LONG).show();
                       }else{
                           //对话框消失，弹出提示
                           mInPswDialog.dismiss();
                           Toast.makeText(HomeActivity.this,"密码有误，请重新输入!",0).show();
                       }
                   }

                   @Override
                   public void cancle() {
                       mInPswDialog.dismiss();
                   }
               });
                   mInPswDialog.setCancelable(true);
               mInPswDialog.show();
           }
        /*保存密码 本方法需要完成手机防盗模块之后才能启用*/
        private  void savePswd(String affirmPwsd){
       SharedPreferences.Editor edit = msharedPreferences.edit();
            //为防止用户隐私被泄露，因此需要加密密码
            edit.putString("PhoneAntiTheftPWD",MD5Utils.encode(affirmPwsd));
            edit.commit();
        }
        /*获取密码*/
        private String getPassword() {
            String password = msharedPreferences.getString("PhoneAntiTheftPWD", null);
            if (TextUtils.isEmpty(password)) {
                return "";

            }
            return password;
        }
        //判断用户是否设置手机防盗密码
    private boolean isSetUpPassword() {
        String password = msharedPreferences.getString("PhoneAntiTheftPWD",null);
        if (TextUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
}
