package org.cardna

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityMainBinding
import org.cardna.ui.cardpack.BottomDialogCardFragment
import org.cardna.ui.cardpack.CardCreateActivity

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
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


    // cardpackFragment의 버튼을 눌렀을 때, 이 MainActivity의 함수를 실행
    // 즉, MainActivity에서 BottomsheetDialog를 띄워주는 함수
    fun showBottomDialogCardFragment() {
        // 바텀싯 다이얼로그가 뜬 후, 카드나 or 카드너를 선택했을 때, 그거에 따라 어떤 액티비티를 띄워줘야 하는지를 명세한 Fragment정의하고
        val bottomDialogCardFragment: BottomDialogCardFragment = BottomDialogCardFragment {
            when (it) {
                CARD_ME -> {
                    Toast.makeText(this, "카드나 작성", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CardCreateActivity::class.java)
                    startActivity(intent)
                }
                //
                CARD_YOU -> Toast.makeText(this, "카드너 추가", Toast.LENGTH_SHORT).show()
            }
        }
        bottomDialogCardFragment.show(supportFragmentManager, bottomDialogCardFragment.tag)
    }

    companion object{
        const val CARD_ME = 0
        const val CARD_YOU = 1
    }

}