package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Carro(identificador: String, var motor: Motor): Veiculo(identificador), Ligavel{
    override fun ligar() {
        motor.ligar()
    }

    override fun desligar() {
        motor.desligar()
    }

    override fun estaLigado(): Boolean {
        return motor.estaLigado()
    }

    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {
        if (estaLigado()){
            if (posicao.x == x && posicao.y == y){
                throw AlterarPosicaoException()
            }
            posicao = Posicao(x, y)
            desligar()
        }
        return
    }

    override fun toString(): String {
        val aquisicao = LocalDate.ofInstant(dataDeAquisicao.toInstant(), ZoneId.systemDefault()).format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        return "Carro | ${identificador} | $aquisicao | Posicao | x:${posicao.x} | y:${posicao.y}"
    }

}