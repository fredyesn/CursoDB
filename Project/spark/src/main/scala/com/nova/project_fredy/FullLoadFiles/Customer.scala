package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType, TimestampType}

object Customer {
  val file = "customer.csv"

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

  val cmp_select: String =
    s"""
       | customer_id INTEGER,
       | store_id INTEGER,
       | first_name STRING,
       | last_name STRING,
       | email STRING,
       | address_id INTEGER,
       | activebool STRING,
       | create_date STRING,
       | last_update STRING,
       | active STRING
       |""".stripMargin
}
