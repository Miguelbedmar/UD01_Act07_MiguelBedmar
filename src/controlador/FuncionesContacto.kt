package controlador
import modelo.*
import java.io.IOException
import java.lang.Integer.parseInt
import kotlin.collections.sortBy
import kotlin.system.exitProcess

class FuncionesContacto(private val agenda:Agenda= Agenda()) {
    // Colores
    val ROJO="\u001B[31m"
    val RESET = "\u001B[0m"
    //constantes expresiones regulares
    val expresionTelefono="^(?:\\+34|0034|34)?[6-9]\\d{8}$".toRegex() // Con la funcion toRegex convertimos el string a una expresion regular
    val expresionEmail="^[\\w.-]+@[\\w-]+\\.[\\w-]+$".toRegex()
     // Constantes de mensajeError
     val mensajeError="${ROJO} La agenda no contiene contactos $RESET"
    fun anadirContacto(){
        var controladorBucle=false

        while (!controladorBucle) {
         try {
             /**Esta función incorpora la función require  una herramienta
              * muy útil incorporada en las librerias estandars de kotlin
              * Require es una función que evaluea una condicion boolean
              * si devuelve true el codigo  continua , en cambio si devuelve
              * false lanza una excepcionde tipo illegalArgumentError
              * require tambien nos permite personalizar mensajes de errores **/

             //Pedimos el nombre del contacto a añadir
             println("Ingrese el nombre a añadir:")
             val nombre = readlnOrNull()?.trim() ?: return
             require(nombre.isNotEmpty()){"${ROJO} ERROR! El nombre no puede estar vacio $RESET"} // comprobamos el nombre y en caso de que se cumpla la condicion imprimimos un mensaje personalizado
              // Pedimos el numero de telefono
             println("Ingrese el numero de telefono")
             val telefono = readlnOrNull()?.trim()?: return
             require(telefono.isNotEmpty()&&expresionTelefono.matches(telefono)&&telefono.all { it.isDigit() }){
                 "${ROJO} ERROR!Telefono no válido $RESET"
             }

             //pedimos el apellido ha añadir
             println("Ingrese el apellido a añadir")
             val apellido=readlnOrNull()?.trim() ?: return
             require( apellido.isNotEmpty()){"${ROJO} ERROR! el apellido no puede estar vacio $RESET"}


             println("Ingrese el email (Ej:ejemplo123@gmail.com)")
             val email = readlnOrNull()?.trim()?:return
             require(email.isNotEmpty()&&expresionEmail.matches(email)){"${ROJO} ERROR! Email no válido $RESET"} // Validamos si el email cumple con la expresion regular

             agenda.contactoAgenda.add(Contacto(nombre,apellido,telefono,email)) // Añadimos los datos a la lista de contacto
             agenda.contactoAgenda.sortBy { it.nombre.lowercase()} // he utilizado lowecase porque  a partir de la version 1.5 toLowerCase se convirtio en un metodo obsoleto.

             println("Se ha añadido el contacto correctamente")
             controladorBucle = true

         } catch (e: IllegalArgumentException) {
             println(e.message)

         } catch (e: IOException) {
             println("${ROJO}ERROR! has accedido fuera del programa $RESET")
             controladorBucle=true
         }
     }
    }

    fun mostarListaContacto() {
        if (agenda.contactoAgenda.isEmpty()) { // Compruebo si se encuentra vacía
            println(mensajeError)
            return
        }
        imprimirListaContacto()
    }

    fun buscarContacto(){
        if (agenda.contactoAgenda.isEmpty()) { // Comprobamos que la agenda no este vacia
            println(mensajeError)
            return
        }

        try {
            println("Introduce el nombre que desee buscar")
            val nombreContac = readlnOrNull()?.trim() ?: return // registramos el nombre

            if (nombreContac.isEmpty()) {
                println("${ROJO} ERROR! El nombre no existe entre los contacos $RESET")
                return
            }
            val nombrebuscado = agenda.contactoAgenda.filter {
                "${it.nombre} ${it.apellido}".contains(
                    nombreContac,
                    ignoreCase = true
                )
            } // Filtro por nombre

            if (nombrebuscado.isEmpty()) {
                println("${ROJO}  ERRO! no se han encontrado ningun contacto con ese nombre $RESET")
                return
            } else {
                imprimirListaContacto() // LLamamos a imprimir lista
            }
        }catch (e:IllegalArgumentException){
            println("${ROJO} ERROR!  Valor no válido $RESET")

        }catch (e:IOException){
            println("${ROJO} ERROR! Has accedido más alla del programa $RESET")
            exitProcess(0)
        }
    }
    fun eliminarContacto(){
        if(agenda.contactoAgenda.isNotEmpty()){
            println(mensajeError)
        }
        imprimirListaContacto()
        try {
            println("Itroduce el  indice del contacto  a eliminar")
            val numeroContac = readlnOrNull()?.trim()?.toIntOrNull()

            if (numeroContac == null || numeroContac !in 1..agenda.contactoAgenda.size) {
                println("${ROJO} ERROR! EL contacto no existe  $RESET")
                return
            }
            agenda.contactoAgenda.removeAt(numeroContac - 1)
            println("Agenda eliminada correctamente")
        }catch (e:IllegalArgumentException){
            println("${ROJO} ERROR!  Valor no válido $RESET")
        }catch (e: IOException){
                println("${ROJO} ERROR! Has accedido más alla del programa $RESET")
                exitProcess(0)
        }
        }


   private fun imprimirListaContacto(){ // Esta función imprime la lista de contactos en buscar y listar  para evitar duplicar codigo,
        agenda.contactoAgenda.forEachIndexed { index, contacto ->println("${index+1}.${contacto.nombre}-${contacto.apellido}-${contacto.telefono}-${contacto.email}")  }

    }

}