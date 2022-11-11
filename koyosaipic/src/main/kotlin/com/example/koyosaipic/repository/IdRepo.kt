package com.example.koyosaipic.repository

import com.example.koyosaipic.entity.InsertId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IdRepo: JpaRepository<InsertId, Int>