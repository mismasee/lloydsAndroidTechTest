package com.lloydtechassignment.data.mapper.animalmapper


import com.lloydtechassignment.data.mapper.EntityMapper
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.data.source.local.AnimalEntity


/**
 * A Child class of Entity Mapper
 * which will be responsible for converting entity in to model and model in to entity
 * */
class AnimalEntityMapper : EntityMapper<AnimalEntity, AnimalUIModel> {

    override fun mapFromEntity(entity: AnimalEntity): AnimalUIModel {
        return AnimalUIModel(
            id = entity.id,
            name = entity.name,
            isFav = entity.isFav,
            habitat = entity.habitat,
            imageLink = entity.imageLink
        )
    }

    override fun mapToEntity(model: AnimalUIModel): AnimalEntity {
        return AnimalEntity(
            id = model.id,
            name = model.name,
            isFav = model.isFav,
            habitat = model.habitat,
            imageLink = model.imageLink
        )
    }

    fun mapFromEntityList(entities: List<AnimalEntity>): List<AnimalUIModel> {
        return entities.map { mapFromEntity(it) }
    }

}