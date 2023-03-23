package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.time.*
import java.time.format.DateTimeFormatter
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
            if (veiculo.identificador == identificador) {
                val index = veiculos.indexOf(veiculo)
                return veiculos[index]
            }
        }
        throw VeiculoNaoEncontradoException()
    }

    fun venderVeiculo(identificador: String, comprador: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        veiculos.remove(veiculo)
        veiculo.dataDeAquisicao = Date()
        comprador.comprarVeiculo(veiculo)
        return
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        for (veiculo in veiculos) {
            if (veiculo.identificador.equals(identificador)) {
                if ((veiculo.requerCarta() && temCarta())){
                    veiculo.moverPara(x,y)
                    return
                }else if(!veiculo.requerCarta()){
                    veiculo.moverPara(x,y)
                    return
                }else if ((veiculo.requerCarta() && !temCarta())){
                    throw PessoaSemCartaException(nome)
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
        val atual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val nascimento = LocalDate.ofInstant(dataDeNascimento.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val idade = Period.between(LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(atual, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).years

        if (idade < 18){
            throw MenorDeIdadeException()
        }

        carta = Carta()
        return
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
        return
    }

    override fun toString(): String {
        val nascimento = LocalDate.ofInstant(dataDeNascimento.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        return "Pessoa | $nome | $nascimento | Posicao | x:${posicao.x} | y:${posicao.y}"
    }
}