package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType, TimestampType}

object Customer extends File {
  val name = "Customer"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("customer_id",IntegerType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("first_name",StringType,nullable = true),
    StructField("last_name",StringType,nullable = true),
    StructField("email",StringType,nullable = true),
    StructField("address_id",IntegerType,nullable = true),
    StructField("activebool",StringType,nullable = true),
    StructField("create_date",DateType,nullable = true),
    StructField("last_update",TimestampType,nullable = true),
    StructField("active",IntegerType,nullable = true)
  ))
}
