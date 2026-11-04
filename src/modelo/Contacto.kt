package modelo

data class Contacto(val nombre:String ,val apellido:String,val telefono:String,val email:String)


val contactoAgenda=mutableListOf<Contacto>() // Creamos una lista mutable de contacto

