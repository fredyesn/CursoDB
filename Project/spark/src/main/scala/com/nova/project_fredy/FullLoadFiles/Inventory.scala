package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object Inventory extends File {
  val name = "Inventory"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("inventory_id",IntegerType,nullable = true),
    StructField("film_id",IntegerType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
