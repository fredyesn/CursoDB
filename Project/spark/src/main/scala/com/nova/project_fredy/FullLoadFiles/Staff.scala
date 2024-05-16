package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

object Staff {
  val file = "staff.csv"

  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("staff_id",IntegerType,nullable = true),
    StructField("first_name",StringType,nullable = true),
    StructField("last_name",StringType,nullable = true),
    StructField("address_id",StringType,nullable = true),
    StructField("email",StringType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("active",StringType,nullable = true),
    StructField("username",StringType,nullable = true),
    StructField("password",StringType,nullable = true),
    StructField("last_update",TimestampType,nullable = true),
    StructField("picture",StringType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | staff_id INTEGER,
       | first_name STRING,
       | last_name STRING,
       | address_id INTEGER,
       | email STRING,
       | store_id INTEGER,
       | active STRING,
       | username STRING,
       | password STRING,
       | last_update STRING,
       | picture STRING
       |""".stripMargin
}
