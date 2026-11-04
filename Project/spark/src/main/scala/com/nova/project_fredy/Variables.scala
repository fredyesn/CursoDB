package com.nova.project_fredy

import com.nova.project_fredy.FullLoadFiles._

object Variables {
  val full_load_obj_lst = Seq(
    Actor,
    Address,
    Category,
    City,
    Country,
    Customer,
    Film,
    FilmActor,
    FilmCategory,
    Inventory,
    Languaje,
    Payment,
    Rental,
    Staff,
    Store
  )

  val table = "exp_table"


  val businessDB = "bu_exproject"
  val unidad = "PHI"

  val query = s"SELECT * FROM $businessDB.${Variables.table} where unidad = '$unidad '"




}
