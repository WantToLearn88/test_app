package com.app.editor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.event.SelectionChangeEvent;

import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.event.EditorKeyEvent;
// import io.github.rosemoe.sora.event.InlayHintClickEvent;
import io.github.rosemoe.sora.event.KeyBindingEvent;
import io.github.rosemoe.sora.event.PublishSearchResultEvent;
import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.event.SideIconClickEvent;
import io.github.rosemoe.sora.event.TextSizeChangeEvent;
// import io.github.rosemoe.sora.graphics.inlayHint.ColorInlayHintRenderer;
// import io.github.rosemoe.sora.graphics.inlayHint.TextInlayHintRenderer;
import io.github.rosemoe.sora.lang.EmptyLanguage;
// import io.github.rosemoe.sora.lang.JavaLanguageSpec;

import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
// import io.github.rosemoe.sora.langs.textmate.registry.dsl.languages;
import io.github.rosemoe.sora.langs.textmate.registry.model.DefaultGrammarDefinition;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;

import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorSearcher;
import io.github.rosemoe.sora.widget.EditorSearcher.SearchOptions;
import io.github.rosemoe.sora.widget.SelectionMovement;
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion;
import io.github.rosemoe.sora.widget.component.Magnifier;
import io.github.rosemoe.sora.widget.ext.EditorSpanInteractionHandler;
// import io.github.rosemoe.sora.widget.getComponent;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;
import io.github.rosemoe.sora.widget.schemes.SchemeEclipse;
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub;
import io.github.rosemoe.sora.widget.schemes.SchemeNotepadXX;
import io.github.rosemoe.sora.widget.schemes.SchemeVS2019;
import io.github.rosemoe.sora.widget.style.LineInfoPanelPosition;
import io.github.rosemoe.sora.widget.style.LineInfoPanelPositionMode;
// import io.github.rosemoe.sora.widget.subscribeAlways;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import io.github.rosemoe.sora.app.databinding.ActivityMainBinding;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import org.eclipse.tm4e.core.registry.IThemeSource;



import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;

import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import org.eclipse.tm4e.core.registry.IThemeSource;

public class MainActivity extends AppCompatActivity {

    private CodeEditor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // تعيين الواجهة (تأكد أن ID المحرر في XML هو @+id/editor)
        setContentView(R.layout.activity_main);

        // ربط الـ Toolbar
        Toolbar toolbar = findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);

        // ربط المحرر بدون Binding
        editor = findViewById(R.id.editor);

        // إعدادات افتراضية
        setupEditorDefaults();

        // إعداد نظام اللغات
        setupTextMate();
        
        // نص تجريبي
        editor.setText("/*\n * مرحباً بك في تطبيقك الخاص\n */\npublic class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n    }\n}");
    }

    private void setupEditorDefaults() {
        try {
            // تحميل الخط من assets
            editor.setTypefaceText(Typeface.createFromAsset(getAssets(), "JetBrainsMono-Regular.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.setLineNumberEnabled(true);
    }

    private void setupTextMate() {
        FileProviderRegistry.getInstance().addFileProvider(new AssetsFileResolver(getAssets()));
        try {
            GrammarRegistry.getInstance().loadGrammars("textmate/languages.json");
            loadThemeAsset("darcula", true);
            loadThemeAsset("quietlight", false);
            
            ensureTextmateTheme();
            ThemeRegistry.getInstance().setTheme("darcula");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadThemeAsset(String name, boolean isDark) throws IOException {
        String path = "textmate/" + name + ".json";
        InputStream is = getAssets().open(path);
        ThemeRegistry.getInstance().loadTheme(
                new ThemeModel(IThemeSource.fromInputStream(is, path, null), name).setDark(isDark)
        );
    }

    private void ensureTextmateTheme() {
        if (!(editor.getColorScheme() instanceof TextMateColorScheme)) {
            editor.setColorScheme(TextMateColorScheme.create(ThemeRegistry.getInstance()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.text_undo) {
            if (editor.canUndo()) editor.undo();
            return true;
        } else if (id == R.id.text_redo) {
            if (editor.canRedo()) editor.redo();
            return true;
        } else if (id == R.id.code_format) {
            editor.formatCodeAsync();
            return true;
        } else if (id == R.id.switch_language) {
            showLanguageDialog();
            return true;
        } else if (id == R.id.switch_colors) {
            showThemeDialog();
            return true;
        } else if (id == R.id.editor_settings) {
            showSettingsDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showLanguageDialog() {
        String[] langs = {"Java", "Python", "HTML", "C++", "JavaScript", "Plain Text"};
        String[] scopes = {"source.java", "source.python", "text.html.basic", "source.cpp", "source.js", ""};

        new AlertDialog.Builder(this)
                .setTitle("اختر لغة البرمجة")
                .setItems(langs, (dialog, which) -> {
                    if (scopes[which].isEmpty()) {
                        editor.setEditorLanguage(null);
                    } else {
                        ensureTextmateTheme();
                        editor.setEditorLanguage(TextMateLanguage.create(scopes[which], true));
                    }
                }).show();
    }

    private void showThemeDialog() {
        String[] themes = {"Darcula (Dark)", "QuietLight (Light)", "Default"};
        new AlertDialog.Builder(this)
                .setTitle("اختر السمة")
                .setItems(themes, (dialog, which) -> {
                    if (which == 2) {
                        editor.setColorScheme(new EditorColorScheme());
                    } else {
                        ensureTextmateTheme();
                        ThemeRegistry.getInstance().setTheme(which == 0 ? "darcula" : "quietlight");
                    }
                    editor.invalidate();
                }).show();
    }

    private void showSettingsDialog() {
        String[] options = {"التفاف النص (WordWrap)", "أرقام الأسطر", "تثبيت الأرقام (Pin)", "وضع القراءة فقط"};
        boolean[] states = {
                editor.isWordwrap(), 
                editor.isLineNumberEnabled(), 
                editor.isLineNumberPinned(), 
                !editor.isEditable()
        };

        new AlertDialog.Builder(this)
                .setTitle("تخصيص المحرر")
                .setMultiChoiceItems(options, states, (dialog, which, isChecked) -> {
                    switch (which) {
                        case 0: editor.setWordwrap(isChecked); break;
                        case 1: editor.setLineNumberEnabled(isChecked); break;
                        case 2: editor.setPinLineNumber(isChecked); break;
                        case 3: editor.setEditable(!isChecked); break;
                    }
                })
                .setPositiveButton("إغلاق", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (editor != null) editor.release();
    }
}