package com.app.editor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProjectTreeAdapter
        extends RecyclerView.Adapter<ProjectTreeAdapter.ViewHolder> {

    public interface OnFileClickListener {
        void onFileClick(DocumentFile file);
    }

    private final List<TreeNode> nodes = new ArrayList<>();
    private final OnFileClickListener listener;

    public ProjectTreeAdapter(TreeNode root,
                              OnFileClickListener listener) {
        this.listener = listener;
        nodes.add(root);
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

        TreeNode node = nodes.get(position);
        holder.textView.setText(getPrefix(node) + node.file.getName());

        holder.itemView.setOnClickListener(v -> {
            if (node.isDirectory()) {
                toggleNode(position);
            } else {
                if (listener != null) {
                    listener.onFileClick(node.file);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    private String getPrefix(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < node.depth; i++) {
            sb.append("   ");
        }
        if (node.isDirectory()) {
            sb.append(node.isExpanded ? "📂 " : "📁 ");
        } else {
            sb.append("📄 ");
        }
        return sb.toString();
    }

    private void toggleNode(int position) {

        TreeNode node = nodes.get(position);

        if (node.isExpanded) {
            collapseNode(position);
        } else {
            expandNode(position);
        }
    }

    private void expandNode(int position) {

        TreeNode node = nodes.get(position);
        node.isExpanded = true;

        DocumentFile[] children = node.file.listFiles();
        int insertIndex = position + 1;

        for (DocumentFile child : children) {
            nodes.add(insertIndex,
                    new TreeNode(child, node.depth + 1));
            insertIndex++;
        }

        notifyDataSetChanged();
    }

    private void collapseNode(int position) {

        TreeNode node = nodes.get(position);
        node.isExpanded = false;

        int depth = node.depth;
        int removeIndex = position + 1;

        while (removeIndex < nodes.size()
                && nodes.get(removeIndex).depth > depth) {
            nodes.remove(removeIndex);
        }

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
