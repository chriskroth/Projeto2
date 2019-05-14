package projeto2;

import java.util.ArrayList;

/**
 *
 * @author cfkroth
 */
public class Estatistica {
    private int nroReviews;
    private String melhorJogo;
    private double pontosMelhorJogo;
    private String piorJogo;
    private double pontosPiorJogo;
    private int qtdeMediocre;
    private int qtdeAction;
    private double somaPontuacao;
    private ArrayList<Double> pontuacoes;
    
    public Estatistica(){
        pontuacoes = new ArrayList<>();
    }

    public int getNroReviews() {
        return nroReviews;
    }

    public void setNroReviews(int nroReviews) {
        this.nroReviews = nroReviews;
    }
    
    public String getMelhorJogo() {
        return melhorJogo;
    }

    public void setMelhorJogo(String melhorJogo) {
        this.melhorJogo = melhorJogo;
    }

    public String getPiorJogo() {
        return piorJogo;
    }

    public void setPiorJogo(String piorJogo) {
        this.piorJogo = piorJogo;
    }

    public int getQtdeMediocre() {
        return qtdeMediocre;
    }

    public void setQtdeMediocre(int qtdeMediocre) {
        this.qtdeMediocre = qtdeMediocre;
    }

    public double getPontosMelhorJogo() {
        return pontosMelhorJogo;
    }

    public void setPontosMelhorJogo(double pontosMelhorJogo) {
        this.pontosMelhorJogo = pontosMelhorJogo;
    }

    public double getPontosPiorJogo() {
        return pontosPiorJogo;
    }

    public void setPontosPiorJogo(double pontosPiorJogo) {
        this.pontosPiorJogo = pontosPiorJogo;
    }

    public int getQtdeAction() {
        return qtdeAction;
    }

    public void setQtdeAction(int qtdeAction) {
        this.qtdeAction = qtdeAction;
    }

    public double getSomaPontuacao() {
        return somaPontuacao;
    }

    public void setSomaPontuacao(double somaPontuacao) {
        this.somaPontuacao = somaPontuacao;
    }

    public ArrayList<Double> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(ArrayList<Double> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }
    
    public void addPontuacao(double pontuacao){
        this.pontuacoes.add(pontuacao);
    }
    
    public double getMediaPontuacao(){
        return this.getSomaPontuacao() / this.getNroReviews();
    }
    
    public double getPercentualMediocre(){
        return (100 * (double)this.getQtdeMediocre()) / (double)this.getNroReviews();
    }
    
    public double getDesvioPadrao(){
        double media = this.getMediaPontuacao();
        double somaDesviosQuadrados = 0;
        
        for(double pontos : this.pontuacoes){
            double desvio = pontos - media;
            double desvioQuadrado = Math.pow(desvio, 2);
            
            somaDesviosQuadrados += desvioQuadrado;
        }
        
        double divisaoSomaQuadrados = somaDesviosQuadrados / this.getNroReviews();
        return Math.sqrt(divisaoSomaQuadrados);
    }
}