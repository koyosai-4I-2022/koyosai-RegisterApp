package com.example.koyosaipic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HtmlController {
    //操作用変数
    public open var regiSlot = 0
    public open var hNow = 0
    public open var h = 0
    public open var i = 1
    public open var j = 0
    //時間枠
    public open var time = arrayOf("9", "10", "11", "12", "13", "14", "15", "16", "終了")
    public open var timeList = arrayOf("00", "06", "12", "18", "24", "30", "36", "42", "48", "54", "しました")

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
        touroku.addAttribute("nowTimeH", time[hNow])
        touroku.addAttribute("nowTimeTable", timeList[j])
        if (regiSlot == 6) {
            touroku.addAttribute("timeH", time[h])
            touroku.addAttribute("registerTime", timeList[i+1])
            touroku.addAttribute("regiSlot", "6")
        } else {
            touroku.addAttribute("timeH", time[h])
            touroku.addAttribute("registerTime", timeList[i])
            touroku.addAttribute("regiSlot", 6-regiSlot)
        }

        return "register"
    }

    @GetMapping("/load")
    fun toTicket(): String{

        return "load"
    }

    @GetMapping("/bookingProcess")
    //予約処理（中間ページにアクセスして動作→整理券画面にリダイレクト）
    fun booking(): String{
        //予約時間
        regiSlot++
        if (regiSlot > 6){
            i++
            regiSlot = 1
        }
        if (i > 9){
            h++
            i = 0
        }
        return "redirect:ticket"
    }

    @GetMapping("/ticket")
    //予約情報ページ
    fun ticket(seiriken: Model):String{

        //htmlに値を渡す
        seiriken.addAttribute("timeH", time[h])
        seiriken.addAttribute("timeLine", timeList[i])
        return "ticket"
    }

    @GetMapping("/operation")
    //運営ページ
    fun operation(): String{

        return "operation"
    }

    @GetMapping("/member")
    //シフトページ
    fun member(): String{

        return "member"
    }

    @GetMapping("/success")
    //時間更新成功
    fun success(): String{
        j++
        if (j > 9){
            hNow++
            j = 0
        }
        return "success"
    }

}
