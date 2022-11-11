package com.example.koyosaipic.entity

import javax.persistence.*

@Entity
@Table(name="reservaition")
data class InsertId (
    @Id
    @Column(name="id")
    @GeneratedValue
    val id: Int? = null
)