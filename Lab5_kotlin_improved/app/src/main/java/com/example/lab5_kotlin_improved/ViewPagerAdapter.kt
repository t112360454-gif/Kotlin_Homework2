package com.example.lab5_kotlin_improved

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// 定義一個 ViewPagerAdapter 類別
// 傳遞 FragmentManager 和 Lifecycle 物件
// 接收兩個必要參數：FragmentManager、Lifecycle
//fm: FragmentManager 。FragmentManager = Fragment 的管理者 ViewPager2 裡的每一頁都是 Fragment 所以 Adapter 必須用 FragmentManager 來控制 Fragment
//lifecycle: Lifecycle 。 lifecycle 用來管理 Fragment 生命週期 確保 Fragment 在對的時間： onStart onResume onPause onDestroyView 都能正確執行。
// ViewPagerAdapter 繼承 FragmentStateAdapter 類別
class ViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    // 直接用一個 List 管理所有 Fragment，比較好維護
    private val fragments: List<Fragment> = listOf(
        FirstFragment(),   // 第一頁 Fragment
        SecondFragment(),  // 第二頁 Fragment
        ThirdFragment()    // 第三頁 Fragment
    )

    // 回傳 Fragment 的數量
    override fun getItemCount(): Int = fragments.size

    // 根據 position 位置回傳對應的 Fragment
    override fun createFragment(position: Int): Fragment = fragments[position]
}

//這個 Adapter 是專門用在 ViewPager2 的。
//ViewPager2 不會自己顯示頁面，它需要「Adapter」來告訴它： 有幾頁？ 每頁要顯示哪個 Fragment？
//這個 Adapter 就是用來提供答案的。
