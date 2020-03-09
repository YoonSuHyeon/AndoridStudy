package com.example.realmexample

import io.realm.RealmObject

open class MemoVO(var title: String? = null,
                  var content: String? = null) : RealmObject()
