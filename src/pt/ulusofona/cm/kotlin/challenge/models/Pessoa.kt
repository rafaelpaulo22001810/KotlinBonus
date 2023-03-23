package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
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
        veiculo.dataDeAquisicao = Date()
        comprador.comprarVeiculo(veiculo)
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        if (carta ==  null){
            throw PessoaSemCartaException("$nome não tem carta para conduzir o veículo indicado")
        }

        for (veiculo in veiculos) {
            if (veiculo.identificador.equals(identificador)) {
                if ((veiculo.requerCarta() && temCarta())){
                    val index = veiculos.indexOf(veiculo)
                    veiculos[index].moverPara(x,y)
                }else if(!veiculo.requerCarta()){
                    val index = veiculos.indexOf(veiculo)
                    veiculos[index].moverPara(x,y)
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
        val atual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val nascimento = LocalDate.ofInstant(dataDeNascimento.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val idade = Period.between(LocalDate.parse(nascimento, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(atual, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).years

        if (idade < 18){
            throw MenorDeIdadeException()
        }

        carta = Carta()
    }

    override fun moverPara(x: Int, y: Int) {
        if (posicao.x == x && posicao.y == y){
            throw AlterarPosicaoException()
        }
        posicao = Posicao(x, y)
    }

    override fun toString(): String {
        val nascimento = LocalDate.ofInstant(dataDeNascimento.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        return "Pessoa | $nome | $nascimento. | Posicao | x:${posicao.x} | y:${posicao.y}"
    }
}