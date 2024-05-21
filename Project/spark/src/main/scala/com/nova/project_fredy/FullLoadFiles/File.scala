package com.nova.project_fredy.FullLoadFiles

import com.nova.project_fredy.FullLoadFiles.Actor.name
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, TimestampType}

trait File {
  val name: String
  val file: String
  val land_table: String
  val business_table: String
  val flg_part: Boolean
  val schema: StructType

  def file(name: String): String = s"$name.csv".toLowerCase
  def land_table(name: String): String = s"land_proj.$name".toLowerCase
  def business_table(name: String): String = s"bu_proj.$name".toLowerCase
}
