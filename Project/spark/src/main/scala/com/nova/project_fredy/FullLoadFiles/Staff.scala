package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Staff extends File {
  val name = "Staff"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("staff_id",IntegerType,nullable = true),
    StructField("first_name",StringType,nullable = true),
    StructField("last_name",StringType,nullable = true),
    StructField("address_id",StringType,nullable = true),
    StructField("email",StringType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("active",StringType,nullable = true),
    StructField("username",StringType,nullable = true),
    StructField("password",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true),
    StructField("picture",StringType,nullable = true)
  ))
}
