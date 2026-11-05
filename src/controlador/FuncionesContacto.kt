package controlador
import modelo.*
import java.io.IOException
import java.lang.Integer.parseInt

class FuncionesContacto(private val agenda:Agenda= Agenda()) {
    val ROJO="\u001B[31m"
    val RESET = "\u001B[0m"
    var controladorBucle=false
    val expresionTelefono="^(?:\\\\+34|0034|34)?[6-9]\\\\d{8}\$".toRegex() // Con la funcion toRegex convertimos el string a una expresion regular
    val expresionEmail=""
    fun anadirContacto(contacto: Contacto){
     while (!controladorBucle) {
         try {
             /**Esta función incorpora la función require  una herramienta
              * muy útil incorporada en las librerias estandars de kotlin
              * Require es una función que evaluea una condicion boolean
              * si devuelve true el codigo  continua , en cambio si devuelve
              * false lanza una excepcionde tipo illegalAccesError
              * require tambien nos permite personalizar mensajes de errores **/
             println("Ingrese el nombre a añadir:")
             val nombre = readlnOrNull()?.trim() ?: return
             require(nombre.isNotEmpty()){"${ROJO} ERROR! El nombre no puede estar vacio $RESET"} // Comprobamos
             println("Ingrese el numero de telefono")

             println("Ingrese el apellido a añadir")
             val apellido=readlnOrNull()?.trim() ?: return
             require( apellido.isNotEmpty()){"${ROJO} ERROR! el apellido no puede estar vacio $RESET"}
             val telefono = readlnOrNull()?.trim()?: return
             require(telefono.isNotEmpty()&&expresionTelefono.matches(telefono)&&telefono.all { it.isDigit() }){
                 "${ROJO} ERROR!  el telefono debe contener digitos $RESET"
             }
             val telefonoConvertido= parseInt(telefono) // Parseamos telefono de string a int

             if(telefonoConvertido!=9){
                 println("${ROJO} El telefono no tiene la longitud  valida $RESET")
                  continue
             }



             println("Ingrese el email (Opcional)")
             val email = readlnOrNull()?.trim().orEmpty()



         } catch (e: IllegalAccessError) {
             println("${ROJO} ERROR!  valor no válido!$RESET")

         } catch (e: IOException) {
             println("${ROJO}ERROR! has accedido fuera del programa $RESET")
             controladorBucle=true
         }
     }


    }

}