package com.baudoin.sofascore.entity

import javax.persistence.Entity;
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Int? = null

    private val name: String? = null

}