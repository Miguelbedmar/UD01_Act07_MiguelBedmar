package controlador
import modelo.*
import java.io.IOException
import java.lang.Integer.parseInt

class FuncionesContacto(private val agenda:Agenda= Agenda()) {
    val ROJO="\u001B[31m"
    val RESET = "\u001B[0m"
    var controladorBucle=false
    val expresionTelefono="^(?:\\\\+34|0034|34)?[6-9]\\\\d{8}\$".toRegex() // Con la funcion toRegex convertimos el string a una expresion regular
    val expresionEmail="\"^[\\\\w\\\\.-]+@[\\\\w-]+\\\\.[\\\\w-]+\$\"".toRegex()

    fun anadirContacto(contacto: Contacto){
     while (!controladorBucle) {
         try {
             /**Esta función incorpora la función require  una herramienta
              * muy útil incorporada en las librerias estandars de kotlin
              * Require es una función que evaluea una condicion boolean
              * si devuelve true el codigo  continua , en cambio si devuelve
              * false lanza una excepcionde tipo illegalAccesError
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
             val telefonoConvertido= parseInt(telefono) // Parseamos telefono de string a int
             if(telefonoConvertido!=9){ // Comprobamos la longitud
                 println("${ROJO} El telefono no es válido$RESET")
                 continue
             }
             //pedimos el apellido ha añadir
             println("Ingrese el apellido a añadir")
             val apellido=readlnOrNull()?.trim() ?: return
             require( apellido.isNotEmpty()){"${ROJO} ERROR! el apellido no puede estar vacio $RESET"}


             println("Ingrese el email (Ej:ejemplo123@gmail.com)")
             val email = readlnOrNull()?.trim()?:return
             require(email.isNotEmpty()&&expresionEmail.matches(email)){"${ROJO} ERROR! Email no válido $RESET"}

            agenda.contactoAgenda.add(Contacto(nombre,apellido,telefono,email))
             println("Se ha añadido el contacto correctamente")
             controladorBucle = true

         } catch (e: IllegalAccessError) {
             println("${ROJO} ERROR!  valor no válido!$RESET")

         } catch (e: IOException) {
             println("${ROJO}ERROR! has accedido fuera del programa $RESET")
             controladorBucle=true
         }
     }


    }

}