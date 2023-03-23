package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.time.LocalDateTime
import java.time.Period

class Pessoa(
    var nome: String,
    var dataDeNascimento: LocalDateTime,
    var posicao: Posicao = Posicao(),
    var carta: Carta? = null,
    var veiculos: MutableList<Veiculo> = mutableListOf()
) : Movimentavel {


    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String): Veiculo? {
        for (veiculo in veiculos) {
            if (veiculo.identificador.equals(identificador)) {
                val index = veiculos.indexOf(veiculo)
                return veiculos.get(index)
            }
        }
        return null
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        if (veiculo != null) {
            veiculo.dataDeAquisicao = LocalDateTime.now()
            comprador.comprarVeiculo(veiculo)
        }
    }

    fun moverVeiculo(identificador: String, x: Int, y: Int) {
        if (carta ==  null){
            throw PessoaSemCartaException("${nome} não tem carta para conduzir o veículo indicado")
        }
        for (veiculo in veiculos) {
            if (veiculo.identificador.equals(identificador)) {
                val index = veiculos.indexOf(veiculo)
                veiculos.get(index).moverPara(x,y)
            }
        }
    }

    fun temCarta(): Boolean {
        if (carta == null) {
            return false
        }
        return true
    }

    fun tirarCarta() {
        if (Period.between(dataDeNascimento.toLocalDate(), LocalDateTime.now().toLocalDate()).years < 18){
            throw MenorDeIdadeException()
        }
        carta = Carta()
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.x = x
        posicao.y = y
    }
}