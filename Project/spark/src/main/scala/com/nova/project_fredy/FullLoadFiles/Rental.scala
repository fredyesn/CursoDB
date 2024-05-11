package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object Rental {
  val file = "rental.csv"

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
