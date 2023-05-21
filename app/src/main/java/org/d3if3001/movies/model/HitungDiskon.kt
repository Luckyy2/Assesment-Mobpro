package org.d3if3001.movies.model

import org.d3if3001.movies.db.DiskonEntity

fun DiskonEntity.hitungDiskon(): HasilDiskon {
    val jumlahDiskon = harga * diskon / 100
    val hasilDiskon = harga - jumlahDiskon
    val namaBarang = namaBarang

    return HasilDiskon(hasilDiskon,namaBarang)
}