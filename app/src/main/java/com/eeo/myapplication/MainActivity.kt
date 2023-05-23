package com.eeo.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.eeo.myapplication.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(), View.OnClickListener {

  private val TAG = "DrawerLayoutActivity"
  private lateinit var binding: ActivityMainBinding
  private val titleArray = arrayOf("首页", "新闻", "娱乐", "博客", "论坛") // 左侧菜单项的标题数组
  private val settingArray = arrayOf("我的", "设置", "关于") // 右侧菜单项的标题数组
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // 给抽屉布局设置侧滑监听器
    binding.dlLayout.addDrawerListener(SlidingListener())

    binding.btnDrawerLeft.setOnClickListener(this)
    binding.btnDrawerRight.setOnClickListener(this)
    initListDrawer() // 初始化侧滑的菜单列表
  }

  // 初始化侧滑的菜单列表
  private fun initListDrawer() {
    // 下面初始化左侧菜单的列表视图

    val leftAdapter = ArrayAdapter<String>(
      this,
      R.layout.item_select, titleArray
    )
    binding.lvDrawerLeft.adapter = leftAdapter
    binding.lvDrawerLeft.onItemClickListener = LeftListListener()
    // 下面初始化右侧菜单的列表视图
    val rightAdapter = ArrayAdapter<String>(
      this,
      R.layout.item_select, settingArray
    )
    binding.lvDrawerRight.adapter = rightAdapter
    binding.lvDrawerRight.onItemClickListener = RightListListener()
  }

  override fun onClick(v: View) {
    if (v.id == R.id.btn_drawer_left) {
      if (binding.dlLayout.isDrawerOpen(binding.lvDrawerLeft)) { // 左侧菜单列表已打开
        binding.dlLayout.closeDrawer(binding.lvDrawerLeft) // 关闭左侧抽屉
      } else { // 左侧菜单列表未打开
        binding.dlLayout.openDrawer(binding.lvDrawerLeft) // 打开左侧抽屉
      }
    } else if (v.id == R.id.btn_drawer_right) {
      if (binding.dlLayout.isDrawerOpen(binding.lvDrawerRight)) { // 右侧菜单列表已打开
        binding.dlLayout.closeDrawer(binding.lvDrawerRight) // 关闭右侧抽屉
      } else { // 右侧菜单列表未打开
        binding.dlLayout.openDrawer(binding.lvDrawerRight) // 打开右侧抽屉
      }
    }
  }

  // 定义一个左侧菜单列表的点击监听器
  private inner class LeftListListener: OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
      val text = titleArray[position]
      binding.tvDrawerCenter.text = "这里是" + text + "页面"
      binding.dlLayout.closeDrawers() // 关闭所有抽屉
    }
  }

  // 定义一个右侧菜单列表的点击监听器
  private inner class RightListListener: OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
      val text = settingArray[position]
      binding.tvDrawerCenter.text = "这里是" + text + "页面"
      binding.dlLayout.closeDrawers() // 关闭所有抽屉
    }
  }

  // 定义一个抽屉布局的侧滑监听器
  private inner class SlidingListener: DrawerListener {
    // 在拉出抽屉的过程中触发
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

    // 在侧滑抽屉打开后触发
    override fun onDrawerOpened(drawerView: View) {
      if (drawerView.id == R.id.lv_drawer_left) {
        binding.btnDrawerLeft.text = "关闭左边侧滑"
      } else {
        binding.btnDrawerRight.text = "关闭右边侧滑"
      }
    }

    // 在侧滑抽屉关闭后触发
    override fun onDrawerClosed(drawerView: View) {
      if (drawerView.id == R.id.lv_drawer_left) {
        binding.btnDrawerLeft.text = "打开左边侧滑"
      } else {
        binding.btnDrawerRight.text = "打开右边侧滑"
      }
    }

    // 在侧滑状态变更时触发
    override fun onDrawerStateChanged(paramInt: Int) {}
  }

}