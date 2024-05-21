package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType, TimestampType}

object Customer extends File {
  val name = "Customer"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
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
