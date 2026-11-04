package com.nova.project_fredy

import org.apache.spark.sql.types._
import org.apache.spark.implicits._
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.{col, collect_list, concat_ws, regexp_replace, when}

object ejemplos {


  val customerDfSchema = StructType(Array(
    StructField("address_id",IntegerType,true),
    StructField("birth_country",StringType,true),
    StructField("birthdate",DateType,true),
    StructField("customer_id",IntegerType,true),
    StructField("demographics",StructType(Array(
      StructField("buy_potential",StringType,true),
      StructField("credit_rating",StringType,true),
      StructField("education_status",StringType,true),
      StructField("income_range",ArrayType(IntegerType,true),true),
      StructField("purchase_estimate",IntegerType,true),
      StructField("vehicle_count",IntegerType,true))),true),
    StructField("email_address",StringType,true),
    StructField("firstname",StringType,true),
    StructField("gender",StringType,true),
    StructField("is_preffered_customer",StringType,true),
    StructField("lastname",StringType,true),
    StructField("salutation",StringType,true)
  ))


  val expData = Seq(
    ("PHI", "202502", "S500", "S500", "cto1", 200.3),
    ("PHI", "202501", "S500", "", "cto2", 1500.0),
    ("PHI", "202501", "S500", "", "cto3", 300.0),
    ("PHI", "202412", "S601", "S601", "cto4", 300.0),
    ("PHI", "202502", "S601", "", "cto5", 4200.0)
  )

  val dfSaldos = expData.toDF("unidad", "mesano", "perimetro", "perimetro1", "contrato", "saldo")

  dfSaldos.agg(
    regexp_replace(regexp_replace(concat_ws(",", collect_list(col("perimetro1"))), ",+", ","), ",$", "").as("ex")
  ).show()

  def regexp_replace_1(colIni: Column, regex: String, rep: String): Column = {
    val colRed = regexp_replace(colIni, regex, rep)

    when(colRed.equalTo(colIni), colIni).otherwise(regexp_replace_1(colRed, regex: String, rep: String))
  }

  regexp_replace_1(colRed, regex: String, rep: String)

  dfIni.printSchema

  val dfAgrupPer = dfSaldos
    .groupBy(
      col("unidad"),
      col("mesano"),
      col("perimetro")
    )
    .agg(
      sum(col("saldo")).as("saldo_per")
    )


  val dfSaldos2 = dfSaldos.alias("A").join(
      dfAgrupPer.alias("B"),
      col("A.unidad").equalTo(col("B.unidad"))
        .and(col("A.mesano").equalTo(col("B.mesano")))
        && col("A.mesano") === col("B.mesano"), "left"
    )
    .select(
      col("A.*"),
      col("B.saldo_per")
    )


  import org.apache.spark.sql.expressions.Window
  import org.apache.spark.sql.functions.sum

  val window = Window
    .partitionBy("unidad", "mesano", "perimetro")

  val dfSaldos1 = dfSaldos
    .withColumn("saldo_per", sum(col("saldo")).over(window))


  import org.apache.spark.sql.expressions.Window
  import org.apache.spark.sql.functions.sum

  val window = Window
    .partitionBy("unidad", "mesano")
    .orderBy(col("perimetro").desc)
    .rowsBetween(Window.unboundedPreceding, Window.currentRow)

  val dfSaldos3 = dfSaldos
    .withColumn("orden", lit(1))
    .withColumn("orden", sum(col("orden")).over(window))


  dfSaldos1.explain("extended")


  val arrAnimales: Array[String] = Array("perro", "gato", "conejo", "paloma")

  val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))

  fruit.head.codePoints()
  fruit(1).codePointAt(0)
  fruit.toIterable
  fruit()

  val arr2: Seq[Any] = Seq("1", 0)


  import scala.collection.immutable.List
  import scala.collection.mutable.Seq


}
