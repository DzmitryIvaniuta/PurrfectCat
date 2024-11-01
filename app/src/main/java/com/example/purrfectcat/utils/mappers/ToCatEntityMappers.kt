package com.example.purrfectcat.utils.mappers

import com.example.purrfectcat.core.data.network.models.CatResponseModel
import com.example.purrfectcat.core.data.storage.models.CatEntity
import com.example.purrfectcat.core.domain.model.Cat

fun CatEntity.entityToCat(): Cat {
    return Cat (
        id = this.id,
        url = this.url
    )
}

fun CatResponseModel.responseToCat(): Cat {
    return Cat (
        id = this.id ?: throw IllegalArgumentException("Cat ID is missing"),
        url = this.url ?: ""
    )
}