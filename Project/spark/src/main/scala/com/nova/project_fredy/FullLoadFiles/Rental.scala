package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object Rental extends File {
  val name = "Rental"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("rental_id",IntegerType,nullable = true),
    StructField("rental_date",TimestampType,nullable = true),
    StructField("inventory_id",IntegerType,nullable = true),
    StructField("customer_id",IntegerType,nullable = true),
    StructField("return_date",TimestampType,nullable = true),
    StructField("staff_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
