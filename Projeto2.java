package projeto2;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/*
Christian F Kroth - 562678
Guilherme Maccali - 571363

(562678 + 571363) % 3 = 2
*/

public class Projeto2 {
    public static void main(String[] args) {
        Map<Integer,Estatistica> map = new TreeMap<Integer,Estatistica>();
        //SimpleReader f = new SimpleReader("C:\\Users\\cfkroth\\desktop\\game-reviews.csv");
        SimpleReader f = new SimpleReader("C:\\Users\\Christian F. Kroth\\desktop\\game-reviews.csv");
        String s = f.readLine();
        
        boolean primeiraLinha = true;
        
        while (s != null) {
            if(!primeiraLinha){
                String colunas[] = s.split(";");

                String titulo = colunas[0];
                String plataforma = colunas[1];
                String frase = colunas[2];
                double pontuacao = Double.parseDouble(colunas[3]);
                String genero = colunas[4];
                char escolhaEditor = colunas[5].charAt(0);
                int ano = Integer.parseInt(colunas[6]);
                
                if(map.get(ano) == null){
                    int qtdeMediocre = 0;
                    if(frase.equals("Mediocre")){
                        qtdeMediocre = 1;
                    }
                    
                    int qtdeAction = 0;
                    if(genero.equals("Action")){
                        qtdeAction = 1;
                    }
                    
                    Estatistica estatistica = new Estatistica();
                    estatistica.setNroReviews(1);
                    estatistica.setQtdeMediocre(qtdeMediocre);
                    estatistica.setMelhorJogo(titulo);
                    estatistica.setPontosMelhorJogo(pontuacao);
                    estatistica.setPiorJogo(titulo);
                    estatistica.setPontosPiorJogo(pontuacao);
                    estatistica.setSomaPontuacao(pontuacao);
                    estatistica.addPontuacao(pontuacao);
                    estatistica.setQtdeAction(qtdeAction);
                    
                    map.put(ano, estatistica);
                }else{
                    map.get(ano).setNroReviews(map.get(ano).getNroReviews() + 1);
                    map.get(ano).setSomaPontuacao(map.get(ano).getSomaPontuacao() + pontuacao);
                    map.get(ano).addPontuacao(pontuacao);
                    
                    if(pontuacao > map.get(ano).getPontosMelhorJogo()){
                        map.get(ano).setMelhorJogo(titulo);
                        map.get(ano).setPontosMelhorJogo(pontuacao);
                    }
                    
                    if(pontuacao < map.get(ano).getPontosPiorJogo()){
                        map.get(ano).setPiorJogo(titulo);
                        map.get(ano).setPontosPiorJogo(pontuacao);
                    }
                    
                    if(frase.equals("Mediocre")){
                        map.get(ano).setQtdeMediocre(map.get(ano).getQtdeMediocre()+ 1);
                    }
                    
                    if(genero.equals("Action")){
                        map.get(ano).setQtdeAction(map.get(ano).getQtdeAction() + 1);
                    }
                }
            }else{
                primeiraLinha = false;
            }
            
            s = f.readLine();
        }
        f.close();
        
        DecimalFormat df = new DecimalFormat("#.00");
        int anoAction = 0;
        int qtdeAction = 0;
        
        for(int ano : map.keySet()){
            Estatistica es = map.get(ano);
            
            if(es.getQtdeAction() > qtdeAction){
                qtdeAction = es.getQtdeAction();
                anoAction = ano;
            }
            
            System.out.println("----------------------------------------"+ano+"----------------------------------------");
            System.out.println("Nro de reviews - " + es.getNroReviews());
            System.out.println("Perc. Mediocre - " + df.format(es.getPercentualMediocre()) + "%");
            System.out.println("Média pontuação - " + df.format(es.getMediaPontuacao()));
            System.out.println("Desvio padrão - " + df.format(es.getDesvioPadrao()));
            System.out.println("Melhor jogo - " + es.getMelhorJogo() + " (" + es.getPontosMelhorJogo() + ")");
            System.out.println("Pior jogo - " + es.getPiorJogo() + " (" + es.getPontosPiorJogo() + ")");
            System.out.println("");
        }
        
        System.out.println("");
        System.out.println("ANO COM MAIOR NÚMERO DE JOGOS ACTION: "+anoAction + "("+qtdeAction+" JOGOS)");
    }
}