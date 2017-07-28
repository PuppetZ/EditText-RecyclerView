package com.zz.edittextrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


/**
 * Created by zhangjing on 2017/7/26.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>/* implements TextView.OnEditorActionListener, TextWatcher, View.OnFocusChangeListener*/ {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHold viewHold = new ViewHold(mInflater.inflate(R.layout.item_recyclerview, parent, false));
        return viewHold;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);//不使用复用 防止数据多时 复用时  多个item中的EditText填写的数据一样
        ((ViewHold) holder).tv.setText(mList.get(position));

        ((ViewHold) holder).mEditText.setTag(position);
        ((ViewHold) holder).mEditText.setText(mList.get(position));
//        ((ViewHold) holder).mEditText.setOnEditorActionListener(this);
//        ((ViewHold) holder).mEditText.addTextChangedListener(this);
//        ((ViewHold) holder).mEditText.setOnFocusChangeListener(this);
        ((ViewHold) holder).mEditText.addTextChangedListener(new TextWatcher() {//监听EditText的text变化
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mList.set(position, s.toString());//更新list的数据,防止rv滑动的时候重新绘制,数据还是之前的

            }

            @Override
            public void afterTextChanged(Editable s) {
               /* SaveEditListener saveEditListener = (SaveEditListener) mContext;
                String up = ((ViewHold) holder).mEditText.getText().toString();
                saveEditListener.SaveEdit(position,up);



                String up = ((ViewHold) holder).mEditText.getText().toString();
                mList.set(position,up);
                notifyDataSetChanged();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /*@Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int position = (int) v.getTag();
        String up = v.getText().toString();
        mList.set(position, up);
        notifyDataSetChanged();
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        *//*String up = ((ViewHold) holder).mEditText.getText().toString();
        mList.set(position,up);
        notifyDataSetChanged();*//*
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            int position = (int) v.getTag();
            TextView textView = (TextView) v;
            String up = textView.getText().toString();
            mList.set(position, up);
            notifyDataSetChanged();
        }
    }*/


    public class ViewHold extends RecyclerView.ViewHolder {
        private TextView tv;
        public EditText mEditText;


        public ViewHold(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            mEditText = (EditText) itemView.findViewById(R.id.edit);

        }
    }

}
