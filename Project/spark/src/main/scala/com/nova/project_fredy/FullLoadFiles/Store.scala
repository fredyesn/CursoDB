package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Store {
  val file = "store.csv"

  val schema: StructType = StructType( Array(
    StructField("store_id",IntegerType,nullable = true),
    StructField("manager_staff_id",IntegerType,nullable = true),
    StructField("address_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))
}
