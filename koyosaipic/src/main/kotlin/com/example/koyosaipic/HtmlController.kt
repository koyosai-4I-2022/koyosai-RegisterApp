package com.example.koyosaipic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import com.example.koyosaipic.entity.InsertId
import com.example.koyosaipic.repository.IdRepo
import com.example.koyosaipic.service.ReservationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
class HtmlController(private val reservationService: ReservationService) {
    @Autowired
    lateinit var idRepo: IdRepo

    //操作用変数
    var regiSlot = 0
    var hNow: String = "10"
    var mNow: String = "00"
    var min : Int = 0
    var hour : Int = 0

    //時間枠
    var time = arrayOf("10", "11", "12", "13", "14", "15", "16", "終了", "終了")
    var timeList = arrayOf("06", "12", "18", "24", "30", "36", "42", "48", "54", "00", "終了")

    @GetMapping("/")
    //Home
    fun home(nowTime: Model): String {
        nowTime.addAttribute("hNow", hNow)
        nowTime.addAttribute("mNow", mNow)
        return "index"
    }

    @GetMapping("/register")
    //登録用ページ
    fun register(touroku: Model): String {

        //htmlに値を渡す
        touroku.addAttribute("hNow", hNow)
        touroku.addAttribute("mNow", mNow)
        if (regiSlot == 6) {
            touroku.addAttribute("timeH", time[hour])
            touroku.addAttribute("registerTime", timeList[min + 1])
        } else {
            touroku.addAttribute("timeH", time[hour])
            touroku.addAttribute("registerTime", timeList[min])
        }

        return "register"
    }
    @PostMapping("/reservation")
    fun reservation(): String{
        reservationService.save()
        return "redirect:success"
    }


    @GetMapping("/operation")
    //運営ページ
    fun operation(): String {

        return "operation"
    }

    @PostMapping("/operation")
    //プレイ中の時間枠設定
    fun timeSet(@RequestParam hour: String, @RequestParam min: String) {
        hNow = hour
        mNow = min
    }



    @GetMapping("/success")
    fun success(seiriken: Model): String {
        //予約成功
        var i = idRepo.findAll()
        var iLength = i.size
        min = iLength / 6
        hour = iLength / 60
        //htmlに値を渡す
        seiriken.addAttribute("timeH", time[hour])
        seiriken.addAttribute("timeLine", timeList[min])
        return "success"
    }

}
