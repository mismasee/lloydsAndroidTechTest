package com.lloydtechassignment.data.mapper


import com.lloydtechassignment.data.source.local.AnimalEntity
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.util.BLANK


/**
 * A Child class of Entity Mapper
 * which will be responsible for converting entity in to model and model in to entity
 * */
class AnimalEntityMapper() : EntityMapper<AnimalEntity, AnimalsRespItem> {


    override fun mapFromEntity(entity: AnimalEntity): AnimalsRespItem {
        return AnimalsRespItem(
            id = entity.id,
            name = entity.name,
            isFav = entity.isFav,
            habitat = entity.habitat,
            image_link = entity.imageLink
        )
    }


    override fun mapToEntity(model: AnimalsRespItem): AnimalEntity {
        return AnimalEntity(
            id = model.id ?: 0,
            name = model.name ?: BLANK,
            isFav = model.isFav ?: 0,
            habitat = model.habitat ?: BLANK,
            imageLink = model.image_link ?: BLANK
        )
    }

    fun mapFromEntityList(entities: List<AnimalEntity>): List<AnimalsRespItem> {
        return entities.map { mapFromEntity(it) }
    }

}