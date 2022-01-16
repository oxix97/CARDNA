package org.cardna.ui.cardpack

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityCardCreateBinding
import org.cardna.util.shortToast

class CardCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateBinding>(R.layout.activity_card_create) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setChooseCardListener()
        makeCardListener()
    }

    override fun initView() {

    }


    // 심볼 선택할 때도 그에 따른 이미지 이미지뷰에 띄워주고, cl 선택안되게
    private fun setChooseCardListener() {
        binding.clCardcreateImg.setOnClickListener {
            val bottomDialogImageFragment: BottomDialogImageFragment = BottomDialogImageFragment {
                var img_index: Int = R.drawable.ic_symbol_cardme_0
                when (it) {
                    SYMBOL_0 -> {
                        Toast.makeText(this, "SYMBOL_0", Toast.LENGTH_SHORT).show()
                        img_index= R.drawable.ic_symbol_cardme_0
                    }
                    SYMBOL_1 -> {
                        Toast.makeText(this, "SYMBOL_1", Toast.LENGTH_SHORT).show()
                        img_index= R.drawable.ic_symbol_cardme_1
                    }
                    SYMBOL_2 -> {
                        Toast.makeText(this, "SYMBOL_2", Toast.LENGTH_SHORT).show()
                        img_index= R.drawable.ic_symbol_cardme_2
                    }
                    SYMBOL_3 -> {
                        Toast.makeText(this, "SYMBOL_3", Toast.LENGTH_SHORT).show()
                        img_index= R.drawable.ic_symbol_cardme_3
                    }
                    SYMBOL_4 -> {
                        Toast.makeText(this, "SYMBOL_4", Toast.LENGTH_SHORT).show()
                        img_index= R.drawable.ic_symbol_cardme_2                    }

                }
                with(binding){
                    ivCardcreateImg.visibility = View.VISIBLE
                    ivCardcreateImg.setImageResource(img_index)
                    clCardcreateImg.visibility = View.INVISIBLE
                }
            }
            bottomDialogImageFragment.show(supportFragmentManager, bottomDialogImageFragment.tag)
        }
    }

    private fun makeCardListener() {
        binding.btnCardcreateComplete.setOnClickListener {
            // 카드나 만들기 버튼을 눌렀을 때, dialog를 띄워준다.
            val meOrYou = CARD_ME
            val cardImg = R.drawable.ic_symbol_cardme_0 // 나중에 바꿔야 함
            val cardTitle = binding.etCardcreateKeyword.text.toString()

            val dialog = CardCreateCompleteDialog(this, meOrYou, cardImg, cardTitle)
            dialog.showDialog()

            // 3초간 띄우고 현재 activity 없애기
        }
    }

    // gallery access
    fun checkPermission() {
        val cameraPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            //프로그램 진행
            startProcess()
        } else {
            //권한요청
            requestPermission()
        }
    }

    private fun startProcess() {
        val intent = Intent().apply {
            setType("image/*")
            setAction(Intent.ACTION_GET_CONTENT)
        }
        getResultText.launch(intent)
    }

    val getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data  //Intent를 반환->Intent에서 Uri로 get하기

            // imageView는 보이도록
            binding.ivCardcreateImg.visibility = View.VISIBLE
            binding.clCardcreateImg.visibility = View.INVISIBLE // 이제 INVISIBLE이니까 한번 이미지 선택하면 다시 선택불가
            Glide.with(this).load(uri).into(binding.ivCardcreateImg)
        }
        //else if (result.resultCode == Activity.RESULT_CANCELED) {} =>Activity.RESULT_CANCELED일때 처리코드가 필요하다면
    }

    private fun requestPermission() {
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { isGranted: Boolean ->
            when (isGranted) {
                true -> startProcess()
                false -> shortToast("갤러리 권한을 허용해주세요.")
            }
        }

    companion object {
        const val SYMBOL_0 = 0
        const val SYMBOL_1 = 1
        const val SYMBOL_2 = 2
        const val SYMBOL_3 = 3
        const val SYMBOL_4 = 4
        const val GALLERY = 5

        const val CARD_ME = 6
        const val CARD_YOU = 7
    }
}