package com.example.nobroker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoBroker")
class NoBrokerDataEntity(@ColumnInfo (name = "Image") var image :String?,
                         @ColumnInfo (name = "Title") var title :String?,
                         @ColumnInfo (name = "SubTitle") var subTitle :String?){

    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "Id") var id:Int? = null


}