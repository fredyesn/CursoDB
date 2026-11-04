package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Actor extends File {
  val name = "Actor"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false


  val fl_schema: StructType = StructType( Array(
    StructField("actor_id",IntegerType,nullable = false),
    StructField("first_name",StringType,nullable = true),
    StructField("last_name",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
