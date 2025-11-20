package com.example.lab6_java_improved;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // 按鈕元件宣告成成員變數，只 findViewById 一次
    private Button btnToast;
    private Button btnSnackBar;
    private Button btnDialog1;
    private Button btnDialog2;
    private Button btnDialog3;

    // 列表選項提升為成員變數，共用同一份陣列
    private final String[] items = new String[]{
            "選項 1", "選項 2", "選項 3", "選項 4", "選項 5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupListeners();
    }

    // 只負責把畫面上的元件抓出來
    private void initViews() {
        btnToast = findViewById(R.id.btnToast);
        btnSnackBar = findViewById(R.id.btnSnackBar);
        btnDialog1 = findViewById(R.id.btnDialog1);
        btnDialog2 = findViewById(R.id.btnDialog2);
        btnDialog3 = findViewById(R.id.btnDialog3);
    }

    // 負責設定所有按鈕的點擊事件
    private void setupListeners() {
        btnToast.setOnClickListener(v -> showToast("預設 Toast"));

        btnSnackBar.setOnClickListener(this::showSnackBar);

        btnDialog1.setOnClickListener(v -> showButtonDialog());

        btnDialog2.setOnClickListener(v -> showListDialog());

        btnDialog3.setOnClickListener(v -> showSingleChoiceDialog());
    }

    // SnackBar 抽成一個函式，讓 onCreate 看起來比較乾淨
    private void showSnackBar(View view) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("按鈕", v -> showToast("已回應"))
                .show();
    }

    // 三顆按鈕的 AlertDialog
    private void showButtonDialog() {
        new AlertDialog.Builder(this)
                .setTitle("按鈕式 AlertDialog")
                .setMessage("AlertDialog 內容")
                .setNeutralButton("左按鈕", (dialog, which) -> showToast("左按鈕"))
                .setNegativeButton("中按鈕", (dialog, which) -> showToast("中按鈕"))
                .setPositiveButton("右按鈕", (dialog, which) -> showToast("右按鈕"))
                .show();
    }

    // 列表式 AlertDialog
    private void showListDialog() {
        new AlertDialog.Builder(this)
                .setTitle("列表式 AlertDialog")
                .setItems(items, (dialog, i) -> showToast("你選的是" + items[i]))
                .show();
    }

    // 單選式 AlertDialog
    private void showSingleChoiceDialog() {
        final int[] position = {0};

        new AlertDialog.Builder(this)
                .setTitle("單選式 AlertDialog")
                .setSingleChoiceItems(items, 0, (dialog, i) -> position[0] = i)
                .setPositiveButton("確定", (dialog, which) ->
                        showToast("你選的是" + items[position[0]])
                )
                .show();
    }

    // 顯示 Toast 訊息的共用方法
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
