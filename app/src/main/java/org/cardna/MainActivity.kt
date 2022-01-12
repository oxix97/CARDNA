package org.cardna

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityMainBinding

class MainActivity : BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun initView() {
        setBottomNavigation()
        setBottomNavigationSelectListener()
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnvMain.setupWithNavController(navController)
        binding.bnvMain.isItemHorizontalTranslationEnabled = true
    }

    private fun setBottomNavigationSelectListener() {
        binding.bnvMain.setItemIconTintList(null)
        binding.bnvMain.selectedItemId = R.id.menu_main_maincard
    }
}