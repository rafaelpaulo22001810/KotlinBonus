package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.util.*

class Pessoa(
    var nome: String,
    var dataDeNascimento: Date,
    var posicao: Posicao = Posicao(),
    var carta: Carta? = null,
    var veiculos: MutableList<Veiculo> = mutableListOf()
) : Movimentavel {


    fun comprarVeiculo(veiculo: Veiculo) {
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String): Veiculo {
        for (veiculo in veiculos) {
            if (veiculo.identificador.equals(identificador)) {
                val index = veiculos.indexOf(veiculo)
                return veiculos.get(index)
            }
        }
        throw VeiculoNaoEncontradoException()
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        veiculo.dataDeAquisicao = Date()
        comprador.comprarVeiculo(veiculo)
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        if (carta ==  null){
            throw PessoaSemCartaException("${nome} não tem carta para conduzir o veículo indicado")
        }

        for (veiculo in veiculos) {
            if (veiculo.identificador.equals(identificador)) {
                if ((veiculo.requerCarta() && temCarta()) || !veiculo.requerCarta()){
                    val index = veiculos.indexOf(veiculo)
                    veiculos.get(index).moverPara(x,y)
                }else{
                    throw AlterarPosicaoException()
                }
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
        val atual = Calendar.getInstance()
        val nascimento = Calendar.getInstance()
        nascimento.time = dataDeNascimento
        nascimento.add(Calendar.YEAR, 18)

        if (atual.after(dataDeNascimento)){
            carta = Carta()
        }
        throw MenorDeIdadeException()
    }

    override fun moverPara(x: Int, y: Int) {
        if (posicao.x == x && posicao.y == y){
            throw AlterarPosicaoException()
        }
        posicao = Posicao(x, y)
    }

    override fun toString(): String {
        return "Pessoa | $nome | $dataDeNascimento | Posicao | x:${posicao.x} | y:${posicao.y}"
    }
}