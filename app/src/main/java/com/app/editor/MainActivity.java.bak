package com.app.editor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // =========================
    // Constants
    // =========================
    private static final int REQUEST_OPEN_PROJECT = 100;

    // =========================
    // UI
    // =========================
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerTree;
    private CodeEditor editor;

    // =========================
    // Data
    // =========================
    private Uri projectRootUri;
    private final List<DocumentFile> projectFiles = new ArrayList<>();
    private ProjectTreeAdapter treeAdapter;

    // =========================
    // Lifecycle
    // =========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupEditor();
    }

    // =========================
    // Setup Methods
    // =========================
    private void setupUI() {

        drawerLayout = findViewById(R.id.drawerLayout);
        recyclerTree = findViewById(R.id.recyclerTree);
        editor = findViewById(R.id.editor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        android.R.string.ok,
                        android.R.string.cancel
                );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerTree.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupEditor() {
        editor.setColorScheme(new SchemeDarcula());
        editor.setEditorLanguage(new JavaLanguage());
        editor.setTextSize(14f);
        editor.setLineNumberEnabled(true);
    }

    // =========================
    // Open Project
    // =========================
    public void openProject() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        );
        startActivityForResult(intent, REQUEST_OPEN_PROJECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != REQUEST_OPEN_PROJECT) return;

        if (resultCode != RESULT_OK || data == null) {
            showMessage("Project opening cancelled");
            return;
        }

        Uri uri = data.getData();
        if (uri == null) {
            showMessage("Invalid project folder");
            return;
        }

        final int flags = data.getFlags() &
                (Intent.FLAG_GRANT_READ_URI_PERMISSION |
                 Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        try {
            getContentResolver().takePersistableUriPermission(uri, flags);
        } catch (SecurityException e) {
            showMessage("Permission denied");
            return;
        }

        projectRootUri = uri;
        loadProjectFiles();
        showMessage("Project loaded");
    }

    // =========================
    // Load Files
    // =========================
    private void loadProjectFiles() {

        projectFiles.clear();

        DocumentFile root = DocumentFile.fromTreeUri(this, projectRootUri);
        if (root == null || !root.isDirectory()) {
            showMessage("Invalid directory");
            return;
        }

        readDirectoryRecursive(root);

        if (projectFiles.isEmpty()) {
            showMessage("No supported files found");
        }

        showFilesInDrawer();
    }

    private void readDirectoryRecursive(DocumentFile dir) {

        for (DocumentFile file : dir.listFiles()) {

            if (file.isDirectory()) {
                readDirectoryRecursive(file);
                continue;
            }

            if (!file.isFile() || !file.canRead()) continue;

            String name = file.getName();
            if (name == null) continue;

            if (isSupportedFile(name)) {
                projectFiles.add(file);
            }
        }
    }

    private boolean isSupportedFile(String name) {
        String lower = name.toLowerCase();
        return lower.endsWith(".java")
                || lower.endsWith(".kt")
                || lower.endsWith(".xml")
                || lower.endsWith(".json")
                || lower.endsWith(".txt");
    }

    // =========================
    // Drawer Display
    // =========================
    private void showFilesInDrawer() {

        treeAdapter = new ProjectTreeAdapter(projectFiles, file -> {
            openFileFromProject(file);
            drawerLayout.closeDrawers();
        });

        recyclerTree.setAdapter(treeAdapter);
    }

    // =========================
    // Open File
    // =========================
    private void openFileFromProject(DocumentFile file) {

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            getContentResolver().openInputStream(file.getUri())
                    )
            );

            StringBuilder text = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }

            reader.close();
            editor.setText(text.toString());

        } catch (Exception e) {
            showMessage("Failed to open file");
        }
    }

    // =========================
    // Utils
    // =========================
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
