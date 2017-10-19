package cn.edu.gdmec.android.mobileguard.m1home.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Administrator on 2017/10/16.
 */

public class HomeAdapter extends BaseAdapter{
    int[] imageId = {R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,R.drawable.trojan,R.drawable.sysoptimize,R.drawable.taskmanager,R.drawable.netmanager,
            R.drawable.atools,R.drawable.settings};
    String[] names = {"手机防盗","通讯卫士","软件管家","手机杀毒","缓存清理","进程管理","流量管理","高级工具","设置中心"};
    private Context context;
    public HomeAdapter(Context context){
        this.context = context;
    }
    public int getCount(){
        return 9;
    }
    public Object getItem(int i){
        return null;
    }
    public long getItemId(int i){
        return 0;
    }
    public View getView(int i, View view, ViewGroup viewGroup){
        View view1 = View.inflate(context,R.layout.item_home,null);
        ImageView iv_icon = view1.findViewById(R.id.iv_home);
        TextView tv_name = view1.findViewById(R.id.tv_home);
        iv_icon.setImageResource(imageId[i]);
        tv_name.setText(names[i]);
        return view1;
    }
}
