package com.example.lab5_kotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    //Activity 的初始化階段 畫面 UI 會在這裡透過 setContentView 建立 ，找元件 findViewById
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //super.onCreate() 呼叫父類別的 onCreate
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) //主程式用setContentView(R.layout.activity_main) 把 activity_main.xml 顯示出來。
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.e("MainActivity","onCreate")
        // 取得 ViewPager2 元件
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        // 建立 ViewPagerAdapter
        //ViewPagerAdapter 是自己寫的 class，用來決定：ViewPager2 有幾頁？每一頁是哪個 Fragment？
        //supportFragmentManager 管理 Fragment（建立、顯示、銷毀）
        //this.lifecycle ➜ MainActivity 的 lifecycle
        val adapter = ViewPagerAdapter(supportFragmentManager, this.lifecycle)
        //並設定給 ViewPager2 讓 ViewPager2 可以顯示 Fragment。
        //沒有這行 → ViewPager2 會是空白的。
        viewPager2.adapter = adapter
        // 預先載入鄰近的頁面
        viewPager2.offscreenPageLimit = 1
    }
    //Activity 從停止（onStop）狀態再次回到前台前一定會呼叫
    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity", "onRestart")
    }
    //Activity 已經進入螢幕範圍 「可見」但「還不能互動」
    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "onStart")
    }
    //Activity 完全顯示在前台 使用者可以開始操作所有 UI 元件
    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")
    }
    //Activity 正要被其他畫面覆蓋 仍然留在記憶體中，但不再可互動
    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause")
    }
    //Activity 已經完全離開螢幕 使用者完全看不到畫面
    override fun onStop() {
        super.onStop()
        Log.e("MainActivity","onStop")
    }
    //Activity 即將被銷毀 系統要把整個 Activity 的資源完全清除
    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity","onDestroy")
    }
}
//啟動 App：onCreate → onStart → onResume
//按 Home： onPause → onStop
//重新回到 App： onRestart → onStart → onResume
//關掉 App： onPause → onStop → onDestroy