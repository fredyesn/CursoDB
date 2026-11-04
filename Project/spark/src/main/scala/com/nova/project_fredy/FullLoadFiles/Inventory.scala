package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Inventory extends File {
  val name = "Inventory"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false

  val fl_schema: StructType = StructType( Array(
    StructField("inventory_id",IntegerType,nullable = false),
    StructField("film_id",IntegerType,nullable = false),
    StructField("store_id",IntegerType,nullable = false),
    StructField("last_update",TimestampType,nullable = true),
    StructField("data_date_part",StringType,nullable = false)
  ))
}
