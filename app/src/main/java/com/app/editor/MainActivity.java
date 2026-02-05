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

    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);

    // الإعدادات الافتراضية
    editor.setColorScheme(new SchemeDarcula());
    editor.setEditorLanguage(new JavaLanguage());
    editor.setTextSize(14f);
    editor.setLineNumberEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  // التعامل مع اختيار الخيارات
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.switch_language:
        Toast.makeText(this, "تم اختيار الخيار 1", Toast.LENGTH_SHORT).show();
        chooseLanguage();
        return true;
      case R.id.switch_colors:
        chooseTheme();
        Toast.makeText(this, "تم اختيار الخيار 2", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.text_size:
        chooseTextSize();
        Toast.makeText(this, "تم اختيار الخيار 3", Toast.LENGTH_SHORT).show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  // @Override
  // public boolean onOptionsItemSelected(@NonNull MenuItem item) {
  // int id = item.getItemId();

  // if (id == R.id.switch_language) {
  // chooseLanguage();
  // Toast.makeText(MainActivity.this, "Your message here", Toast.LENGTH_SHORT).show();
  // return true;
  // } else if (id == R.id.switch_colors) {
  // chooseTheme();
  // Toast.makeText(MainActivity.this, "Your message here", Toast.LENGTH_SHORT).show();
  // return true;
  // } else if (id == R.id.text_size) {
  // chooseTextSize();
  // Toast.makeText(MainActivity.this , "Your message here", Toast.LENGTH_SHORT).show();
  // }

  // return super.onOptionsItemSelected(item);
  // }

  private void chooseLanguage() {
    final String[] languages = {"Java", "Python", "JavaScript", "Text"};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.switch_language);
    builder.setSingleChoiceItems(
        languages,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case 0:
                editor.setEditorLanguage(new JavaLanguage());
                break;
              case 1:
                // editor.setEditorLanguage(new PythonLanguage());
                break;
              case 2:
                // editor.setEditorLanguage(new JavaScriptLanguage());
                break;
              case 3:
                editor.setEditorLanguage(new EmptyLanguage());
                break;
            }
            dialog.dismiss();
          }
        });
    builder.setNegativeButton(android.R.string.cancel, null);
    builder.show();
  }

  private void chooseTheme() {
    final String[] themes = {"Default", "GitHub", "Eclipse", "Darcula", "VS2019", "NotepadXX"};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.color_scheme);
    builder.setSingleChoiceItems(
        themes,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case 0:
                editor.setColorScheme(new EditorColorScheme());
                break;
              case 1:
                editor.setColorScheme(new SchemeGitHub());
                break;
              case 2:
                editor.setColorScheme(new SchemeEclipse());
                break;
              case 3:
                editor.setColorScheme(new SchemeDarcula());
                break;
              case 4:
                editor.setColorScheme(new SchemeVS2019());
                break;
              case 5:
                editor.setColorScheme(new SchemeNotepadXX());
                break;
            }
            dialog.dismiss();
          }
        });
    builder.setNegativeButton(android.R.string.cancel, null);
    builder.show();
  }

  private void chooseTextSize() {
    final String[] sizes = {"Small", "Normal", "Large", "Extra Large"};
    final float[] sizeValues = {12f, 14f, 18f, 22f};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.text_size);
    builder.setSingleChoiceItems(
        sizes,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            editor.setTextSize(sizeValues[which]);
            dialog.dismiss();
          }
        });
    builder.setNegativeButton(android.R.string.cancel, null);
    builder.show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (editor != null) {
      editor.release();
    }
  }
}
