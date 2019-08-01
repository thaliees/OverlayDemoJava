package com.thaliees.overlaydemo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thaliees.overlaydemo.R;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private String[] tags;
    private View.OnClickListener itemListener;
    private Context context;
    private ArrayList<Button> buttons = new ArrayList<>();
    private Integer currentPosition = 0;

    public TagAdapter(String[] tags) {
        this.tags = tags;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tag_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.getTagItem().setText(tags[position]);
        viewHolder.getTagItem().setTag(tags[position]);
        viewHolder.getTagItem().setId(position);
        if (position == getCurrentPosition()) viewHolder.getTagItem().setTextColor(context.getResources().getColor(R.color.colorAccent));
        else viewHolder.getTagItem().setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        if (!existButton(tags[position])) buttons.add(viewHolder.tagItem);
    }

    @Override
    public int getItemCount() {
        return tags.length;
    }

    public void setItemListener(View.OnClickListener itemListener) {
        this.itemListener = itemListener;
    }

    private boolean existButton(String tag){
        if (buttons.isEmpty()) return false;
        for (Button button: buttons) {
            if (button.getTag().equals(tag)) return true;
        }
        return false;
    }

    public void changeColorAll(){
        for (Button button: buttons) {
            button.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final Button tagItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagItem = itemView.findViewById(R.id.tagItem);
            tagItem.setOnClickListener(itemListener);
        }

        public Button getTagItem() {
            return tagItem;
        }
    }
}