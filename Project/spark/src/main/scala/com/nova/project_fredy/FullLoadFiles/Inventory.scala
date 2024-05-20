package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object Inventory extends File {
  val name = "Inventory"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("inventory_id",IntegerType,nullable = true),
    StructField("film_id",IntegerType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
