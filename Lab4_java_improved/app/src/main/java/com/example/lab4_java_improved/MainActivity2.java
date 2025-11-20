package com.example.lab4_java_improved;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    // 宣告元件變數
    private EditText edDrink;
    private RadioGroup rgSugar;
    private RadioGroup rgIce;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 取得元件
        edDrink = findViewById(R.id.edDrink);
        rgSugar = findViewById(R.id.rgSugar);
        rgIce = findViewById(R.id.rgIce);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String drinkName = edDrink.getText().toString().trim();

            if (drinkName.isEmpty()) {
                Toast.makeText(MainActivity2.this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show();
                return;
            }

            // 取得目前選取的 RadioButton 文字
            String sugarText = findCheckedText(rgSugar);
            String iceText = findCheckedText(rgIce);

            // 準備要回傳給 MainActivity 的資料
            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.KEY_DRINK, drinkName);
            resultIntent.putExtra(MainActivity.KEY_SUGAR, sugarText);
            resultIntent.putExtra(MainActivity.KEY_ICE, iceText);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    /**
     * 小工具函式：
     * 根據 RadioGroup 找出被勾選的 RadioButton，回傳其文字內容
     */
    private String findCheckedText(RadioGroup rg) {
        int checkedId = rg.getCheckedRadioButtonId();
        RadioButton checkedBtn = rg.findViewById(checkedId);
        if (checkedBtn == null) return "無";
        return checkedBtn.getText().toString();
    }
}
