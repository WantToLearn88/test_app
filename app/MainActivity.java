<?xml version="1.0" encoding="utf-8"?>  
<menu xmlns:android="http://schemas.android.com/apk/res/android"  
    xmlns:app="http://schemas.android.com/apk/res-auto">  
      
    <item  
        android:id="@+id/switch_language"  
        android:title="@string/switch_language" />  
          
    <item  
        android:id="@+id/switch_colors"  
        android:title="@string/switch_color_scheme" />  
          
    <item  
        android:id="@+id/text_size"  
        android:title="@string/text_size" />  
          
</menu>
🔶🔶🔶🔶🔶📕


<resources>  
    <string name="switch_language">Switch Language</string>  
    <string name="switch_color_scheme">Switch Color Scheme</string>  
    <string name="text_size">Text Size</string>  
    <string name="color_scheme">Color Scheme</string>  
</resources>

🔶🔶🔶📱


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
  
public class MainActivity extends AppCompatActivity {  
      
    private CodeEditor editor;  
      
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
      
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        getMenuInflater().inflate(R.menu.menu_main, menu);  
        return true;  
    }  
      
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        int id = item.itemId;  
          
        switch (id) {  
            case R.id.switch_language:  
                chooseLanguage();  
                return true;  
                  
            case R.id.switch_colors:  
                chooseTheme();  
                return true;  
                  
            case R.id.text_size:  
                chooseTextSize();  
                return true;  
                  
            default:  
                return super.onOptionsItemSelected(item);  
        }  
    }  

private void chooseLanguage() {  
        final String[] languages = {"Java", "Python", "JavaScript", "Text"};  
          
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        builder.setTitle(R.string.switch_language);  
        builder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {  
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
        builder.setSingleChoiceItems(themes, -1, new DialogInterface.OnClickListener() {  
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
        builder.setSingleChoiceItems(sizes, -1, new DialogInterface.OnClickListener() {  
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

🔶🔶🔶🔶

private void chooseLanguage() {  
    final String[] languages = {"Java", "Python", "JavaScript", "XML", "Text"};  
      
    AlertDialog.Builder builder = new AlertDialog.Builder(this);  
    builder.setTitle(R.string.switch_language);  
    builder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {  
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
                    // إعداد لغة XML باستخدام TextMate  
                    try {  
                        // تهيئة TextMate للغة XML  
                        ensureTextmateGrammar();  
                        editor.setEditorLanguage(TextMateLanguage.create("text.xml"));  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                        // استخدام لغة فارغة كخيار احتياطي  
                        editor.setEditorLanguage(new EmptyLanguage());  
                    }  
                    break;  
                case 4:  
                    editor.setEditorLanguage(new EmptyLanguage());  
                    break;  
            }  
            dialog.dismiss();  
        }  
    });  
    builder.setNegativeButton(android.R.string.cancel, null);  
    builder.show();  
}  
  
// دالة مساعدة لتهيئة TextMate grammars  
private void ensureTextmateGrammar() {  
    // التأكد من أن TextMate registry مهيأ  
    if (GrammarRegistry.getInstance().getGrammar("text.xml") == null) {  
        // تحميل قواعد XML من assets أو مصدر آخر  
        GrammarRegistry.getInstance().loadGrammar(  
            "text.xml",  
            new DefaultGrammarDefinition("text.xml", "XML")  
        );  
    }  
}

