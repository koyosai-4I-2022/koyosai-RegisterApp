package com.example.koyosaipic.service

import com.example.koyosaipic.entity.InsertId
import com.example.koyosaipic.repository.IdRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping


@Service
class ReservationService(private val idRepo: IdRepo){
    /*@Autowired
    private lateinit var insertId: InsertId*/


    fun findAll(): List<InsertId> = idRepo.findAll()


    fun save() {
        val insertId = InsertId()
        idRepo.save(insertId)
    }
}