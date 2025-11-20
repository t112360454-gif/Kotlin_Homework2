package com.example.lab4_java_improved;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 統一管理 Intent 傳遞用的 Key，避免打錯字
    public static final String KEY_DRINK = "drrink";
    public static final String KEY_SUGAR = "sugar";
    public static final String KEY_ICE   = "ice";

    // 先宣告畫面上的元件變數，之後 onCreate 再初始化
    private TextView tvMeal;
    private Button btnChoice;

    // ActivityResultLauncher：負責接收 MainActivity2 回傳的結果
    private final ActivityResultLauncher<Intent> startForResult =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            ActivityResult ar = result;
                            Intent data = ar.getData();
                            if (data == null) return;

                            // 使用常數 Key 取出資料，若為 null 則給預設「無」
                            String drink = data.getStringExtra(KEY_DRINK);
                            if (drink == null) drink = "無";

                            String sugar = data.getStringExtra(KEY_SUGAR);
                            if (sugar == null) sugar = "無";

                            String ice = data.getStringExtra(KEY_ICE);
                            if (ice == null) ice = "無";

                            tvMeal.setText("飲料：" + drink + "\n\n甜度：" + sugar + "\n\n冰塊：" + ice);
                        }
                    }
            );

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

        // 初始化元件
        tvMeal = findViewById(R.id.tvMeal);
        btnChoice = findViewById(R.id.btnChoice);

        // 按鈕點擊：啟動 MainActivity2
        btnChoice.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startForResult.launch(intent);
        });
    }
}
