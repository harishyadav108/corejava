package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample {

    static class UserScore {
        private final String name;
        private final int score;

        public UserScore(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return this.name;
        }

        public int getScore() {
            return this.score;
        }

        @Override
        public String toString() {
            return "UserScore{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    static class Competition {
        private final List<UserScore> scores;

        private Competition(UserScore... scores) {
            this.scores = List.of(scores);
        }

        public List<UserScore> getScores() {
            return this.scores;
        }
    }

    public static void main(String[] args) {
        Competition round1 = new Competition(
                new UserScore("Harish", 2),
                new UserScore("Rudy", 4),
                new UserScore("Alexa", 10),
                new UserScore("Alexa", 9),
                new UserScore("Mary", 10),
                new UserScore("Bella", 2),
                new UserScore("John", 1)
        );


        // Q1: sum of all scores
        Optional t = round1.getScores().stream().map(i -> i.getScore()).reduce(Integer::sum);
        System.out.println(t.get());
        double sum = round1.getScores().stream().collect(Collectors.summingDouble(UserScore::getScore));
        System.out.println("sum=" + sum);

        double sum1 = round1.getScores().stream().mapToDouble(UserScore::getScore).sum();
        System.out.println("sum1=" + sum1);
        int scoreSum = 0;
        for (UserScore score : round1.getScores()) {
            scoreSum = scoreSum + score.getScore();
        }
        System.out.println(scoreSum);
        // Q1: sort by score in ascending order

        //if score is same then sort by name

        //implement in the same stream operation.
        round1.getScores().stream().sorted(Comparator.comparingInt(UserScore::getScore)
                .thenComparing(Comparator.comparing(UserScore::getName)))
                .forEach(System.out::println);
        // Q3: find the name who has maximum score.
       UserScore us =  round1.getScores().stream().max(Comparator.comparingInt(UserScore::getScore)).get();
        System.out.println("max user score="+us);

        UserScore us1 = round1.getScores().stream().sorted(Comparator.comparingInt(UserScore::getScore).reversed()).findFirst().get();
        System.out.println(us1);

        UserScore us2 = round1.getScores().stream().sorted((i1,i2)->i1.getScore()>i2.getScore()?-1:i1.getScore()<i2.getScore()?1:0).findFirst().get();
        System.out.println("us2"+us2);

        UserScore us3 = round1.getScores().stream().sorted((i1,i2)->Integer.compare(i1.getScore(),i2.getScore())).findFirst().get();
        System.out.println("us2"+us3);

        //output of below lines
        List<String> list = Arrays.asList("sample", "Test");
        Stream<String> stream = list.stream();
        List<String> collect = stream.map(each -> each + "cool").collect(Collectors.toList());
        List<String> collect1 = stream.map(each -> each + "cooltest").collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);
    }
}


