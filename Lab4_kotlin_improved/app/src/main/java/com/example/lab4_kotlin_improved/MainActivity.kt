package com.example.lab4_kotlin_improved

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // 統一管理 Intent 傳遞用的 Key，避免打錯字
    companion object {
        const val KEY_DRINK = "drrink"
        const val KEY_SUGAR = "sugar"
        const val KEY_ICE = "ice"
    }

    // 先宣告畫面上的元件變數，之後 onCreate 再初始化
    private lateinit var tvMeal: TextView
    private lateinit var btnChoice: Button

    // ActivityResultLauncher：負責接收 MainActivity2 回傳的結果
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data ?: return@registerForActivityResult

            // 使用 companion object 中的常數 Key
            val drink = data.getStringExtra(KEY_DRINK) ?: "無"
            val sugar = data.getStringExtra(KEY_SUGAR) ?: "無"
            val ice = data.getStringExtra(KEY_ICE) ?: "無"

            tvMeal.text = "飲料：$drink\n\n甜度：$sugar\n\n冰塊：$ice"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 初始化元件
        tvMeal = findViewById(R.id.tvMeal)
        btnChoice = findViewById(R.id.btnChoice)

        // 按鈕點擊：啟動 MainActivity2
        btnChoice.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startForResult.launch(intent)
        }
    }
}
