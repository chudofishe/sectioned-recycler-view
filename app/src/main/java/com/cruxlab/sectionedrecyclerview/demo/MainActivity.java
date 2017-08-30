package com.cruxlab.sectionedrecyclerview.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cruxlab.sectionedrecyclerview.R;
import com.cruxlab.sectionedrecyclerview.lib.SectionedRV;
import com.cruxlab.sectionedrecyclerview.lib.SectionedRVAdapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SectionedRV srv;
    private SectionedRVAdapter adapter;

    private String[] strings = new String[] {"One", "Two", "Three", "Four", "Five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srv = findViewById(R.id.srv);
        adapter = new SectionedRVAdapter();
        srv.setAdapter(adapter);
        for (int i = 0; i < 20; i++) {
            adapter.addSection(i % 3 == 0 ? yellowSectionAdapter : i % 3 == 1 ? redSectionAdapter : blueSectionAdapter);
        }
    }

    private RecyclerView.Adapter<StringViewHolder> yellowSectionAdapter = new RecyclerView.Adapter<StringViewHolder>() {

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section, parent, false);
            return new StringViewHolder(view, Color.YELLOW);
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            holder.bind(strings[position]);
        }

        @Override
        public int getItemCount() {
            return strings.length;
        }
    };

    private RecyclerView.Adapter<StringViewHolder> redSectionAdapter = new RecyclerView.Adapter<StringViewHolder>() {

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section, parent, false);
            return new StringViewHolder(view, Color.RED);
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            holder.bind(strings[position]);
        }

        @Override
        public int getItemCount() {
            return strings.length;
        }
    };

    private RecyclerView.Adapter<StringViewHolder> blueSectionAdapter = new RecyclerView.Adapter<StringViewHolder>() {

        @Override
        public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section, parent, false);
            return new StringViewHolder(view, Color.BLUE);
        }

        @Override
        public void onBindViewHolder(StringViewHolder holder, int position) {
            holder.bind(strings[position]);
        }

        @Override
        public int getItemCount() {
            return strings.length;
        }
    };

    private class StringViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private int color;

        public StringViewHolder(View itemView, int color) {
            super(itemView);
            this.color = color;
            this.text = itemView.findViewById(R.id.tv_text);
        }

        public void bind(final String string) {
            text.setText(string);
            text.setBackgroundColor(color);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int section = new Random().nextInt(adapter.getSectionCount());
                    if (section % 3 == 0) {
                        adapter.insertSection(section, yellowSectionAdapter);
                    } else if (section % 3 == 1) {
                        adapter.removeSection(section);
                    } else {
                        adapter.changeSection(section, blueSectionAdapter);
                    }
                }
            });
        }
    }

}