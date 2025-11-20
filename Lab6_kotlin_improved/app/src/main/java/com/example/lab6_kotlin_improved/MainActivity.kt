package com.example.lab6_kotlin_improved

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // 按鈕元件宣告成成員變數，只 findViewById 一次
    private lateinit var btnToast: Button
    private lateinit var btnSnackBar: Button
    private lateinit var btnDialog1: Button
    private lateinit var btnDialog2: Button
    private lateinit var btnDialog3: Button

    // 列表選項提升為成員變數，共用同一份陣列
    private val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
    }

    // 只負責把畫面上的元件抓出來
    private fun initViews() {
        btnToast = findViewById(R.id.btnToast)
        btnSnackBar = findViewById(R.id.btnSnackBar)
        btnDialog1 = findViewById(R.id.btnDialog1)
        btnDialog2 = findViewById(R.id.btnDialog2)
        btnDialog3 = findViewById(R.id.btnDialog3)
    }

    // 負責設定所有按鈕的點擊事件
    private fun setupListeners() {
        btnToast.setOnClickListener {
            showToast("預設 Toast")
        }

        btnSnackBar.setOnClickListener { view ->
            showSnackBar(view)
        }

        btnDialog1.setOnClickListener {
            showButtonDialog()
        }

        btnDialog2.setOnClickListener {
            showListDialog()
        }

        btnDialog3.setOnClickListener {
            showSingleChoiceDialog()
        }
    }

    // SnackBar 抽成一個函式，讓 onCreate 看起來比較乾淨
    private fun showSnackBar(view: View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("按鈕") {
                showToast("已回應")
            }
            .show()
    }

    // 三顆按鈕的 AlertDialog
    private fun showButtonDialog() {
        AlertDialog.Builder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("AlertDialog 內容")
            .setNeutralButton("左按鈕") { _, _ ->
                showToast("左按鈕")
            }
            .setNegativeButton("中按鈕") { _, _ ->
                showToast("中按鈕")
            }
            .setPositiveButton("右按鈕") { _, _ ->
                showToast("右按鈕")
            }
            .show()
    }

    // 列表式 AlertDialog
    private fun showListDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, i ->
                showToast("你選的是${items[i]}")
            }
            .show()
    }

    // 單選式 AlertDialog
    private fun showSingleChoiceDialog() {
        var position = 0

        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, 0) { _, i ->
                position = i
            }
            .setPositiveButton("確定") { _, _ ->
                showToast("你選的是${items[position]}")
            }
            .show()
    }

    // 顯示 Toast 訊息的共用方法
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
