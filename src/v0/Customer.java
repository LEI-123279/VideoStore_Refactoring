package v0;

import java.util.Vector;

/**
 * Representa um cliente da loja de aluguer de vídeos.
 * <p>
 * Esta classe mantém o registo das locações (alugueres) atuais de um cliente
 * e é responsável por gerar os extratos (statements) em diferentes formatos
 * (texto e HTML), bem como calcular os totais de custos e pontos de fidelidade.
 */
public class Customer {

    /** O nome do cliente. */
    private String _name;

    /** A lista de alugueres (Rental) associados a este cliente. */
    private Vector<Rental> _rentals = new Vector<Rental>();

    /**
     * Constrói um novo cliente com o nome especificado.
     *
     * @param _name O nome do cliente.
     */
    public Customer(String _name) {
        this._name = _name;
    }

    /**
     * Adiciona um novo aluguer à lista de alugueres do cliente.
     *
     * @param arg O objeto {@link Rental} a ser adicionado.
     */
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return Uma String contendo o nome do cliente.
     */
    public String getName() {
        return _name;
    }

    /**
     * Gera um extrato dos alugueres do cliente em formato HTML.
     * <p>
     * O extrato inclui um cabeçalho formatado, uma lista não ordenada dos filmes
     * alugados com os respetivos preços, e um rodapé com o valor total e os
     * pontos de fidelidade acumulados.
     *
     * @return Uma String contendo o código HTML do extrato.
     */
    public String htmlStatement() {

        // header
        String result = "<font size=\"5\" face=\"Georgia, Arial, Garamond\" color=\"green\">\n";
        result += "<h2>Rental Record for <i>" + getName() + "</i></h2>\n";

        result += "<ul>\n";
        for (Rental each : _rentals)
            result += "\t<li>" + each.getMovie().getTitle() + "\t" + each.getMovie().getRentalAmount(each.getDaysRented()) +"\n";
        result += "</ul>\n";

        // add footer lines
        result += "Amount owed is " + getTotalAmount() + "<br>\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points<br>\n";
        result += "</font>\n";

        return result;
    }

    /**
     * Gera um extrato dos alugueres do cliente em formato de texto simples.
     * <p>
     * O extrato lista cada filme alugado e o seu custo, seguido pelo valor
     * total devido e os pontos de fidelidade ganhos.
     *
     * @return Uma String representando o extrato textual.
     */
    public String statement() {

        // header
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : _rentals) {
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + each.getMovie().getRentalAmount(each.getDaysRented()) + "\n";
        }

        // add footer lines
        result += "Amount owed is " + getTotalAmount() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    /**
     * Calcula o total de pontos de fidelidade (frequent renter points)
     * acumulados em todos os alugueres atuais do cliente.
     *
     * @return O número inteiro total de pontos de fidelidade.
     */
    public int getTotalFrequentRenterPoints()
    {
        int frequentRenterPoints = 0;
        for (Rental each: _rentals)
            frequentRenterPoints += each._movie.getFrequentRentalPoints(each.getDaysRented());
        return frequentRenterPoints;
    }

    /**
     * Calcula o valor monetário total devido por todos os alugueres atuais.
     * Percorre a lista de alugueres e soma o custo individual de cada um.
     *
     * @return O valor total (double) a ser pago pelo cliente.
     */
    public double getTotalAmount()
    {
        double totalAmount = 0;
        for (Rental each: _rentals)
            totalAmount += each.getMovie().getRentalAmount(each.getDaysRented());
        return totalAmount;
    }

}