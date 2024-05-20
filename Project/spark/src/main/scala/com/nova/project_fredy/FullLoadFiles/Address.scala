package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructField, StructType, TimestampType}

object Address extends File {
  val name = "Address"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("address_id",IntegerType,nullable = true),
    StructField("address",StringType,nullable = true),
    StructField("address2",StringType,nullable = true),
    StructField("district",StringType,nullable = true),
    StructField("city_id",IntegerType,nullable = true),
    StructField("postal_code",IntegerType,nullable = true),
    StructField("phone",LongType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
