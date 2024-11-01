package com.example.purrfectcat.utils.mappers

import com.example.purrfectcat.core.data.storage.models.CatEntity
import com.example.purrfectcat.core.domain.model.Cat

fun Cat.domainToCatEntity(): CatEntity {
    return CatEntity(
        id = this.id,
        url = this.url
    )
}