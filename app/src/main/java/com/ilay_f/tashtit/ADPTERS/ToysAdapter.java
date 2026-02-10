package com.ilay_f.tashtit.ADPTERS;

import com.ilay_f.model.Toy;
import com.ilay_f.tashtit.ADPTERS.BASE.GenericAdapter;

import java.util.List;

public class ToysAdapter extends GenericAdapter<Toy> {
    public ToysAdapter(List<Toy> items, int layoutId, InitializeViewHolder initializeViewHolder, BindViewHolder<Toy> bindViewHolder) {
        super(items, layoutId, initializeViewHolder, bindViewHolder);
    }
}