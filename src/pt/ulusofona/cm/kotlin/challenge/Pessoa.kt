package pt.ulusofona.cm.kotlin.challenge

import java.util.*

class Pessoa {
    lateinit var carta: Carta
    lateinit var posicao: Posicao
    val veiculos = mutableListOf<Veiculo>()

    class Pessoa constructor(var nome: String, var dataDeNascimento: Date)

    fun comprarVeiculo(veiculo: Veiculo){
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String): Veiculo? {
        for (veiculo in veiculos){
            if (veiculo.identificador.equals(identificador)){
                var index = veiculos.indexOf(veiculo)
                return veiculos.get(index)
            }
        }
        return null
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {

    }

    fun moverVeiculo(identificador: String, x: Int, y: Int){

    }

    fun temCarta(): Boolean {
        if (carta == null){
            return false
        }
        return true
    }

    fun tirarCarta(){

    }
}