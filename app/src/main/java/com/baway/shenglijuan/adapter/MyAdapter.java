package com.baway.shenglijuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.baway.shenglijuan.R;
import com.baway.shenglijuan.bean.Goods;
import java.util.HashMap;
import java.util.List;
/**
 类作用:适配器
 创建人：绳利娟
 创建时间： 2017/7/17   10:26
 */
public class MyAdapter extends BaseAdapter {
    private List<Goods> goods;
    private Context context;
    private static HashMap<Integer, Boolean> isSelected;
    private onCheckListener listener;
    private CheckBox check;
    public MyAdapter(List<Goods> goods, Context context) {
        super();
        this.goods = goods;
        this.context = context;
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < goods.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return goods.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return goods.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Goods goodss=goods.get(position);
        if (convertView==null) {
            convertView=View.inflate(context, R.layout.item_layout, null);
            holder=new ViewHolder();
            holder.title=(TextView) convertView.findViewById(R.id.tv_title);
            holder.money=(TextView) convertView.findViewById(R.id.tv_money);
            holder.flag=(CheckBox) convertView.findViewById(R.id.cb_checkbox);
            convertView.setTag(holder);
        }
        holder=(ViewHolder) convertView.getTag();
        holder.title.setText(goodss.getTitle());
        holder.money.setText(goodss.getMoney()+"");
        holder.flag.setChecked(goodss.isFlag());

        // 根据isSelected来设置checkbox的选中状况
        holder.flag.setChecked(getIsSelected().get(position));
        holder.flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }
                listener.onCheck(isSelected);
            }

        });

        return convertView;
    }

    static class ViewHolder{
        public TextView title;
        public TextView money;
        public CheckBox flag;
    }


    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }

    public void setListener(onCheckListener listener) {
        this.listener = listener;
    }

    public interface onCheckListener {
        void onCheck(HashMap<Integer, Boolean> map);
    }
}
