package com.develo.ff_arsimulator.data.source.local.entity.relations

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.develo.ff_arsimulator.data.source.local.entity.LabEntity
import com.develo.ff_arsimulator.data.source.local.entity.ModuleEntity
import com.develo.ff_arsimulator.data.source.local.entity.TheoryEntity

data class ModuleWithTheoryWithLab (
    @Embedded val moduleEntity: ModuleEntity,
    @Relation(parentColumn = "id", entityColumn = "moduleId")
    val theory: List<TheoryEntity>,
    @Relation(parentColumn = "id", entityColumn = "moduleId")
    val lab: List<LabEntity>,
)