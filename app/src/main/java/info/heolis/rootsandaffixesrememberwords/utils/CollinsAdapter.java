package info.heolis.rootsandaffixesrememberwords.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.heolis.rootsandaffixesrememberwords.Bean.Collins_message;
import info.heolis.rootsandaffixesrememberwords.R;

public class CollinsAdapter extends RecyclerView.Adapter<CollinsAdapter.ViewHolder> {
    private View root;
    private Collins_message mCollinsMessage;

    public CollinsAdapter(Collins_message message) {
        this.mCollinsMessage = message;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.collins_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
