package pt.ulusofona.cm.kotlin.challenge.exceptions

class PessoaSemCartaException(mensagem: String) : Exception("$mensagem não tem carta para conduzir o veículo indicado")