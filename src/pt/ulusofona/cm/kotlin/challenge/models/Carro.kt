package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

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

}