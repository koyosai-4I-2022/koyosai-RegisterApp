package com.example.koyosaipic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HtmlController {
    public open var regiSlot = 0
    public open var i = 1
    public open var j = 1
    public open var timeList = arrayOf("start", "9:00", "9:05")

    @GetMapping("/")
    //Home
    fun home(model: Model): String {
        val test = "Home"
        model.addAttribute("test", test)
        //model["title"] = "test"
        return "index"
    }

    @GetMapping("/register")
    //登録用ページ
    fun register(touroku: Model): String{

        //htmlに値を渡す
        touroku.addAttribute("timeTable", timeList[j+1])
        if (regiSlot == 6) {
            touroku.addAttribute("registerTime", timeList[i+1])
        } else {
            touroku.addAttribute("registerTime", timeList[i])
        }

        return "register"
    }

    @GetMapping("/ticket")
    //予約情報ページ
    fun ticket(seiriken: Model):String{
        //予約時間
        val timeTable = "11:10"
        regiSlot++
        if (regiSlot > 6){
            i++
            regiSlot = 1
        }

        //htmlに値を渡す
        seiriken.addAttribute("timeLine", timeList[i])
        return "ticket"
    }

    @GetMapping("/operation")
    //運営ページ
    fun operation(): String{

        return "operation"
    }

}
