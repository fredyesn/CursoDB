package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types.{IntegerType, StructField, StructType, TimestampType}

object Inventory extends File {
  val name = "inventory"
  val flg_part: Boolean = false

  val schema: StructType = StructType( Array(
    StructField("inventory_id",IntegerType,nullable = true),
    StructField("film_id",IntegerType,nullable = true),
    StructField("store_id",IntegerType,nullable = true),
    StructField("last_update",TimestampType,nullable = true)
  ))

  val cmp_select: String =
    s"""
       | inventory_id INTEGER,
       | film_id INTEGER,
       | store_id INTEGER,
       | last_update STRING
       |""".stripMargin
}
