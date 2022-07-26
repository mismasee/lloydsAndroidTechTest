package com.lloydtechassignment.data.mapper.animalmapper

import com.lloydtechassignment.data.mapper.EntityMapper
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.model.AnimalDomainModel
import com.lloydtechassignment.util.BLANK

class AnimalUIModelMapper : EntityMapper<AnimalUIModel,AnimalDomainModel> {

    override fun mapFromEntity(entity: AnimalUIModel): AnimalDomainModel {
        return AnimalDomainModel(
            id = entity.id,
            name = entity.name,
            isFav = entity.isFav,
            habitat = entity.habitat,
            image_link = entity.imageLink
        )
    }

    override fun mapToEntity(model: AnimalDomainModel): AnimalUIModel {
        return AnimalUIModel(
            id = model.id ?: 0,
            name = model.name ?: BLANK,
            isFav = model.isFav ?: 0,
            habitat = model.habitat ?: BLANK,
            imageLink = model.image_link ?: BLANK
        )
    }

    fun mapFromEntityList(entities: List<AnimalDomainModel>): List<AnimalUIModel> {
        return entities.map { mapToEntity(it) }
    }
}