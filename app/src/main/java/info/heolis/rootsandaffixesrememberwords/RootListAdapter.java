package info.heolis.rootsandaffixesrememberwords;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import info.heolis.rootsandaffixesrememberwords.Bean.RootName;

public class RootListAdapter extends RecyclerView.Adapter<RootListAdapter.ViewHolder> {
    private List<RootName> mRootNames;
    private View view;

    public RootListAdapter(List<RootName> rootNames){
        mRootNames = rootNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_root_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvTitle.setText(mRootNames.get(position).getRootName());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mRootNames.get(position).getRootName();
                int name = Integer.parseInt(title.split("\\.")[0]) - 1;
                Log.e("file_index", name + "");
                Intent intent = new Intent();
                intent.putExtra("fileName", name + "");
                intent.setClass(view.getContext(), Main2Activity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRootNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_root_name_item);
        }
    }
}
