package java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapStream {
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
                new UserScore("Alex", 2),
                new UserScore("Rudy", 4),
                new UserScore("Alexa", 9),
                new UserScore("Mary", 10),
                new UserScore("Bella", 2),
                new UserScore("John", 1)
        );
        Competition round2 = new Competition(
                new UserScore("Terry", 10),
                new UserScore("Mark", 8),
                new UserScore("Angela", 3)
        );
        Competition round3 = new Competition(
                new UserScore("Catty", 5),
                new UserScore("Raymond", 2),
                new UserScore("Bella", 8)
        );

        // Calculate averageScore for all rounds, do not use list concatenation
        double averageScore = Stream.of(round1.getScores(), round2.getScores(), round3.getScores()).flatMap(List::stream).collect(Collectors.averagingInt(UserScore::getScore));
        System.out.println(averageScore);
        // Only users with score more than 4 passed round. Find all user names for those who passed the round.
        //List<String> passedUsersNames= Stream.of(round1.getScores(),round2.getScores(),round3.getScores()).flatMap(u->u.stream().filter(u1->u1.score>4)).map(u2->u2.name).collect(Collectors.toList());
        //List<String> passedUsersNames=Stream.of(round1.getScores(),round2.getScores(),round3.getScores()).filter(u->(u.stream().flatMap(u1->u1.score))>4).map(user->user.getName()).collect(Collectors.toList());


        Stream.of(round1.getScores(), round2.getScores(), round3.getScores())
                .flatMap(r -> r.stream().filter(s -> s.getScore() > 4).map(u -> u.getName())).collect(Collectors.toList()).forEach(System.out::println);


        List<String> passedUsersNames = Stream.of(round1.getScores(), round2.getScores(), round3.getScores()).flatMap(List::stream).filter(u1 -> u1.score > 4).map(u2 -> u2.name).collect(Collectors.toList());
        System.out.println(passedUsersNames);
    }
}