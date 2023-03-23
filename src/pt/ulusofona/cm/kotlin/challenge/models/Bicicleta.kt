package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Bicicleta(identificador: String): Veiculo(identificador), Movimentavel {
    override fun requerCarta(): Boolean {
        return false
    }

    override fun toString(): String {
        val aquisicao = LocalDate.ofInstant(dataDeAquisicao.toInstant(), ZoneId.systemDefault()).format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        return "Bicicleta | ${identificador} | $aquisicao | Posicao | x:${posicao.x} | y:${posicao.y}"
    }
}