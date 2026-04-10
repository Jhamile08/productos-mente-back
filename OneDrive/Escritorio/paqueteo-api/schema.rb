# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# This file is the source Rails uses to define your schema when running `bin/rails
# db:schema:load`. When creating a new database, `bin/rails db:schema:load` tends to
# be faster and is potentially less error prone than running all of your
# migrations from scratch. Old migrations may fail to apply correctly if those
# migrations use external dependencies or application code.
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema[7.2].define(version: 2026_02_07_143829) do
  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "blocked_ips", force: :cascade do |t|
    t.string "ip"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "clientes", force: :cascade do |t|
    t.string "nombre"
    t.bigint "user_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_clientes_on_user_id"
  end

  create_table "contabilidads", force: :cascade do |t|
    t.string "titulo"
    t.date "fecha_inicio"
    t.date "fecha_final"
    t.integer "total_tipo1"
    t.integer "total_tipo2"
    t.integer "total_general"
    t.bigint "user_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.boolean "estado"
    t.integer "cliente_id"
    t.index ["user_id"], name: "index_contabilidads_on_user_id"
  end

  create_table "devolucions", force: :cascade do |t|
    t.datetime "fecha"
    t.bigint "paquete_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.integer "tipo"
    t.index ["paquete_id"], name: "index_devolucions_on_paquete_id"
  end

  create_table "mensajeros", force: :cascade do |t|
    t.string "nombre"
    t.string "celular"
    t.bigint "user_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_mensajeros_on_user_id"
  end

  create_table "municipios", force: :cascade do |t|
    t.string "nombre"
    t.string "tipo"
    t.bigint "user_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_municipios_on_user_id"
  end

  create_table "operacions", force: :cascade do |t|
    t.string "nombre"
    t.bigint "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_operacions_on_user_id"
  end

  create_table "organizations", force: :cascade do |t|
    t.string "name"
    t.text "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "paquetes", force: :cascade do |t|
    t.date "date"
    t.string "codigo"
    t.bigint "municipio_id", null: false
    t.bigint "mensajero_id", null: false
    t.bigint "operacion_id"
    t.bigint "user_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.bigint "zona_id"
    t.bigint "cliente_id"
    t.decimal "precio"
    t.string "estado"
    t.index ["cliente_id"], name: "index_paquetes_on_cliente_id"
    t.index ["mensajero_id"], name: "index_paquetes_on_mensajero_id"
    t.index ["municipio_id"], name: "index_paquetes_on_municipio_id"
    t.index ["operacion_id"], name: "index_paquetes_on_operacion_id"
    t.index ["user_id"], name: "index_paquetes_on_user_id"
    t.index ["zona_id"], name: "index_paquetes_on_zona_id"
  end

  create_table "users", force: :cascade do |t|
    t.string "email", default: "", null: false
    t.string "encrypted_password", default: "", null: false
    t.string "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.bigint "organization_id"
    t.string "name"
    t.string "profile_photo_url"
    t.index ["email"], name: "index_users_on_email", unique: true
    t.index ["organization_id"], name: "index_users_on_organization_id"
    t.index ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true
  end

  create_table "zonas", force: :cascade do |t|
    t.string "nombre"
    t.bigint "user_id", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_zonas_on_user_id"
  end

  add_foreign_key "clientes", "users"
  add_foreign_key "contabilidads", "users"
  add_foreign_key "devolucions", "paquetes", on_delete: :cascade
  add_foreign_key "mensajeros", "users"
  add_foreign_key "municipios", "users"
  add_foreign_key "operacions", "users"
  add_foreign_key "paquetes", "clientes"
  add_foreign_key "paquetes", "mensajeros"
  add_foreign_key "paquetes", "municipios"
  add_foreign_key "paquetes", "operacions"
  add_foreign_key "paquetes", "users"
  add_foreign_key "paquetes", "zonas"
  add_foreign_key "users", "organizations"
  add_foreign_key "zonas", "users"
end
