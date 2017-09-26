package com.textanalyzer.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class TextAnalyzerService {
    public  String parsing(String file) throws IOException {
        int flagRoundBrackets = 0;
        int flagFigureBrakets = 0;
        int flagSquareBrackets = 0;

        String str = readFilePath(file);
        for (int i = 0; i < str.length(); i++) {

            String s = str.substring(i, i + 1);
            if (s.equals("(")&&flagRoundBrackets>=0){
                flagRoundBrackets++;
                continue;
            }
            if (s.equals("{")&&flagFigureBrakets>=0 ) {
                flagFigureBrakets++;
                continue;
            }
            if (s.equals("[")&&flagSquareBrackets>=0 ) {
                flagSquareBrackets++;
                continue;
            }
            if (s.equals(")")&&flagRoundBrackets>=0){
                flagRoundBrackets--;
            }
            if (s.equals("}")&&flagFigureBrakets>=0){
                flagFigureBrakets--;
            }
            if (s.equals("]")&&flagSquareBrackets>=0){
                flagSquareBrackets--;
            }
        }
        if (flagRoundBrackets == 0 && flagFigureBrakets == 0&&flagSquareBrackets == 0) {
            return "correct";
        } else {
            return "uncorrect";
        }

    }

    public String readFilePath(String file) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
        String f;
        int count=0;
        StringBuffer strBuffer = new StringBuffer();
        Map<String ,Integer> map = new HashMap<String,Integer>();
        while ((f=bufferedReader.readLine())!=null){
            StringTokenizer tokenizer = new StringTokenizer(f, " \t\n\r,:-.");
            while(tokenizer.hasMoreTokens()) {
                String s = tokenizer.nextToken();
                if(s.length()>2) {
                    strBuffer.append(s);
                    int cnt = map.get(s) != null ? map.get(s) : 0;
                    cnt++;
                    map.put(s, cnt);
                }
            }
        }
        return String.valueOf(map);
    }
    public List<String> readFile(String file) throws IOException {

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
        String f;
        int count=0;
        StringBuffer strBuffer = new StringBuffer();
        Map<String ,Integer> map = new HashMap<String,Integer>();
        while ((f=bufferedReader.readLine())!=null){
            StringTokenizer tokenizer = new StringTokenizer(f, " \t\n\r,:-.");
            while(tokenizer.hasMoreTokens()) {
                String s = tokenizer.nextToken();
                if(s.length()>2) {
                    strBuffer.append(s);
                    int cnt = map.get(s) != null ? map.get(s) : 0;
                    cnt++;
                    map.put(s, cnt);
                }
            }
        }

        List list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });
        ArrayList<String> newList = new ArrayList();
        if(list.size()>10){
            for(int i = 0; i<10; i++){
                newList.add(String.valueOf(list.get(i)));
            }return newList;
        }
        return list;

    }
}
