package main
import java.util.Scanner
import java.util.Locale

val sc=  Scanner(System.`in`).useLocale(Locale.US)
val ROJO="\u001B[31m" // Creamos una variable con el nombre del color  para perzonalizar los mensajes de errores
val RESET = "\u001B[0m" // Reseteamos el color al terminar el mensaje de error

fun String.formateoMenuPrincipalContacto(anchoTotal:Int):String{ // Creamos una función extension
    val ancho= anchoTotal-2 // restamos dos al ancho total para centrarlo
    if(this.length>=ancho)return "<$this>" // comprobamos si la longitud es superior  a la cadena de caracteres

    val textoEspacio= if(this.isEmpty())"" else "$this" // Comprobamos si el texto está vacio

    val relleno= ancho- textoEspacio.length //Calculamos la cantidad de relleno

    val izq= relleno/2
    val de=relleno-izq
return "<"+"=".repeat(izq)+textoEspacio+"=".repeat(de)+">"
}



fun menuPrincipal(){
    var salir=false
    val ancho=40 // Ancho predeterminado para rellenar con <==>
    do {
        try {
            println("AGENDA CONTACTOS".formateoMenuPrincipalContacto(ancho))
            println("0. Salir de la Agenda".formateoMenuPrincipalContacto(ancho)) 
            println("1. Añadir contacto".formateoMenuPrincipalContacto(ancho))
            println("2. Listar contacto".formateoMenuPrincipalContacto(ancho))
            println("3. Buscar contacto".formateoMenuPrincipalContacto(ancho))
            println("4. Eliminar contacto".formateoMenuPrincipalContacto(ancho))
            println("Seleccione una opción".formateoMenuPrincipalContacto(ancho))
            if(!sc.hasNextInt()) {
                sc.next()
                println("Error: La opción seleccionada no está disponible")
                continue
            }
            val opcion = sc.nextInt()
            when(opcion){
                1-> println()
                2->{
                    println()
                }
                3-> {
                    println()
                }
                4-> {
                    println()
                }
                0->{
                    salir=true
                    println("Hasta luego")
                    println("Has salido del programa....")
                }

                else -> println("Opción introducida no disponible.")


            }


        }catch (ex: Exception){
            println("${ROJO} ERROR! Valor inválido detectado $RESET"+ex.message)
        }catch ( e:NoSuchElementException){
            println("${ROJO} ERROR! ERROR!: Has salido fuera del fichero $RESET "+e.message)
            salir=true
        }

    }while (!salir)
}
fun main() { 
menuPrincipal()
}