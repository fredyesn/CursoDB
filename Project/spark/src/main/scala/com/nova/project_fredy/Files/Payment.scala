package com.nova.project_fredy.Files

import org.apache.spark.sql.types.{DecimalType, IntegerType, StructField, StructType, TimestampType}

object Payment {
  val file = "payment.csv"

  val schema: StructType = StructType( Array(
    StructField("payment_id",IntegerType,nullable = true),
    StructField("customer_id",IntegerType,nullable = true),
    StructField("staff_id",IntegerType,nullable = true),
    StructField("rental_id",IntegerType,nullable = true),
    StructField("amount",DecimalType(10,2),nullable = true),
    StructField("payment_date",TimestampType,nullable = true)
  ))
}
