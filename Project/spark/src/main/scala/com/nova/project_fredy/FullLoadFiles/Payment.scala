package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DecimalType, IntegerType, StructField, StructType, TimestampType}

object Payment extends File {
  val name = "Payment"
  val file: String = s"$name.csv".toLowerCase
  val land_table: String = s"land_proj.$name".toLowerCase
  val business_table: String = s"bu_proj.$name".toLowerCase
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("payment_id",IntegerType,nullable = true),
    StructField("customer_id",IntegerType,nullable = true),
    StructField("staff_id",IntegerType,nullable = true),
    StructField("rental_id",IntegerType,nullable = true),
    StructField("amount",DecimalType(10,2),nullable = true),
    StructField("payment_date",TimestampType,nullable = true)
  ))
}
