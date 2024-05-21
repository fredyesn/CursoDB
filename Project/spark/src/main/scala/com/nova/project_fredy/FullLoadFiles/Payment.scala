package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DecimalType, IntegerType, StructField, StructType, TimestampType}

object Payment extends File {
  val name = "Payment"
  val file: String = file(name)
  val land_table: String = land_table(name)
  val business_table: String = business_table(name)
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
