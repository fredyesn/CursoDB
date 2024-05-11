package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object Inventory {
  val file = "inventory.csv"

  val schema: StructType = StructType( Array(
    StructField("inventory_id",IntegerType,nullable = true),
    StructField("film_id",IntegerType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
