package com.example.lab4_kotlin_improved

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    // 宣告元件變數
    private lateinit var edDrink: EditText
    private lateinit var rgSugar: RadioGroup
    private lateinit var rgIce: RadioGroup
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 取得元件
        edDrink = findViewById(R.id.edDrink)
        rgSugar = findViewById(R.id.rgSugar)
        rgIce = findViewById(R.id.rgIce)
        btnSend = findViewById(R.id.btnSend)

        btnSend.setOnClickListener {
            val drinkName = edDrink.text.toString().trim()

            if (drinkName.isEmpty()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 取得目前選取的 RadioButton 文字
            val sugarText = findCheckedText(rgSugar)
            val iceText = findCheckedText(rgIce)

            // 準備要回傳給 MainActivity 的資料
            val bundle = bundleOf(
                MainActivity.KEY_DRINK to drinkName,
                MainActivity.KEY_SUGAR to sugarText,
                MainActivity.KEY_ICE to iceText
            )

            val resultIntent = Intent().putExtras(bundle)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    /**
     * 小工具函式：
     * 根據 RadioGroup 找出被勾選的 RadioButton，回傳其文字內容
     */
    private fun findCheckedText(rg: RadioGroup): String {
        val checkedId = rg.checkedRadioButtonId
        val checkedBtn = rg.findViewById<RadioButton>(checkedId)
        return checkedBtn.text.toString()
    }
}
