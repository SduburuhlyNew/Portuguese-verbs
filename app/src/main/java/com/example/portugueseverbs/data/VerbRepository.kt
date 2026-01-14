package com.example.portugueseverbs.data

import com.example.portugueseverbs.model.*

class VerbRepository {

    // Database of known verbs (mainly irregulars and common verbs with translations)
    private val knownVerbs = listOf(
        // ============================================
        // FIRST CONJUGATION (-ar) - Regular verbs
        // ============================================
        Verb("falar", "to speak", ConjugationGroup.FIRST),
        Verb("amar", "to love", ConjugationGroup.FIRST),
        Verb("trabalhar", "to work", ConjugationGroup.FIRST),
        Verb("estudar", "to study", ConjugationGroup.FIRST),
        Verb("comprar", "to buy", ConjugationGroup.FIRST),
        Verb("andar", "to walk", ConjugationGroup.FIRST),
        Verb("cantar", "to sing", ConjugationGroup.FIRST),
        Verb("dançar", "to dance", ConjugationGroup.FIRST),
        Verb("morar", "to live/reside", ConjugationGroup.FIRST),
        Verb("viajar", "to travel", ConjugationGroup.FIRST),
        Verb("gostar", "to like", ConjugationGroup.FIRST),
        Verb("pensar", "to think", ConjugationGroup.FIRST),
        Verb("olhar", "to look", ConjugationGroup.FIRST),
        Verb("escutar", "to listen", ConjugationGroup.FIRST),
        Verb("chamar", "to call", ConjugationGroup.FIRST),
        Verb("chegar", "to arrive", ConjugationGroup.FIRST),
        Verb("deixar", "to leave/let", ConjugationGroup.FIRST),
        Verb("encontrar", "to find/meet", ConjugationGroup.FIRST),
        Verb("entrar", "to enter", ConjugationGroup.FIRST),
        Verb("esperar", "to wait/hope", ConjugationGroup.FIRST),
        Verb("fechar", "to close", ConjugationGroup.FIRST),
        Verb("ficar", "to stay/become", ConjugationGroup.FIRST),
        Verb("ganhar", "to win/earn", ConjugationGroup.FIRST),
        Verb("jogar", "to play (game)", ConjugationGroup.FIRST),
        Verb("lavar", "to wash", ConjugationGroup.FIRST),
        Verb("levar", "to take/carry", ConjugationGroup.FIRST),
        Verb("ligar", "to call/turn on", ConjugationGroup.FIRST),
        Verb("limpar", "to clean", ConjugationGroup.FIRST),
        Verb("mandar", "to send/order", ConjugationGroup.FIRST),
        Verb("mostrar", "to show", ConjugationGroup.FIRST),
        Verb("nadar", "to swim", ConjugationGroup.FIRST),
        Verb("passar", "to pass/spend", ConjugationGroup.FIRST),
        Verb("pagar", "to pay", ConjugationGroup.FIRST),
        Verb("parar", "to stop", ConjugationGroup.FIRST),
        Verb("perguntar", "to ask", ConjugationGroup.FIRST),
        Verb("precisar", "to need", ConjugationGroup.FIRST),
        Verb("preparar", "to prepare", ConjugationGroup.FIRST),
        Verb("procurar", "to look for", ConjugationGroup.FIRST),
        Verb("terminar", "to finish", ConjugationGroup.FIRST),
        Verb("tirar", "to take out/remove", ConjugationGroup.FIRST),
        Verb("tocar", "to touch/play (music)", ConjugationGroup.FIRST),
        Verb("tomar", "to take/drink", ConjugationGroup.FIRST),
        Verb("trocar", "to change/exchange", ConjugationGroup.FIRST),
        Verb("usar", "to use", ConjugationGroup.FIRST),
        Verb("voltar", "to return", ConjugationGroup.FIRST),
        Verb("acordar", "to wake up", ConjugationGroup.FIRST),
        Verb("ajudar", "to help", ConjugationGroup.FIRST),
        Verb("almoçar", "to have lunch", ConjugationGroup.FIRST),
        Verb("começar", "to begin", ConjugationGroup.FIRST),
        Verb("contar", "to count/tell", ConjugationGroup.FIRST),
        Verb("continuar", "to continue", ConjugationGroup.FIRST),
        Verb("cortar", "to cut", ConjugationGroup.FIRST),
        Verb("cozinhar", "to cook", ConjugationGroup.FIRST),
        Verb("criar", "to create", ConjugationGroup.FIRST),
        Verb("descansar", "to rest", ConjugationGroup.FIRST),
        Verb("desejar", "to wish/desire", ConjugationGroup.FIRST),
        Verb("ensinar", "to teach", ConjugationGroup.FIRST),
        Verb("errar", "to make mistake", ConjugationGroup.FIRST),
        Verb("explicar", "to explain", ConjugationGroup.FIRST),
        Verb("gritar", "to shout", ConjugationGroup.FIRST),
        Verb("guardar", "to keep/save", ConjugationGroup.FIRST),
        Verb("imaginar", "to imagine", ConjugationGroup.FIRST),
        Verb("interessar", "to interest", ConjugationGroup.FIRST),
        Verb("jantar", "to have dinner", ConjugationGroup.FIRST),
        Verb("lembrar", "to remember", ConjugationGroup.FIRST),
        Verb("melhorar", "to improve", ConjugationGroup.FIRST),
        Verb("mudar", "to change/move", ConjugationGroup.FIRST),
        Verb("namorar", "to date", ConjugationGroup.FIRST),
        Verb("observar", "to observe", ConjugationGroup.FIRST),
        Verb("odiar", "to hate", ConjugationGroup.FIRST),
        Verb("organizar", "to organize", ConjugationGroup.FIRST),
        Verb("pegar", "to grab/catch", ConjugationGroup.FIRST),
        Verb("praticar", "to practice", ConjugationGroup.FIRST),
        Verb("preocupar", "to worry", ConjugationGroup.FIRST),
        Verb("recusar", "to refuse", ConjugationGroup.FIRST),
        Verb("relaxar", "to relax", ConjugationGroup.FIRST),
        Verb("reparar", "to repair/notice", ConjugationGroup.FIRST),
        Verb("salvar", "to save", ConjugationGroup.FIRST),
        Verb("sonhar", "to dream", ConjugationGroup.FIRST),
        Verb("telefonar", "to phone", ConjugationGroup.FIRST),
        Verb("tentar", "to try", ConjugationGroup.FIRST),
        Verb("tratar", "to treat", ConjugationGroup.FIRST),
        Verb("utilizar", "to utilize", ConjugationGroup.FIRST),
        Verb("verificar", "to verify", ConjugationGroup.FIRST),
        Verb("visitar", "to visit", ConjugationGroup.FIRST),

        // First conjugation (-ar) - Irregular verbs
        Verb("dar", "to give", ConjugationGroup.FIRST, isIrregular = true),
        Verb("estar", "to be (state)", ConjugationGroup.FIRST, isIrregular = true),

        // ============================================
        // SECOND CONJUGATION (-er) - Regular verbs
        // ============================================
        Verb("comer", "to eat", ConjugationGroup.SECOND),
        Verb("beber", "to drink", ConjugationGroup.SECOND),
        Verb("vender", "to sell", ConjugationGroup.SECOND),
        Verb("aprender", "to learn", ConjugationGroup.SECOND),
        Verb("escrever", "to write", ConjugationGroup.SECOND),
        Verb("correr", "to run", ConjugationGroup.SECOND),
        Verb("viver", "to live", ConjugationGroup.SECOND),
        Verb("acontecer", "to happen", ConjugationGroup.SECOND),
        Verb("aparecer", "to appear", ConjugationGroup.SECOND),
        Verb("atender", "to attend/answer", ConjugationGroup.SECOND),
        Verb("bater", "to hit/knock", ConjugationGroup.SECOND),
        Verb("caber", "to fit", ConjugationGroup.SECOND),
        Verb("chover", "to rain", ConjugationGroup.SECOND),
        Verb("compreender", "to understand", ConjugationGroup.SECOND),
        Verb("conhecer", "to know (person)", ConjugationGroup.SECOND),
        Verb("crescer", "to grow", ConjugationGroup.SECOND),
        Verb("defender", "to defend", ConjugationGroup.SECOND),
        Verb("depender", "to depend", ConjugationGroup.SECOND),
        Verb("descer", "to descend/go down", ConjugationGroup.SECOND),
        Verb("devolver", "to return (object)", ConjugationGroup.SECOND),
        Verb("entender", "to understand", ConjugationGroup.SECOND),
        Verb("envolver", "to involve", ConjugationGroup.SECOND),
        Verb("escolher", "to choose", ConjugationGroup.SECOND),
        Verb("esconder", "to hide", ConjugationGroup.SECOND),
        Verb("esquecer", "to forget", ConjugationGroup.SECOND),
        Verb("merecer", "to deserve", ConjugationGroup.SECOND),
        Verb("meter", "to put in", ConjugationGroup.SECOND),
        Verb("morrer", "to die", ConjugationGroup.SECOND),
        Verb("mover", "to move", ConjugationGroup.SECOND),
        Verb("nascer", "to be born", ConjugationGroup.SECOND),
        Verb("oferecer", "to offer", ConjugationGroup.SECOND),
        Verb("parecer", "to seem", ConjugationGroup.SECOND),
        Verb("perder", "to lose", ConjugationGroup.SECOND),
        Verb("pertencer", "to belong", ConjugationGroup.SECOND),
        Verb("prender", "to arrest/fasten", ConjugationGroup.SECOND),
        Verb("promover", "to promote", ConjugationGroup.SECOND),
        Verb("proteger", "to protect", ConjugationGroup.SECOND),
        Verb("receber", "to receive", ConjugationGroup.SECOND),
        Verb("reconhecer", "to recognize", ConjugationGroup.SECOND),
        Verb("remover", "to remove", ConjugationGroup.SECOND),
        Verb("resolver", "to resolve/solve", ConjugationGroup.SECOND),
        Verb("responder", "to answer", ConjugationGroup.SECOND),
        Verb("sofrer", "to suffer", ConjugationGroup.SECOND),
        Verb("surpreender", "to surprise", ConjugationGroup.SECOND),
        Verb("temer", "to fear", ConjugationGroup.SECOND),
        Verb("torcer", "to twist/cheer", ConjugationGroup.SECOND),
        Verb("varrer", "to sweep", ConjugationGroup.SECOND),

        // Second conjugation (-er) - Irregular verbs
        Verb("ter", "to have", ConjugationGroup.SECOND, isIrregular = true),
        Verb("ser", "to be (essence)", ConjugationGroup.SECOND, isIrregular = true),
        Verb("fazer", "to do/make", ConjugationGroup.SECOND, isIrregular = true),
        Verb("poder", "can/to be able", ConjugationGroup.SECOND, isIrregular = true),
        Verb("querer", "to want", ConjugationGroup.SECOND, isIrregular = true),
        Verb("saber", "to know (fact)", ConjugationGroup.SECOND, isIrregular = true),
        Verb("ver", "to see", ConjugationGroup.SECOND, isIrregular = true),
        Verb("dizer", "to say/tell", ConjugationGroup.SECOND, isIrregular = true),
        Verb("trazer", "to bring", ConjugationGroup.SECOND, isIrregular = true),
        Verb("ler", "to read", ConjugationGroup.SECOND, isIrregular = true),
        Verb("pôr", "to put", ConjugationGroup.SECOND, isIrregular = true),
        Verb("haver", "to have (aux)/there is", ConjugationGroup.SECOND, isIrregular = true),
        Verb("crer", "to believe", ConjugationGroup.SECOND, isIrregular = true),
        Verb("prever", "to foresee", ConjugationGroup.SECOND, isIrregular = true),
        Verb("rever", "to review/see again", ConjugationGroup.SECOND, isIrregular = true),
        Verb("satisfazer", "to satisfy", ConjugationGroup.SECOND, isIrregular = true),
        Verb("desfazer", "to undo", ConjugationGroup.SECOND, isIrregular = true),
        Verb("refazer", "to redo", ConjugationGroup.SECOND, isIrregular = true),
        Verb("manter", "to maintain", ConjugationGroup.SECOND, isIrregular = true),
        Verb("obter", "to obtain", ConjugationGroup.SECOND, isIrregular = true),
        Verb("conter", "to contain", ConjugationGroup.SECOND, isIrregular = true),
        Verb("reter", "to retain", ConjugationGroup.SECOND, isIrregular = true),
        Verb("deter", "to detain", ConjugationGroup.SECOND, isIrregular = true),
        Verb("entreter", "to entertain", ConjugationGroup.SECOND, isIrregular = true),

        // ============================================
        // THIRD CONJUGATION (-ir) - Regular verbs
        // ============================================
        Verb("partir", "to leave/depart", ConjugationGroup.THIRD),
        Verb("abrir", "to open", ConjugationGroup.THIRD),
        Verb("decidir", "to decide", ConjugationGroup.THIRD),
        Verb("dividir", "to divide", ConjugationGroup.THIRD),
        Verb("assistir", "to watch/attend", ConjugationGroup.THIRD),
        Verb("admitir", "to admit", ConjugationGroup.THIRD),
        Verb("aplaudir", "to applaud", ConjugationGroup.THIRD),
        Verb("assumir", "to assume", ConjugationGroup.THIRD),
        Verb("cobrir", "to cover", ConjugationGroup.THIRD),
        Verb("confundir", "to confuse", ConjugationGroup.THIRD),
        Verb("conseguir", "to manage/get", ConjugationGroup.THIRD),
        Verb("consumir", "to consume", ConjugationGroup.THIRD),
        Verb("cumprir", "to fulfill", ConjugationGroup.THIRD),
        Verb("curtir", "to enjoy", ConjugationGroup.THIRD),
        Verb("descobrir", "to discover", ConjugationGroup.THIRD),
        Verb("desistir", "to give up", ConjugationGroup.THIRD),
        Verb("discutir", "to discuss", ConjugationGroup.THIRD),
        Verb("distinguir", "to distinguish", ConjugationGroup.THIRD),
        Verb("distribuir", "to distribute", ConjugationGroup.THIRD),
        Verb("emitir", "to emit", ConjugationGroup.THIRD),
        Verb("encobrir", "to cover up", ConjugationGroup.THIRD),
        Verb("engolir", "to swallow", ConjugationGroup.THIRD),
        Verb("excluir", "to exclude", ConjugationGroup.THIRD),
        Verb("existir", "to exist", ConjugationGroup.THIRD),
        Verb("exigir", "to demand", ConjugationGroup.THIRD),
        Verb("garantir", "to guarantee", ConjugationGroup.THIRD),
        Verb("imprimir", "to print", ConjugationGroup.THIRD),
        Verb("incluir", "to include", ConjugationGroup.THIRD),
        Verb("insistir", "to insist", ConjugationGroup.THIRD),
        Verb("instruir", "to instruct", ConjugationGroup.THIRD),
        Verb("investir", "to invest", ConjugationGroup.THIRD),
        Verb("nutrir", "to nourish", ConjugationGroup.THIRD),
        Verb("ocorrir", "to occur", ConjugationGroup.THIRD),
        Verb("omitir", "to omit", ConjugationGroup.THIRD),
        Verb("permitir", "to permit", ConjugationGroup.THIRD),
        Verb("persistir", "to persist", ConjugationGroup.THIRD),
        Verb("possuir", "to possess", ConjugationGroup.THIRD),
        Verb("proibir", "to prohibit", ConjugationGroup.THIRD),
        Verb("reagir", "to react", ConjugationGroup.THIRD),
        Verb("reconstruir", "to reconstruct", ConjugationGroup.THIRD),
        Verb("reduzir", "to reduce", ConjugationGroup.THIRD),
        Verb("repetir", "to repeat", ConjugationGroup.THIRD),
        Verb("resistir", "to resist", ConjugationGroup.THIRD),
        Verb("restituir", "to restore", ConjugationGroup.THIRD),
        Verb("reunir", "to gather/meet", ConjugationGroup.THIRD),
        Verb("sacudir", "to shake", ConjugationGroup.THIRD),
        Verb("substituir", "to substitute", ConjugationGroup.THIRD),
        Verb("sumir", "to disappear", ConjugationGroup.THIRD),
        Verb("surgir", "to arise/appear", ConjugationGroup.THIRD),
        Verb("traduzir", "to translate", ConjugationGroup.THIRD),
        Verb("transmitir", "to transmit", ConjugationGroup.THIRD),
        Verb("unir", "to unite", ConjugationGroup.THIRD),

        // Third conjugation (-ir) - Irregular verbs
        Verb("ir", "to go", ConjugationGroup.THIRD, isIrregular = true),
        Verb("vir", "to come", ConjugationGroup.THIRD, isIrregular = true),
        Verb("pedir", "to ask for/request", ConjugationGroup.THIRD, isIrregular = true),
        Verb("dormir", "to sleep", ConjugationGroup.THIRD, isIrregular = true),
        Verb("sentir", "to feel", ConjugationGroup.THIRD, isIrregular = true),
        Verb("ouvir", "to hear", ConjugationGroup.THIRD, isIrregular = true),
        Verb("sair", "to leave/go out", ConjugationGroup.THIRD, isIrregular = true),
        Verb("cair", "to fall", ConjugationGroup.THIRD, isIrregular = true),
        Verb("subir", "to go up/climb", ConjugationGroup.THIRD, isIrregular = true),
        Verb("fugir", "to flee", ConjugationGroup.THIRD, isIrregular = true),
        Verb("seguir", "to follow", ConjugationGroup.THIRD, isIrregular = true),
        Verb("servir", "to serve", ConjugationGroup.THIRD, isIrregular = true),
        Verb("vestir", "to dress/wear", ConjugationGroup.THIRD, isIrregular = true),
        Verb("mentir", "to lie", ConjugationGroup.THIRD, isIrregular = true),
        Verb("preferir", "to prefer", ConjugationGroup.THIRD, isIrregular = true),
        Verb("sugerir", "to suggest", ConjugationGroup.THIRD, isIrregular = true),
        Verb("divertir", "to amuse/entertain", ConjugationGroup.THIRD, isIrregular = true),
        Verb("ferir", "to wound/hurt", ConjugationGroup.THIRD, isIrregular = true),
        Verb("medir", "to measure", ConjugationGroup.THIRD, isIrregular = true),
        Verb("despir", "to undress", ConjugationGroup.THIRD, isIrregular = true),
        Verb("competir", "to compete", ConjugationGroup.THIRD, isIrregular = true),
        Verb("impedir", "to prevent", ConjugationGroup.THIRD, isIrregular = true),
        Verb("expedir", "to dispatch", ConjugationGroup.THIRD, isIrregular = true),
        Verb("rir", "to laugh", ConjugationGroup.THIRD, isIrregular = true),
        Verb("sorrir", "to smile", ConjugationGroup.THIRD, isIrregular = true),
        Verb("advir", "to come from", ConjugationGroup.THIRD, isIrregular = true),
        Verb("convir", "to be suitable", ConjugationGroup.THIRD, isIrregular = true),
        Verb("intervir", "to intervene", ConjugationGroup.THIRD, isIrregular = true),
        Verb("provir", "to come from", ConjugationGroup.THIRD, isIrregular = true)
    )

    /**
     * Gets a verb from the database, or creates a regular verb if it's a valid infinitive.
     * Returns null only if the input is not a valid Portuguese verb infinitive.
     */
    fun getOrCreateVerb(infinitive: String): Verb? {
        val normalized = infinitive.trim().lowercase()

        // First check if it's in our database
        val knownVerb = knownVerbs.find { it.infinitive.equals(normalized, ignoreCase = true) }
        if (knownVerb != null) {
            return knownVerb
        }

        // If not in database, try to create a regular verb from the infinitive
        return Verb.fromInfinitive(normalized)
    }

    /**
     * Checks if a verb is in the known verbs database.
     */
    fun isKnownVerb(infinitive: String): Boolean {
        return knownVerbs.any { it.infinitive.equals(infinitive.trim(), ignoreCase = true) }
    }

    /**
     * Checks if a string is a valid verb infinitive (ends with -ar, -er, or -ir).
     */
    fun isValidInfinitive(infinitive: String): Boolean {
        val normalized = infinitive.trim().lowercase()
        return normalized.length >= 3 &&
               (normalized.endsWith("ar") || normalized.endsWith("er") || normalized.endsWith("ir"))
    }

    fun getAllVerbs(): List<Verb> = knownVerbs.sortedBy { it.infinitive }

    fun getVerbsByGroup(group: ConjugationGroup): List<Verb> =
        knownVerbs.filter { it.conjugationGroup == group }.sortedBy { it.infinitive }

    fun getIrregularVerbs(): List<Verb> =
        knownVerbs.filter { it.isIrregular }.sortedBy { it.infinitive }

    fun getRegularVerbs(): List<Verb> =
        knownVerbs.filter { !it.isIrregular }.sortedBy { it.infinitive }

    fun searchVerbs(query: String): List<Verb> =
        knownVerbs.filter {
            it.infinitive.contains(query, ignoreCase = true) ||
            (it.translation?.contains(query, ignoreCase = true) == true)
        }.sortedBy { it.infinitive }

    fun getVerbByInfinitive(infinitive: String): Verb? =
        knownVerbs.find { it.infinitive.equals(infinitive, ignoreCase = true) }
}
