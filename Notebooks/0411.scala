// Databricks notebook source


// COMMAND ----------

// TODO: Revisar si esto funciona
// Create catalog volume
val catalogVolume = Volume
  .newBuilder()
  .setName("CatalogVolume")
  .setDescription("Volume for catalog data")
  .setCapacity(1000) // in GB
  .setLocation("path/to/catalog")
  .build()

// Save catalog volume
volumeManager.save(catalogVolume)

// COMMAND ----------

// MAGIC %sql
// MAGIC /*Crear un volumen*/
// MAGIC CREATE CATALOG volumes;
// MAGIC CREATE SCHEMA volumes.csv;
// MAGIC CREATE VOLUME volumes.csv.files;

// COMMAND ----------

val csvFileReadOptions = Map("header" -> "true"
             ,"delimiter" -> "|"
             ,"inferSchema" -> "true")

val df_actor = spark.read
  .options(csvFileReadOptions)
  .csv("/Volumes/volumes/csv/files/actor.csv")

df_actor.show()
df_actor.printSchema()
