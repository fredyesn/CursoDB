package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DecimalType, IntegerType, StructField, StructType, TimestampType}

object Payment {
  val file = "payment.csv"

  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("payment_id",IntegerType,nullable = true),
    StructField("customer_id",IntegerType,nullable = true),
    StructField("staff_id",IntegerType,nullable = true),
    StructField("rental_id",IntegerType,nullable = true),
    StructField("amount",DecimalType(10,2),nullable = true),
    StructField("payment_date",TimestampType,nullable = true)
  ))

  val cmp_select =
    s"""
       | payment_id INTEGER,
       | customer_id INTEGER,
       | staff_id INTEGER,
       | rental_id INTEGER,
       | amount DECIMAL(10,2),
       | payment_date STRING
       |""".stripMargin
}
