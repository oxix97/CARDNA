package org.cardna

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityMainBinding
import org.cardna.ui.cardpack.BottomDialogCardFragment
import org.cardna.ui.cardpack.CardCreateActivity

import org.cardna.ui.mypage.OtherWriteActivity
import java.nio.channels.InterruptedByTimeoutException

import org.cardna.ui.cardpack.CardPackFragment
import org.cardna.ui.insight.InsightFragment
import org.cardna.ui.maincard.MainCardFragment
import org.cardna.ui.mypage.MyPageFragment
import org.cardna.util.replace

class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainCardFragment = MainCardFragment()
    private val insightFragment = InsightFragment()
    private val mypageFragment = MyPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun initView() {
        replace(R.id.fcv_main, mainCardFragment)
        initBottomNavigation()
        setBottomNavigationSelectListener()
    }

    //bottomnavigation연결
    private fun initBottomNavigation() {

        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main_maincard -> {
                    supportFragmentManager.popBackStack()
                    replace(R.id.fcv_main, mainCardFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_cardpack -> {
                    supportFragmentManager.popBackStack()
                    replace(R.id.fcv_main, CardPackFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_insight -> {
                    supportFragmentManager.popBackStack()
                    replace(R.id.fcv_main, insightFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    supportFragmentManager.popBackStack()
                    replace(R.id.fcv_main, mypageFragment)
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    private fun setBottomNavigationSelectListener() {
        binding.bnvMain.itemIconTintList = null
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
                CARD_YOU -> {
                    Toast.makeText(this, "카드너 추가", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, OtherWriteActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        bottomDialogCardFragment.show(supportFragmentManager, bottomDialogCardFragment.tag)
    }

    companion object {
        const val CARD_ME = 0
        const val CARD_YOU = 1
    }
}