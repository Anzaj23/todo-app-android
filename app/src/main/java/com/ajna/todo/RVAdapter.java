package com.ajna.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {
    private List<Note> notes;

    public RVAdapter(List<Note> notes) {
        this.notes = notes;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDetails;
        public ImageButton btnItemOptions;

        public MyViewHolder(View view) {
            super(view);
            this.tvTitle = view.findViewById(R.id.tv_noteTitle);
            this.tvDetails = view.findViewById(R.id.tv_noteDetails);
            this.btnItemOptions = view.findViewById(R.id.btn_item_options);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvTitle.setText(notes.get(position).getNoteTitle());
        holder.tvDetails.setText(notes.get(position).getNoteDetails());
        holder.btnItemOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(holder.btnItemOptions.getContext(), holder.btnItemOptions);
                popupMenu.inflate(R.menu.menu_item);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_delete_item:
                                int pos = holder.getAdapterPosition();
                                notes.remove(pos);
                                notifyItemChanged(pos);
                                notifyItemRangeRemoved(pos, 1);
                                return true;
                            default:
                                return false;

                        }
                    }
                });
                popupMenu.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

}
