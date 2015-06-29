package com.example.cravisundaram.deltatask4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.cravisundaram.rows.RowItem;

public class MainActivity extends Activity  {
    int count = 0;
    ArrayList<String> titles;
    private CustomBaseAdapter adapter;

    public static final Integer[] images = {R.drawable.straw,
            R.drawable.banana, R.drawable.orange, R.drawable.mixed, R.drawable.watermelon, R.drawable.grape};

    ListView listView;
    List<RowItem> rows;
    Button ba;
    String msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ba = (Button) findViewById(R.id.button);
        titles = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.list);
        final EditText editText = (EditText) findViewById(R.id.editText);
        rows = new ArrayList<RowItem>();
        if (titles == null) {
            listView.setEmptyView(findViewById(R.id.button));
        }

        adapter = new CustomBaseAdapter(MainActivity.this, rows);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = editText.getText().toString();
                titles.add(msg);
                RowItem item = new RowItem(images[count], titles.get(count));
                rows.add(item);
                adapter.notifyDataSetChanged();
                ++count;
            }
        });
    }
    public class CustomBaseAdapter extends BaseAdapter {
        Context context;
        List<RowItem> rowItems;

        public CustomBaseAdapter(Context context, List<RowItem> items) {
            this.context = context;
            this.rowItems = items;
        }

        private class ViewHolder {
            ImageView imageView;
            TextView txtTitle;
            Button b;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
                holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
                holder.b=(Button)convertView.findViewById(R.id.button_r);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            final RowItem rowItem = (RowItem) getItem(position);

            holder.txtTitle.setText(rowItem.getTitle());
            holder.imageView.setImageResource(rowItem.getImageId());

            Button del=(Button)convertView.findViewById(R.id.button_r);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                rows.remove(rowItem);
                adapter.notifyDataSetChanged();

                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            return rowItems.size();
        }

        @Override
        public Object getItem(int position) {
            return rowItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return rowItems.indexOf(getItem(position));
        }
    }

}

