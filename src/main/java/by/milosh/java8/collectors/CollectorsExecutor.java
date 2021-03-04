package by.milosh.java8.collectors;

import by.milosh.entity.Player;
import by.milosh.entity.Position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static by.milosh.entity.Position.BACK;
import static by.milosh.entity.Position.FORWARD;
import static by.milosh.entity.Position.GOALKEEPER;
import static by.milosh.entity.Position.HALFBACK;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * Contains examples of some features of java 8 Collectors class.
 * <p/>
 * Created on 2021-03-04
 * <p/>
 *
 * @author Alexander Milosh
 */
public class CollectorsExecutor {

    public void execute() {
        List<Player> players = composeList();

        // return Players for each Country: Map<Country, List<Player>>
        Map<String, List<Player>> playersGroupByCountry = players.stream().collect(groupingBy(Player::getCountry));

        // return Players for each Position: Map<Position, List<Player>>
        Map<Position, List<Player>> playersGroupByPosition = players.stream().collect(groupingBy(Player::getPosition));

        // return Player names for each Country: Map<Country, List<Player.name>>
        Map<String, List<String>> playersGroupByCountryString =
                players.stream().collect(
                        groupingBy(Player::getCountry,
                                mapping(Player::getName, toList())));

        // return two level Map<Team, Map<Country, List<Player>>>
        Map<String, Map<String, List<Player>>> playersGroupByTeamAndByCountry =
                players.stream().collect(
                        groupingBy(Player::getTeam,
                                groupingBy(Player::getCountry)));

        // return Player with max salary for each Team and each Country
        Map<String, Map<String, Optional<Player>>> playersGroupByTeamAndByCountryMaxSalary =
                players.stream().collect(
                        groupingBy(Player::getTeam,
                                groupingBy(Player::getCountry,
                                        maxBy(Comparator.comparingInt(Player::getSalary)))));

        // return Player with max salary for each Team
        Map<String, Optional<Player>> playersGroupByTeamPlayerMaxSalary1 =
                players.stream().collect(
                        groupingBy(Player::getTeam,
                                maxBy(Comparator.comparingInt(Player::getSalary))));

        // return Player with max salary for each Team
        Map<String, Player> playersGroupByTeamPlayerMaxSalary2 =
                players.stream().collect(
                        Collectors.toMap(Player::getTeam,
                                Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Player::getSalary))));

        // return Teams and max salary from Players of the team
        Map<String, Integer> playersGroupByTeamMaxSalary1 =
                players.stream().collect(
                        Collectors.toMap(Player::getTeam,
                                Player::getSalary, Integer::max));

        // return Teams and max salary from Players of the team
        Map<String, Integer> playersGroupByTeamMaxSalary2 =
                players.stream().collect(
                        Collectors.toMap(Player::getTeam,
                                Player::getSalary, BinaryOperator.maxBy(Comparator.comparingInt(o -> o))));

        // return Teams and max salary from Players of the team
        Map<String, Integer> playersGroupByTeamMaxSalary3 =
                players.stream().collect(
                        Collectors.groupingBy(Player::getTeam,
                                reducing(-1, Player::getSalary, Integer::max)));

        System.out.println("For debug breakpoint.");
    }

    private List<Player> composeList() {
        Player player11 = new Player("Zidane", "Juventus", "France", HALFBACK, 10);
        Player player12 = new Player("Bonucci", "Juventus", "Italy", BACK, 7);
        Player player13 = new Player("C. Ronaldo", "Juventus", "Portugal", FORWARD, 25);
        Player player14 = new Player("Buffon", "Juventus", "Italy", GOALKEEPER, 9);
        Player player15 = new Player("Del Piero", "Juventus", "Italy", FORWARD, 12);

        Player player21 = new Player("Messi", "Barcelona", "Argentina", FORWARD, 20);
        Player player22 = new Player("Mascerano", "Barcelona", "Argentina", BACK, 3);
        Player player23 = new Player("Iniesta", "Barcelona", "Spain", HALFBACK, 9);
        Player player24 = new Player("Xavi", "Barcelona", "Spain", HALFBACK, 9);

        Player player31 = new Player("Noyer", "Bayern Munich", "Germany", GOALKEEPER, 9);
        Player player32 = new Player("Levandovski", "Bayern Munich", "Poland", FORWARD, 15);

        return Arrays.asList(player11, player12, player12, player13, player14, player15, player21, player22, player23, player24, player31,
                player32);
    }
}
