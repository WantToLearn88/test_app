package com.app.editor;

import androidx.documentfile.provider.DocumentFile;

public class TreeNode {

    public DocumentFile file;
    public int depth;
    public boolean isExpanded;

    public TreeNode(DocumentFile file, int depth) {
        this.file = file;
        this.depth = depth;
        this.isExpanded = false;
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }
}
