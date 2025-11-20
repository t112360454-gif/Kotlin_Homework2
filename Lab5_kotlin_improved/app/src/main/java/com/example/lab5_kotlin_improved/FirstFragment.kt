package com.example.lab5_kotlin_improved

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FirstFragment : Fragment() {

    companion object {
        private const val TAG = "FirstFragment"
    }

    //Fragment 被加入 Activity 此時可以取得 Activity 的 context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG,"onAttach")
    }
    //Fragment 本體正在初始化 此時畫面還沒開始建立（沒有 UI）
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"onCreate")
    }
    //建立畫面（View 階段）
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG,"onCreateView")
        // 填充 Layout 佈局，返回 View 對象
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    //畫面已經建立完成，可使用 findViewById 適合做 UI 的設定
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"onViewCreated")
    }
    ///Fragment 已經「可見」 但還「不能互動」
    override fun onStart() {
        super.onStart()
        Log.e(TAG,"onStart")
    }
    //Fragment 完全呈現在畫面上 使用者可以操作 UI（按按鈕等）
    override fun onResume() {
        super.onResume()
        Log.e(TAG,"onResume")
    }
    //使用者看不到 UI（例如切換頁面）
    override fun onPause() {
        super.onPause()
        Log.e(TAG,"onPause")
    }
    //Fragment 完整消失 但畫面 View 還沒銷毀
    override fun onStop() {
        super.onStop()
        Log.e(TAG,"onStop")
    }
    //Fragment 的畫面（View）被銷毀 Fragment 本體（物件）還在 這時候： 所有 UI 元件（Button、TextView）都消失 不能再用 findViewById
    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG,"onDestroyView")
    }
    //Fragment 本體被清掉（物件銷毀） 適合釋放資源
    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"onDestroy")
    }
    //Fragment 從 Activity 分離 代表 Fragment 不再屬於該 Activity 到此 Fragment 完全結束。
    override fun onDetach() {
        super.onDetach()
        Log.e(TAG,"onDetach")
    }
}


