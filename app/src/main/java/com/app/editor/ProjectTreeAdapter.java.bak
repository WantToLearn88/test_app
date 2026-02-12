package com.app.editor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.documentfile.provider.DocumentFile;

import java.util.List;

public class ProjectTreeAdapter
        extends RecyclerView.Adapter<ProjectTreeAdapter.ViewHolder> {

    public interface OnFileClickListener {
        void onFileClick(DocumentFile file);
    }

    private final List<DocumentFile> files;
    private final OnFileClickListener listener;

    public ProjectTreeAdapter(List<DocumentFile> files,
                              OnFileClickListener listener) {
        this.files = files;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DocumentFile file = files.get(position);
        holder.textView.setText(file.getName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFileClick(file);
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
