package prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClass {

    public static void generateAndPrintConcordance(List<String> inputLines) {
        TreeMap<String,Word> listOfWords = new TreeMap<String,Word>();
        int sentenceIdx = 0;
       for(String input : inputLines){
            String[] inputArray = input.replace("^a-zA-Z","").split(" ");
            for(String wordString : inputArray){
                wordString = wordString.toLowerCase();
                Word word = listOfWords.getOrDefault(wordString, new Word(wordString));
                word.count++;
                word.sentences.add(sentenceIdx);
                listOfWords.put(wordString,word);
            }
            sentenceIdx++;
        }
        listOfWords.forEach((k,v)->System.out.println(v.toString()));
    }

    static class Word{
        String word;
        int count;
        TreeSet<Integer> sentences;
        public Word(String word){
            this.word = word;
            this.count = 0;
            this.sentences = new TreeSet<Integer>();
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(this.word);
            sb.append(": {");
            sb.append(this.count);
            sb.append(":");
            for(int sentence : sentences){
                sb.append(sentence);
                sb.append(",");
            }
            sb.setLength(sb.length()-1);
            sb.append("}");
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int inputLinesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> inputLines = IntStream.range(0, inputLinesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(Collectors.toList());

        generateAndPrintConcordance(inputLines);

        bufferedReader.close();
    }
}
