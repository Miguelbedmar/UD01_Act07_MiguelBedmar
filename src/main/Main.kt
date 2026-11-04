package main
import java.util.Scanner
import java.util.Locale

val sc=  Scanner(System.`in`).useLocale(Locale.US)
val ROJO="\u001B[31m" // Creamos una variable con el nombre del color  para perzonalizar los mensajes de errores
val RESET = "\u001B[0m" // Reseteamos el color al terminar el mensaje de error




fun menuPrincipal(){
    var salir=false
    do {
        try {
            println("<=====AGENDA CONTACTOS========>\n")
            println("<=======1. Consultar saldo========>")
            println("<=======2. Ingresar dinero====>")
            println("<=======3. Retirar dinero========>")
            println("<=======4. Salir==>")
            println("<=======Seleccione una opción==========>")
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