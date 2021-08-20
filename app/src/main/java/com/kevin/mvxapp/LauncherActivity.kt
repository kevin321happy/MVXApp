package com.kevin.mvxapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kevin.common.constance.RoutePath
import com.kevin.common.net.ApiServer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint


class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ skip() }, 1500)
    }

    private fun skip() {
        ARouter.getInstance().build(RoutePath.MAIN_ACTIVITY).navigation()
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

}