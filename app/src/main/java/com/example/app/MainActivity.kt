package com.example.app

import android.drm.DrmStore.Playback.STOP
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class   MainActivity : AppCompatActivity() {
    //保存抽奖名单
    var names = listOf<String>("张三","李四","老王")
    //定时器 每隔一段时间切换一次名字
    lateinit var timer: Timer
    //记录当前索引
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        //设置默认显示第一个人
        mNameTextView.text = names[0]

        //给按钮添加点击事件
        mStartBtn.setOnClickListener {
            //判断标题是start还是stop
            if(mStartBtn.text.toString() == "START")  {
                mStartBtn.text = "STOP"
                //创建定时器
                timer = Timer()
                //分配一个定时任务
                timer.schedule(object:TimerTask (){
                    override fun run(){
                        //判断是否越界
                        index = if (index+1 > names.size-1) 0 else index+1//此处注意不可用++
                        //取出对应的名字
                        mNameTextView.text = names[index]
                    }
                },0,100)//此处直接打上时间，delay和period会自己出现
            }else{
                mStartBtn.text = "START"
                timer.cancel()
            }
        }
    }
}