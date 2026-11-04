package com.nova.project_fredy.FullLoadFiles

import org.apache.spark.sql.types._

trait File {
  val name: String
  val file: String
  val land_table: String
  val business_table: String
  val flg_part: Boolean
  val fl_schema: StructType

  def file(name: String): String = s"$name.csv".toLowerCase
  def land_table(name: String): String = s"land_proj.$name".toLowerCase
  def business_table(name: String): String = s"bu_proj.$name".toLowerCase
}
