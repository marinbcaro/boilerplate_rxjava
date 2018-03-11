package com.example.android.boilerplate.ui;

import com.example.android.boilerplate.R;
import com.example.android.boilerplate.model.GitRepo;

import java.util.List;

/**
 * Created by carolinamarin on 2/25/18.
 */

public class Adapter extends BaseAdapter {
    private List<GitRepo> data;

    public Adapter(List<GitRepo> data) {
        this.data = data;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.rowlayout;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
