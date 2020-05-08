package MatchResult;

import java.util.*;

class MatchResult {

    public String winningTeam;
    public String losingTeam;

    public MatchResult(String _winningTeam, String _losingTeam){
        this.winningTeam = _winningTeam;
        this.losingTeam = _losingTeam;
    }

}

public class MainClass{

    public static boolean canTeamABeatTeamB(List<MatchResult> matches, String teamA, String teamB){
        return isReachableDfs(buildGraph(matches), teamA, teamB, new HashSet<>());
    }

    private static boolean isReachableDfs(Map<String, Set<String>>  graph, String curr, String dest, HashSet<String> visited) {
        if(curr.equals(dest))return true;
        else if(visited.contains(curr) || graph.get(curr)==null)return false;

        visited.add(curr);
        return graph.get(curr).stream().anyMatch(team-> isReachableDfs(graph,team,dest,visited));
    }

    private static Map<String, Set<String>> buildGraph(List<MatchResult> matches) {
        Map<String, Set<String>> graph = new HashMap<>();
        for(MatchResult match : matches){
            graph.putIfAbsent(match.winningTeam,new HashSet<>()).add(match.losingTeam);
        }

        return graph;
    }


}


