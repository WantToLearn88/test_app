package com.app.editor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.lang.EmptyLanguage;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub;
import io.github.rosemoe.sora.widget.schemes.SchemeEclipse;
import io.github.rosemoe.sora.widget.schemes.SchemeVS2019;
import io.github.rosemoe.sora.widget.schemes.SchemeNotepadXX;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import androidx.annotation.NonNull;
import android.widget.Toast;
import android.content.*;
import android.content.res.*;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {

  private CodeEditor editor;
  private Toolbar _toolbar;
  
  private static final int REQUEST_OPEN = 1;
  private static final int REQUEST_SAVE = 2;

   

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editor = findViewById(R.id.editor);

 

    // الإعدادات الافتراضية
    editor.setColorScheme(new SchemeDarcula());
    editor.setEditorLanguage(new JavaLanguage());
    editor.setTextSize(14f);
    editor.setLineNumberEnabled(true);
    
    Button btnOpen = findViewById(R.id.btnOpen);
Button btnSave = findViewById(R.id.btnSave);

btnOpen.setOnClickListener(v -> openFile());
btnSave.setOnClickListener(v -> saveFile());

  }
  
  private void openFile() {
    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    intent.setType("text/*");
    startActivityForResult(intent, REQUEST_OPEN);
}

  private void saveFile() {
    Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TITLE, "code.txt");
    startActivityForResult(intent, REQUEST_SAVE);
}


  @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode != RESULT_OK || data == null) return;

    Uri uri = data.getData();

    try {
        if (requestCode == REQUEST_OPEN) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getContentResolver().openInputStream(uri))
            );

            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            reader.close();

            editor.setText(text.toString());

        } else if (requestCode == REQUEST_SAVE) {
            OutputStream outputStream =
                    getContentResolver().openOutputStream(uri);

            outputStream.write(editor.getText().toString().getBytes());
            outputStream.close();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}




}
