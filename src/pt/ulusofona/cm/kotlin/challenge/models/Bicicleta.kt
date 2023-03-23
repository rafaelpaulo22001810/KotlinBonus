package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel

class Bicicleta(identificador: String): Veiculo(identificador), Movimentavel {
    override fun requerCarta(): Boolean {
        return false
    }
}