package org.cardna.ui.maincard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.cardna.R
import org.cardna.base.baseutil.BaseViewUtil
import org.cardna.databinding.ActivityAlarmBinding

class AlarmActivity : BaseViewUtil.BaseAppCompatActivity<ActivityAlarmBinding>(R.layout.activity_alarm) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {

    }
}