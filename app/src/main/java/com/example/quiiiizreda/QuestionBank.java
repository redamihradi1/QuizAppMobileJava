package com.example.quiiiizreda;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private static List<QuestionsList> javaQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Lequel des éléments suivants n’est pas un concept POO en Java?","Héritage","Encapsulation","Polymorphisme","Compilation" ,"Compilation","");
        final  QuestionsList question2 = new QuestionsList("Quand la surcharge de méthode est-elle déterminée?","Au moment de l’exécution","Au moment de la compilation","Au moment du codage","Au moment de l’exécution" ,"Au moment de la compilation","");
        final  QuestionsList question3 = new QuestionsList("Comment ça s’appelle si un objet a son propre cycle de vie?","Agrégation","Composition","Encapsulation","Association" ,"Association","");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);

        return questionsLists;
    }
    private static List<QuestionsList> phpQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Que signifie PHP?","Personal Home Page","Hypertext Preprocessor","Pretext Hypertext Processor","Preprocessor Home Page" ,"Hypertext Preprocessor","");
        final  QuestionsList question2 = new QuestionsList("Les fichiers PHP ont l’extension …. ?",".html",".xml",".php","ph",".php","");

        questionsLists.add(question1);
        questionsLists.add(question2);

        return questionsLists;
    }
    private static List<QuestionsList> htmlQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("Quelle organisation définit les standards Web?","Apple Inc.","IBM Corporation","World Wide Web Consortium","Microsoft Corporation" ,"World Wide Web Consortium","");
        final  QuestionsList question2 = new QuestionsList("HTML est considéré comme ______ ?","Langage de programmation","Langage POO","Langage de haut niveau","Langage de balisage" ,"Langage de balisage","");

        questionsLists.add(question1);
        questionsLists.add(question2);

        return questionsLists;
    }
    private static List<QuestionsList> androidQuestions(){
        final List<QuestionsList> questionsLists = new ArrayList<>();
        final  QuestionsList question1 = new QuestionsList("la premiere question android","1ere option","2eme option","3eme ooption","4eme option" ,"1ere option","");
        final  QuestionsList question2 = new QuestionsList("la 2eme question android","1ere option","2eme option","3eme ooption","4eme option" ,"1ere option","");

        questionsLists.add(question1);
        questionsLists.add(question2);

        return questionsLists;
    }
    public static List<QuestionsList> getQuestions(String selectedTopicName){
        switch (selectedTopicName){
            case "java":
                return javaQuestions();
            case "php":
                return phpQuestions();
            case "android":
                return androidQuestions();
            default:
                return htmlQuestions();

        }
    }

}
