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

public class MainActivity extends AppCompatActivity {

  private CodeEditor editor;
  private Toolbar _toolbar;

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
  }


}
