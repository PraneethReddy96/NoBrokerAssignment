package com.example.nobroker.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/*

An entity refers to a table, here a table with a predefined structure is created.

 */

@Entity(tableName = "NoBroker")
class NoBrokerDataEntity(
    @ColumnInfo(name = "Image") var image: String?,
    @ColumnInfo(name = "Title") var title: String?,
    @ColumnInfo(name = "SubTitle") var subTitle: String?,
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null


}