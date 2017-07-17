package com.baway.shenglijuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.shenglijuan.adapter.MyAdapter;
import com.baway.shenglijuan.bean.Goods;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 类作用:主界面
 创建人：绳利娟
 创建时间： 2017/7/17   9:46
 */

public class MainActivity extends AppCompatActivity implements MyAdapter.onCheckListener {
    private List<Goods> goods=new ArrayList<>();
    private TextView tv_num;
    private TextView tv_zongjai;
    private CheckBox cb_fanxuan;
    private CheckBox cb_quanxuan;
    private int checkNum = 0; // 记录选中的条目数量
    private float total = 0.00f;
    private ListView lv;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_zongjai = (TextView) findViewById(R.id.tv_zongjai);
        cb_fanxuan = (CheckBox) findViewById(R.id.cb_fanxuan);
        cb_quanxuan = (CheckBox) findViewById(R.id.cb_quanxuan);
        lv = (ListView) findViewById(R.id.lv);

        //获得数据
        tojson();

        //cb_fanxuan.setOnClickListener(this);
        //cb_quanxuan.setOnClickListener(this);

        //反选
        cb_fanxuan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkNum=0;
                //不为全选时，也就是有选中的，有没选中的
                // 遍历list的长度，设为未选
                for (int i = 0; i < goods.size(); i++) {
                    //获得当条目的选中状态
                    Boolean n=adapter.getIsSelected().get(i);
                    //如果选中，设为不选中
                    if (n) {
                        adapter.getIsSelected().put(i, false);
                        //获得选中条目的布尔值
                        Boolean a=adapter.getIsSelected().get(i);
                        //
                        if (a) {
                            total+=Float.valueOf(goods.get(i).getMoney());
                            Log.i("总价111：", total+"");
                            checkNum++;// 数量减1
                        }
                    }else{
                        adapter.getIsSelected().put(i, true);
                        Boolean a=adapter.getIsSelected().get(i);
                        if (a) {
                            total+=Float.valueOf(goods.get(i).getMoney());
                            Log.i("总价222：", total+"");
                            checkNum++;// 数量减1
                        }
                    }


                }

                // 刷新listview和TextView的显示
                dataChanged(total);
                //设置全选为不选中状态
                cb_quanxuan.setChecked(false);
            }
        });

        //全选
        cb_quanxuan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //当全选框变为全选时，
                if (isChecked) {
                    // 当不是全选时，设置为全选
                    total = 0.00f;
                    // 遍历list的长度，设为已选
                    checkNum = goods.size();
                    for (int i = 0; i < goods.size(); i++) {
                        adapter.getIsSelected().put(i, true);
                        total += Float.valueOf(goods.get(i).getMoney()) ;
                    }

                    // 刷新listview和TextView的显示
                    dataChanged(total);

                }else{
                    // 遍历list的长度，设为未选
                    for (int i = 0; i < goods.size(); i++) {
                        adapter.getIsSelected().put(i, false);
                        checkNum=0;// 数量减1
                        total = 0.00f;
                    }
                    // 刷新listview和TextView的显示
                    dataChanged(total);

                }

                //把反选设置为不选中
                cb_fanxuan.setChecked(false);

            }
        });
    }
    private void tojson() {
        for (int i = 0; i <20 ; i++) {
            Goods datas = new Goods();
            datas.setFlag(false);
            datas.setMoney(""+i);
            datas.setTitle("第"+i+"商品");
            goods.add(datas);
        }

        adapter = new MyAdapter(goods, this);
        lv.setAdapter(adapter);
        adapter.setListener(this);

    }
    private void dataChanged(float total2) {
        DecimalFormat decimalFormat = new DecimalFormat();// 构造方法的字符格式这里如果小数不足2位,会以0补足.
        String price_num = decimalFormat.format(total);// format 返回的是字符串
        String str = "合计" + "￥" + price_num + " ";
        // 通知listView刷新
        adapter.notifyDataSetChanged();
        // 用TextView显示       总价、选中数量
        tv_zongjai.setText(str);
        tv_num.setText("已选中："+checkNum);
    }

    @Override
    public void onCheck(HashMap<Integer, Boolean> map) {
        // TODO 自动生成的方法存根
        total = 0.00f;
        checkNum = 0;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i)) {
                total += Float.valueOf(goods.get(i).getMoney());
                checkNum++;
            }
        }
        //更新显示数据
        dataChanged(total);

    }



}
