package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.util.*

abstract class Veiculo(
    var identificador: String,
) : Movimentavel {
    var posicao: Posicao = Posicao()
    var dataDeAquisicao: Date = Date()

    abstract fun requerCarta(): Boolean

    abstract override fun moverPara(x: Int, y: Int)
}
