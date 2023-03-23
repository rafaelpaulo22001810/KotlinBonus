package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.time.LocalDateTime
import java.util.*

abstract class Veiculo(
    var identificador: String,
) : Movimentavel {
    var posicao: Posicao = Posicao()
    var dataDeAquisicao: Date = Date()

    abstract fun requerCarta(): Boolean

    override fun moverPara(x: Int, y: Int) {
        if (posicao.x == x && posicao.y == y){
            throw AlterarPosicaoException()
        }
        posicao = Posicao(x, y)
    }
}
