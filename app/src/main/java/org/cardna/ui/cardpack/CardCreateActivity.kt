package org.cardna.ui.cardpack

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.data.remote.api.ApiService.cardService
import org.cardna.data.remote.model.cardpack.RequestCreateCardMeData
import org.cardna.databinding.ActivityCardCreateBinding
import org.cardna.util.shortToast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.net.URI

class CardCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCardCreateBinding>(R.layout.activity_card_create) {
    private var symbolId: Int? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setChooseCardListener()
        makeCardListener()
    }

    private fun setChooseCardListener() {
        binding.clCardcreateImg.setOnClickListener {
            val bottomDialogImageFragment: BottomDialogImageFragment = BottomDialogImageFragment {
                var img_index: Int = GALLERY
                when (it) {
                    // 심볼이 하나라도 선택이 되어서 dialog가 닫히면
                    SYMBOL_0 -> {
                        Toast.makeText(this, "SYMBOL_0", Toast.LENGTH_SHORT).show()
                        img_index = R.drawable.ic_symbol_cardme_0
                        symbolId = 0
                    }
                    SYMBOL_1 -> {
                        Toast.makeText(this, "SYMBOL_1", Toast.LENGTH_SHORT).show()
                        img_index = R.drawable.ic_symbol_cardme_1
                        symbolId = 1
                    }
                    SYMBOL_2 -> {
                        Toast.makeText(this, "SYMBOL_2", Toast.LENGTH_SHORT).show()
                        img_index = R.drawable.ic_symbol_cardme_2
                        symbolId = 2
                    }
                    SYMBOL_3 -> {
                        Toast.makeText(this, "SYMBOL_3", Toast.LENGTH_SHORT).show()
                        img_index = R.drawable.ic_symbol_cardme_3
                        symbolId = 3
                    }
                    SYMBOL_4 -> {
                        Toast.makeText(this, "SYMBOL_4", Toast.LENGTH_SHORT).show()
                        img_index = R.drawable.ic_symbol_cardme_4
                        symbolId = 4
                    }
                }
                with(binding) {
                    // 이미지 뷰 보이도록
                    ivCardcreateGalleryImg.visibility = View.VISIBLE
                    // 각 심볼 이미지 띄워주기
                    ivCardcreateGalleryImg.setImageResource(img_index)
                    clCardcreateImg.visibility = View.INVISIBLE
                }
            }
            bottomDialogImageFragment.show(supportFragmentManager, bottomDialogImageFragment.tag)
        }
    }

    private fun resToUri(resId: Int): Uri =
        Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(resId))
            .appendPath(resources.getResourceTypeName(resId))
            .appendPath(resources.getResourceEntryName(resId))
            .build()

    private fun makeCardListener() {
        //
        binding.btnCardcreateComplete.setOnClickListener {
            // 카드나 만들기 버튼을 눌렀을 때,

            // 1. 서버로 title, content, symbolId, uri 전송
            // symbolId - 카드 이미지 심볼 id, 이미지가 있는 경우 null을 보내주면 됨

            val body = RequestCreateCardMeData(
                binding.etCardcreateKeyword.text.toString(),
                binding.etCardcreateDetail.text.toString(),
                symbolId
            ).toRequestBody()

            lifecycleScope.launch(Dispatchers.IO) {
                runCatching { cardService.postCreateCardMe(body, makeUriToFile()) }
                    .onSuccess { Log.d("성공", it.message)}
                    .onFailure { it.printStackTrace() }
            }

            // if(파일이 잘 들어갔을 때)
            // 2. cardCreateCompleteActivity로 인텐트로 이동
            val intent = Intent(this@CardCreateActivity, CardCreateCompleteActivity::class.java)
            intent.putExtra("meOrYou", CARD_ME)
            intent.putExtra("cardImg", R.drawable.ic_symbol_cardme_0)
            intent.putExtra("cardTitle", binding.etCardcreateKeyword.text.toString())

            startActivity(intent)
        }
    }


    private fun makeUriToFile(): MultipartBody.Part {
        val options = BitmapFactory.Options()
        val inputStream: InputStream =
            requireNotNull(contentResolver.openInputStream(uri!!))
        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream)
        val fileBody = RequestBody.create(
            MediaType.parse("image/png"),
            byteArrayOutputStream.toByteArray()
        )

        val part = MultipartBody.Part.createFormData(
            "image",
            File("${uri.toString()}.png").name,
            fileBody
        )

        return part
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
            uri = result.data?.data  //Intent를 반환->Intent에서 Uri로 get하기

            // imageView는 보이도록
            binding.ivCardcreateGalleryImg.visibility = View.VISIBLE
            binding.clCardcreateImg.visibility =
                View.INVISIBLE // 이제 INVISIBLE이니까 한번 이미지 선택하면 다시 선택불가
            Glide.with(this).load(uri).into(binding.ivCardcreateGalleryImg)

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