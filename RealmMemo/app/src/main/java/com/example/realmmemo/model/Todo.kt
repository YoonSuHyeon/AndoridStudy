package com.example.realmmemo.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Todo (
    @PrimaryKey var id: Long =0,
    var itemName:String? =null


): RealmObject()